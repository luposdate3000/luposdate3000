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
package lupos.shared

import lupos.shared.operator.iterator.ColumnIterator
import kotlin.jvm.JvmField
public abstract class ColumnIteratorChildIterator(public val query: IQuery) : ColumnIterator() {
    @JvmField
    public var queue: Array<ColumnIterator> = Array(100) { this }

    @JvmField
    public var queueRead: Int = 0

    @JvmField
    public var queueWrite: Int = 0

    @JvmField
    public var label: Int = 1

    @Suppress("NOTHING_TO_INLINE")
    public fun addChildColumnIteratorValue(value: DictionaryValueType) {
        val res = ColumnIteratorValue()
        res.value = value
        res.done = false
        addChild(res)
    }

    @Suppress("NOTHING_TO_INLINE")
    public fun addChild(child: ColumnIterator) {
        if (queueRead == queueWrite) {
            queueRead = 0
            queueWrite = 0
        } else if (queueWrite == queue.size) {
            if (queueRead == 0) {
                val buf = Array<ColumnIterator>(queue.size * 2) { this }
                queue.copyInto(buf, 0, queueRead, queueWrite)
                queue = buf
            } else {
                queue.copyInto(queue, 0, queueRead, queueWrite)
                queueWrite -= queueRead
            }
            queueRead = 0
        }
        queue[queueWrite] = child
        queueWrite++
    }

    @Suppress("NOTHING_TO_INLINE")
    public fun closeOnNoMoreElements() {
        if (label != 0) {
            label = 2
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public fun releaseValue(obj: ColumnIterator) {
        obj.close()
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ public fun _close() {
        if (label != 0) {
            label = 0
            for (i in queueRead until queueWrite) {
                releaseValue(queue[i])
            }
        }
    }
}
