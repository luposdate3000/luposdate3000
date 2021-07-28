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
import lupos.shared.dictionary.IDictionaryCache
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
public class DictionaryCache : IDictionaryCache {
    private var valueCapacity: Int
    private var offset = 0
    private var valueIds: DictionaryValueTypeArray
    private var valueContent: Array<ByteArrayWrapper>

    public constructor(instance: Luposdate3000Instance) : this(instance.dictionaryCacheCapacity)
    public constructor(capacity: Int) {
        valueCapacity = capacity
        valueIds = DictionaryValueTypeArray(valueCapacity) { DictionaryValueHelper.booleanTrueValue }
        valueContent = Array(valueCapacity) {
            val tmp = ByteArrayWrapper()
            ByteArrayWrapperExt.copyInto(DictionaryExt.booleanTrueValue3, tmp, false)
            tmp
        }
    }
    override fun forEach(action: (ByteArrayWrapper, DictionaryValueType) -> Unit) {
        for (i in 0 until valueCapacity) {
            if (valueIds[i] != DictionaryValueHelper.booleanTrueValue) {
                action(valueContent[i], valueIds[i])
            }
        }
    }
    override fun getValueByContent(buffer: ByteArrayWrapper): DictionaryValueType {
        for (i in 0 until valueCapacity) {
            if (ByteArrayWrapperExt.compare_fast(valueContent[i], buffer) == 0) {
                return valueIds[i]
            }
        }
        return DictionaryValueHelper.nullValue
    }

    override fun getValueById(buffer: ByteArrayWrapper, id: DictionaryValueType): Boolean {
        for (i in 0 until valueCapacity) {
            if (valueIds[i] == id) {
                ByteArrayWrapperExt.copyInto(valueContent[i], buffer, false)
                return true
            }
        }
        return false
    }

    override fun insertValuePair(buffer: ByteArrayWrapper, id: DictionaryValueType) {
        insertValuePairInternal(buffer, id, false)
    }
    override fun insertValuePairExtend(buffer: ByteArrayWrapper, id: DictionaryValueType) {
        insertValuePairInternal(buffer, id, true)
    }

    private fun insertValuePairInternal(buffer: ByteArrayWrapper, id: DictionaryValueType, extend: Boolean) {
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
        if (extend && offset >= valueCapacity) {
            var target = valueCapacity * 2
            if (target == 0) {
                target = 1
            }
            val tmpIds = DictionaryValueTypeArray(target) {
                if (it <valueCapacity) {
                    valueIds[it]
                } else {
                    DictionaryValueHelper.booleanTrueValue
                }
            }
            val tmpContents = Array(target) {
                if (it <valueCapacity) {
                    valueContent[it]
                } else {
                    val tmp = ByteArrayWrapper()
                    ByteArrayWrapperExt.copyInto(DictionaryExt.booleanTrueValue3, tmp, false)
                    tmp
                }
            }
            valueIds = tmpIds
            valueContent = tmpContents
            valueCapacity = target
        }
        ByteArrayWrapperExt.copyInto(buffer, valueContent[offset], false)
        valueIds[offset] = id
        offset++
    }
}
