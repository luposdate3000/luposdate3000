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
import lupos.shared.UUID_Counter
import lupos.shared.dynamicArray.ByteArrayWrapper
import java.io.InputStream
import kotlin.jvm.JvmField
import lupos.shared.EOFException

public actual class MyInputStream(@JvmField public val stream: InputStream) : IMyInputStream {

    @JvmField
    public val buf8: ByteArray = ByteArray(8)

    @JvmField
public    val buf8Wrapper :ByteArrayWrapper= ByteArrayWrapper(buf8, 8)

    @JvmField
    public val uuid:Long = UUID_Counter.getNextUUID()

    init {
    }

    actual override fun read(buf: ByteArray): Int {
        return read(buf, buf.size)
    }

    actual override fun read(buf: ByteArray, len: Int): Int {
        var o = 0
        var s = len
        while (s > 0) {
            val tmp = stream.read(buf, o, s)
            if (tmp <= 0) {
                return len - s
            }
            s -= tmp
            o += tmp
        }
        return len
    }

    actual override fun read(buf: ByteArray, off: Int, len: Int): Int {
        var o = off
        var s = len
        while (s > 0) {
            val tmp = stream.read(buf, o, s)
            if (tmp <= 0) {
                return len - s
            }
            s -= tmp
            o += tmp
        }
        return len
    }

    actual override fun readInt(): Int {
if(        read(buf8, 4)<4){
throw EOFException()
}
        return ByteArrayHelper.readInt4(buf8, 0)
    }

    actual override fun readDictionaryValueType(): DictionaryValueType {
if(        read(buf8, DictionaryValueHelper.getSize())<DictionaryValueHelper.getSize()){
throw EOFException()
}
        return DictionaryValueHelper.fromByteArray(buf8Wrapper, 0)
    }

    actual override fun readLong(): Long {
if(        read(buf8, 8)<8){ 
throw EOFException()
}
        return ByteArrayHelper.readLong8(buf8, 0)
    }

    actual override fun readByte(): Byte {
if(        read(buf8, 1)<1){ 
throw EOFException()
}
        return buf8[0]
    }

    actual override fun close() {
        stream.close()
    }

    @JvmField
    public var buffer :ByteArray= ByteArray(1)
    actual override fun readLine(): String? {
// TODO this may break on utf-8 if '\r' or '\0' is part of another char
        var len = 0
        try {
            var b = readByte()
            while (true) {
                when (b) {
                    '\n'.code.toByte() -> {
                        break
                    }
                    '\r'.code.toByte() -> {
                    }
                    0.toByte() -> throw Exception("zero Bytes not allowed within utf8-string")
                    else -> {
                        if (len >= buffer.size) {
                            val bb = ByteArray(len * 2)
                            buffer.copyInto(bb)
                            buffer = bb
                        }
                        buffer[len++] = b
                    }
                }
                b = readByte()
            }
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/jvmMain/kotlin/lupos/shared/inline/MyInputStream.kt:133"/*SOURCE_FILE_END*/ )
            if (len == 0) {
                return null
            }
        }
        val res = buffer.decodeToString(0, len)
        return res
    }
}
