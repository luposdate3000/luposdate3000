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
import lupos.shared.IMyOutputStream
import lupos.shared.SanityCheck
import java.io.OutputStream
import kotlin.jvm.JvmField

internal actual class MyOutputStream : IMyOutputStream {
    @JvmField
    val buffer: ByteArray
    var bufferPos = 0

    @JvmField
    internal var stream: OutputStream?

    private var closedBy: MutableList<Throwable>? = null

    internal constructor(it: OutputStream) {
        // kotlin.io.println("MyOutputStream.constructor $this")
        stream = it
        buffer = ByteArray(8192)
    }

    internal actual constructor() {
        stream = null
        buffer = ByteArray(8192)
    }

    actual override fun writeDictionaryValueType(value: DictionaryValueType) {
        DictionaryValueHelper.sendToStream(this, value)
    }

    actual override fun writeInt(value: Int) {
        if (bufferPos + 4 > buffer.size) {
            localFlush()
        }
        ByteArrayHelper.writeInt4(buffer, bufferPos, value)
        bufferPos += 4
    }

    actual override fun writeLong(value: Long) {
        if (bufferPos + 8 > buffer.size) {
            localFlush()
        }
        ByteArrayHelper.writeLong8(buffer, bufferPos, value)
        bufferPos += 8
    }

    actual override fun close() {
  if(SanityCheck.enabled)            {
                try {
                    throw Exception()
                } catch (e: Throwable) {
                    if (closedBy == null) {
                        closedBy = mutableListOf(e)
                    } else {
                        closedBy!!.add(e)
                    }
                }
                if (stream == null) {
                    for (e in closedBy!!) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/shared/inline/MyOutputStream.kt:82"/*SOURCE_FILE_END*/ )
                    }
                }
            }
        // kotlin.io.println("MyOutputStream.close $this")
        flush()
        stream!!.close()
        stream = null
    }

    private fun localFlush() {
        // kotlin.io.println("MyOutputStream.localFlush $this $bufferPos")
        if (bufferPos > 0) {
            stream!!.write(buffer, 0, bufferPos)
            bufferPos = 0
        }
    }

    actual override fun flush() {
        // kotlin.io.println("MyOutputStream.flush $this")
        localFlush()
        stream!!.flush()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun _write(buf: ByteArray, off: Int, len: Int) {
        // kotlin.io.println("MyOutputStream._write $this")
        if (bufferPos + len > buffer.size) {
            localFlush()
        }
        if (len > buffer.size) {
            stream!!.write(buf, off, len)
        } else {
            buf.copyInto(buffer, bufferPos, off, off + len)
            bufferPos += len
        }
    }

    actual override fun write(buf: ByteArray) {
        _write(buf, 0, buf.size)
    }

    actual override fun write(buf: ByteArray, len: Int) {
        _write(buf, 0, len)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun _print(x: String) {
        val buf = x.encodeToByteArray()
        _write(buf, 0, buf.size)
    }

    actual override fun println(x: String) = _print("$x\n")
    actual override fun print(x: String) = _print(x)
    actual override fun print(x: Boolean) = _print("$x")
    actual override fun print(x: Int) = _print("$x")
    actual override fun print(x: Double) = _print("$x")
    actual override fun println() = _print("\n")
}
