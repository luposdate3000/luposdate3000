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
package lupos.endpoint_launcher
import lupos.dictionary.ADictionary
import lupos.dictionary.DictionaryCache
import lupos.dictionary.DictionaryInlineValues
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

internal class RemoteDictionaryClient(@JvmField val input: IMyInputStream, @JvmField val output: IMyOutputStream, instance: Luposdate3000Instance, isLocal: Boolean) : ADictionary(instance, isLocal) {
    private val cache = DictionaryCache(instance)

    override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit): Unit = TODO()
    override fun isInmemoryOnly(): Boolean = true
    override fun delete() {
    }

    override fun valueToGlobal(value: DictionaryValueType): DictionaryValueType {
        output.writeInt(3)
        output.writeDictionaryValueType(value)
        output.flush()
        return input.readDictionaryValueType()
    }

    override fun createNewBNode(): DictionaryValueType {
        output.writeInt(1)
        output.flush()
        return input.readDictionaryValueType()
    }

    override fun createNewUUID(): Int {
        output.writeInt(7)
        output.flush()
        return input.readInt()
    }

    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
        if (Luposdate3000Instance.useDictionaryInlineEncoding) {
            val res = DictionaryInlineValues.getValueByContent(buffer)
            if (res != DictionaryValueHelper.nullValue) {
                return res
            }
        }
        val tmp2 = instance.nodeGlobalDictionary!!.hasValue(buffer)
        if (tmp2 != DictionaryValueHelper.nullValue) {
            return tmp2
        }
        if (Luposdate3000Instance.dictionaryCacheCapacity> 0) {
            val tmp = cache.getValueByContent(buffer)
            if (tmp != DictionaryValueHelper.nullValue) {
                return tmp
            }
        }
        output.writeInt(2)
        output.writeInt(ByteArrayWrapperExt.getSize(buffer))
        output.write(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer))
        output.flush()
        val res = input.readDictionaryValueType()
        if (res == DictionaryValueHelper.nullValue) {
            return DictionaryValueHelper.nullValue
        }
        if (Luposdate3000Instance.dictionaryCacheCapacity> 0) {
            cache.insertValuePair(buffer, res)
        }
        return res
    }

    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        if (Luposdate3000Instance.useDictionaryInlineEncoding) {
            val res = DictionaryInlineValues.getValueByContent(buffer)
            if (res != DictionaryValueHelper.nullValue) {
                return res
            }
        }
        val tmp2 = instance.nodeGlobalDictionary!!.hasValue(buffer)
        if (tmp2 != DictionaryValueHelper.nullValue) {
            return tmp2
        }
        if (Luposdate3000Instance.dictionaryCacheCapacity> 0) {
            val tmp = cache.getValueByContent(buffer)
            if (tmp != DictionaryValueHelper.nullValue) {
                return tmp
            }
        }
        output.writeInt(5)
        output.writeInt(ByteArrayWrapperExt.getSize(buffer))
        output.write(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer))
        output.flush()
        val res = input.readDictionaryValueType()
        if (Luposdate3000Instance.dictionaryCacheCapacity> 0) {
            cache.insertValuePair(buffer, res)
        }
        return res
    }

    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        if (Luposdate3000Instance.useDictionaryInlineEncoding) {
            if (DictionaryInlineValues.getValueById(buffer, value)) {
                return
            }
        }

        if (isLocal == ((value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal)) {
            if (Luposdate3000Instance.dictionaryCacheCapacity> 0) {
                val tmp = cache.getValueById(buffer, value)
                if (tmp) {
                    return
                }
            }
        } else {
            return instance.nodeGlobalDictionary!!.getValue(buffer, value)
        }
        output.writeInt(6)
        output.writeDictionaryValueType(value)
        output.flush()
        val len = input.readInt()
        ByteArrayWrapperExt.setSize(buffer, len, false)
        input.read(ByteArrayWrapperExt.getBuf(buffer), len)
        if (Luposdate3000Instance.dictionaryCacheCapacity> 0) {
            cache.insertValuePair(buffer, value)
        }
    }

    override fun close() {
        output.writeInt(0)
        output.flush()
        output.close()
        input.close()
    }
}
