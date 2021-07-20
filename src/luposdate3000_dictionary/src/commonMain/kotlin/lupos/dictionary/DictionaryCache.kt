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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.Luposdate3000Instance
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
public class DictionaryCache(instance: Luposdate3000Instance) {
    private val valueCapacity = instance.dictionaryCacheCapacity
    private var offset = 0
    private val valueIds = DictionaryValueTypeArray(valueCapacity) { DictionaryValueHelper.booleanTrueValue }
    private val valueContent = Array(valueCapacity) {
        val tmp = ByteArrayWrapper()
        ByteArrayWrapperExt.copyInto(DictionaryExt.booleanTrueValue3, tmp, false)
        tmp
    }

    public fun getValueByContent(buffer: ByteArrayWrapper): DictionaryValueType {
        for (i in 0 until valueCapacity) {
            if (ByteArrayWrapperExt.compare_fast(valueContent[i], buffer) == 0) {
                return valueIds[i]
            }
        }
        return DictionaryValueHelper.nullValue
    }

    public fun getValueById(buffer: ByteArrayWrapper, id: DictionaryValueType): Boolean {
        for (i in 0 until valueCapacity) {
            if (valueIds[i] == id) {
                ByteArrayWrapperExt.copyInto(valueContent[i], buffer, false)
                return true
            }
        }
        return false
    }

    public fun insertValuePair(buffer: ByteArrayWrapper, id: DictionaryValueType) {
        for (i in 0 until valueCapacity) {
            if (valueIds[i] == id) {
                return
            }
        }
        for (i in 0 until valueCapacity) {
            if (ByteArrayWrapperExt.compare_fast(valueContent[i], buffer) == 0) {
                return // to be save, otherwise we miss the case where a local dictionary id is upgraded to a global one
            }
        }
        ByteArrayWrapperExt.copyInto(buffer, valueContent[offset], false)
        valueIds[offset] = id
        offset++
    }
}
