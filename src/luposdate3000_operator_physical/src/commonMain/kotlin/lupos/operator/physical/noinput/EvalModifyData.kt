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
package lupos.operator.physical.noinput

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EModifyTypeExt
import lupos.shared.operator.iterator.IteratorBundle
public object EvalModifyData {
    public operator fun invoke(): IteratorBundle {
        val iteratorDataMap = mutableMapOf<String, Array<MutableList<DictionaryValueType>>>()
        val dictionary = query.getDictionary()
        for (t in data) {
            for (i in 0 until 3) {
                var tmp = iteratorDataMap[t.graph]
                if (tmp == null) {
                    tmp = Array(3) { mutableListOf() }
                    iteratorDataMap[t.graph] = tmp
                }
                tmp[i].add(dictionary.valueToGlobal((t.children[i] as AOPConstant).value))
            }
        }
        for ((graph, iteratorData) in iteratorDataMap) {
            val graphLocal = query.getInstance().tripleStoreManager!!.getGraph(graph)
            val cache = graphLocal.modify_create_cache(query, EModifyTypeExt.INSERT, -1, false)
            val iterator = Array(3) { ColumnIteratorMultiValue(iteratorData[it]) }
            while (true) {
                val s = iterator[0].next()
                val p = iterator[1].next()
                val o = iterator[2].next()
                if (s == DictionaryValueHelper.nullValue) {
                    break
                }
                cache.writeRow(s, p, o, query)
            }
            cache.close()
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, DictionaryValueHelper.booleanTrueValue)))
    }
}
