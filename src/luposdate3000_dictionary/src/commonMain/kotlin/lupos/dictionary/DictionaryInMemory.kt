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

public class DictionaryInMemory internal constructor(isLocal: Boolean, instance: Luposdate3000Instance) : ADictionary(instance) {
    init {
        this.isLocal = isLocal
    }

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
    }

    public override fun delete() {
        close()
    }

    public override fun createNewBNode(): Int {
        var res: Int = bNodeCounter++
        if (isLocal) {
            res = res or flagLocal
        }
        return res
    }

    public override fun createNewUUID(): Int {
        var res: Int = uuidCounter++
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        when (value) {
            DictionaryExt.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
            DictionaryExt.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
            DictionaryExt.errorValue -> DictionaryHelper.errorToByteArray(buffer)
            DictionaryExt.undefValue -> DictionaryHelper.undefToByteArray(buffer)
            DictionaryExt.nullValue -> throw Exception("invalid call")
            else -> {
                if (isLocal == ((value and flagLocal) == flagLocal)) {
                    if ((value and flagNoBNode) == flagNoBNode) {
                        val buf = dataI2V[value and maskValue]
                        ByteArrayWrapperExt.copyInto(buf, buffer)
                    } else {
                        SanityCheck.check({ (value and maskValue) < bNodeCounter }, { "$value < $bNodeCounter" })
                        SanityCheck.check({ (value and maskValue) >= 0 }, { " $value >= 0" })
                        DictionaryHelper.bnodeToByteArray(buffer, value and maskValue)
                    }
                } else {
                    instance.nodeGlobalDictionary!!.getValue(buffer, value)
                }
            }
        }
    }

    public override fun createValue(buffer: ByteArrayWrapper): Int {
        when (DictionaryHelper.byteArrayToType(buffer)) {
            ETripleComponentTypeExt.BLANK_NODE -> {
                val tmp = if (buffer.size == 8) {
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
                    res = res or flagLocal
                }
                return res or flagNoBNode
            }
        }
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { !isLocal }
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        SanityCheck.check { type != ETripleComponentTypeExt.BOOLEAN }
        SanityCheck.check { type != ETripleComponentTypeExt.ERROR }
        SanityCheck.check { type != ETripleComponentTypeExt.UNDEF }
        val res = dataV2I[buffer] ?: return null
        return res or flagNoBNode
    }
}
