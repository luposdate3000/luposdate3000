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

import lupos.shared.DictionaryValueType
import lupos.shared.IMyOutputStream

public actual class MyOutputStream : IMyOutputStream {
    public actual constructor() {}

    actual override fun writeInt(value: Int): Unit = TODO("MyOutputStream")
    actual override fun writeLong(value: Long): Unit = TODO("MyOutputStream")
    actual override fun writeDictionaryValueType(value: DictionaryValueType): Unit = TODO("MyOutputStream")
    actual override fun close(): Unit = TODO("MyOutputStream")
    actual override fun flush(): Unit = TODO("MyOutputStream")
    actual override fun write(buf: ByteArray): Unit = write(buf, buf.size)
    actual override fun write(buf: ByteArray, len: Int): Unit = TODO("MyOutputStream")
    actual override fun println(x: String): Unit = TODO("MyOutputStream")
    actual override fun print(x: String): Unit = TODO("MyOutputStream")
    actual override fun print(x: Boolean): Unit = TODO("MyOutputStream")
    actual override fun print(x: Int): Unit = TODO("MyOutputStream")
    actual override fun print(x: Double): Unit = TODO("MyOutputStream")
    actual override fun println(): Unit = TODO("MyOutputStream")
}
