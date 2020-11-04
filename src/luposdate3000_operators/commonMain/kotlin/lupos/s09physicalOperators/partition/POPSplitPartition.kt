package lupos.s09physicalOperators.partition

import kotlin.coroutines.Continuation
import lupos.s00misc.BugException
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.MyLock
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelCondition
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.PartitionHelper
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSplitPartition(query: IQuery, projectedVariables: List<String>, val partitionVariable: String, var partitionCount: Int, var partitionID: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPSplitPartitionID, "POPSplitPartition", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        if (variable == partitionVariable) {
            return partitionCount
        } else {
            return children[0].getPartitionCount(variable)
        }
    }

    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", "" + partitionCount)
        res.addAttribute("partitionID", "" + partitionID)
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

    override fun cloneOP(): IOPBase = POPSplitPartition(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPSplitPartition && children[0] == other.children[0] && partitionVariable == other.partitionVariable && partitionCount == other.partitionCount
    override suspend fun evaluate(parent: Partition): IteratorBundle {
//throw BugException("POPSplitPartition","child is not launching, because coroutine is missing suspension point")
        var partitionCount = parent.limit[partitionVariable]!!
        if (partitionCount == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var iterators: Array<IteratorBundle>? = null
            var job: ParallelJob?
            val childPartition = Partition(parent, partitionVariable)
            var partitionHelper: PartitionHelper?
            partitionHelper = (query as Query).getPartitionHelper(uuid)
            SanityCheck.println { "lock(${partitionHelper.lock.getUUID()}) x178" }
            partitionHelper.lock.lock()
            if (partitionHelper.iterators != null) {
                iterators = partitionHelper.iterators!![childPartition]
            }
            if (partitionHelper.jobs != null) {
                job = partitionHelper.jobs!![childPartition]
            }
            var error: Throwable? = null
            if (iterators == null) {
                iterators = Array(partitionCount) { IteratorBundle(0) }
                val variables = getProvidedVariableNames()
                val variables0 = children[0].getProvidedVariableNames()
                SanityCheck.check { variables0.containsAll(variables) }
                SanityCheck.check { variables.containsAll(variables0) }
                SanityCheck.check { variables.contains(partitionVariable) }
                val elementsPerRing = Partition.queue_size * variables.size
                val ringbuffer = IntArray(elementsPerRing * partitionCount) //only modified by writer, reader just modifies its pointer
                val ringbufferStart = IntArray(partitionCount) { it * elementsPerRing } //constant
                val ringbufferReadHead = IntArray(partitionCount) { 0 } //owned by read-thread - no locking required
                val ringbufferWriteHead = IntArray(partitionCount) { 0 } //owned by write thread - no locking required
                var continuationLock = MyLock()
                val ringbufferReaderContinuation = Array<ParallelCondition>(partitionCount) { Parallel.createCondition(continuationLock) }
                var ringbufferWriterContinuation: ParallelCondition = Parallel.createCondition(continuationLock)
                val readerFinished = IntArray(partitionCount) { 0 } //writer changes to 1 if finished
                var writerFinished = 0
                SanityCheck.println({ "ringbuffersize = ${ringbuffer.size} ${elementsPerRing} ${partitionCount} ${ringbufferStart.map { it }} ${ringbufferReadHead.map { it }} ${ringbufferWriteHead.map { it }}" })
                SanityCheck.println({ "split $uuid writer launched A" })
                job = Parallel.launch {
                    var child2: RowIterator? = null
                    try {
                        SanityCheck.println({ "split $uuid writer launched B" })
                        val child = children[0].evaluate(childPartition).rows
                        child2 = child
                        SanityCheck.println({ "split $uuid writer launched C" })
                        var hashVariableIndex = -1
                        val variableMapping = IntArray(variables.size)
                        for (variable in 0 until variables.size) {
                            for (variable2 in 0 until variables.size) {
                                if (child.columns[variable] == partitionVariable) {
                                    hashVariableIndex = variable
                                }
                                if (variables[variable2] == child.columns[variable]) {
                                    variableMapping[variable] = variable2
                                    break
                                }
                            }
                        }
                        SanityCheck.check { hashVariableIndex != -1 }
                        val cacheArr = IntArray(partitionCount) { it }
                        SanityCheck.println({ "split $uuid writer launched D" })
                        loop@ while (true) {
                            SanityCheck.println({ "split $uuid writer loop start" })
                            var tmp = child.next()
                            var readerFinishedCounter = 0
                            for (p in 0 until partitionCount) {
                                if (readerFinished[p] != 0) {
                                    readerFinishedCounter++
                                }
                            }
                            if (readerFinishedCounter == partitionCount) {
                                SanityCheck.println({ "split $uuid writer launched E" })
                                tmp = -1
                            }
                            if (tmp == -1) {
                                SanityCheck.println({ "split $uuid writer closed A" })
                                break@loop
                            } else {
                                var q = child.buf[tmp + hashVariableIndex]
                                var cacheSize: Int
                                if (q == ResultSetDictionaryExt.undefValue) {
                                    //broadcast undef to every partition
                                    SanityCheck.println({ " attention may increase result count here - this is always ok, _if there is a join afterwards immediately - otherwise probably not" })
                                    cacheSize = partitionCount
                                    cacheArr[0] = 0
                                } else {
                                    cacheSize = 1
                                    q = Partition.hashFunction(q, partitionCount)
                                    cacheArr[0] = q
                                }
                                loopcache@ for (i in 0 until cacheSize) {
                                    val p = cacheArr[i]
                                    if (readerFinished[p] != 0) {
                                        continue@loopcache
                                    }
                                    SanityCheck.println({ "selected $p for $partitionVariable = $hashVariableIndex value ${child.buf[tmp + hashVariableIndex]}" })
                                    var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    while (ringbufferReadHead[p] == t && readerFinished[p] == 0) {
                                        ringbufferReaderContinuation[p].signal()
                                        ringbufferWriterContinuation.waitCondition({ ringbufferReadHead[p] == t && readerFinished[p] == 0 })
                                    }
                                    if (readerFinished[p] != 0) {
                                        continue@loopcache
                                    }
                                    SanityCheck.println({ "split $uuid $p writer append data ${variables.size} ${variableMapping.toMutableList()} ${ringbufferStart[p]}" })
                                    for (variable in 0 until variables.size) {
                                        SanityCheck.println({ "split $uuid $p writer append data ... ${variable} ${ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]} ${tmp + variable}" })
                                        ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                                        SanityCheck.println({ "split $uuid $p writer append data --- $variables ${child.columns.map { it }} ${ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]}<-${tmp + variable} = ${child.buf[tmp + variable]}" })
                                    }
                                    SanityCheck.println({ "split $uuid $p writer append data - written data" })
                                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    SanityCheck.println({ "split $uuid $p writer append data - increased pointer" })
                                    ringbufferReaderContinuation[p].signal()
                                }
                            }
                            SanityCheck.println({ "split $uuid writer loop end of iteration" })
                        }
                    } catch (e: Throwable) {
                        error = e
                    }
                    SanityCheck.println({ "split $uuid writer launched F" })
                    if (child2 != null) {
                        child2.close()
                    }
                    continuationLock.lock()
                    writerFinished = 1
                    for (p in 0 until partitionCount) {
                        ringbufferReaderContinuation[p].signal()
                    }
                    continuationLock.unlock()
                    SanityCheck.println({ "split $uuid writer launched G" })
                    SanityCheck.println({ "split $uuid writer exited loop" })
                }
                for (p in 0 until partitionCount) {
                    var iterator = RowIterator()
                    iterator.columns = variables.toTypedArray()
                    iterator.buf = IntArray(variables.size)
                    iterator.next = {
                        var res = -1
                        loop@ while (true) {
                            SanityCheck.println({ "split $uuid $p reader loop start" })
                            if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                //non empty queue -> read one row
                                SanityCheck.println({ "split $uuid $p reader consumed data" })
                                for (variable in 0 until variables.size) {
                                    iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                                }
                                res = 0
                                ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
                                break@loop
                            } else if (writerFinished == 1) {
                                SanityCheck.println({ "split $uuid $p reader closed" })
                                iterator.close()
                                if (error != null) {
                                    throw error!!
                                }
                                break@loop
                            }
                            ringbufferWriterContinuation.signal()
                            ringbufferReaderContinuation[p].waitCondition({ ringbufferReadHead[p] == ringbufferWriteHead[p] && writerFinished == 0 })
                        }
                        /*return*/res
                    }
                    iterator.close = {
                        SanityCheck.println({ "split $uuid $p reader close" })
                        continuationLock.lock()
                        readerFinished[p] = 1
                        ringbufferWriterContinuation.signal()
                        continuationLock.unlock()
                    }
                    iterators[p] = IteratorBundle(iterator)
                }
                if (partitionHelper.iterators == null || partitionHelper.jobs == null) {
                    partitionHelper.iterators = mutableMapOf(childPartition to iterators)
                    partitionHelper.jobs = mutableMapOf(childPartition to job)
                } else {
                    partitionHelper.iterators!![childPartition] = iterators
                    partitionHelper.jobs!![childPartition] = job
                }
            }
            SanityCheck.println { "unlock(${partitionHelper.lock.getUUID()}) x191" }
            partitionHelper.lock.unlock()
            return iterators!![parent.data[partitionVariable]!!]
        }
    }
}
