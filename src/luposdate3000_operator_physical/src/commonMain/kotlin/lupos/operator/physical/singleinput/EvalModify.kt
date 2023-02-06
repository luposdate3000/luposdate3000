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

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.logical.noinput.LOPTriple
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalModify {
    private class EvalLocalClass(
        private val child: IteratorBundle,
        private val query: IQuery,
        private val modify: Array<Pair<LOPTriple, EModifyType>>,
    ) : ColumnIterator() {
        private var __first = true
        override fun close() {
            __first = false
        }

        override fun next(): DictionaryValueType {
            if (!__first) {
                return DictionaryValueHelper.nullValue
            }
            // println("EvalModify ${modify.map{it.first.toString() + EModifyTypeExt.names[it.second]}}")
            __first = false
            val variables = child.columns.keys.toList()
            val columns = Array(variables.size) { child.columns[variables[it]]!! }
            val row = DictionaryValueTypeArray(variables.size) { DictionaryValueHelper.undefValue }
            val data = mutableMapOf<String, Array<Array<MutableList<DictionaryValueType>>>>()
            val buffer = ByteArrayWrapper()
            loop@ while (true) {
                if (variables.isNotEmpty()) {
                    for (columnIndex in variables.indices) {
                        val value = columns[columnIndex].next()
                        if (value == DictionaryValueHelper.nullValue) {
                            if (SanityCheck.enabled) { if (!(columnIndex == 0)) { throw Exception("SanityCheck failed") } }
                            break@loop
                        }
                        row[columnIndex] = value
                    }
                } else {
                    if (!child.hasNext2()) {
                        child.hasNext2Close()
                        break@loop
                    }
                }
                for ((first, second) in modify) {
                    val graphName: String = if (first.graphVar) {
                        var graphVarIdx = variables.indexOf(first.graph)
                        query.getDictionary().getValue(buffer, row[graphVarIdx])
                        DictionaryHelper.byteArrayToSparql(buffer)
                    } else {
                        first.graph
                    }
                    if (data[graphName] == null) {
                        data[graphName] = Array(EModifyTypeExt.values_size) { Array(3) { mutableListOf() } }
                    }
                    val target = data[graphName]!![second]
                    loop2@ for (columnIndex in 0 until 3) {
                        val tmp = first.children[columnIndex]
                        if (tmp is AOPConstant) {
                            target[columnIndex].add(tmp.value)
                        } else {
                            for (columnIndex2 in variables.indices) {
                                if (variables[columnIndex2] == (tmp as AOPVariable).name) {
                                    target[columnIndex].add(row[columnIndex2])
                                    continue@loop2
                                }
                            }
                            SanityCheck.checkUnreachable()
                        }
                    }
                }
            }
            if (row.isNotEmpty()) {
                for (closeIndex in child.columns.values) {
                    closeIndex.close()
                }
            } else {
                child.hasNext2Close()
            }
            for ((graphName, iterator) in data) {
                val dict = query.getDictionary()
                val store = query.getInstance().tripleStoreManager!!.getGraph(graphName)
                for (type in 0 until EModifyTypeExt.values_size) {
                    if (iterator[type][0].size > 0) {
                        val cache = store.modify_create_cache(query, EModifyTypeExt.INSERT, -1, false)
                        val iterator2 = Array(3) { ColumnIteratorMultiValue(iterator[type][it]) }
                        while (true) {
                            val s = dict.valueToGlobal(iterator2[0].next())
                            val p = dict.valueToGlobal(iterator2[1].next())
                            val o = dict.valueToGlobal(iterator2[2].next())
                            if (s == DictionaryValueHelper.nullValue) {
                                break
                            }
                            cache.writeRow(s, p, o, query)
                        }
                        cache.close()
                    }
                }
            }
            return DictionaryValueHelper.booleanTrueValue
        }
    }

    public operator fun invoke(
        child: IteratorBundle,
        query: IQuery,
        modify: Array<Pair<LOPTriple, EModifyType>>,
    ): IteratorBundle {
        return IteratorBundle(
            mapOf(
                "?success" to EvalLocalClass(child, query, modify)
            )
        )
    }
}
