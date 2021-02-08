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
import lupos.s00misc.IMyInputStream
import lupos.s00misc.ByteArrayHelper
import kotlin.jvm.JvmField
internal class _MyStringStream(str: String) : IMyInputStream {
   @JvmField val buf4 = ByteArray(4)
  @JvmField public val data = str.encodeToByteArray()
    @JvmField public var off = 0
    override fun read(buf: ByteArray): Int {
        var len = off + buf.size
        var res = buf.size
        if (len> data.size) {
            len = data.size
            res = len - off
        }
        data.copyInto(buf, 0, off, len)
        off = len
        return res
    }
    override fun read(buf: ByteArray,len:Int) {
        val s = off + len
        data.copyInto(buf, 0, off, s)
        off = s
    }
override fun readInt(): Int {
        read(buf4, 4)
        return ByteArrayHelper.readInt4(buf4, 0)
    }
}
