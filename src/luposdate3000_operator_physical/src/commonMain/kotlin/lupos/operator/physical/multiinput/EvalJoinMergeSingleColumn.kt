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

import lupos.shared.DictionaryValueHelper
import lupos.shared.IQuery
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorEmpty
import lupos.shared.operator.iterator.IteratorBundle
public object EvalJoinMergeSingleColumn {
    public operator fun invoke(
        query: IQuery,
        child0: IteratorBundle,
        child1: IteratorBundle,
    ): IteratorBundle {
        val child00 = child0.columns.values.first()
        val child11 = child1.columns.values.first()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val a = child00.next()
        val b = child11.next()
        if (a != DictionaryValueHelper.nullValue && b != DictionaryValueHelper.nullValue) {
            outMap[child0.columns.keys.first()] = POPJoinMergeSingleColumn_Iterator(query, child00, child11, a, b)
        } else {
            outMap[child0.columns.keys.first()] = ColumnIteratorEmpty()
            child00.close()
            child11.close()
        }
        return IteratorBundle(outMap)
    }
}
