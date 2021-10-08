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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.ESortTypeExt
import lupos.shared.Parallel
import lupos.shared.ParallelCondition
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator

public object EvalChangePartitionOrderedByIntId {
    public operator fun invoke(): IteratorBundle {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalChangePartitionOrderedByIntId.kt:30"/*SOURCE_FILE_END*/ }, { partitionCountTo < partitionCountFrom })
        val partitionCountSrc = partitionCountFrom / partitionCountTo
        var error: Throwable? = null
        val variables = getProvidedVariableNames()
        val variables0 = children[0].getProvidedVariableNames()
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalChangePartitionOrderedByIntId.kt:35"/*SOURCE_FILE_END*/ }, { variables0.containsAll(variables) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalChangePartitionOrderedByIntId.kt:36"/*SOURCE_FILE_END*/ }, { variables.containsAll(variables0) })
        // the variable may be eliminated directly after using it in the join            SanityCheck.check({/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalChangePartitionOrderedByIntId.kt:37"/*SOURCE_FILE_END*/},{ variables.contains(partitionVariable) })
        var queue_size = query.getInstance().queue_size
        var elementsPerRing = queue_size * variables.size
        var buffersize = elementsPerRing * partitionCountSrc
        while (buffersize <= 0 || elementsPerRing <= 0) {
            queue_size = queue_size / 2
            elementsPerRing = queue_size * variables.size
            buffersize = elementsPerRing * partitionCountSrc
        }
        val ringbuffer = DictionaryValueTypeArray(buffersize) // only modified by writer, reader just modifies its pointer
        val ringbufferStart = IntArray(partitionCountSrc) { it * elementsPerRing } // constant
        val ringbufferReadHead = IntArray(partitionCountSrc) { 0 } // owned by read-thread - no locking required
        val ringbufferWriteHead = IntArray(partitionCountSrc) { 0 } // owned by write thread - no locking required
        val ringbufferWriterContinuation = Array(partitionCountSrc) { Parallel.createCondition() }
        val ringbufferReaderContinuation: ParallelCondition = Parallel.createCondition()
        val writerFinished = IntArray(partitionCountSrc) { 0 } // writer changes to 1 if finished
        var readerFinished = 0
        for (p1 in 0 until partitionCountSrc) {
            val pChild = p1 * partitionCountTo + parent.data[partitionVariable]!!
            Parallel.launch {
                try {
                    val childEval2: IteratorBundle?
                    childEval2 = children[0].evaluate(Partition(parent, partitionVariable, pChild, partitionCountFrom))
                    if (childEval2.hasColumnMode()) {
                        val child = childEval2.columns
                        if (variables.size == 1) {
                            val childIterator = child[variables[0]]!!
                            loop@ while (readerFinished == 0) {
                                val t = (ringbufferWriteHead[p1] + 1) % elementsPerRing
                                while (ringbufferReadHead[p1] == t && readerFinished == 0) {
                                    ringbufferReaderContinuation.signal()
                                    ringbufferWriterContinuation[p1].waitCondition { ringbufferReadHead[p1] == t && readerFinished == 0 }
                                }
                                if (readerFinished != 0) {
                                    childIterator.close()
                                    break@loop
                                }
                                val tmp = childIterator.next()
                                if (tmp == DictionaryValueHelper.nullValue) {
                                    break@loop
                                } else {
                                    ringbuffer[ringbufferWriteHead[p1] + ringbufferStart[p1]] = tmp
                                    ringbufferWriteHead[p1] = (ringbufferWriteHead[p1] + 1) % elementsPerRing
                                    ringbufferReaderContinuation.signal()
                                }
                            }
                        } else {
                            val variableMapping = Array(variables.size) { child[variables[it]]!! }
                            loop@ while (readerFinished == 0) {
                                val t = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
                                while (ringbufferReadHead[p1] == t && readerFinished == 0) {
                                    ringbufferReaderContinuation.signal()
                                    ringbufferWriterContinuation[p1].waitCondition { ringbufferReadHead[p1] == t && readerFinished == 0 }
                                }
                                if (readerFinished != 0) {
                                    for (variable in 0 until variables.size) {
                                        variableMapping[variable].close()
                                    }
                                    break@loop
                                }
                                val tmp = variableMapping[0].next()
                                if (tmp == DictionaryValueHelper.nullValue) {
                                    for (variable in 0 until variables.size) {
                                        variableMapping[variable].close()
                                    }
                                    break@loop
                                } else {
                                    ringbuffer[ringbufferWriteHead[p1] + ringbufferStart[p1]] = tmp
                                    for (variableIdx in 1 until variables.size) {
                                        try {
                                            ringbuffer[ringbufferWriteHead[p1] + variableIdx + ringbufferStart[p1]] = variableMapping[variableIdx].next()
                                        } catch (e: Throwable) {
                                            e.printStackTrace()
                                            for (variableIdx2 in 0 until variables.size) {
                                                variableMapping[variableIdx2].close()
                                            }
                                            break@loop
                                        }
                                    }
                                    ringbufferWriteHead[p1] = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
                                    ringbufferReaderContinuation.signal()
                                }
                            }
                        }
                    } else {
                        val child = childEval2.rows
                        val variableMapping = IntArray(variables.size)
                        for (variable in variables.indices) {
                            for (variable2 in variables.indices) {
                                if (variables[variable2] == child.columns[variable]) {
                                    variableMapping[variable] = variable2
                                    break
                                }
                            }
                        }
                        loop@ while (readerFinished == 0) {
                            val t = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
                            while (ringbufferReadHead[p1] == t && readerFinished == 0) {
                                ringbufferReaderContinuation.signal()
                                ringbufferWriterContinuation[p1].waitCondition { ringbufferReadHead[p1] == t && readerFinished == 0 }
                            }
                            if (readerFinished != 0) {
                                child.close()
                                break@loop
                            }
                            val tmp = child.next()
                            if (tmp == -1) {
                                break@loop
                            } else {
                                for (variable in variables.indices) {
                                    ringbuffer[ringbufferWriteHead[p1] + variableMapping[variable] + ringbufferStart[p1]] = child.buf[tmp + variable]
                                }
                                ringbufferWriteHead[p1] = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
                                ringbufferReaderContinuation.signal()
                            }
                        }
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    error = e
                }
                writerFinished[p1] = 1
                ringbufferReaderContinuation.signal()
            }
        }
        val sortColumns = IntArray(mySortPriority.size) { variables.indexOf(mySortPriority[it].variableName) }
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalChangePartitionOrderedByIntId.kt:164"/*SOURCE_FILE_END*/ },
            {
                for (x in sortColumns.indices) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalChangePartitionOrderedByIntId.kt:167"/*SOURCE_FILE_END*/ }, { sortColumns[x] >= 0 })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalChangePartitionOrderedByIntId.kt:168"/*SOURCE_FILE_END*/ }, { mySortPriority[x].sortType == ESortTypeExt.FAST })
                }
            }
        )
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        iterator.next = {
            var res = -1
            loop@ while (true) {
                var partitionToUse = -1
                loop2@ for (p in 0 until partitionCountSrc) {
                    if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                        partitionToUse = if (partitionToUse < 0) {
                            p
                        } else {
                            for (sp in sortColumns) {
                                val valThis = ringbuffer[ringbufferReadHead[p] + sp + ringbufferStart[p]]
                                val valSmallest = ringbuffer[ringbufferReadHead[partitionToUse] + sp + ringbufferStart[partitionToUse]]
                                if (valThis > valSmallest) {
                                    continue@loop2
                                }
                            }
                            p
                        }
                    } else if (writerFinished[p] == 0) {
                        ringbufferWriterContinuation[p].signal()
                        partitionToUse = -1
                        break@loop2
                    }
                }
                if (partitionToUse >= 0) {
                    for (variable in variables.indices) {
                        iterator.buf[variable] = (ringbuffer[ringbufferReadHead[partitionToUse] + variable + ringbufferStart[partitionToUse]])
                    }
                    res = 0
                    ringbufferReadHead[partitionToUse] = (ringbufferReadHead[partitionToUse] + variables.size) % elementsPerRing
                    ringbufferWriterContinuation[partitionToUse].signal()
                    break@loop
                }
                var finishedWriters = 0
                ringbufferReaderContinuation.waitCondition {
                    var flag = true
                    for (p in 0 until partitionCountSrc) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            flag = false
                            break
                        } else if (writerFinished[p] != 0) {
                            finishedWriters++
                        }
                    }
                    (flag && finishedWriters < partitionCountSrc)
                }
                if (finishedWriters == partitionCountSrc) {
                    break@loop
                }
            }
            if (error != null) {
                iterator.close()
                throw error!!
            }
            res
        }
        iterator.close = {
            readerFinished = 1
            for (p in 0 until partitionCountSrc) {
                ringbufferWriterContinuation[p].signal()
            }
        }
        return IteratorBundle(iterator)
    }
}
