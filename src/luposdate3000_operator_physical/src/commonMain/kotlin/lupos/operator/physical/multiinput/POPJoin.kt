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
package lupos.operator.physical.multiinput

import lupos.operator.base.iterator.ColumnIteratorMultiIterator
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorRepeatIterator
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import lupos.shared.TooManyIntermediateResultsException
import lupos.shared.operator.iterator.ColumnIterator
internal object POPJoin {
    fun crossProduct(dataO0: Array<MutableList<DictionaryValueType>>, dataO1: Array<MutableList<DictionaryValueType>>, dataJ: DictionaryValueTypeArray, outO0: List<ColumnIteratorChildIterator?>, outO1: List<ColumnIteratorChildIterator?>, outJ: List<ColumnIteratorChildIterator?>, countA: Int, countB: Int) {
        /*result ordered by first child*/
        val count = countA * countB
        if (count < 0 || count < countA || count < countB) {
            throw TooManyIntermediateResultsException()
        }
        if (SanityCheck.enabled) { if (!(count > 0)) { throw Exception("SanityCheck failed") } }
        when {
            count == 1 -> {
                for (columnIndex in outO0.indices) {
                    outO0[columnIndex]?.addChildColumnIteratorValue(dataO0[columnIndex][0])
                }
                for (columnIndex in outO1.indices) {
                    outO1[columnIndex]?.addChildColumnIteratorValue(dataO1[columnIndex][0])
                }
                for (columnIndex in outJ.indices) {
                    outJ[columnIndex]?.addChildColumnIteratorValue(dataJ[columnIndex])
                }
            }
            count < 100 -> {
                for (columnIndex in outO0.indices) {
                    val d = DictionaryValueTypeArray(count)
                    for (i in 0 until countA) {
                        val x = dataO0[columnIndex][i]
                        for (j in 0 until countB) {
                            d[j * countA + i] = x
                        }
                    }
                    outO0[columnIndex]?.addChild(ColumnIteratorMultiValue(d, count))
                }
                for (columnIndex in outO1.indices) {
                    val d = DictionaryValueTypeArray(count)
                    for (j in 0 until countB) {
                        val x = dataO1[columnIndex][j]
                        for (i in 0 until countA) {
                            d[j * countA + i] = x
                        }
                    }
                    outO1[columnIndex]?.addChild(ColumnIteratorMultiValue(d, count))
                }
                for (columnIndex in outJ.indices) {
                    outJ[columnIndex]?.addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
                }
            }
            else -> {
                for (columnIndex in outO0.indices) {
                    val iterators = mutableListOf<ColumnIterator>()
                    for (i in 0 until countA) {
                        iterators.add(ColumnIteratorRepeatValue(countB, dataO0[columnIndex][i]))
                    }
                    if (iterators.size == 1) {
                        outO0[columnIndex]?.addChild(iterators[0])
                    } else {
                        outO0[columnIndex]?.addChild(ColumnIteratorMultiIterator(iterators))
                    }
                }
                for (columnIndex in outO1.indices) {
                    val d = DictionaryValueTypeArray(countB) { dataO1[columnIndex][it] }
                    if (countA == 1) {
                        outO1[columnIndex]?.addChild(ColumnIteratorMultiValue(d, countB))
                    } else {
                        outO1[columnIndex]?.addChild(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d, countB)))
                    }
                }
                for (columnIndex in outJ.indices) {
                    outJ[columnIndex]?.addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
                }
            }
        }
    }

    fun crossProduct(dataO0: Array<DictionaryValueTypeArray>, dataO1: Array<DictionaryValueTypeArray>, dataJ: DictionaryValueTypeArray, outO0: List<ColumnIteratorChildIterator?>, outO1: List<ColumnIteratorChildIterator?>, outJ: List<ColumnIteratorChildIterator?>, countA: Int, countB: Int) {
        /*result ordered by first child*/
        val count = countA * countB
        if (count < 0 || count < countA || count < countB) {
            throw TooManyIntermediateResultsException()
        }

        if (SanityCheck.enabled) { if (!(count > 0)) { throw Exception("SanityCheck failed") } }
        when {
            count == 1 -> {
                for (columnIndex in outO0.indices) {
                    outO0[columnIndex]?.addChildColumnIteratorValue(dataO0[columnIndex][0])
                }
                for (columnIndex in outO1.indices) {
                    outO1[columnIndex]?.addChildColumnIteratorValue(dataO1[columnIndex][0])
                }
                for (columnIndex in outJ.indices) {
                    outJ[columnIndex]?.addChildColumnIteratorValue(dataJ[columnIndex])
                }
            }
            count < 100 -> {
                for (columnIndex in outO0.indices) {
                    val d = DictionaryValueTypeArray(count)
                    for (i in 0 until countA) {
                        val x = dataO0[columnIndex][i]
                        for (j in 0 until countB) {
                            d[j * countA + i] = x
                        }
                    }
                    outO0[columnIndex]?.addChild(ColumnIteratorMultiValue(d, count))
                }
                for (columnIndex in outO1.indices) {
                    val d = DictionaryValueTypeArray(count)
                    for (j in 0 until countB) {
                        val x = dataO1[columnIndex][j]
                        for (i in 0 until countA) {
                            d[j * countA + i] = x
                        }
                    }
                    outO1[columnIndex]?.addChild(ColumnIteratorMultiValue(d, count))
                }
                for (columnIndex in outJ.indices) {
                    outJ[columnIndex]?.addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
                }
            }
            else -> {
                for (columnIndex in outO0.indices) {
                    val iterators = mutableListOf<ColumnIterator>()
                    for (i in 0 until countA) {
                        iterators.add(ColumnIteratorRepeatValue(countB, dataO0[columnIndex][i]))
                    }
                    if (iterators.size == 1) {
                        outO0[columnIndex]?.addChild(iterators[0])
                    } else {
                        outO0[columnIndex]?.addChild(ColumnIteratorMultiIterator(iterators))
                    }
                }
                for (columnIndex in outO1.indices) {
                    val d = DictionaryValueTypeArray(countB) { dataO1[columnIndex][it] }
                    if (countA == 1) {
                        outO1[columnIndex]?.addChild(ColumnIteratorMultiValue(d, countB))
                    } else {
                        outO1[columnIndex]?.addChild(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d, countB)))
                    }
                }
                for (columnIndex in outJ.indices) {
                    outJ[columnIndex]?.addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
                }
            }
        }
    }
}
