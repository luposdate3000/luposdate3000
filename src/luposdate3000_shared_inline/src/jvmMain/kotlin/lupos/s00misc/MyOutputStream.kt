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
internal class MyOutputStream(@JvmField public val stream: OutputStream) : IMyOutputStream {
    @JvmField val buf4 = ByteArray(4)
    override fun write(buf: ByteArray) {
        stream.write(buf, 0, buf.size)
    }
    override fun writeInt(i: Int) {
        ByteArrayHelper.writeInt4(buf4, 0, i)
        write(buf4)
    }
}
