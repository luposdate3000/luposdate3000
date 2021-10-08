/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.operator.physical.partition
import lupos.operator.base.PartitionHelper2
import lupos.operator.base.Query
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.Parallel
import lupos.shared.ParallelCondition
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator

public class EvalSplitPartition {
    public operator fun invoke(): IteratorBundle {
// throw BugException("POPSplitPartition","child is not launching, because coroutine is missing suspension point")
        if (partitionCount == 1) {
            // single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var iterators: Array<IteratorBundle>? = null
            val childPartition = Partition(parent, partitionVariable!!)
            val partitionHelper: PartitionHelper2?
            partitionHelper = (query as Query).getPartitionHelper(uuid)
            partitionHelper.lock.lock()
            if (partitionHelper.iterators != null) {
                iterators = partitionHelper.iterators!![childPartition]
            }
            var error: Throwable? = null
            if (iterators == null) {
                iterators = Array(partitionCount) { IteratorBundle(0) }
                val variables = getProvidedVariableNames()
                val variables0 = children[0].getProvidedVariableNames()
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalSplitPartition.kt:48"/*SOURCE_FILE_END*/ }, { variables0.containsAll(variables) })
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalSplitPartition.kt:49"/*SOURCE_FILE_END*/ }, { variables.containsAll(variables0) })
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalSplitPartition.kt:50"/*SOURCE_FILE_END*/ }, { variables.contains(partitionVariable) })
                var queue_size = query.getInstance().queue_size
                var elementsPerRing = queue_size * variables.size
                var buffersize = elementsPerRing * partitionCount
                while (buffersize <= 0 || elementsPerRing <= 0) {
                    queue_size = queue_size / 2
                    elementsPerRing = queue_size * variables.size
                    buffersize = elementsPerRing * partitionCount
                }
                val ringbuffer = DictionaryValueTypeArray(buffersize) // only modified by writer, reader just modifies its pointer
                val ringbufferStart = IntArray(partitionCount) { it * elementsPerRing } // constant
                val ringbufferReadHead = IntArray(partitionCount) { 0 } // owned by read-thread - no locking required
                val ringbufferWriteHead = IntArray(partitionCount) { 0 } // owned by write thread - no locking required
                val ringbufferReaderContinuation = Array(partitionCount) { Parallel.createCondition() }
                val ringbufferWriterContinuation: ParallelCondition = Parallel.createCondition()
                val readerFinished = IntArray(partitionCount) { 0 } // writer changes to 1 if finished
                var writerFinished = 0
                val job = Parallel.launch {
                    var child2: RowIterator? = null
                    try {
                        val child = children[0].evaluate(childPartition).rows
                        child2 = child
                        var hashVariableIndex = -1
                        val variableMapping = IntArray(variables.size)
                        for (variable in variables.indices) {
                            for (variable2 in variables.indices) {
                                if (child.columns[variable] == partitionVariable) {
                                    hashVariableIndex = variable
                                }
                                if (variables[variable2] == child.columns[variable]) {
                                    variableMapping[variable] = variable2
                                    break
                                }
                            }
                        }
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalSplitPartition.kt:85"/*SOURCE_FILE_END*/ }, { hashVariableIndex != -1 })
                        val cacheArr = IntArray(partitionCount) { it }
                        loop@ while (true) {
                            var tmp = child.next()
                            var readerFinishedCounter = 0
                            for (p in 0 until partitionCount) {
                                if (readerFinished[p] != 0) {
                                    readerFinishedCounter++
                                }
                            }
                            if (readerFinishedCounter == partitionCount) {
                                tmp = -1
                            }
                            if (tmp == -1) {
                                break@loop
                            } else {
                                val q = child.buf[tmp + hashVariableIndex]
                                var cacheSize: Int
                                if (q == DictionaryValueHelper.undefValue) {
                                    // broadcast undef to every partition
                                    SanityCheck.println { " attention may increase result count here - this is always ok, _if there is a join afterwards immediately - otherwise probably not" }
                                    cacheSize = partitionCount
                                    cacheArr[0] = 0
                                } else {
                                    cacheSize = 1
                                    cacheArr[0] = DictionaryValueHelper.toInt(q % partitionCount)
                                }
                                loopcache@ for (i in 0 until cacheSize) {
                                    val p = cacheArr[i]
                                    if (readerFinished[p] != 0) {
                                        continue@loopcache
                                    }
                                    val t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    while (ringbufferReadHead[p] == t && readerFinished[p] == 0) {
                                        ringbufferReaderContinuation[p].signal()
                                        ringbufferWriterContinuation.waitCondition { ringbufferReadHead[p] == t && readerFinished[p] == 0 }
                                    }
                                    if (readerFinished[p] != 0) {
                                        continue@loopcache
                                    }
                                    for (variable in variables.indices) {
                                        ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                                    }
                                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    ringbufferReaderContinuation[p].signal()
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                        error = e
                    }
                    child2?.close?.invoke()
                    writerFinished = 1
                    for (p in 0 until partitionCount) {
                        ringbufferReaderContinuation[p].signal()
                    }
                }
                for (p in 0 until partitionCount) {
                    val iterator = RowIterator()
                    iterator.columns = variables.toTypedArray()
                    iterator.buf = DictionaryValueTypeArray(variables.size)
                    iterator.next = {
                        var res = -1
                        loop@ while (true) {
                            if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                // non empty queue -> read one row
                                for (variable in variables.indices) {
                                    iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                                }
                                res = 0
                                ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
                                break@loop
                            } else if (writerFinished == 1) {
                                iterator.close()
                                if (error != null) {
                                    throw error!!
                                }
                                break@loop
                            }
                            ringbufferWriterContinuation.signal()
                            ringbufferReaderContinuation[p].waitCondition { ringbufferReadHead[p] == ringbufferWriteHead[p] && writerFinished == 0 }
                        }
                        res
                    }
                    iterator.close = {
                        readerFinished[p] = 1
                        ringbufferWriterContinuation.signal()
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
            partitionHelper.lock.unlock()
            return iterators[parent.data[partitionVariable]!!]
        }
    }
}
