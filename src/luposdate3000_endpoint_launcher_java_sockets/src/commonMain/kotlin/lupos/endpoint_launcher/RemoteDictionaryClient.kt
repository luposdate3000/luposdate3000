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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.dictionary.DictionaryInlineValues
import kotlin.jvm.JvmField

internal class RemoteDictionaryClient : ADictionary {
@JvmField val input: IMyInputStream,
    @JvmField val output: IMyOutputStream,

internal companion object {
operator fun invoke(
input: IMyInputStream,
     output: IMyOutputStream,
    instance: Luposdate3000Instance,
    isLocal: Boolean,
):DictionaryCacheLayer{
return DictionaryCacheLayer(instance,
RemoteDictionaryClient(
input,
output,
instance,
isLocal,
0,
),
)
}
}
    
private constructor( input: IMyInputStream,
     output: IMyOutputStream,
    instance: Luposdate3000Instance,
    isLocal: Boolean,
unusedParam:Int,
):super(instance, isLocal){
this.input=input
this.output=output
}


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
        if (instance.allowDistributedBNodeAssignment) {
            return instance.nodeGlobalDictionary!!.createNewBNode()
        } else {
            output.writeInt(1)
            output.flush()
            return input.readDictionaryValueType()
        }
    }

    override fun createNewUUID(): Int {
        output.writeInt(7)
        output.flush()
        return input.readInt()
    }

    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
        output.writeInt(2)
        output.writeInt(ByteArrayWrapperExt.getSize(buffer))
        output.write(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer))
        output.flush()
        val res = input.readDictionaryValueType()
        if (res == DictionaryValueHelper.nullValue) {
            return DictionaryValueHelper.nullValue
        }
        return res
    }

    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        output.writeInt(5)
        output.writeInt(ByteArrayWrapperExt.getSize(buffer))
        output.write(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer))
        output.flush()
        val res = input.readDictionaryValueType()
        return res
    }

    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        output.writeInt(6)
        output.writeDictionaryValueType(value)
        output.flush()
        val len = input.readInt()
        ByteArrayWrapperExt.setSize(buffer, len, false)
        input.read(ByteArrayWrapperExt.getBuf(buffer), len)
    }

    override fun close() {
        output.writeInt(0)
        output.flush()
        output.close()
        input.close()
    }
}
