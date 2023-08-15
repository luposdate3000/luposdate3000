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
package lupos.operator.physical.partition

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalDistributedSendWrapper {
    public operator fun invoke(child: IteratorBundle, action: () -> Unit): IteratorBundle {
        return IteratorBundle(
            mapOf(
                "" to object : ColumnIterator() {
                    private val variables = child.names
                    private val columns = Array(variables.size) { child.columns[variables[it]]!! }
                    private var first = true
                    override fun close() {
                        first = false
                        for (c in columns) {
                            c.close()
                        }
                    }

                    override fun next(): DictionaryValueType {
                        if (first) {
                            first = false
                            action()
                        }
                        return DictionaryValueHelper.nullValue
                    }
                },
            ),
        )
    }
}
