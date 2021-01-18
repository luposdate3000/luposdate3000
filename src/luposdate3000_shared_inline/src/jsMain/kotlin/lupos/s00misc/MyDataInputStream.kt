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
import lupos.s00misc.NotImplementedException
internal actual class MyDataInputStream {
val fd:Int
var pos=0
constructor(fd:Int){
this.fd=fd
}
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readInt(): Int {
val buffer=ByteArray(4)
val l = ext.fs.readSync(fd, buffer, 0, buffer.size, pos)
if(l!=4){
throw Exception("invalid len $l")
}
pos+=l
return ByteArrayHelper.readInt4(buffer, 0)
}
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readByte(): Byte {
val buffer=ByteArray(1)
val l = ext.fs.readSync(fd, buffer, 0, buffer.size, pos)
if(l!=1){
throw Exception("invalid len $l")
}
pos+=l
return buffer[0]
}
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun read(buf: ByteArray, off: Int, len: Int): Int {
val l= ext.fs.readSync(fd, buf, off, len, pos)
pos+=l
return l
}
}
