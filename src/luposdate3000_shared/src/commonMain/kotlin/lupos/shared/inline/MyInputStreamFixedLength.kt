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
import lupos.shared.myPrintStackTrace

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.IMyInputStream
import kotlin.jvm.JvmField
import lupos.shared.EOFException

public class MyInputStreamFixedLength(@JvmField public val stream: IMyInputStream, @JvmField public var remainingBytes: Int) : IMyInputStream {
    override fun readDictionaryValueType(): DictionaryValueType {
        if (remainingBytes >= DictionaryValueHelper.getSize()) {
            remainingBytes -= DictionaryValueHelper.getSize()
            return stream.readDictionaryValueType()
        } else {
            throw EOFException("not enough bytes available $remainingBytes")
        }
    }

    override fun readInt(): Int {
        if (remainingBytes >= 4) {
            remainingBytes -= 4
            return stream.readInt()
        } else {
            throw EOFException("not enough bytes available $remainingBytes")
        }
    }

    override fun readLong(): Long {
        if (remainingBytes >= 8) {
            remainingBytes -= 8
            return stream.readLong()
        } else {
            throw EOFException("not enough bytes available $remainingBytes")
        }
    }

    override fun readByte(): Byte {
        if (remainingBytes >= 1) {
            remainingBytes -= 1
            return stream.readByte()
        } else {
            throw EOFException("not enough bytes available $remainingBytes")
        }
    }

    override fun read(buf: ByteArray): Int {
        if (remainingBytes >= buf.size) {
            remainingBytes -= buf.size
            return stream.read(buf)
        } else {
            throw EOFException("not enough bytes available $remainingBytes")
        }
    }

    override fun read(buf: ByteArray, len: Int): Int {
        if (remainingBytes >= len) {
            remainingBytes -= len
            return stream.read(buf, len)
        } else {
            throw EOFException("not enough bytes available $remainingBytes")
        }
    }

    override fun read(buf: ByteArray, off: Int, len: Int): Int {
        if (remainingBytes >= len) {
            remainingBytes -= len
            return stream.read(buf, off, len)
        } else {
            throw EOFException("not enough bytes available $remainingBytes")
        }
    }

    override fun close() {
        stream.close()
    }

    override fun readLine(): String? {
// TODO this may break on utf-8
        var buf = mutableListOf<Byte>()
        try {
            var b = readByte()
            while (b != '\n'.code.toByte()) {
                if (b != '\r'.code.toByte()) {
                    buf.add(b)
                }
                b = readByte()
            }
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/inline/MyInputStreamFixedLength.kt:105"/*SOURCE_FILE_END*/ )
            if (buf.size == 0) {
                return null
            }
        }
        return buf.toByteArray().decodeToString()
    }
}
