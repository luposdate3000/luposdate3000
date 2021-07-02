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
import lupos.shared.inline.DictionaryConstants
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public class DictionaryInMemory internal constructor(isLocal: Boolean, instance: Luposdate3000Instance) : ADictionary(instance, isLocal) {
    @JvmField
    internal val uuid = UUID_Counter.getNextUUID()

    @JvmField
    internal var dataI2V = Array(1) { ByteArrayWrapper() }

    @JvmField
    internal var dataV2I = mutableMapOf<ByteArrayWrapper, Int>()

    @JvmField
    internal var bNodeCounter = 5

    @JvmField
    internal var uuidCounter = 0

    public override fun isInmemoryOnly(): Boolean = true
    public override fun close() {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
    }

    public override fun delete() {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        close()
    }

    public override fun createNewBNode(): Int {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        var res: Int = bNodeCounter++
        if (isLocal) {
            res = res or DictionaryConstants.flagLocal
        }
        return res
    }

    public override fun createNewUUID(): Int {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        var res: Int = uuidCounter++
        return res
    }
    public override fun forEachValue(buffer: ByteArrayWrapper, action: (Int) -> Unit) {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        var flag = DictionaryConstants.flagNoBNode
        var flag2 = 0
        if (isLocal) {
            flag = flag or DictionaryConstants.flagLocal
            flag2 = flag2 or DictionaryConstants.flagLocal
        }
        DictionaryHelper.booleanToByteArray(buffer, true)
        action(DictionaryExt.booleanTrueValue)
        DictionaryHelper.booleanToByteArray(buffer, false)
        action(DictionaryExt.booleanFalseValue)
        DictionaryHelper.errorToByteArray(buffer)
        action(DictionaryExt.errorValue)
        DictionaryHelper.undefToByteArray(buffer)
        action(DictionaryExt.undefValue)
        for (i in 5 until bNodeCounter) {
            DictionaryHelper.bnodeToByteArray(buffer, i)
            action(i or flag2)
        }
        for ((k, v) in dataV2I) {
            ByteArrayWrapperExt.copyInto(k, buffer)
            action(v or flag)
        }
    }
    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        when (value) {
            DictionaryExt.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
            DictionaryExt.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
            DictionaryExt.errorValue -> DictionaryHelper.errorToByteArray(buffer)
            DictionaryExt.undefValue -> DictionaryHelper.undefToByteArray(buffer)
            DictionaryExt.nullValue -> throw Exception("invalid call")
            else -> {
                if (isLocal == ((value and DictionaryConstants.flagLocal) == DictionaryConstants.flagLocal)) {
                    SanityCheck.check({ (value and DictionaryConstants.maskValue) >= 0 }, { " $value >= 0" })
                    if ((value and DictionaryConstants.flagNoBNode) == DictionaryConstants.flagNoBNode) {
                        val buf = dataI2V[value and DictionaryConstants.maskValue]
                        ByteArrayWrapperExt.copyInto(buf, buffer)
                        SanityCheck.check({ ByteArrayWrapperExt.getSize(buffer) >= 4 }, { "xxx" + value })
                        SanityCheck.check({ (value and DictionaryConstants.maskValue) < dataV2I.size }, { "$value < ${dataV2I.size}" })
                    } else {
                        SanityCheck.check({ (value and DictionaryConstants.maskValue) < bNodeCounter }, { "$value < $bNodeCounter" })
                        DictionaryHelper.bnodeToByteArray(buffer, value and DictionaryConstants.maskValue)
                    }
                } else {
                    instance.nodeGlobalDictionary!!.getValue(buffer, value)
                }
            }
        }
        SanityCheck.check({ ByteArrayWrapperExt.getSize(buffer) >= 4 }, { "" + value })
    }

    public override fun createValue(buffer: ByteArrayWrapper): Int {
        SanityCheck.check { ByteArrayWrapperExt.getSize(buffer) >= 4 }
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        when (DictionaryHelper.byteArrayToType(buffer)) {
            ETripleComponentTypeExt.BLANK_NODE -> {
                val tmp = if (ByteArrayWrapperExt.getSize(buffer) == 8) {
                    DictionaryHelper.byteArrayToBnode_I(buffer)
                } else {
                    createNewBNode(DictionaryHelper.byteArrayToBnode_S(buffer))
                }
                return tmp
            }
            ETripleComponentTypeExt.BOOLEAN -> {
                val tmp = if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    DictionaryExt.booleanTrueValue
                } else {
                    DictionaryExt.booleanFalseValue
                }
                return tmp
            }
            ETripleComponentTypeExt.ERROR -> {
                return DictionaryExt.errorValue
            }
            ETripleComponentTypeExt.UNDEF -> {
                return DictionaryExt.undefValue
            }
            else -> {
                if (isLocal) {
                    val tmp = instance.nodeGlobalDictionary!!.hasValue(buffer)
                    if (tmp != null) {
                        return tmp
                    }
                }
                var res = dataV2I[buffer]
                if (res == null) {
                    res = dataV2I.size
                    val bufferCopy = ByteArrayWrapper()
                    ByteArrayWrapperExt.copyInto(buffer, bufferCopy)
                    dataV2I[bufferCopy] = res
                    if (dataI2V.size <= res) {
                        val tmp = dataI2V
                        dataI2V = Array(dataI2V.size * 2) { bufferCopy }
                        tmp.copyInto(dataI2V)
                    }
                    dataI2V[res] = bufferCopy
                }
                if (isLocal) {
                    res = res or DictionaryConstants.flagLocal
                }
                return res or DictionaryConstants.flagNoBNode
            }
        }
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        SanityCheck.check { ByteArrayWrapperExt.getSize(buffer) >= 4 }
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { !isLocal }
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        SanityCheck.check { type != ETripleComponentTypeExt.BOOLEAN }
        SanityCheck.check { type != ETripleComponentTypeExt.ERROR }
        SanityCheck.check { type != ETripleComponentTypeExt.UNDEF }
        val res = dataV2I[buffer]
        if (res == null) {
            return null
        }
        return res or DictionaryConstants.flagNoBNode
    }
}
