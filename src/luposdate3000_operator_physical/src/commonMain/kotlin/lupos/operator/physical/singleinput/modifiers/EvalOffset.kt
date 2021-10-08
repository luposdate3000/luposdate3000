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
package lupos.operator.physical.singleinput.modifiers

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
public object EvalOffset {
    public operator fun invoke(child: IteratorBundle, offset: Int): IteratorBundle {
        val variables = child.columns.keys.toList()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val columns = Array(variables.size) { child.columns[variables[it]] }
        var tmp: DictionaryValueType = DictionaryValueHelper.nullValue
        loop@ for (i in 0 until offset) {
            for (element in columns) {
                tmp = element!!.next()
                if (tmp == DictionaryValueHelper.nullValue) {
                    for (element2 in columns) {
                        element2!!.close()
                    }
                    break@loop
                }
            }
        }
        for (variable in variables) {
            if (tmp == DictionaryValueHelper.nullValue) {
                child.columns[variable]!!.close()
            }
            outMap[variable] = child.columns[variable]!!
        }
        return IteratorBundle(outMap)
    }
}
