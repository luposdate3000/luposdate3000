package lupos.s09physicalOperators.partition

import kotlin.coroutines.Continuation
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortType
import lupos.s00misc.MyLock
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelCondition
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

//http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
class POPChangePartitionOrderedByIntId(query: IQuery, projectedVariables: List<String>, val partitionVariable: String, var partitionCountFrom: Int, var partitionCountTo: Int, var partitionIDFrom: Int, var partitionIDTo: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPChangePartitionOrderedByIntIdID, "POPChangePartitionOrderedByIntId", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        if (variable == partitionVariable) {
            return partitionCountTo
        } else {
            return children[0].getPartitionCount(variable)
        }
    }

    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCountFrom", "" + partitionCountFrom)
        res.addAttribute("partitionCountTo", "" + partitionCountTo)
        res.addAttribute("partitionIDFrom", "" + partitionIDFrom)
        res.addAttribute("partitionIDTo", "" + partitionIDTo)
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

    override fun cloneOP(): IOPBase = POPChangePartitionOrderedByIntId(query, projectedVariables, partitionVariable, partitionCountFrom, partitionCountTo, partitionIDFrom, partitionIDTo, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPChangePartitionOrderedByIntId && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { partitionCountTo < partitionCountFrom }
        var partitionCountSrc = partitionCountFrom / partitionCountTo
        var error: Throwable? = null
        val variables = getProvidedVariableNames()
        val variables0 = children[0].getProvidedVariableNames()
        SanityCheck.check { variables0.containsAll(variables) }
        SanityCheck.check { variables.containsAll(variables0) }
        //the variable may be eliminated directly after using it in the join            SanityCheck.check { variables.contains(partitionVariable) }
        val elementsPerRing = Partition.queue_size * variables.size
        val ringbuffer = IntArray(elementsPerRing * partitionCountSrc) //only modified by writer, reader just modifies its pointer
        val ringbufferStart = IntArray(partitionCountSrc) { it * elementsPerRing } //constant
        val ringbufferReadHead = IntArray(partitionCountSrc) { 0 } //owned by read-thread - no locking required
        val ringbufferWriteHead = IntArray(partitionCountSrc) { 0 } //owned by write thread - no locking required
        var continuationLock = MyLock()
        val ringbufferWriterContinuation = Array<ParallelCondition>(partitionCountSrc) { Parallel.createCondition(continuationLock) }
        var ringbufferReaderContinuation: ParallelCondition = Parallel.createCondition(continuationLock)
        val writerFinished = IntArray(partitionCountSrc) { 0 } //writer changes to 1 if finished
        var readerFinished = 0
        for (p1 in 0 until partitionCountSrc) {
            val pChild = p1 * partitionCountTo + parent.data[partitionVariable]!!
            val pLocal = p1
            SanityCheck.println({ "merge $uuid $pChild writer launched G" })
            Parallel.launch {
                try {
                    var childEval2: IteratorBundle?
                    childEval2 = children[0].evaluate(Partition(parent, partitionVariable, pChild, partitionCountFrom))
                    SanityCheck.println({ "merge $uuid $pChild writer launched A" })
                    val childEval = childEval2
                    if (childEval.hasColumnMode()) {
                        SanityCheck.println({ "merge $uuid $pChild writer launched B" })
                        val child = childEval.columns
                        if (variables.size == 1) {
                            SanityCheck.println({ "merge $uuid $pChild writer launched C" })
                            val childIterator = child[variables[0]]!!
                            loop@ while (readerFinished == 0) {
                                SanityCheck.println({ "merge $uuid $pChild writer loop start" })
                                var t = (ringbufferWriteHead[pLocal] + 1) % elementsPerRing
                                while (ringbufferReadHead[pLocal] == t && readerFinished == 0) {
                                    ringbufferReaderContinuation.signal()
                                    ringbufferWriterContinuation[pLocal].waitCondition({ ringbufferReadHead[pLocal] == t && readerFinished == 0 })
                                }
                                if (readerFinished != 0) {
                                    childIterator.close()
                                    break@loop
                                }
                                var tmp = childIterator.next()
                                if (tmp == ResultSetDictionaryExt.nullValue) {
                                    break@loop
                                } else {
                                    SanityCheck.println({ "merge $uuid $pChild writer append data" })
                                    ringbuffer[ringbufferWriteHead[pLocal] + ringbufferStart[pLocal]] = tmp
                                    //println("$p produced")
                                    ringbufferWriteHead[pLocal] = (ringbufferWriteHead[pLocal] + 1) % elementsPerRing
                                    ringbufferReaderContinuation.signal()
                                }
                            }
                        } else {
                            SanityCheck.println({ "merge $uuid $pChild writer launched D" })
                            val variableMapping = Array<ColumnIterator>(variables.size) { child[variables[it]]!! }
                            loop@ while (readerFinished == 0) {
                                SanityCheck.println({ "merge $uuid $pChild writer loop start" })
                                var t = (ringbufferWriteHead[pLocal] + variables.size) % elementsPerRing
                                while (ringbufferReadHead[pLocal] == t && readerFinished == 0) {
                                    ringbufferReaderContinuation.signal()
                                    ringbufferWriterContinuation[pLocal].waitCondition({ ringbufferReadHead[pLocal] == t && readerFinished == 0 })
                                }
                                if (readerFinished != 0) {
                                    for (variable in 0 until variables.size) {
                                        variableMapping[variable].close()
                                    }
                                    break@loop
                                }
                                var tmp = variableMapping[0].next()
                                if (tmp == ResultSetDictionaryExt.nullValue) {
                                    SanityCheck.println({ "merge $uuid $pChild writer closed B" })
                                    for (variable in 0 until variables.size) {
                                        variableMapping[variable].close()
                                    }
                                    break@loop
                                } else {
                                    SanityCheck.println({ "merge $uuid $pChild writer append data" })
                                    ringbuffer[ringbufferWriteHead[pLocal] + ringbufferStart[pLocal]] = tmp
                                    for (variableIdx in 1 until variables.size) {
                                        try {
                                            ringbuffer[ringbufferWriteHead[pLocal] + variableIdx + ringbufferStart[pLocal]] = variableMapping[variableIdx].next()
                                        } catch (e: Throwable) {
                                            SanityCheck.println({ "merge $uuid $pChild writer closed A" })
                                            for (variableIdx2 in 0 until variables.size) {
                                                variableMapping[variableIdx2].close()
                                            }
                                            break@loop
                                        }
                                    }
                                    //println("$p produced")
                                    ringbufferWriteHead[pLocal] = (ringbufferWriteHead[pLocal] + variables.size) % elementsPerRing
                                    ringbufferReaderContinuation.signal()
                                }
                            }
                        }
                    } else {
                        SanityCheck.println({ "merge $uuid $pChild writer launched E" })
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
                            SanityCheck.println({ "merge $uuid $pChild writer loop start" })
                            var t = (ringbufferWriteHead[pLocal] + variables.size) % elementsPerRing
                            while (ringbufferReadHead[pLocal] == t && readerFinished == 0) {
                                ringbufferReaderContinuation.signal()
                                ringbufferWriterContinuation[pLocal].waitCondition({ ringbufferReadHead[pLocal] == t && readerFinished == 0 })
                            }
                            if (readerFinished != 0) {
                                child.close()
                                break@loop
                            }
                            var tmp = child.next()
                            if (tmp == -1) {
                                SanityCheck.println({ "merge $uuid $pChild writer closed B" })
                                break@loop
                            } else {
                                SanityCheck.println({ "merge $uuid $pChild writer append data" })
                                for (variable in 0 until variables.size) {
                                    ringbuffer[ringbufferWriteHead[pLocal] + variableMapping[variable] + ringbufferStart[pLocal]] = child.buf[tmp + variable]
                                }
                                //println("$p produced")
                                ringbufferWriteHead[pLocal] = (ringbufferWriteHead[pLocal] + variables.size) % elementsPerRing
                                ringbufferReaderContinuation.signal()
                            }
                        }
                    }
                } catch (e: Throwable) {
                    error = e
                    e.printStackTrace()
                }
                continuationLock.lock()
                writerFinished[pLocal] = 1
                ringbufferReaderContinuation.signal()
                continuationLock.unlock()
                SanityCheck.println({ "merge $uuid $pChild writer exited loop" })
            }
            SanityCheck.println({ "merge $uuid $pChild writer lupos.s00misc.ParallelJob init :: " })
        }
        var sortColumns = IntArray(mySortPriority.size) { variables.indexOf(mySortPriority[it].variableName) }
        SanityCheck {
            for (x in 0 until sortColumns.size) {
                SanityCheck.check { sortColumns[x] >= 0 }
                SanityCheck.check { mySortPriority[x].sortType == ESortType.FAST }
            }
        }
        var iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = IntArray(variables.size)
        iterator.next = {
            var res = -1
            loop@ while (true) {
                SanityCheck.println({ "merge $uuid reader loop start" })
                var partitionToUse = -1
                loop2@ for (p in 0 until partitionCountSrc) {
                    if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                        if (partitionToUse < 0) {
                            partitionToUse = p
                        } else {
                            for (sp in sortColumns) {
                                val val_this = ringbuffer[ringbufferReadHead[p] + sp + ringbufferStart[p]]
                                val val_smallest = ringbuffer[ringbufferReadHead[partitionToUse] + sp + ringbufferStart[partitionToUse]]
                                if (val_this > val_smallest) {
                                    continue@loop2
                                }
                            }
                            partitionToUse = p
                        }
                    } else if (writerFinished[p] == 0) {
                        ringbufferWriterContinuation[p].signal()
                        partitionToUse = -1
                        break@loop2
                    }
                }
                if (partitionToUse >= 0) {
                    for (variable in 0 until variables.size) {
                        iterator.buf[variable] = (ringbuffer[ringbufferReadHead[partitionToUse] + variable + ringbufferStart[partitionToUse]])
                    }
                    res = 0
                    ringbufferReadHead[partitionToUse] = (ringbufferReadHead[partitionToUse] + variables.size) % elementsPerRing
                    ringbufferWriterContinuation[partitionToUse].signal()
                    break@loop
                }
                var finishedWriters = 0
                ringbufferReaderContinuation.waitCondition({
                    var flag = true
                    for (p in 0 until partitionCountSrc) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            flag = false
                            break
                        } else if (writerFinished[p] != 0) {
                            finishedWriters++
                        }
                    }
                    /*return*/(flag && finishedWriters < partitionCountSrc)
                })
                if (finishedWriters == partitionCountSrc) {
                    break@loop
                }
            }
            if (error != null) {
                iterator.close()
                throw error!!
            }
            /*return*/res
        }
        iterator.close = {
            SanityCheck.println({ "merge $uuid reader closed" })
            continuationLock.lock()
            readerFinished = 1
            for (p in 0 until partitionCountSrc) {
                ringbufferWriterContinuation[p].signal()
            }
            continuationLock.unlock()
        }
        return IteratorBundle(iterator)
    }
}
