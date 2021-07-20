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
package lupos.shared.dynamicArray
import lupos.shared.SanityCheck
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public class ByteArrayWrapper : Comparable<ByteArrayWrapper> {
    @JvmField public var buf_: ByteArray = buf

    @JvmField public var size_: Int = 0

    public constructor(buf: ByteArray, size: Int) {
        this.size_ = size
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dynamicArray/ByteArrayWrapper.kt:29"/*SOURCE_FILE_END*/ }, { size <= buf.size })
    }
    public constructor(buf: ByteArray) : this(buf, buf.size)
    public constructor() : this(ByteArray(20), 0)

    override fun compareTo(other: ByteArrayWrapper): Int {
        return ByteArrayWrapperExt.compare_slow(this, other)
    }

    override fun equals(other: Any?): Boolean {
        return other is ByteArrayWrapper && compareTo(other) == 0
    }

    override fun hashCode(): Int {
        var res = size_
        for (i in 0 until size_) {
            res = (res shl 1) + buf_[i]
        }
        return res
    }

    override fun toString(): String {
        return buf_.map { it }.subList(0, size_).toString()
    }
}
