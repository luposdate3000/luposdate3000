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
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.MyReadWriteLock
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

internal class RemoteDictionaryServer(@JvmField val dictionary: IDictionary, instance: Luposdate3000Instance) : ADictionary(instance, true) {
    override fun forEachValue(buffer: ByteArrayWrapper, action: (Int) -> Unit): Unit = TODO()
    override fun isInmemoryOnly(): Boolean = true
    override fun delete() {
    }

    override fun close() {
    }

    @JvmField
    internal val lock = MyReadWriteLock()
    override fun valueToGlobal(value: Int): Int {
        var res: Int = 0
        lock.withWriteLock {
            res = dictionary.valueToGlobal(value)
        }
        return res
    }

    override fun createValue(buffer: ByteArrayWrapper): Int {
        return dictionary.createValue(buffer)
    }

    override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        dictionary.getValue(buffer, value)
    }

    override fun createNewBNode(): Int {
        return dictionary.createNewBNode()
    }

    override fun createNewUUID(): Int {
        return dictionary.createNewUUID()
    }

    override fun hasValue(buffer: ByteArrayWrapper): Int? {
        return dictionary.hasValue(buffer)
    }

    fun connect(input: IMyInputStream, output: IMyOutputStream) {
        val buffer = ByteArrayWrapper()
        loop@ while (true) {
            when (input.readInt()) {
                0 -> {
                    break@loop
                }
                1 -> {
                    val res = createNewBNode()
                    output.writeInt(res)
                }
                2 -> {
                    val len = input.readInt()
                    ByteArrayWrapperExt.setSize(buffer, len)
                    input.read(ByteArrayWrapperExt.getBuf(buffer), len)
                    val res = hasValue(buffer)
                    if (res == null) {
                        output.writeInt(DictionaryExt.nullValue)
                    } else {
                        output.writeInt(res)
                    }
                }
                3 -> {
                    val value = input.readInt()
                    output.writeInt(valueToGlobal(value))
                }
                5 -> {
                    val len = input.readInt()
                    ByteArrayWrapperExt.setSize(buffer, len)
                    input.read(ByteArrayWrapperExt.getBuf(buffer), len)
                    val res = createValue(buffer)
                    output.writeInt(res)
                }
                6 -> {
                    val value = input.readInt()
                    getValue(buffer, value)
                    output.writeInt(ByteArrayWrapperExt.getSize(buffer))
                    output.write(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer))
                }
                7 -> {
                    val res = createNewUUID()
                    output.writeInt(res)
                }
            }
            output.flush()
        }
    }
}
