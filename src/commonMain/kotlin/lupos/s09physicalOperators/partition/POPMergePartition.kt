package lupos.s09physicalOperators.partition

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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
                val job = parent.scope.launch(Dispatchers.Default) {
                    val scope = this
                    try {
                        val childEval = children[0].evaluate(Partition(parent, partitionVariable, p, scope))
                        if (childEval.hasColumnMode()) {
                            val child = childEval.columns
                            if (variables.size == 1) {
                                val childIterator = child[variables[0]]!!
                                loop@ while (readerFinished == 0) {
                                    SanityCheck.println({ "merge $uuid $p writer loop start" })
                                    var t = (ringbufferWriteHead[p] + 1) % elementsPerRing
                                    if (ringbufferReadHead[p] == t) {
                                        SanityCheck.println { "lock(${continuationLock.uuid}) x1" }
                                        continuationLock.lock()
                                        var tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        if (tmp2 != null) {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x84" }
                                            continuationLock.unlock()
                                            tmp2.resume(Unit)
                                            SanityCheck.println { "lock(${continuationLock.uuid}) x85" }
                                            continuationLock.lock()
                                        }
                                        if (ringbufferReadHead[p] == t) {
                                            suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                                ringbufferWriterContinuation[p] = continuation
                                                SanityCheck.println { "unlock(${continuationLock.uuid}) x2" }
                                                continuationLock.unlock()
                                                COROUTINE_SUSPENDED
                                            }
                                        } else {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x3" }
                                            continuationLock.unlock()
                                        }
                                        if (readerFinished != 0) {
                                            childIterator.close()
                                            writerFinished[p] = 1
                                            break@loop
                                        }
                                    }
                                    var tmp = childIterator.next()
                                    if (tmp == ResultSetDictionary.nullValue) {
                                        SanityCheck.println({ "merge $uuid $p writer closed B" })
                                        SanityCheck.println { "lock(${continuationLock.uuid}) x4" }
                                        continuationLock.lock()
                                        writerFinished[p] = 1
                                        var tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) x5" }
                                        continuationLock.unlock()
                                        if (tmp2 != null) {
                                            tmp2.resume(Unit)
                                        }
                                        break@loop
                                    } else {
                                        SanityCheck.println({ "merge $uuid $p writer append data" })
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        //println("$p produced")
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + 1) % elementsPerRing
                                        var tmp2 = ringbufferReaderContinuation
                                        if (tmp2 != null) {
                                            SanityCheck.println { "lock(${continuationLock.uuid}) x6" }
                                            continuationLock.lock()
                                            tmp2 = ringbufferReaderContinuation
                                            ringbufferReaderContinuation = null
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x7" }
                                            continuationLock.unlock()
                                            if (tmp2 != null) {
                                                tmp2.resume(Unit)
                                            }
                                        }
                                    }
                                }
                            } else {
                                val variableMapping = Array<ColumnIterator>(variables.size) { child[variables[it]]!! }
                                loop@ while (readerFinished == 0) {
                                    SanityCheck.println({ "merge $uuid $p writer loop start" })
                                    var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    if (ringbufferReadHead[p] == t) {
                                        SanityCheck.println { "lock(${continuationLock.uuid}) x8" }
                                        continuationLock.lock()
                                        var tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        if (tmp2 != null) {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x86" }
                                            continuationLock.unlock()
                                            tmp2.resume(Unit)
                                            SanityCheck.println { "lock(${continuationLock.uuid}) x87" }
                                            continuationLock.lock()
                                        }
                                        if (ringbufferReadHead[p] == t) {
                                            suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                                ringbufferWriterContinuation[p] = continuation
                                                SanityCheck.println { "unlock(${continuationLock.uuid}) x9" }
                                                continuationLock.unlock()
                                                COROUTINE_SUSPENDED
                                            }
                                        } else {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x10" }
                                            continuationLock.unlock()
                                        }
                                        if (readerFinished != 0) {
                                            for (variable in 0 until variables.size) {
                                                variableMapping[variable].close()
                                            }
                                            writerFinished[p] = 1
                                            break@loop
                                        }
                                    }
                                    var tmp = variableMapping[0].next()
                                    if (tmp == ResultSetDictionary.nullValue) {
                                        SanityCheck.println({ "merge $uuid $p writer closed B" })
                                        for (variable in 0 until variables.size) {
                                            variableMapping[variable].close()
                                        }
                                        SanityCheck.println { "lock(${continuationLock.uuid}) x11" }
                                        continuationLock.lock()
                                        writerFinished[p] = 1
                                        var tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) x12" }
                                        continuationLock.unlock()
                                        if (tmp2 != null) {
                                            tmp2.resume(Unit)
                                        }
                                        break@loop
                                    } else {
                                        SanityCheck.println({ "merge $uuid $p writer append data" })
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        for (variable in 1 until variables.size) {
                                            try {
                                                ringbuffer[ringbufferWriteHead[p] + variable + ringbufferStart[p]] = variableMapping[variable].next()
                                            } catch (e: Throwable) {
                                                SanityCheck.println({ "merge $uuid $p writer closed A" })
                                                for (variable in 0 until variables.size) {
                                                    variableMapping[variable].close()
                                                }
                                                writerFinished[p] = 1
                                                break@loop
                                            }
                                        }
                                        //println("$p produced")
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                        var tmp2 = ringbufferReaderContinuation
                                        if (tmp2 != null) {
                                            SanityCheck.println { "lock(${continuationLock.uuid}) x13" }
                                            continuationLock.lock()
                                            tmp2 = ringbufferReaderContinuation
                                            ringbufferReaderContinuation = null
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x14" }
                                            continuationLock.unlock()
                                            if (tmp2 != null) {
                                                tmp2.resume(Unit)
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
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
                                    SanityCheck.println { "lock(${continuationLock.uuid}) x15" }
                                    continuationLock.lock()
                                    var tmp2 = ringbufferReaderContinuation
                                    ringbufferReaderContinuation = null
                                    if (tmp2 != null) {
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) x81" }
                                        continuationLock.unlock()
                                        tmp2.resume(Unit)
                                        SanityCheck.println { "lock(${continuationLock.uuid}) x88" }
                                        continuationLock.lock()
                                    }
                                    if (ringbufferReadHead[p] == t) {
                                        suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                            ringbufferWriterContinuation[p] = continuation
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x16" }
                                            continuationLock.unlock()
                                            COROUTINE_SUSPENDED
                                        }
                                    } else {
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) x17" }
                                        continuationLock.unlock()
                                    }
                                    if (readerFinished != 0) {
                                        child.close()
                                        writerFinished[p] = 1
                                        break@loop
                                    }
                                }
                                var tmp = child.next()
                                if (tmp == -1) {
                                    SanityCheck.println({ "merge $uuid $p writer closed B" })
                                    SanityCheck.println { "lock(${continuationLock.uuid}) x18" }
                                    continuationLock.lock()
                                    writerFinished[p] = 1
                                    var tmp2 = ringbufferReaderContinuation
                                    ringbufferReaderContinuation = null
                                    SanityCheck.println { "unlock(${continuationLock.uuid}) x19" }
                                    continuationLock.unlock()
                                    if (tmp2 != null) {
                                        tmp2.resume(Unit)
                                    }
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
                                        SanityCheck.println { "lock(${continuationLock.uuid}) x20" }
                                        continuationLock.lock()
                                        tmp2 = ringbufferReaderContinuation
                                        ringbufferReaderContinuation = null
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) x21" }
                                        continuationLock.unlock()
                                        if (tmp2 != null) {
                                            tmp2.resume(Unit)
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        ex = e
                        e.printStackTrace()
                        SanityCheck.println { "lock(${continuationLock.uuid}) x22" }
                        continuationLock.lock()
                        writerFinished[p] = 1
                        var tmp2 = ringbufferReaderContinuation
                        ringbufferReaderContinuation = null
                        SanityCheck.println { "unlock(${continuationLock.uuid}) x23" }
                        continuationLock.unlock()
                        if (tmp2 != null) {
                            tmp2.resume(Unit)
                        }
                    }
                    SanityCheck.println({ "merge $uuid $p writer exited loop" })
                }
                jobs.add(job)
            }
            var iterator = RowIterator()
            iterator.columns = variables.toTypedArray()
            iterator.buf = IntArray(variables.size)
            iterator.next = {
                var res = -1
                loop@ while (true) {
                    SanityCheck.println({ "merge $uuid reader loop start" })
                    var finishedWriters = 0
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
                                SanityCheck.println { "lock(${continuationLock.uuid}) x24" }
                                continuationLock.lock()
                                tmp2 = ringbufferWriterContinuation[p]
                                ringbufferWriterContinuation[p] = null
                                SanityCheck.println { "unlock(${continuationLock.uuid}) x25" }
                                continuationLock.unlock()
                                if (tmp2 != null) {
                                    (tmp2 as Continuation<Unit>).resume(Unit)
                                }
                            }
                            //println("$p consumed")
                            break@loop
                        } else if (writerFinished[p] == 1) {
                            finishedWriters++
                        } else {
                            SanityCheck.println { "lock(${continuationLock.uuid}) x91" }
                            continuationLock.lock()
                            var tmp2 = ringbufferWriterContinuation[p]
                            ringbufferWriterContinuation[p] = null
                            if (tmp2 != null) {
                                SanityCheck.println { "unlock(${continuationLock.uuid}) x89" }
                                continuationLock.unlock()
                                tmp2!!.resume(Unit)
                            } else {
                                SanityCheck.println { "unlock(${continuationLock.uuid}) x90" }
                                continuationLock.unlock()
                            }
                        }
                    }
                    if (finishedWriters == Partition.k) {
                        //done
                        break@loop
                    }
                    SanityCheck.println { "lock(${continuationLock.uuid}) x26" }
                    continuationLock.lock()
                    finishedWriters = 0
                    var flag = true
                    for (p in 0 until Partition.k) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            flag = false
                        } else if (writerFinished[p] == 1) {
                            finishedWriters++
                        }
                    }
                    if (finishedWriters == Partition.k) {
                        SanityCheck.println { "unlock(${continuationLock.uuid}) x27" }
                        continuationLock.unlock()
                        break@loop
                    } else if (flag) {
                        suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                            ringbufferReaderContinuation = continuation
                            SanityCheck.println { "unlock(${continuationLock.uuid}) x28" }
                            continuationLock.unlock()
                            COROUTINE_SUSPENDED
                        }
                    } else {
                        SanityCheck.println { "unlock(${continuationLock.uuid}) x29" }
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
                readerFinished = 1
                for (p in 0 until Partition.k) {
                    var tmp2 = ringbufferWriterContinuation[p]
                    if (tmp2 != null) {
                        SanityCheck.println { "lock(${continuationLock.uuid}) x30" }
                        continuationLock.lock()
                        tmp2 = ringbufferWriterContinuation[p]
                        ringbufferWriterContinuation[p] = null
                        SanityCheck.println { "unlock(${continuationLock.uuid}) x31" }
                        continuationLock.unlock()
                        if (tmp2 != null) {
                            (tmp2 as Continuation<Unit>).resume(Unit)
                        }
                    }
                }
                for (job in jobs) {
                    job.cancelAndJoin()
                }
            }
            return IteratorBundle(iterator)
        }
    }
}
