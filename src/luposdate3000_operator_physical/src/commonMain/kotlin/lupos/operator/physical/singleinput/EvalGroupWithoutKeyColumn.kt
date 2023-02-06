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
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle

public object EvalGroupWithoutKeyColumn {
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
    ): IteratorBundle {
        val valueColumnNames = child.names
        val outMap = mutableMapOf<String, ColumnIterator>()
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
        val localMap = mutableMapOf<String, ColumnIterator>()
        val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
        for (columnIndex in 0 until valueColumnNames.size) {
            localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
        }
        val row = IteratorBundle(localMap)
        val localAggregations = Array(aggregations.size) {
            val tmp = aggregations[it].createIterator(row)
            localMap["#" + aggregations[it].uuid] = tmp
            tmp
        }
        val localRow = POPGroup_Row(row, localAggregations, localColumns)
        if (valueColumnNames.size == 0) {
            for (i in 0 until child.count()) {
                for (aggregate in localRow.aggregates) {
                    aggregate.evaluate()
                }
            }
        } else {
            loop2@ while (true) {
                for (columnIndex in 0 until valueColumnNames.size) {
                    val value = valueColumns[columnIndex].next()
                    if (value == DictionaryValueHelper.nullValue) {
if(SanityCheck.enabled){if(!( columnIndex == 0 )){throw Exception("SanityCheck failed")}}
                        for (closeIndex in 0 until valueColumnNames.size) {
                            valueColumns[closeIndex].close()
                        }
                        break@loop2
                    }
                    localRow.columns[columnIndex].tmp = value
                }
                for (aggregate in localRow.aggregates) {
                    aggregate.evaluate()
                }
            }
        }
        for (columnIndex in 0 until bindings.size) {
            val columnName = bindings[columnIndex].first
            if (projectedVariables.contains(columnName)) {
                val value = bindings[columnIndex].second.evaluateID(localRow.iterators)()
                outMap[columnName] = ColumnIteratorRepeatValue(1, value)
            }
        }
        return IteratorBundle(outMap)
    }
}
