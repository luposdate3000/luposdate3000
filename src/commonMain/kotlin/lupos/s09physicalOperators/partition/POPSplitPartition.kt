package lupos.s09physicalOperators.partition

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lupos.s00misc.BugException
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
import lupos.s04logicalOperators.PartitionHelper
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSplitPartition(query: Query, projectedVariables: List<String>, val partitionVariable: String, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPSplitPartitionID, "POPSplitPartition", arrayOf(child), ESortPriority.PREVENT_ANY) {
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

    override fun cloneOP() = POPSplitPartition(query, projectedVariables, partitionVariable, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPSplitPartition && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override suspend fun evaluate(parent: Partition): IteratorBundle {
//throw BugException("POPSplitPartition","child is not launching, because coroutine is missing suspension point")
        if (Partition.k == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var iterators: Array<IteratorBundle>? = null
            var job: Job? = null
            val childPartition = Partition(parent, partitionVariable, GlobalScope)
            var partitionHelper: PartitionHelper? = null
            partitionHelper = query.getPartitionHelper(uuid)
            SanityCheck.println { "lock(${partitionHelper!!.lock.uuid}) x32" }
            partitionHelper!!.lock.lock()
            val tmpIterators = partitionHelper!!.iterators
            if (tmpIterators != null) {
                iterators = tmpIterators[childPartition]
            }
            val tmpJob = partitionHelper!!.jobs
            if (tmpJob != null) {
                job = tmpJob[childPartition]
            }
            if (iterators == null) {
                iterators = Array(Partition.k) { IteratorBundle(0) }
                val variables = getProvidedVariableNames()
                val variables0 = children[0].getProvidedVariableNames()
                SanityCheck.check { variables0.containsAll(variables) }
                SanityCheck.check { variables.containsAll(variables0) }
                SanityCheck.check { variables.contains(partitionVariable) }
                val elementsPerRing = Partition.queue_size * variables.size
                val ringbuffer = IntArray(elementsPerRing * Partition.k) //only modified by writer, reader just modifies its pointer
                val ringbufferStart = IntArray(Partition.k) { it * elementsPerRing } //constant
                val ringbufferReadHead = IntArray(Partition.k) { 0 } //owned by read-thread - no locking required
                val ringbufferWriteHead = IntArray(Partition.k) { 0 } //owned by write thread - no locking required
                val ringbufferReaderContinuation = Array<Continuation<Unit>?>(Partition.k) { null }
                var ringbufferWriterContinuation: Continuation<Unit>? = null
                var continuationLock = Lock()
                val readerFinished = IntArray(Partition.k) { 0 } //writer changes to 1 if finished
                var writerFinished = 0
                SanityCheck.println({ "ringbuffersize = ${ringbuffer.size} ${elementsPerRing} ${Partition.k} ${ringbufferStart.map { it }} ${ringbufferReadHead.map { it }} ${ringbufferWriteHead.map { it }}" })
                job = parent.scope.launch(Dispatchers.Default) {
                    childPartition.scope = this
                    SanityCheck.println({ "split $uuid writer launched" })
                    val child = children[0].evaluate(childPartition).rows
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
                    val cacheArr = IntArray(Partition.k) { it }
                    SanityCheck.println({ "split $uuid writer before loop" })
                    loop@ while (true) {
                        SanityCheck.println({ "split $uuid writer loop start" })
                        var tmp = child.next()
                        var readerFinishedCounter = 0
                        for (p in 0 until Partition.k) {
                            if (readerFinished[p] != 0) {
                                readerFinishedCounter++
                            }
                        }
                        if (readerFinishedCounter == Partition.k) {
                            tmp = -1
                        }
                        if (tmp == -1) {
                            SanityCheck.println({ "split $uuid writer closed A" })
                            break@loop
                        } else {
                            var q = child.buf[tmp + hashVariableIndex]
                            var cacheSize: Int
                            if (q == ResultSetDictionary.undefValue) {
                                //broadcast undef to every partition
                                SanityCheck.println({ " attention may increase result count here - this is always ok, _if there is a join afterwards immediately - otherwise probably not" })
                                cacheSize = Partition.k
                                cacheArr[0] = 0
                            } else {
                                cacheSize = 1
                                q = Partition.hashFunction(q)
                                cacheArr[0] = q
                            }
                            loopcache@ for (i in 0 until cacheSize) {
                                val p = cacheArr[i]
                                if (readerFinished[p] != 0) {
                                    continue@loopcache
                                }
                                SanityCheck.println({ "selected $p for $partitionVariable = $hashVariableIndex value ${child.buf[tmp + hashVariableIndex]}" })
                                var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                if (ringbufferReadHead[p] == t) {
                                    SanityCheck.println { "lock(${continuationLock.uuid}) x67" }
                                    continuationLock.lock()
var tmp2 = ringbufferReaderContinuation[p]
                                        ringbufferReaderContinuation[p] = null
                                        if (tmp2 != null) {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x92" }
                                            continuationLock.unlock()
                                            tmp2.resume(Unit)
                                            SanityCheck.println { "lock(${continuationLock.uuid}) x95" }
                                            continuationLock.lock()
                                        }
                                    if (ringbufferReadHead[p] == t) {
                                        suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                            ringbufferWriterContinuation = continuation
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x68" }
                                            continuationLock.unlock()
                                            COROUTINE_SUSPENDED
                                        }
                                    } else {
                                        SanityCheck.println { "unlock(${continuationLock.uuid}) x69" }
                                        continuationLock.unlock()
                                    }
                                    if (readerFinished[p] != 0) {
                                        continue@loopcache
                                    }
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
                                var tmp2 = ringbufferReaderContinuation[p]
                                if (tmp2 != null) {
                                    SanityCheck.println { "lock(${continuationLock.uuid}) x70" }
                                    continuationLock.lock()
                                    tmp2 = ringbufferReaderContinuation[p]
                                    ringbufferReaderContinuation[p] = null
                                    SanityCheck.println { "unlock(${continuationLock.uuid}) x71" }
                                    continuationLock.unlock()
                                    if (tmp2 != null) {
                                        (tmp2 as Continuation<Unit>).resume(Unit)
                                    }
                                }
                            }
                        }
                        SanityCheck.println({ "split $uuid writer loop end of iteration" })
                    }
                    child.close()
                    writerFinished = 1
                    for (p in 0 until Partition.k) {
                        var tmp2 = ringbufferReaderContinuation[p]
                        if (tmp2 != null) {
                            SanityCheck.println { "lock(${continuationLock.uuid}) x72" }
                            continuationLock.lock()
                            tmp2 = ringbufferReaderContinuation[p]
                            ringbufferReaderContinuation[p] = null
                            SanityCheck.println { "unlock(${continuationLock.uuid}) x73" }
                            continuationLock.unlock()
                            if (tmp2 != null) {
                                (tmp2 as Continuation<Unit>).resume(Unit)
                            }
                        }
                    }
                    SanityCheck.println({ "split $uuid writer exited loop" })
                }
                for (p in 0 until Partition.k) {
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
                                break@loop
                            }
                            SanityCheck.println { "lock(${continuationLock.uuid}) x74" }
                            continuationLock.lock()
var tmp2 = ringbufferWriterContinuation
                                        ringbufferWriterContinuation = null
                                        if (tmp2 != null) {
                                            SanityCheck.println { "unlock(${continuationLock.uuid}) x93" }
                                            continuationLock.unlock()
                                            tmp2!!.resume(Unit)
                                            SanityCheck.println { "lock(${continuationLock.uuid}) x94" }
                                            continuationLock.lock()
                                        }
                            if (ringbufferReadHead[p] == ringbufferWriteHead[p]) {
                                suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                                    ringbufferReaderContinuation[p] = continuation
                                    SanityCheck.println { "unlock(${continuationLock.uuid}) x75" }
                                    continuationLock.unlock()
                                    COROUTINE_SUSPENDED
                                }
                            } else {
                                SanityCheck.println { "unlock(${continuationLock.uuid}) x76" }
                                continuationLock.unlock()
                            }
                        }
                        /*return*/res
                    }
                    iterator.close = {
                        SanityCheck.println({ "split $uuid $p reader close" })
                        SanityCheck.println { "lock(${continuationLock.uuid}) x77" }
                        continuationLock.lock()
                        readerFinished[p] = 1
                        var tmp = 0
                        for (p in 0 until Partition.k) {
                            if (readerFinished[p] == 1) {
                                tmp++
                            }
                        }
                        SanityCheck.println { "unlock(${continuationLock.uuid}) x78" }
                        continuationLock.unlock()
                        if (tmp == Partition.k) {
                            job!!.cancelAndJoin()
                        } else {
                        }
                        SanityCheck.println({ "split $uuid $p reader closed" })
                    }
                    iterators!![p] = IteratorBundle(iterator)
                }
                if (tmpIterators == null || tmpJob == null) {
                    partitionHelper!!.iterators = mutableMapOf(childPartition to iterators!!)
                    partitionHelper!!.jobs = mutableMapOf(childPartition to job!!)
                } else {
                    tmpIterators[childPartition] = iterators!!
                    tmpJob[childPartition] = job!!
                }
            }
            SanityCheck.println { "unlock(${partitionHelper!!.lock.uuid}) x33" }
            partitionHelper!!.lock.unlock()
            return iterators!![parent.data[partitionVariable]!!]
        }
    }
}
