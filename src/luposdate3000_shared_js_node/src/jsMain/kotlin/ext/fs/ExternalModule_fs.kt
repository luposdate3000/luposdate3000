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
package lupos.shared.js

public object ExternalModule_fs {
    public fun openSync(filename: String, flags: String): Int = openSync_(filename, flags)
    public fun readSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = readSync_(fd, buffer, offset, length, position)
    public fun writeSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = writeSync_(fd, buffer, offset, length, position)
    public fun closeSync(fd: Int): Unit = closeSync_(fd)
    public fun readFileSync(filename: String): ByteArray = readFileSync_(filename)
}
