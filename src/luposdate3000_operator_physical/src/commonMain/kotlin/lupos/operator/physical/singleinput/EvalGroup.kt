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

import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.physical.MapKey
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
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
        keyColumnNames: Array<String>,
        projection: Array<String>,
    ): IteratorBundle {
        val localVariables = child.names
        val outMap = mutableMapOf<String, ColumnIterator>()
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
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
                if (v in projection) {
                    outMap[v] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                }
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
                if (keyColumnNames[columnIndex] in projection) {
                    outMap[keyColumnNames[columnIndex]] = ColumnIteratorMultiValue(outKeys[columnIndex])
                }
            }
            for (columnIndex in 0 until bindings.size) {
                outMap[bindings[columnIndex].first] = ColumnIteratorMultiValue(outValues[columnIndex])
            }
        }
        return IteratorBundle(outMap)
    }
}
