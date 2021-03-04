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
package ext.fs

import kotlin.js.JsName

@JsName("openSync")
public fun openSync_(filename: String, flags: String): Int = throw Exception("not implemented")

@JsName("readSync")
public fun readSync_(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = throw Exception("not implemented")

@JsName("writeSync")
public fun writeSync_(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = throw Exception("not implemented")

@JsName("closeSync")
public fun closeSync_(fd: Int): Unit = throw Exception("not implemented")

@JsName("readFileSync")
public fun readFileSync_(filename: String): ByteArray = throw Exception("not implemented")
