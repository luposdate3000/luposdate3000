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
package lupos.simulator_db.luposdate3000

import lupos.shared.CrashInputStreamException
import lupos.shared.DictionaryValueType
import lupos.shared.IMyInputStream

internal class InputStreamCrashAll(val target: Int, val path: String, val params: Map<String, String>) : IMyInputStream {
    override fun close() {}
    override fun read(buf: ByteArray): Int {
        throw CrashInputStreamException()
    }

    override fun read(buf: ByteArray, len: Int): Int {
        throw CrashInputStreamException()
    }

    override fun read(buf: ByteArray, off: Int, len: Int): Int {
        throw CrashInputStreamException()
    }

    override fun readByte(): Byte {
        throw CrashInputStreamException()
    }

    override fun readInt(): Int {
        throw CrashInputStreamException()
    }

    override fun readLong(): Long {
        throw CrashInputStreamException()
    }

    override fun readDictionaryValueType(): DictionaryValueType {
        throw CrashInputStreamException()
    }

    override fun readLine(): String? {
        throw CrashInputStreamException()
    }
}
