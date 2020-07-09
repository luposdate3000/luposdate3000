package lupos.s09physicalOperators.parallel

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.yield
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSplitParallel(query: Query, projectedVariables: List<String>, val partitionVariable: String, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPSplitParallelID, "POPSplitParallel", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        return res
    }

    override fun cloneOP() = POPSplitParallel(query, projectedVariables, partitionVariable, children[0].cloneOP())
    override fun toSparql() = "{" + children[0].toSparql() + "} SplitParallel {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPSplitParallel && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (ParallelBase.k == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
                var iterators: Array<IteratorBundle>? = null
                var job: Job? = null
            query.partitionsLock.withWriteLockSuspend {
                 iterators= query.partitionsIterators[uuid]
                 job = query.partitionsJobs[uuid]
                if (iterators == null) {
                    iterators = Array(ParallelBase.k) { IteratorBundle(0) }
                    val variables = getProvidedVariableNames()
                    val variables0 = children[0].getProvidedVariableNames()
                    SanityCheck.check { variables0.containsAll(variables) }
                    SanityCheck.check { variables.containsAll(variables0) }
                    SanityCheck.check { variables.contains(partitionVariable) }
                    val elementsPerRing = ParallelBase.queue_size * variables.size
                    val ringbuffer = IntArray(elementsPerRing * ParallelBase.k) //only modified by writer, reader just modifies its pointer
                    val ringbufferStart = IntArray(ParallelBase.k) { it * elementsPerRing } //constant
                    val ringbufferReadHead = IntArray(ParallelBase.k) { it * elementsPerRing } //owned by read-thread - no locking required
                    val ringbufferWriteHead = IntArray(ParallelBase.k) { it * elementsPerRing } //owned by write thread - no locking required
                    val readerFinished = IntArray(ParallelBase.k) { 0 } //writer changes to 1 if finished
                    var writerFinished = 0
                    runBlocking {
                        job = launch {
                            val child = children[0].evaluate(Partition(parent, partitionVariable)).rows
                            loop@ while (isActive) {
                                var tmp = child.next()
                                if (tmp == -1) {
                                    writerFinished = 1
                                    break@loop
                                } else {
                                    var p = 0
                                    for (i in 0 until variables.size) {
                                        p += child.buf[tmp + i]
                                    }
                                    if (p < 0) {
                                        p = -p
                                    }
                                    p = p % ParallelBase.k
                                    var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    while (ringbufferReadHead[p] == t) {
                                        yield()
                                        if (!isActive) {
                                            child.close()
                                            writerFinished = 1
                                            break@loop
                                        }
                                    }
                                    for (variable in 0 until variables.size) {
                                        ringbuffer[ringbufferWriteHead[p] + variable + ringbufferStart[p]] = child.buf[tmp + variable]
                                    }
                                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                }
                            }
                        }
                    }
                    for (p in 0 until ParallelBase.k) {
                        var iterator = RowIterator()
                        iterator.columns = variables.toTypedArray()
                        iterator.next = {
                            var res = -1
                            loop@ while (true) {
                                if (writerFinished == 1) {
                                    break@loop
                                } else if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                    //non empty queue -> read one row
                                    for (variable in 0 until variables.size) {
                                        iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                                    }
                                    res = 0
                                    ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
                                    break@loop
                                }
                                yield()
                            }
                            /*return*/res
                        }
                        iterator.close = {
                            readerFinished[p] = 1
                            var tmp = 0
                            for (p in 0 until ParallelBase.k) {
                                if (readerFinished[p] == 1) {
                                    tmp++
                                }
                            }
                            if (tmp == ParallelBase.k) {
                                runBlocking {
                                    job!!.cancelAndJoin()
                                }
                            }
                        }
                        iterators!![p] = IteratorBundle(iterator)
                    }
                    query.partitionsIterators[uuid] = iterators!!
                    query.partitionsJobs[uuid] = job!!
                }
            }
                return iterators!![parent.data[partitionVariable]!!]
        }
    }
}
