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
package lupos.dictionary

import lupos.shared.ETripleComponentTypeExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.UUID_Counter
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public class DictionaryNotImplemented internal constructor(isLocal: Boolean, instance: Luposdate3000Instance) : ADictionary(instance, isLocal) {
     override fun isInmemoryOnly(): Boolean = TODO()
     override fun close() :Unit= TODO()  
     override fun delete() :Unit= TODO()  
     override fun createNewBNode(): Int = TODO()  
     override fun createNewUUID(): Int = TODO()  
     override fun forEachValue(buffer: ByteArrayWrapper, action: (Int) -> Unit) = TODO()  
     override fun getValue(buffer: ByteArrayWrapper, value: Int) = TODO()  
     override fun createValue(buffer: ByteArrayWrapper): Int = TODO()  
     override fun hasValue(buffer: ByteArrayWrapper): Int? = TODO()  
override fun createNewBNode(s: String): Int= TODO()
 override fun isBnode(value: Int): Boolean = TODO()
 override fun isLocalValue(value: Int): Boolean= TODO()
override fun valueToGlobal(value: Int): Int= TODO()
override fun importFromDictionaryFile(filename: String): Pair<IntArray, Int> = TODO()
}
