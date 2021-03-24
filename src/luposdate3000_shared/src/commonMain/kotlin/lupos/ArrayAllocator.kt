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

package lupos

public fun ArrayAllocator(size: Int, initializer: (Int) -> IntArray): Array<IntArray> {
    throw Exception("this is jsut fake")
}

public fun ArrayAllocatorIntArray(size: Int, initializer: (Int) -> IntArray): Array<IntArray> {
    val res = Array(size, initializer)
    return res
}

public fun ArrayAllocatorInt(size: Int, initializer: (Int) -> Int): IntArray {
    val res = IntArray(size, initializer)
    return res
}

public fun ArrayAllocatorLongArray(size: Int, initializer: (Int) -> LongArray): Array<LongArray> {
    val res = Array(size, initializer)
    return res
}

public fun ArrayAllocatorString(size: Int, initializer: (Int) -> String): Array<String> {
    val res = Array(size, initializer)
    return res
}

public fun ArrayAllocatorByteArray(size: Int, initializer: (Int) -> ByteArray): Array<ByteArray> {
    val res = Array(size, initializer)
    return res
}

public fun ArrayAllocatorMutableListInt(size: Int, initializer: (Int) -> MutableList<Int>): Array<MutableList<Int>> {
    val res = Array(size, initializer)
    return res
}

public fun ArrayAllocatorMutableListString(size: Int, initializer: (Int) -> MutableList<String>): Array<MutableList<String>> {
    val res = Array(size, initializer)
    return res
}
