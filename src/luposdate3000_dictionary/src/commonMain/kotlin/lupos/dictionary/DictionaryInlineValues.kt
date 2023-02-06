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
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

public object DictionaryInlineValues {

    public fun getValueByContent(buffer: ByteArrayWrapper): DictionaryValueType {
        if (SanityCheck.enabled) {
            val value = DictionaryHelper.byteArrayToType(buffer)
            if (SanityCheck.enabled) { if (!(ETripleComponentTypeExt.BLANK_NODE != value)) { throw Exception("SanityCheck failed") } }
        }

        val s = ByteArrayWrapperExt.getSize(buffer)
        if (s >= DictionaryValueHelper.getSize()) {
            return DictionaryValueHelper.nullValue
        }
        val b = ByteArrayWrapperExt.getBuf(buffer)
        val res = when (s) {
            // fill the values from lower bits first, because there might be 4 or 8 bytes in the return-value-datatype available
            0 -> TODO("DictionaryInlineValues")
            1 -> DictionaryValueHelper.flagNoBNode or DictionaryValueHelper.flagInlineValue1 or (DictionaryValueHelper.fromByte(b[0]) shl 0)
            2 -> DictionaryValueHelper.flagNoBNode or DictionaryValueHelper.flagInlineValue2 or (DictionaryValueHelper.fromByte(b[0]) shl 0) or (DictionaryValueHelper.fromByte(b[1]) shl 8)
            3 -> DictionaryValueHelper.flagNoBNode or DictionaryValueHelper.flagInlineValue3 or (DictionaryValueHelper.fromByte(b[0]) shl 0) or (DictionaryValueHelper.fromByte(b[1]) shl 8) or (DictionaryValueHelper.fromByte(b[2]) shl 16)
            4 -> DictionaryValueHelper.flagNoBNode or DictionaryValueHelper.flagInlineValue4 or (DictionaryValueHelper.fromByte(b[0]) shl 0) or (DictionaryValueHelper.fromByte(b[1]) shl 8) or (DictionaryValueHelper.fromByte(b[2]) shl 16) or (DictionaryValueHelper.fromByte(b[3]) shl 24)
            5 -> DictionaryValueHelper.flagNoBNode or DictionaryValueHelper.flagInlineValue5 or (DictionaryValueHelper.fromByte(b[0]) shl 0) or (DictionaryValueHelper.fromByte(b[1]) shl 8) or (DictionaryValueHelper.fromByte(b[2]) shl 16) or (DictionaryValueHelper.fromByte(b[3]) shl 24) or (DictionaryValueHelper.fromByte(b[4]) shl 32)
            6 -> DictionaryValueHelper.flagNoBNode or DictionaryValueHelper.flagInlineValue6 or (DictionaryValueHelper.fromByte(b[0]) shl 0) or (DictionaryValueHelper.fromByte(b[1]) shl 8) or (DictionaryValueHelper.fromByte(b[2]) shl 16) or (DictionaryValueHelper.fromByte(b[3]) shl 24) or (DictionaryValueHelper.fromByte(b[4]) shl 32) or (DictionaryValueHelper.fromByte(b[5]) shl 40)
            7 -> DictionaryValueHelper.flagNoBNode or DictionaryValueHelper.flagInlineValue7 or (DictionaryValueHelper.fromByte(b[0]) shl 0) or (DictionaryValueHelper.fromByte(b[1]) shl 8) or (DictionaryValueHelper.fromByte(b[2]) shl 16) or (DictionaryValueHelper.fromByte(b[3]) shl 24) or (DictionaryValueHelper.fromByte(b[4]) shl 32) or (DictionaryValueHelper.fromByte(b[5]) shl 40) or (DictionaryValueHelper.fromByte(b[6]) shl 48)
            else -> DictionaryValueHelper.nullValue
        }
        return res
    }

    public fun getValueById(buffer: ByteArrayWrapper, id: DictionaryValueType): Boolean {
        when (id and DictionaryValueHelper.flagInlineValue) {
            DictionaryValueHelper.flagInlineValue1 -> {
                ByteArrayWrapperExt.setSize(buffer, 1, false)
                val b = ByteArrayWrapperExt.getBuf(buffer)
                b[0] = ((id shr 0) and 0xFF).toByte()
                return true
            }
            DictionaryValueHelper.flagInlineValue2 -> {
                ByteArrayWrapperExt.setSize(buffer, 2, false)
                val b = ByteArrayWrapperExt.getBuf(buffer)
                b[0] = ((id shr 0) and 0xFF).toByte()
                b[1] = ((id shr 8) and 0xFF).toByte()
                return true
            }
            DictionaryValueHelper.flagInlineValue3 -> {
                ByteArrayWrapperExt.setSize(buffer, 3, false)
                val b = ByteArrayWrapperExt.getBuf(buffer)
                b[0] = ((id shr 0) and 0xFF).toByte()
                b[1] = ((id shr 8) and 0xFF).toByte()
                b[2] = ((id shr 16) and 0xFF).toByte()
                return true
            }
            DictionaryValueHelper.flagInlineValue4 -> {
                ByteArrayWrapperExt.setSize(buffer, 4, false)
                val b = ByteArrayWrapperExt.getBuf(buffer)
                b[0] = ((id shr 0) and 0xFF).toByte()
                b[1] = ((id shr 8) and 0xFF).toByte()
                b[2] = ((id shr 16) and 0xFF).toByte()
                b[3] = ((id shr 24) and 0xFF).toByte()
                return true
            }
            DictionaryValueHelper.flagInlineValue5 -> {
                ByteArrayWrapperExt.setSize(buffer, 5, false)
                val b = ByteArrayWrapperExt.getBuf(buffer)
                b[0] = ((id shr 0) and 0xFF).toByte()
                b[1] = ((id shr 8) and 0xFF).toByte()
                b[2] = ((id shr 16) and 0xFF).toByte()
                b[3] = ((id shr 24) and 0xFF).toByte()
                b[4] = ((id shr 32) and 0xFF).toByte()
                return true
            }
            DictionaryValueHelper.flagInlineValue6 -> {
                ByteArrayWrapperExt.setSize(buffer, 6, false)
                val b = ByteArrayWrapperExt.getBuf(buffer)
                b[0] = ((id shr 0) and 0xFF).toByte()
                b[1] = ((id shr 8) and 0xFF).toByte()
                b[2] = ((id shr 16) and 0xFF).toByte()
                b[3] = ((id shr 24) and 0xFF).toByte()
                b[4] = ((id shr 32) and 0xFF).toByte()
                b[5] = ((id shr 40) and 0xFF).toByte()
                return true
            }
            DictionaryValueHelper.flagInlineValue7 -> {
                ByteArrayWrapperExt.setSize(buffer, 7, false)
                val b = ByteArrayWrapperExt.getBuf(buffer)
                b[0] = ((id shr 0) and 0xFF).toByte()
                b[1] = ((id shr 8) and 0xFF).toByte()
                b[2] = ((id shr 16) and 0xFF).toByte()
                b[3] = ((id shr 24) and 0xFF).toByte()
                b[4] = ((id shr 32) and 0xFF).toByte()
                b[5] = ((id shr 40) and 0xFF).toByte()
                b[6] = ((id shr 48) and 0xFF).toByte()
                return true
            }
            else -> return false
        }
    }
}
