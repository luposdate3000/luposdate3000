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
package lupos.operator.physical.singleinput

import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.physical.MapKey
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.GroupByDuplicateColumnException
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle

public object EvalGroup {
    private fun getAggregations(node: IOPBase): MutableList<AOPAggregationBase> {
        val res = mutableListOf<AOPAggregationBase>()
        for (n in node.getChildren()) {
            res.addAll(getAggregations(n))
        }
        if (node is AOPAggregationBase) {
            res.add(node)
        }
        return res
    }

    public operator fun invoke(
        child: IteratorBundle,
        bindings: MutableList<Pair<String, AOPBase>>,
        projectedVariables: List<String>,
        keyColumnNames: Array<String>,
        sortPriority: MutableList<SortHelper>,
        dict: IDictionary,
        localVariables: List<String>,
    ): IteratorBundle {
        val buffer = ByteArrayWrapper()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        if (keyColumnNames.size != keyColumnNames.distinct().size) {
            throw GroupByDuplicateColumnException()
        }
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
        val tmpSortPriority = sortPriority.map { it.variableName }
        var canUseSortedInput = true
        if ((!localVariables.containsAll(keyColumnNames.toMutableList())) || (tmpSortPriority.size < keyColumnNames.size)) {
            canUseSortedInput = false
        } else {
            for (element in keyColumnNames) {
                if (!tmpSortPriority.contains(element)) {
                    canUseSortedInput = false
                    break
                }
            }
        }
        if (bindings.size == 1 && bindings.toList().first().second is AOPAggregationCOUNT &&
// simplicity ->
            keyColumnNames.size == 1 && valueColumnNames.size == 0
// <- simplicity
        ) {
            val iterator = keyColumns[0]
            val map = mutableMapOf<DictionaryValueType, Int>()
            while (true) {
                val value = iterator.next()
                if (value == DictionaryValueHelper.nullValue) {
                    iterator.close()
                    break
                }
                val v = map[value]
                if (v == null) {
                    map[value] = 1
                } else {
                    map[value] = v + 1
                }
            }
            val arrK = DictionaryValueTypeArray(map.size)
            val arrV = DictionaryValueTypeArray(map.size)
            var i = 0
            for ((k, v) in map) {
                arrK[i] = k
                DictionaryHelper.integerToByteArray(buffer, BigInteger(v))
                arrV[i] = dict.createValue(buffer)
                i++
            }
            outMap[keyColumnNames[0]] = ColumnIteratorMultiValue(arrK, arrK.size)
            outMap[bindings.toList().first().first] = ColumnIteratorMultiValue(arrV, arrV.size)
        } else {
            val map = mutableMapOf<MapKey, POPGroup_Row>()
            loop@ while (true) {
                val currentKey = DictionaryValueTypeArray(keyColumnNames.size) { DictionaryValueHelper.undefValue }
                for (columnIndex in keyColumnNames.indices) {
                    val value = keyColumns[columnIndex].next()
                    if (value == DictionaryValueHelper.nullValue) {
                        for (element in keyColumns) {
                            element.close()
                        }
                        for (element in valueColumns) {
                            element.close()
                        }
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalGroup.kt:134"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                        break@loop
                    }
                    currentKey[columnIndex] = value
                }
                val key = MapKey(currentKey)
                var localRow = map[key]
                if (localRow == null) {
                    val localMap = mutableMapOf<String, ColumnIterator>()
                    val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                    for (columnIndex in keyColumnNames.indices) {
                        val tmp = ColumnIteratorQueueEmpty()
                        tmp.tmp = currentKey[columnIndex]
                        localMap[keyColumnNames[columnIndex]] = tmp
                    }
                    for (columnIndex in 0 until valueColumnNames.size) {
                        localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
                    }
                    val row = IteratorBundle(localMap)
                    val localAggregations = Array(aggregations.size) {
                        val tmp = aggregations[it].createIterator(row)
                        localMap["#" + aggregations[it].uuid] = tmp
                        tmp
                    }
                    localRow = POPGroup_Row(row, localAggregations, localColumns)
                    map[key] = localRow
                }
                for (columnIndex in 0 until valueColumnNames.size) {
                    localRow.columns[columnIndex].tmp = valueColumns[columnIndex].next()
                }
                for (aggregate in localRow.aggregates) {
                    aggregate.evaluate()
                }
            }
            if (map.isEmpty()) {
                for (v in keyColumnNames) {
                    outMap[v] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                }
                for ((first) in bindings) {
                    outMap[first] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                }
            } else {
                val outKeys = Array(keyColumnNames.size) { mutableListOf<DictionaryValueType>() }
                val outValues = Array(bindings.size) { mutableListOf<DictionaryValueType>() }
                for ((k, v) in map) {
                    for (columnIndex in keyColumnNames.indices) {
                        outKeys[columnIndex].add(k.data[columnIndex])
                    }
                    for (columnIndex in 0 until bindings.size) {
                        outValues[columnIndex].add(bindings[columnIndex].second.evaluateID(v.iterators)())
                    }
                }
                for (columnIndex in keyColumnNames.indices) {
                    outMap[keyColumnNames[columnIndex]] = ColumnIteratorMultiValue(outKeys[columnIndex])
                }
                for (columnIndex in 0 until bindings.size) {
                    outMap[bindings[columnIndex].first] = ColumnIteratorMultiValue(outValues[columnIndex])
                }
            }
        }
        return IteratorBundle(outMap)
    }
}
