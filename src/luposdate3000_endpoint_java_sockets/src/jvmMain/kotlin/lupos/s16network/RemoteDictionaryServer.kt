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
package lupos.s16network

import lupos.dictionary.ADictionary
import lupos.dictionary.DictionaryExt
import lupos.dictionary.IDictionary
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.IMyInputStream
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyReadWriteLock
import kotlin.jvm.JvmField

internal class RemoteDictionaryServer(@JvmField val dictionary: IDictionary) : ADictionary() {
    @JvmField
    internal val lock = MyReadWriteLock()
    public override fun valueToGlobal(value: Int): Int {
        var res: Int?
        lock.withWriteLock {
            res = dictionary.valueToGlobal(value)
        }
        return res!!
    }

    override fun createValue(buffer: ByteArrayWrapper): Int {
        return dictionary.createValue(buffer)
    }

    override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        dictionary.getValue(buffer, value)
    }

    override fun importFromDictionaryFile(filename: String): IntArray = throw Exception("not implemented")
    override fun createNewBNode(): Int {
        return dictionary.createNewBNode()
    }

    override fun hasValue(buffer: ByteArrayWrapper): Int? {
        return dictionary.hasValue(buffer)
    }

    public fun connect(input: IMyInputStream, output: IMyOutputStream) {
        var buffer = ByteArrayWrapper()
        loop@ while (true) {
            val mode = input.readInt()
            when (mode) {
                0 -> {
                    break@loop
                }
                1 -> {
                    val res = createNewBNode()
                    output.writeInt(res)
                }
                2 -> {
                    val len = input.readInt()
                    buffer.setSize(len)
                    input.read(buffer.getBuf(), len)
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
                    buffer.setSize(len)
                    input.read(buffer.getBuf(), len)
                    val res = createValue(buffer)
                    output.writeInt(res)
                }
                6 -> {
                    val value = input.readInt()
                    getValue(buffer, value)
                    output.writeInt(buffer.getSize())
                    output.write(buffer.getBuf(), buffer.getSize())
                }
            }
            output.flush()
        }
    }
}
