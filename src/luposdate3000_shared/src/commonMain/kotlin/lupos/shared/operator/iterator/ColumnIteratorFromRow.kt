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
package lupos.shared.operator.iterator

import lupos.shared.DictionaryValueType
import lupos.shared.inline.ColumnIteratorQueueExt

public object ColumnIteratorFromRow {
    public operator fun invoke(iterator: RowIterator): Map<String, ColumnIterator> {
        val res = mutableMapOf<String, ColumnIterator>()
        val iterators = mutableListOf<ColumnIteratorQueue>()
        for (element in iterator.columns) {
            val iterator2 = object : ColumnIteratorQueue() {
                override /*suspend*/ fun next(): DictionaryValueType {
                    return ColumnIteratorQueueExt.nextHelper(
                        this,
                        {
                            val res2 = iterator.next()
                            if (res2 >= 0) {
                                for (j in iterator.columns.indices) {
                                    iterators[j].queue.add(iterator.buf[res2 + j])
                                }
                            }
                        },
                        {
                            if (label != 0) {
                                ColumnIteratorQueueExt._close(this)
                                iterator.close()
                            }
                        },
                    )
                }

                override /*suspend*/ fun close() {
                    if (label != 0) {
                        ColumnIteratorQueueExt._close(this)
                        iterator.close()
                    }
                }
            }
            iterators.add(iterator2)
            res[element] = iterator2
        }
        return res
    }
}
