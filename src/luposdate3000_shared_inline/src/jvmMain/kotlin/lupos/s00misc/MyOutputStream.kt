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
package lupos.modulename

import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.IMyOutputStream
import java.io.OutputStream
import kotlin.jvm.JvmField

internal actual class _MyOutputStream : IMyOutputStream {
    @JvmField
    val buf4 = ByteArray(4)

    @JvmField
    internal val it: OutputStream?

    internal constructor(it: OutputStream) {
        this.it = it
    }

    internal actual constructor() {
        it = null
    }

    public actual override fun writeInt(value: Int) {
        ByteArrayHelper.writeInt4(buf4, 0, value)
        it!!.write(buf4, 0, 4)
    }

    public actual override fun write(buf: ByteArray, off: Int, len: Int): Unit = it!!.write(buf, off, len)
    public actual override fun close(): Unit = it!!.close()
    public actual override fun flush(): Unit = it!!.flush()
    public actual override fun write(buf: ByteArray): Unit = it!!.write(buf, 0, buf.size)
    public actual override fun write(buf: ByteArray, len: Int): Unit = it!!.write(buf, 0, len)
}
