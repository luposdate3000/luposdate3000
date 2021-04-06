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

import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.SanityCheck

public class DictionaryInMemory : ADictionary {
    private var dataI2V = Array<ByteArrayWrapper>(1) { ByteArrayWrapper() }
    private var dataV2I = mutableMapOf<ByteArrayWrapper, Int>()
    private var bNodeCounter = 5

    internal constructor(isLocal: Boolean) : super() {
        this.isLocal = isLocal
    }

    public override fun isInmemoryOnly(): Boolean = true
    public override fun close() {
    }

    public override fun delete() {
        close()
    }

    public override fun createNewBNode(): Int {
        var res: Int = bNodeCounter++
        if (isLocal) {
            res = res or ADictionary.flagLocal
        }
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        if (isLocal == ((value and ADictionary.flagLocal) == ADictionary.flagLocal)) {
            when (value) {
                DictionaryExt.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
                DictionaryExt.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
                DictionaryExt.errorValue -> DictionaryHelper.errorToByteArray(buffer)
                DictionaryExt.undefValue -> DictionaryHelper.undefToByteArray(buffer)
                DictionaryExt.nullValue -> throw Exception("invalid call")
                else -> {
                    if ((value and ADictionary.flagNoBNode) == ADictionary.flagNoBNode) {
                        val buf = dataI2V[value and ADictionary.maskValue]
                        buf.copyInto(buffer)
                    } else {
                        SanityCheck.check { value < bNodeCounter }
                        SanityCheck.check { value >= 0 }
                        DictionaryHelper.bnodeToByteArray(buffer, value and ADictionary.maskValue)
                    }
                }
            }
        } else {
            nodeGlobalDictionary.getValue(buffer, value)
        }
    }

    public override fun createValue(buffer: ByteArrayWrapper): Int {
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        when (type) {
            ETripleComponentTypeExt.BOOLEAN -> {
                if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    return DictionaryExt.booleanTrueValue
                } else {
                    return DictionaryExt.booleanFalseValue
                }
            }
            ETripleComponentTypeExt.ERROR -> return DictionaryExt.errorValue
            ETripleComponentTypeExt.UNDEF -> return DictionaryExt.undefValue
            else -> {
                if (isLocal) {
                    val tmp = nodeGlobalDictionary.hasValue(buffer)
                    if (tmp != null) {
                        return tmp
                    }
                }
                var res = dataV2I[buffer]
                if (res == null) {
                    res = dataV2I.size
                    val bufferCopy = ByteArrayWrapper()
                    buffer.copyInto(bufferCopy)
                    dataV2I[bufferCopy] = res
                    if (dataI2V.size <= res) {
                        val tmp = dataI2V
                        dataI2V = Array<ByteArrayWrapper>(dataI2V.size * 2) { bufferCopy }
                        tmp.copyInto(dataI2V)
                    }
                    dataI2V[res] = bufferCopy
                }
                if (isLocal) {
                    res = res or ADictionary.flagLocal
                }
                return res or ADictionary.flagNoBNode
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
        var res = dataV2I[buffer]
        if (res == null) {
            return null
        }
        return res or ADictionary.flagNoBNode
    }
}
