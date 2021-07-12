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

import lupos.shared.IMyOutputStream
import lupos.shared.NotImplementedException

internal actual class MyPrintWriter : IMyOutputStream {
    actual constructor(hasBuffer: Boolean)

    actual override fun write(buf: ByteArray, len: Int): Unit = TODO()
    actual override fun write(buf: ByteArray): Unit = TODO()
    actual override fun clearBuffer(): Unit = TODO()
    public actual override fun writeInt(value: Int): Unit = TODO()
    actual override fun toString(): String = TODO()
    actual override fun println(x: String): Unit = TODO()
    actual override fun print(x: String): Unit = TODO()
    actual override fun println(x: Boolean): Unit = TODO()
    actual override fun print(x: Boolean): Unit = TODO()
    actual override fun println(x: Int): Unit = TODO()
    actual override fun print(x: Int): Unit = TODO()
    actual override fun println(x: Double): Unit = TODO()
    actual override fun print(x: Double): Unit = TODO()
    actual override fun println(): Unit = TODO()
    actual override fun close(): Unit = TODO()
    actual override fun flush(): Unit = TODO()
}
