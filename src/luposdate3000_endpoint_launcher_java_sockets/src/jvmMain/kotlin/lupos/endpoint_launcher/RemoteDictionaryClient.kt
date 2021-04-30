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
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared_inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

internal class RemoteDictionaryClient(@JvmField val input: IMyInputStream, @JvmField val output: IMyOutputStream) : ADictionary() {
    public override fun isInmemoryOnly(): Boolean = true
    public override fun delete() {
    }

    public override fun valueToGlobal(value: Int): Int {
        output.writeInt(3)
        output.writeInt(value)
        output.flush()
        return input.readInt()
    }

    override fun createNewBNode(): Int {
        output.writeInt(1)
        output.flush()
        return input.readInt()
    }

    override fun hasValue(buffer: ByteArrayWrapper): Int? {
        output.writeInt(2)
        output.writeInt(buffer.size)
        output.write(buffer.buf, buffer.size)
        output.flush()
        var res = input.readInt()
        if (res == DictionaryExt.nullValue) {
            return null
        }
        return res
    }

    override fun createValue(buffer: ByteArrayWrapper): Int {
        output.writeInt(5)
        output.writeInt(buffer.size)
        output.write(buffer.buf, buffer.size)
        output.flush()
        return input.readInt()
    }

    override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        output.writeInt(6)
        output.writeInt(value)
        output.flush()
        val len = input.readInt()
        ByteArrayWrapperExt.setSize(buffer, len)
        input.read(buffer.buf, len)
    }

    public override fun close() {
        output.writeInt(0)
        output.flush()
        output.close()
        input.close()
    }
}
