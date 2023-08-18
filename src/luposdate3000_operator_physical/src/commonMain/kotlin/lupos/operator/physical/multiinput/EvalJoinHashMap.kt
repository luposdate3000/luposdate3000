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

import lupos.operator.base.multiinput.LOPJoin_Helper
import lupos.operator.physical.MapKey
import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.inline.ColumnIteratorChildIteratorExt
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalJoinHashMap {
    public operator fun invoke(
        query: IQuery,
        childA: IteratorBundle,
        childB: IteratorBundle,
        optional: Boolean,
        projectedVariables: List<String>,
        timeoutInMs: Long,
    ): IteratorBundle {
        val startTime = DateHelperRelative.markNow()
// --- obtain child columns
        val columns = LOPJoin_Helper.getColumns(childA.columns.keys.toList(), childB.columns.keys.toList())
        val columnsINAO = mutableListOf<ColumnIterator>() // only in childA
        val columnsINBO = mutableListOf<ColumnIterator>() // only in childB
        val columnsINAJ = mutableListOf<ColumnIterator>() // join columnA
        val columnsINBJ = mutableListOf<ColumnIterator>() // join columnB
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() } // only in one of the childs
        val outJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() // J,O0,O1,J-but-not-outmap
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        var res: IteratorBundle?
        val tmp = mutableListOf<String>()
        tmp.addAll(childB.columns.keys)
        for (name in childA.columns.keys) {
            if (tmp.contains(name)) {
                columnsINAJ.add(childA.columns[name]!!)
                columnsINBJ.add( childB.columns[name]!!)
                outIterators.add( Pair(name, 0))
                tmp.remove(name)
            } else {
                outIterators.add(Pair(name, 1))
                columnsINAO.add(childA.columns[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINBO.add(childB.columns[name]!!)
        }
        val emptyColumnsWithJoin = outIterators.size == 0 && columnsINAJ.size != 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val mapWithoutUndef = mutableMapOf<MapKey, POPJoinHashMap_Row>()
        val mapWithUndef = mutableMapOf<MapKey, POPJoinHashMap_Row>()
        var currentKey: DictionaryValueTypeArray? = null
        var nextKey: DictionaryValueTypeArray?
        var map: MutableMap<MapKey, POPJoinHashMap_Row> = mapWithUndef
        var nextMap: MutableMap<MapKey, POPJoinHashMap_Row>
        var key: MapKey
        var oldArr: POPJoinHashMap_Row?
        var count: Int
        var countA: Int
        var countB: Int
// --- insert second child into hash table
        while (true) {
            count = if (currentKey != null) {
                1
            } else {
                0
            }
            loopB@ while (true) {
                if (!(timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs)) {
                    TODO("timeout")
                }
                nextKey = DictionaryValueTypeArray(columnsINBJ.size) { DictionaryValueHelper.undefValue }
                nextMap = mapWithoutUndef
                for (columnIndex in 0 until columnsINBJ.size) {
                    val value = columnsINBJ[columnIndex].next()
                    if (value == DictionaryValueHelper.nullValue) {
                        nextKey = null
                        break@loopB
                    }
                    nextKey!![columnIndex] = value
                    if (value == DictionaryValueHelper.undefValue) {
                        nextMap = mapWithUndef
                    }
                }
                if (currentKey == null) {
                    currentKey = nextKey
                    map = nextMap
                } else if (nextKey == null || MapKey(nextKey) != MapKey(currentKey)) {
                    break
                }
                count++
            }
            if (currentKey == null) {
                break
            }
            key = MapKey(currentKey)
            oldArr = map[key]
            if (oldArr == null) {
                oldArr = POPJoinHashMap_Row(columnsINBO.size)
                map[key] = oldArr
            }
            oldArr.count += count
            for (columnIndex in 0 until columnsINBO.size) {
// TODO dont use kotlin lists here, use pages instead
                for (j in 0 until count) {
                    oldArr.columns[columnIndex].add(columnsINBO[columnIndex].next())
                }
            }
            currentKey = nextKey
            map = nextMap
        }
        for (closeIndex in 0 until columnsINBJ.size) {
            columnsINBJ[closeIndex].close()
        }
        for (closeIndex in 0 until columnsINBO.size) {
            columnsINBO[closeIndex].close()
        }
// --- iterate first child
// --- calculate next "cartesian product"
        for ((first, second) in outIterators) {
            val iterator = object : ColumnIteratorChildIterator(query) {
                override /*suspend*/ fun close() {
                    __close()
                }

                /*suspend*/ @Suppress("NOTHING_TO_INLINE")
                fun __close() {
                    if (label != 0) {
                        _close()
                        for (iterator2 in outIteratorsAllocated) {
                            iterator2.closeOnNoMoreElements()
                        }
                        for (closeIndex in 0 until columnsINAJ.size) {
                            columnsINAJ[closeIndex].close()
                        }
                        for (closeIndex in 0 until columnsINAO.size) {
                            columnsINAO[closeIndex].close()
                        }
                    }
                }

                override /*suspend*/ fun next(): DictionaryValueType {
                    if (!(timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs)) {
                        TODO("timeout")
                    }
                    return ColumnIteratorChildIteratorExt.nextHelper(
                        query,
                        this,
                        {
                            var done = false
                            while (!done) {
                                countA = if (currentKey == null) {
                                    0
                                } else {
                                    1
                                }
                                loopA@ while (true) {
                                    nextKey = DictionaryValueTypeArray(columnsINAJ.size) { DictionaryValueHelper.undefValue }
                                    nextMap = mapWithoutUndef
                                    for (columnIndex in 0 until columnsINAJ.size) {
                                        val value = columnsINAJ[columnIndex].next()
                                        if (value == DictionaryValueHelper.nullValue) {
                                            nextKey = null
                                            break@loopA
                                        }
                                        nextKey!![columnIndex] = value
                                        if (value == DictionaryValueHelper.undefValue) {
                                            nextMap = mapWithUndef
                                        }
                                    }
                                    if (currentKey == null) {
                                        map = nextMap
                                        currentKey = nextKey
                                    } else if (nextKey == null || MapKey(nextKey!!) != MapKey(currentKey!!)) {
                                        break
                                    }
                                    countA++
                                }
                                if (currentKey == null) {
                                    done = true
                                    __close()
                                } else {
                                    key = MapKey(currentKey!!)
                                    val others = mutableListOf<Pair<MapKey, POPJoinHashMap_Row>>()
// search for_join-partners
                                    if (map == mapWithoutUndef) {
                                        val tmp2 = mapWithoutUndef[key]
                                        if (tmp2 != null) {
                                            others.add(Pair(key, tmp2))
                                        }
                                        for ((k, v) in mapWithUndef) {
                                            if (k.equalsFuzzy(key)) {
                                                others.add(Pair(k, v))
                                            }
                                        }
                                    } else {
                                        for ((k, v) in mapWithoutUndef) {
                                            if (k.equalsFuzzy(key)) {
                                                others.add(Pair(k, v))
                                            }
                                        }
                                        for ((k, v) in mapWithUndef) {
                                            if (k.equalsFuzzy(key)) {
                                                others.add(Pair(k, v))
                                            }
                                        }
                                    }
                                    val dataOA = Array(columnsINAO.size) { mutableListOf<DictionaryValueType>() }
                                    for (columnIndex in 0 until columnsINAO.size) {
                                        for (i in 0 until countA) {
                                            val tmp2 = columnsINAO[columnIndex].next()
                                            dataOA[columnIndex].add(tmp2)
                                        }
                                    }
                                    if (others.size == 0) {
                                        if (optional) {
// optional clause without match
                                            done = true
                                            countB = 1
                                            val dataJ = DictionaryValueTypeArray(outJ.size) { currentKey!![it] }
                                            POPJoin.crossProduct(dataOA, Array(outO[1].size) { mutableListOf(DictionaryValueHelper.undefValue) }, dataJ, outO[0], outO[1], outJ, countA, countB)
                                        }
                                    } else {
                                        done = true
                                        for (otherIndex in 0 until others.size) {
                                            countB = others[otherIndex].second.count
                                            val dataJ = DictionaryValueTypeArray(outJ.size) {
                                                val res2: DictionaryValueType = if (currentKey!![it] != DictionaryValueHelper.undefValue) {
                                                    currentKey!![it]
                                                } else {
                                                    others[otherIndex].first.data[it]
                                                }
                                                res2
                                            }
                                            POPJoin.crossProduct(dataOA, others[otherIndex].second.columns, dataJ, outO[0], outO[1], outJ, countA, countB)
                                        }
                                    }
                                    map = nextMap
                                    currentKey = nextKey
                                }
                            }
                        },
                        { __close() },
                    )
                }
            }
            outIteratorsAllocated.add(iterator)
            when (second) {
                0 -> {
if(projectedVariables.contains(first)){
                    outMap[first] = iterator
}
                    outJ.add(iterator)
                }
                1 -> {
if(projectedVariables.contains(first)){
                    outMap[first] = iterator
}
                    outO[0].add(iterator)
                }
                2 -> {
if(projectedVariables.contains(first)){
                    outMap[first] = iterator
}
                    outO[1].add(iterator)
                }
                3 -> {
                    outJ.add(iterator)
                }
            }
        }
        res = if (outMap.isNotEmpty()) {
            IteratorBundle(outMap)
        } else {
            IteratorBundle(0)
        }
        if (emptyColumnsWithJoin) {
            res = object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    return outJ[0].next() != DictionaryValueHelper.nullValue
                }

                override /*suspend*/ fun hasNext2Close() {
                    outJ[0].close()
                    for (closeIndex in 0 until columnsINAJ.size) {
                        columnsINAJ[closeIndex].close()
                    }
                    for (closeIndex in 0 until columnsINAO.size) {
                        columnsINAO[closeIndex].close()
                    }
                }
            }
        }
        return res
    }
}
