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

import lupos.operator.base.iterator.ColumnIteratorMultiIterator
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalUnion {
    public operator fun invoke(
        childA: IteratorBundle,
        childB: IteratorBundle,
        variables: List<String>
    ): IteratorBundle {
        val outMap = mutableMapOf<String, ColumnIterator>()
        if (variables.isNotEmpty()) {
            for (variable in variables) {
                outMap[variable] = ColumnIteratorMultiIterator(listOf(childA.columns[variable]!!, childB.columns[variable]!!))
            }
            return IteratorBundle(outMap)
        } else {
            return object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    return childA.hasNext2() || childB.hasNext2()
                }

                override /*suspend*/ fun hasNext2Close() {
                    childA.hasNext2Close()
                    childB.hasNext2Close()
                }
            }
        }
    }
}
