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
package lupos.shared.dictionary

import lupos.shared.dynamicArray.ByteArrayWrapper

public interface IDictionary {
    public fun importFromDictionaryFile(filename: String): Pair<IntArray, Int>
    public fun createNewBNode(): Int
    public fun createNewUUID(): Int
    public fun createNewBNode(s: String): Int
    public fun getValue(buffer: ByteArrayWrapper, value: Int)
    public fun hasValue(buffer: ByteArrayWrapper): Int?
    public fun createValue(buffer: ByteArrayWrapper): Int
    public fun valueToGlobal(value: Int): Int
    public fun isBnode(value: Int): Boolean
    public fun close()
    public fun delete()
    public fun isInmemoryOnly(): Boolean
    public fun isLocalValue(value: Int): Boolean
public fun forEachValue(buffer:ByteArrayWrapper,action:(Int)->Unit)
}
