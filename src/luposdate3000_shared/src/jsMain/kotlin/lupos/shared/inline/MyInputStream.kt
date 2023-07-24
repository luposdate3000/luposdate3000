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
import lupos.shared.IMyInputStream
import lupos.shared.js.JSInputStream

public actual class MyInputStream : IMyInputStream {
    val tmp: JSInputStream

    public constructor(filename: String) {
        tmp = JSInputStream(filename)
    }

    public constructor(fd: Int) {
        tmp = JSInputStream(fd)
    }

    actual override fun readInt(): Int {
        return tmp.readInt()
    }

    actual override fun readDictionaryValueType(): DictionaryValueType {
        return DictionaryValueHelper.readFromStream(this)
    }

    actual override fun readLong(): Long {
        return tmp.readLong()
    }

    actual override fun readByte(): Byte {
        return tmp.readByte()
    }

    actual override fun read(buf: ByteArray, off: Int, len: Int): Int {
        return tmp.read(buf, off, len)
    }

    actual override fun read(buf: ByteArray, len: Int): Int {
        return tmp.read(buf, len)
    }

    actual override fun read(buf: ByteArray): Int {
        return tmp.read(buf)
    }

    actual override fun close() {
        tmp.close()
    }

    actual override fun readLine(): String? {
        return tmp.readLine()
    }
}
