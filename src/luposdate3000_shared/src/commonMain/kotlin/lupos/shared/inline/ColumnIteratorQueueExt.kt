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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.operator.iterator.ColumnIteratorQueue

internal object ColumnIteratorQueueExt {
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun _close(it: ColumnIteratorQueue) {
        if (it.label != 0) {
            it.label = 0
            it.queue.clear()
        }
    }

    /*suspend*/ internal inline fun nextHelper(it: ColumnIteratorQueue, crossinline onEmptyQueue: /*suspend*/ () -> Unit, crossinline onClose: /*suspend*/ () -> Unit): DictionaryValueType {
        when (it.label) {
            1 -> {
                return if (it.queue.size == 0) {
                    onEmptyQueue()
                    if (it.queue.size > 0) {
                        it.queue.removeAt(0)
                    } else {
                        onClose()
                        DictionaryValueHelper.nullValue
                    }
                } else {
                    it.queue.removeAt(0)
                }
            }
            2 -> {
                return if (it.queue.size == 0) {
                    onClose()
                    DictionaryValueHelper.nullValue
                } else {
                    it.queue.removeAt(0)
                }
            }
            else -> {
                return DictionaryValueHelper.nullValue
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun closeOnEmptyQueue(it: ColumnIteratorQueue) {
        if (it.label != 0) {
            it.label = 2
        }
    }
}
