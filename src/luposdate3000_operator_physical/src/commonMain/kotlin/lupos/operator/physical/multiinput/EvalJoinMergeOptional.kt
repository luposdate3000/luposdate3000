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
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.inline.ColumnIteratorChildIteratorExt
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalJoinMergeOptional {
    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun sameElements(key: DictionaryValueTypeArray, keyCopy: DictionaryValueTypeArray, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MutableList<DictionaryValueType>>): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:32"/*SOURCE_FILE_END*/ }, { keyCopy[0] != DictionaryValueHelper.nullValue })
        for (i in 0 until columnsINJ.size) {
            if (key[i] != keyCopy[i]) {
                /* this is an optional element without a match */
                for (j in 0 until columnsINO.size) {
                    data[j].add(DictionaryValueHelper.undefValue)
                }
                return 1
            }
        }
        var count = 0
        /* at least 1 matching row */
        loop@ while (true) {
            count++
            for (i in 0 until columnsINO.size) {
                data[i].add(columnsINO[i].next())
            }
            for (i in 0 until columnsINJ.size) {
                key[i] = columnsINJ[i].next()
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:51"/*SOURCE_FILE_END*/ }, { key[i] != DictionaryValueHelper.undefValue })
            }
            for (i in 0 until columnsINJ.size) {
                if (key[i] != keyCopy[i]) {
                    break@loop
                }
            }
        }
        return count
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun findNextKey(key: Array<DictionaryValueTypeArray>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
        if (key[0][0] != DictionaryValueHelper.nullValue && key[1][0] != DictionaryValueHelper.nullValue) {
            loop@ while (true) {
                for (i in 0 until columnsINJ[0].size) {
                    val a = key[0][i]
                    val b = key[1][i]
                    if (a > b) {
                        for (j in 0 until columnsINO[1].size) {
                            columnsINO[1][j].next()
                        }
                        for (j in 0 until columnsINJ[1].size) {
                            key[1][j] = columnsINJ[1][j].next()
                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:75"/*SOURCE_FILE_END*/ }, { key[1][j] != DictionaryValueHelper.undefValue })
                            if (key[1][j] == DictionaryValueHelper.nullValue) {
                                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:77"/*SOURCE_FILE_END*/ }, { j == 0 })
                                break@loop
                            }
                        }
                        continue@loop
                    }
                }
                break@loop
            }
        }
        return key[0][0] == DictionaryValueHelper.nullValue
    }

    public operator fun invoke(
        query: IQuery,
        child: Array<IteratorBundle>,
        projectedVariables: List<String>,
    ): IteratorBundle {
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() // J,O0,O1,J-ohne-Map
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(child[1].columns.keys.toList())
        for (name in child[0].columns.keys.toList()) {
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:126"/*SOURCE_FILE_END*/ }, { columnsINJ[0].size > 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeOptional.kt:127"/*SOURCE_FILE_END*/ }, { columnsINJ[0].size == columnsINJ[1].size })
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
                val iterator = ColumnIteratorChildIteratorEmpty(query)
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
                val iterator = object : ColumnIteratorChildIterator(query) {
                    override /*suspend*/ fun close() {
                        _close()
                    }

                    override /*suspend*/ fun next(): DictionaryValueType {
                        return ColumnIteratorChildIteratorExt.nextHelper(
                            query,
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
