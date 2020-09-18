package lupos.s09physicalOperators.partition
import lupos.s00misc.Parallel
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import lupos.s00misc.ParallelJob
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Lock
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

//http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
class POPMergePartition(query: Query, projectedVariables: List<String>, val partitionVariable: String, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMergePartitionID, "POPMergePartition", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        return res
    }

    override fun getRequiredVariableNames(): List<String> = listOf<String>()
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = children[0]
        if (tmp is POPBase) {
            return tmp.getProvidedVariableNamesInternal()
        } else {
            return tmp.getProvidedVariableNames()
        }
    }

    override fun cloneOP() = POPMergePartition(query, projectedVariables, partitionVariable, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPMergePartition && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (Partition.k == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var ex: Throwable? = null
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            SanityCheck.check { variables0.containsAll(variables) }
            SanityCheck.check { variables.containsAll(variables0) }
            //the variable may be eliminated directly after using it in the join            SanityCheck.check { variables.contains(partitionVariable) }
            val elementsPerRing = Partition.queue_size * variables.size
            val ringbuffer = IntArray(elementsPerRing * Partition.k) //only modified by writer, reader just modifies its pointer
            val ringbufferStart = IntArray(Partition.k) { it * elementsPerRing } //constant
            val ringbufferReadHead = IntArray(Partition.k) { 0 } //owned by read-thread - no locking required
            val ringbufferWriteHead = IntArray(Partition.k) { 0 } //owned by write thread - no locking required
            var continuationLock = Lock()
            val ringbufferWriterContinuation = Array(Partition.k) { Parallel.createCondition(continuationLock) }
            var ringbufferReaderContinuation= Parallel.createCondition(continuationLock)
            val writerFinished = IntArray(Partition.k) { 0 } //writer changes to 1 if finished
            var readerFinished = 0
            for (p in 0 until Partition.k) {
                SanityCheck.println({ "merge $uuid $p writer launched F" })
                var childEval2: IteratorBundle?
                try {
                    childEval2 = children[0].evaluate(Partition(parent, partitionVariable, p))
                } catch (e: Throwable) {
                    e.printStackTrace()
                    throw e
                }
                SanityCheck.println({ "merge $uuid $p writer launched G" })
                 Parallel.launch{
                    SanityCheck.println({ "merge $uuid $p writer launched A" })
                    val childEval = childEval2
                    try {
                        if (childEval.hasColumnMode()) {
                            SanityCheck.println({ "merge $uuid $p writer launched B" })
                            val child = childEval.columns
                            if (variables.size == 1) {
                                SanityCheck.println({ "merge $uuid $p writer launched C" })
                                val childIterator = child[variables[0]]!!
                                loop@ while (readerFinished == 0) {
                                    SanityCheck.println({ "merge $uuid $p writer loop start" })
                                    var t = (ringbufferWriteHead[p] + 1) % elementsPerRing
                                    while (ringbufferReadHead[p] == t && readerFinished == 0) {
ringbufferReaderContinuation.notify()
ringbufferWriterContinuation[p].waitCondition({ringbufferReadHead[p] == t && readerFinished == 0})
                                    }
                                        if (readerFinished != 0) {
                                            childIterator.close()
                                            break@loop
                                        }
                                    var tmp = childIterator.next()
                                    if (tmp == ResultSetDictionary.nullValue) {
                                        break@loop
                                    } else {
                                        SanityCheck.println({ "merge $uuid $p writer append data" })
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        //println("$p produced")
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + 1) % elementsPerRing
ringbufferReaderContinuation.notify()
                                    }
                                }
                            } else {
                                SanityCheck.println({ "merge $uuid $p writer launched D" })
                                val variableMapping = Array<ColumnIterator>(variables.size) { child[variables[it]]!! }
                                loop@ while (readerFinished == 0) {
                                    SanityCheck.println({ "merge $uuid $p writer loop start" })
                                    var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    while (ringbufferReadHead[p] == t&& readerFinished == 0) {
ringbufferReaderContinuation.notify()
ringbufferWriterContinuation[p].waitCondition({ringbufferReadHead[p] == t && readerFinished == 0})
                                    }
                                        if (readerFinished != 0) {
                                            for (variable in 0 until variables.size) {
                                                variableMapping[variable].close()
                                            }
                                            break@loop
                                        }
                                    var tmp = variableMapping[0].next()
                                    if (tmp == ResultSetDictionary.nullValue) {
                                        SanityCheck.println({ "merge $uuid $p writer closed B" })
                                        for (variable in 0 until variables.size) {
                                            variableMapping[variable].close()
                                        }
                                        break@loop
                                    } else {
                                        SanityCheck.println({ "merge $uuid $p writer append data" })
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        for (variableIdx in 1 until variables.size) {
                                            try {
                                                ringbuffer[ringbufferWriteHead[p] + variableIdx + ringbufferStart[p]] = variableMapping[variableIdx].next()
                                            } catch (e: Throwable) {
                                                SanityCheck.println({ "merge $uuid $p writer closed A" })
                                                for (variableIdx2 in 0 until variables.size) {
                                                    variableMapping[variableIdx2].close()
                                                }
                                                break@loop
                                            }
                                        }
                                        //println("$p produced")
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
ringbufferReaderContinuation.notify()
                                    }
                                }
                            }
                        } else {
                            SanityCheck.println({ "merge $uuid $p writer launched E" })
                            val child = childEval.rows
                            val variableMapping = IntArray(variables.size)
                            for (variable in 0 until variables.size) {
                                for (variable2 in 0 until variables.size) {
                                    if (variables[variable2] == child.columns[variable]) {
                                        variableMapping[variable] = variable2
                                        break
                                    }
                                }
                            }
                            loop@ while (readerFinished == 0) {
                                SanityCheck.println({ "merge $uuid $p writer loop start" })
                                var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                while (ringbufferReadHead[p] == t&& readerFinished == 0) {
ringbufferReaderContinuation.notify()
   ringbufferWriterContinuation[p].waitCondition({ringbufferReadHead[p] == t && readerFinished == 0})
                                }
                                    if (readerFinished != 0) {
                                        child.close()
                                        break@loop
                                    }
                                var tmp = child.next()
                                if (tmp == -1) {
                                    SanityCheck.println({ "merge $uuid $p writer closed B" })
                                    break@loop
                                } else {
                                    SanityCheck.println({ "merge $uuid $p writer append data" })
                                    for (variable in 0 until variables.size) {
                                        ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                                    }
                                    //println("$p produced")
                                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
ringbufferReaderContinuation.notify()
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        ex = e
                        e.printStackTrace()
                    }
continuationLock.lock()
                    writerFinished[p] = 1
continuationLock.unlock()
ringbufferReaderContinuation.notify()
                    SanityCheck.println({ "merge $uuid $p writer exited loop" })
                }
                SanityCheck.println({ "merge $uuid $p writer lupos.s00misc.ParallelJob init :: " })
            }
            var iterator = RowIterator()
            iterator.columns = variables.toTypedArray()
            iterator.buf = IntArray(variables.size)
            iterator.next = {
                var res = -1
                loop@ while (true) {
                    SanityCheck.println({ "merge $uuid reader loop start" })
                    for (p in 0 until Partition.k) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            //non empty queue -> read one row
                            SanityCheck.println({ "merge $uuid $p reader consumed data" })
                            for (variable in 0 until variables.size) {
                                iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                            }
                            res = 0
                            ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
ringbufferWriterContinuation[p].notify()
                            break@loop
                        } else if (writerFinished[p] == 0) {
ringbufferWriterContinuation[p].notify()
                        }
                    }
                    var finishedWriters = 0
                    var flag = true
                    for (p in 0 until Partition.k) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            flag = false
                        } else if (writerFinished[p] != 0) {
                            finishedWriters++
                        }
                    }
                    if (finishedWriters == Partition.k) {
                        break@loop
                    } else {
ringbufferReaderContinuation.waitCondition({flag})
                    }
                }
                if (ex != null) {
                    throw ex!!
                }
                /*return*/res
            }
            iterator.close = {
                SanityCheck.println({ "merge $uuid reader closed" })
continuationLock.lock()
                readerFinished = 1
continuationLock.unlock()
                for (p in 0 until Partition.k) {
ringbufferWriterContinuation[p].notify()
                }
            }
            return IteratorBundle(iterator)
        }
    }
}
