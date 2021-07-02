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

import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.dynamicArray.ByteArrayWrapper

public interface IDictionary {
    public fun importFromDictionaryFile(filename: String): Pair<DictionaryValueTypeArray, Int>
    public fun createNewBNode(): DictionaryValueType
    public fun createNewUUID(): DictionaryValueType
    public fun createNewBNode(s: String): DictionaryValueType
    public fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType)
    public fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType?
    public fun createValue(buffer: ByteArrayWrapper): DictionaryValueType
    public fun valueToGlobal(value: DictionaryValueType): DictionaryValueType
    public fun isBnode(value: DictionaryValueType): Boolean
    public fun close()
    public fun delete()
    public fun isInmemoryOnly(): Boolean
    public fun isLocalValue(value: DictionaryValueType): Boolean
    public fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit)
}
