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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.Luposdate3000Instance
import lupos.shared.dynamicArray.ByteArrayWrapper
import kotlin.jvm.JvmField

public class DictionaryNotImplemented public constructor(
    @JvmField
    private val instance: Luposdate3000Instance,
) : IDictionary {
    internal val bnodeMapToGlobal = mutableMapOf<DictionaryValueType, DictionaryValueType>()
    override fun isInmemoryOnly(): Boolean = TODO()
    override fun close(): Unit = TODO()
    override fun delete(): Unit = TODO()
    override fun createNewBNode(): DictionaryValueType = TODO()
    override fun createNewUUID(): Int = TODO()
    override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit): Unit = TODO()
    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType): Unit = TODO()
    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType = TODO()
    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType = TODO()
    override fun createNewBNode(s: String): DictionaryValueType = TODO()
    override fun isBnode(value: DictionaryValueType): Boolean = TODO()
    override fun valueToGlobal(value: DictionaryValueType): DictionaryValueType {
        if ((value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal) {
            if ((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode) {
                TODO()
            } else {
                val tmp = bnodeMapToGlobal[value]
                if (tmp == null) {
                    val res = instance.nodeGlobalDictionary!!.createNewBNode()
                    bnodeMapToGlobal[value] = res
                    return res
                } else {
                    return tmp
                }
            }
        } else {
            return value
        }
    }

    override fun importFromDictionaryFile(filename: String): Pair<DictionaryValueTypeArray, Int> = TODO()
    override fun isLocalValue(value: DictionaryValueType): Boolean {
        return (value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal
    }
}
