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

import lupos.operator.base.iterator.ColumnIteratorChildIteratorEmpty
import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import lupos.shared.inline.ColumnIteratorChildIteratorExt
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
public object EvalJoinMergeOptional {
    public operator fun invoke(): IteratorBundle {
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:39"/*SOURCE_FILE_END*/ },
            {
                for (v in children[0].getProvidedVariableNames()) {
                    getPartitionCount(v)
                }
                for (v in children[1].getProvidedVariableNames()) {
                    getPartitionCount(v)
                }
            }
        )
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:49"/*SOURCE_FILE_END*/ }, { optional })
// setup columns
        val child = Array(2) { children[it].evaluate(parent) }
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() // J,O0,O1,J-ohne-Map
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    outIterators.add(Pair(name, 0))
                    for (i in 0 until 2) {
                        columnsINJ[i].add(0, child[i].columns[name]!!)
                    }
                } else {
                    for (i in 0 until 2) {
                        columnsINJ[i].add(child[i].columns[name]!!)
                    }
                }
                tmp.remove(name)
            } else {
                outIterators.add(Pair(name, 1))
                columnsINO[0].add(child[0].columns[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINO[1].add(child[1].columns[name]!!)
        }
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:83"/*SOURCE_FILE_END*/ }, { columnsINJ[0].size > 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:84"/*SOURCE_FILE_END*/ }, { columnsINJ[0].size == columnsINJ[1].size })
        val emptyColumnsWithJoin = outIterators.size == 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val key = Array(2) { i -> DictionaryValueTypeArray(columnsINJ[i].size) { columnsINJ[i][it].next() } }
        var done = findNextKey(key, columnsINJ, columnsINO)
        if (done) {
            for (closeIndex2 in 0 until 2) {
                for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                    columnsINJ[closeIndex2][closeIndex].close()
                }
                for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                    columnsINO[closeIndex2][closeIndex].close()
                }
            }
            for ((first, second) in outIterators) {
                val iterator = ColumnIteratorChildIteratorEmpty()
                outIteratorsAllocated.add(iterator)
                when (second) {
                    0 -> {
                        columnsOUTJ.add(iterator)
                        outMap[first] = iterator
                    }
                    1 -> {
                        columnsOUT[0].add(iterator)
                        outMap[first] = iterator
                    }
                    2 -> {
                        columnsOUT[1].add(iterator)
                        outMap[first] = iterator
                    }
                    3 -> {
                        columnsOUTJ.add(iterator)
                    }
                }
            }
        } else {
            val keyCopy = DictionaryValueTypeArray(columnsINJ[0].size) { key[0][it] }
            for ((first, second) in outIterators) {
                val iterator = object : ColumnIteratorChildIterator() {
                    override /*suspend*/ fun close() {
                        _close()
                    }

                    override /*suspend*/ fun next(): DictionaryValueType {
                        return ColumnIteratorChildIteratorExt.nextHelper(
                            this,
                            {
                                for (i in 0 until columnsINJ[0].size) {
                                    keyCopy[i] = key[0][i]
                                }
                                val data = Array(2) { Array(columnsINO[it].size) { mutableListOf<DictionaryValueType>() } }
                                val countA = sameElements(key[0], keyCopy, columnsINJ[0], columnsINO[0], data[0])
                                val countB = sameElements(key[1], keyCopy, columnsINJ[1], columnsINO[1], data[1])
                                done = findNextKey(key, columnsINJ, columnsINO)
                                if (done) {
                                    for (iterator2 in outIteratorsAllocated) {
                                        iterator2.closeOnNoMoreElements()
                                    }
                                    for (closeIndex2 in 0 until 2) {
                                        for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                                            columnsINJ[closeIndex2][closeIndex].close()
                                        }
                                        for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                                            columnsINO[closeIndex2][closeIndex].close()
                                        }
                                    }
                                }
                                POPJoin.crossProduct(data[0], data[1], keyCopy, columnsOUT[0], columnsOUT[1], columnsOUTJ, countA, countB)
                            },
                            { _close() }
                        )
                    }
                }
                outIteratorsAllocated.add(iterator)
                when (second) {
                    0 -> {
                        columnsOUTJ.add(iterator)
                        outMap[first] = iterator
                    }
                    1 -> {
                        columnsOUT[0].add(iterator)
                        outMap[first] = iterator
                    }
                    2 -> {
                        columnsOUT[1].add(iterator)
                        outMap[first] = iterator
                    }
                    3 -> {
                        columnsOUTJ.add(iterator)
                    }
                }
            }
        }
        var res = IteratorBundle(outMap)
        if (emptyColumnsWithJoin) {
            res = object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    return columnsOUTJ[0].next() != DictionaryValueHelper.nullValue
                }

                override /*suspend*/ fun hasNext2Close() {
                    for (closeIndex2 in 0 until 2) {
                        for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                            columnsINJ[closeIndex2][closeIndex].close()
                        }
                        for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                            columnsINO[closeIndex2][closeIndex].close()
                        }
                    }
                }
            }
        }
        return res
    }
}
