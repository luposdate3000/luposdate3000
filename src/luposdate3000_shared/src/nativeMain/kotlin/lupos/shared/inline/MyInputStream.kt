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

import lupos.shared.IMyInputStream

public actual class MyInputStream : IMyInputStream {
    actual override fun readByte(): Byte = TODO("MyInputStream")
    actual override fun readInt(): Int = TODO("MyInputStream")
    actual override fun read(buf: ByteArray, off: Int, len: Int): Int = TODO("MyInputStream")
    actual override fun read(buf: ByteArray, len: Int): Int = TODO("MyInputStream")
    actual override fun read(buf: ByteArray): Int = TODO("MyInputStream")
    actual override fun close() {
    }

    actual override fun readLine(): String? {
// TODO this may break on utf-8
        var buf = mutableListOf<Byte>()
        try {
            var b = readByte()
            while (b != '\n'.toByte()) {
                if (b != '\r'.toByte()) {
                    buf.add(b)
                }
                b = readByte()
            }
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/nativeMain/kotlin/lupos/shared/inline/MyInputStream.kt:42"/*SOURCE_FILE_END*/ )
            if (buf.size == 0) {
                return null
            }
        }
        return buf.toByteArray().decodeToString()
    }
}
