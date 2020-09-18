package lupos.s09physicalOperators.partition
import lupos.s00misc.Parallel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
            val ringbufferWriterContinuation = Array<Continuation<Unit>?>(Partition.k) { null }
            var ringbufferReaderContinuation: Continuation<Unit>? = null
            var continuationLock = Lock()
            val writerFinished = IntArray(Partition.k) { 0 } //writer changes to 1 if finished
            var readerFinished = 0
            val jobs = mutableListOf<Job>()
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
                val job = Parallel.launch{
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
                                    if (ringbufferReadHead[p] == t) {
                                        SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x96" }
                                        continuationLock.lock()
                                        var tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        if (tmp2 != null) {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x84" }
                                            continuationLock.unlock()
                                            SanityCheck.println { "$uuid reader resume coroutine x97" }
                                            tmp2.resume(Unit)
                                            SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x85" }
                                            continuationLock.lock()
                                        }
                                        if (ringbufferReadHead[p] == t && readerFinished == 0) {
                                            suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                                ringbufferWriterContinuation[p] = continuation
                                                SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x98" }
                                                continuationLock.unlock()
                                                SanityCheck.println { "$uuid writer[$p] SUSPENDED coroutine x99 " }
                                                COROUTINE_SUSPENDED
                                            }
                                        } else {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x100" }
                                            continuationLock.unlock()
                                        }
                                        if (readerFinished != 0) {
                                            childIterator.close()
                                            break@loop
                                        }
                                    }
                                    var tmp = childIterator.next()
                                    if (tmp == ResultSetDictionary.nullValue) {
                                        break@loop
                                    } else {
                                        SanityCheck.println({ "merge $uuid $p writer append data" })
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        //println("$p produced")
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + 1) % elementsPerRing
                                        var tmp2 = ringbufferReaderContinuation
                                        if (tmp2 != null) {
                                            SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x104" }
                                            continuationLock.lock()
                                            tmp2 = ringbufferReaderContinuation
                                            ringbufferReaderContinuation = null
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x103" }
                                            continuationLock.unlock()
                                            if (tmp2 != null) {
                                                SanityCheck.println { "$uuid reader resume coroutine x102" }
                                                tmp2.resume(Unit)
                                            }
                                        }
                                    }
                                }
                            } else {
                                SanityCheck.println({ "merge $uuid $p writer launched D" })
                                val variableMapping = Array<ColumnIterator>(variables.size) { child[variables[it]]!! }
                                loop@ while (readerFinished == 0) {
                                    SanityCheck.println({ "merge $uuid $p writer loop start" })
                                    var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    if (ringbufferReadHead[p] == t) {
                                        SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x119" }
                                        continuationLock.lock()
                                        var tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        if (tmp2 != null) {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x86" }
                                            continuationLock.unlock()
                                            SanityCheck.println { "$uuid reader resume coroutine x107" }
                                            tmp2.resume(Unit)
                                            SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x87" }
                                            continuationLock.lock()
                                        }
                                        if (ringbufferReadHead[p] == t && readerFinished == 0) {
                                            suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                                ringbufferWriterContinuation[p] = continuation
                                                SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x108" }
                                                continuationLock.unlock()
                                                SanityCheck.println { "$uuid writer[$p] SUSPENDED coroutine x109 " }
                                                COROUTINE_SUSPENDED
                                            }
                                        } else {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x110" }
                                            continuationLock.unlock()
                                        }
                                        if (readerFinished != 0) {
                                            for (variable in 0 until variables.size) {
                                                variableMapping[variable].close()
                                            }
                                            break@loop
                                        }
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
                                        var tmp2 = ringbufferReaderContinuation
                                        if (tmp2 != null) {
                                            SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x114" }
                                            continuationLock.lock()
                                            tmp2 = ringbufferReaderContinuation
                                            ringbufferReaderContinuation = null
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x115" }
                                            continuationLock.unlock()
                                            if (tmp2 != null) {
                                                SanityCheck.println { "$uuid reader resume coroutine x116" }
                                                tmp2.resume(Unit)
                                            }
                                        }
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
                                if (ringbufferReadHead[p] == t) {
                                    SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x160" }
                                    continuationLock.lock()
                                    var tmp2 = ringbufferReaderContinuation
                                    ringbufferReaderContinuation = null
                                    if (tmp2 != null) {
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x81" }
                                        continuationLock.unlock()
                                        SanityCheck.println { "$uuid reader resume coroutine x118" }
                                        tmp2.resume(Unit)
                                        SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x88" }
                                        continuationLock.lock()
                                    }
                                    if (ringbufferReadHead[p] == t && readerFinished == 0) {
                                        suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                            ringbufferWriterContinuation[p] = continuation
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x161" }
                                            continuationLock.unlock()
                                            SanityCheck.println { "$uuid writer[$p] SUSPENDED coroutine x113 " }
                                            COROUTINE_SUSPENDED
                                        }
                                    } else {
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x83" }
                                        continuationLock.unlock()
                                    }
                                    if (readerFinished != 0) {
                                        child.close()
                                        break@loop
                                    }
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
                                    var tmp2 = ringbufferReaderContinuation
                                    if (tmp2 != null) {
                                        SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x165" }
                                        continuationLock.lock()
                                        tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x166" }
                                        continuationLock.unlock()
                                        if (tmp2 != null) {
                                            SanityCheck.println { "$uuid reader resume coroutine x121" }
                                            tmp2.resume(Unit)
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        ex = e
                        e.printStackTrace()
                    }
                    SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x167" }
                    continuationLock.lock()
                    writerFinished[p] = 1
                    var tmp2 = ringbufferReaderContinuation
                    ringbufferReaderContinuation = null
                    SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x168" }
                    continuationLock.unlock()
                    if (tmp2 != null) {
                        SanityCheck.println { "$uuid reader resume coroutine x120" }
                        tmp2.resume(Unit)
                    }
                    SanityCheck.println({ "merge $uuid $p writer exited loop" })
                }
                jobs.add(job)
                SanityCheck.println({ "merge $uuid $p writer job init :: " })
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
                            var tmp2 = ringbufferWriterContinuation[p]
                            if (tmp2 != null) {
                                SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x169" }
                                continuationLock.lock()
                                tmp2 = ringbufferWriterContinuation[p]
                                ringbufferWriterContinuation[p] = null
                                SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x170" }
                                continuationLock.unlock()
                                if (tmp2 != null) {
                                    SanityCheck.println { "$uuid writer[p] resume coroutine x14" }
                                    (tmp2 as Continuation<Unit>).resume(Unit)
                                }
                            }
                            break@loop
                        } else if (writerFinished[p] == 0) {
                            SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x91" }
                            continuationLock.lock()
                            var tmp2 = ringbufferWriterContinuation[p]
                            ringbufferWriterContinuation[p] = null
                            if (tmp2 != null) {
                                SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x89" }
                                continuationLock.unlock()
                                SanityCheck.println { "$uuid writer[p] resume coroutine x15" }
                                tmp2!!.resume(Unit)
                            } else {
                                SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x171" }
                                continuationLock.unlock()
                            }
                        }
                    }
                    SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x26" }
                    continuationLock.lock()
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
                        SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x27" }
                        continuationLock.unlock()
                        break@loop
                    } else if (flag) {
                        suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                            ringbufferReaderContinuation = continuation
                            SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x173" }
                            continuationLock.unlock()
                            SanityCheck.println { "$uuid reader SUSPENDED coroutine x172 " }
                            COROUTINE_SUSPENDED
                        }
                    } else {
                        SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x174" }
                        continuationLock.unlock()
                    }
                }
                if (ex != null) {
                    throw ex!!
                }
                /*return*/res
            }
            iterator.close = {
                SanityCheck.println({ "merge $uuid reader closed" })
                SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x203" }
                continuationLock.lock()
                readerFinished = 1
                SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x95" }
                continuationLock.unlock()
                for (p in 0 until Partition.k) {
                    var tmp2 = ringbufferWriterContinuation[p]
                    if (tmp2 != null) {
                        SanityCheck.println { "lock(${continuationLock.uuid}) [$uuid] x176" }
                        continuationLock.lock()
                        tmp2 = ringbufferWriterContinuation[p]
                        ringbufferWriterContinuation[p] = null
                        SanityCheck.println { "unlock(${continuationLock.uuid}) [$uuid] x177" }
                        continuationLock.unlock()
                        if (tmp2 != null) {
                            SanityCheck.println { "$uuid writer[p] resume coroutine x175" }
                            (tmp2 as Continuation<Unit>).resume(Unit)
                        }
                    }
                }
            }
            return IteratorBundle(iterator)
        }
    }
}
