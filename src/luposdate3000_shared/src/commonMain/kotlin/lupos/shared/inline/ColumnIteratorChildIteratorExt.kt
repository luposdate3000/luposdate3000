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

package lupos.shared.inline

import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.IQuery
public object ColumnIteratorChildIteratorExt {
    /*suspend*/ public inline fun nextHelper(query: IQuery, iterator: ColumnIteratorChildIterator, crossinline onNoMoreElements: /*suspend*/ () -> Unit, crossinline onClose: /*suspend*/ () -> Unit): DictionaryValueType {
        if (query.shouldAbortNow()) {
            onClose()
            return DictionaryValueHelper.nullValue
        } else {
            when (iterator.label) {
                1 -> {
                    while (iterator.queueRead < iterator.queueWrite) {
                        val res = iterator.queue[iterator.queueRead].next()
                        if (res == DictionaryValueHelper.nullValue) {
                            iterator.releaseValue(iterator.queue[iterator.queueRead])
                            iterator.queueRead++
                        } else {
                            return res
                        }
                    }
                    onNoMoreElements()
                    return if (iterator.queueRead == iterator.queueWrite) {
                        onClose()
                        DictionaryValueHelper.nullValue
                    } else {
                        val res = iterator.queue[iterator.queueRead].next()
                        if (res == DictionaryValueHelper.nullValue) {
                            onClose()
                        }
                        res
                    }
                }
                2 -> {
                    while (iterator.queueRead < iterator.queueWrite) {
                        val res = iterator.queue[iterator.queueRead].next()
                        if (res == DictionaryValueHelper.nullValue) {
                            iterator.releaseValue(iterator.queue[iterator.queueRead])
                            iterator.queueRead++
                        } else {
                            return res
                        }
                    }
                    onClose()
                    return DictionaryValueHelper.nullValue
                }
                else -> {
                    return DictionaryValueHelper.nullValue
                }
            }
        }
    }
}
