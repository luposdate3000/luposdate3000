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

import lupos.operator.base.iterator.ColumnIteratorMerge
import lupos.operator.base.iterator.RowIteratorMerge
import lupos.shared.DictionaryValueType
import lupos.shared.IQuery
import lupos.shared.ValueComparatorASC
import lupos.shared.ValueComparatorDESC
import lupos.shared.operator.iterator.IteratorBundle

public object EvalSort {
    public operator fun invoke(
        child: IteratorBundle,
        mySortPriority: List<String>,
        query: IQuery,
        sortBy: Array<String>,
        variablesOut: List<String>,
        sortOrder: Boolean,
    ): IteratorBundle {
        val comparator: Comparator<DictionaryValueType> = if (sortOrder) {
            ValueComparatorASC(query)
        } else {
            ValueComparatorDESC(query)
        }
        when {
            variablesOut.size == 1 -> {
                return if (sortBy.size == 1) {
                    IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!, comparator)))
                } else {
                    IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!)))
                }
            }
            else -> {
                val columnNamesTmp = mutableListOf<String>()
                for (v in sortBy) {
                    columnNamesTmp.add(v)
                }
                for (v in mySortPriority) {
                    if (variablesOut.contains(v)) {
                        if (!columnNamesTmp.contains(v)) {
                            columnNamesTmp.add(v)
                        }
                    }
                }
                for (v in variablesOut) {
                    if (!columnNamesTmp.contains(v)) {
                        columnNamesTmp.add(v)
                    }
                }
                val columnNames = columnNamesTmp.toTypedArray()
                return IteratorBundle(RowIteratorMerge(child.rows, comparator, sortBy.size, columnNames))
            }
        }
    }
}
