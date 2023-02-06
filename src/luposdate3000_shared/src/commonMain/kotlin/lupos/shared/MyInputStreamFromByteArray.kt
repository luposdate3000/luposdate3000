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

import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField
import kotlin.math.min

public class MyInputStreamFromByteArray public constructor(@JvmField public val data: ByteArrayWrapper) : IMyInputStream {
    private var offset = 0
    override fun close() {}
    override fun read(buf: ByteArray): Int {
        TODO()
    }

    override fun read(buf: ByteArray, len: Int): Int {
        val l = min(len, ByteArrayWrapperExt.getSize(data) - offset)
        ByteArrayWrapperExt.getBuf(data).copyInto(buf, 0, offset, offset + l)
        offset += l
        return l
    }

    override fun read(buf: ByteArray, off: Int, len: Int): Int {
        TODO()
    }

    override fun readByte(): Byte {
        TODO()
    }

    override fun readDictionaryValueType(): DictionaryValueType {
        val res = DictionaryValueHelper.fromByteArray(data, offset)
        offset += DictionaryValueHelper.getSize()
        return res
    }

    override fun readLong(): Long {
        val res = ByteArrayWrapperExt.readLong8(data, offset)
        offset += 8
        return res
    }

    override fun readInt(): Int {
        val res = ByteArrayWrapperExt.readInt4(data, offset)
        offset += 4
        return res
    }

    override fun readLine(): String? {
        TODO()
    }
}
