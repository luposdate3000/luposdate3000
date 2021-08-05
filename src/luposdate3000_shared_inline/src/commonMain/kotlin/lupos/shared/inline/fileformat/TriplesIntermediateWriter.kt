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
package lupos.shared.inline.fileformat

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.inline.Compressor
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.inline.ByteArrayHelper
import lupos.shared.inline.File
import kotlin.jvm.JvmField

internal class TriplesIntermediateWriter : TriplesIntermediate {

    @JvmField
    internal var count = 0L

    @JvmField
    internal var last0: DictionaryValueType = 0

    @JvmField
    internal var last1: DictionaryValueType = 0

    @JvmField
    internal var last2: DictionaryValueType = 0

    @JvmField
    internal val buf: ByteArray = ByteArray(13)
    private val writeOrder: EIndexPattern

    internal constructor(filename: String, writeOrder: EIndexPattern) : super(filename) {
        this.writeOrder = writeOrder
        streamOut = File("$filename$filenameEnding").openOutputStream(false)
        streamOut!!.writeInt(TriplesIntermediate.version)
        streamOut!!.writeInt(writeOrder)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getCount(): Long = count

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun write(s: DictionaryValueType, p: DictionaryValueType, o: DictionaryValueType) {
        val l0: DictionaryValueType
        val l1: DictionaryValueType
        val l2: DictionaryValueType
        when (writeOrder) {
            EIndexPatternExt.SPO -> {
                l0 = s
                l1 = p
                l2 = o
            }
            EIndexPatternExt.SOP -> {
                l0 = s
                l1 = o
                l2 = p
            }
            EIndexPatternExt.PSO -> {
                l0 = p
                l1 = s
                l2 = o
            }
            EIndexPatternExt.POS -> {
                l0 = p
                l1 = o
                l2 = s
            }
            EIndexPatternExt.OSP -> {
                l0 = o
                l1 = s
                l2 = p
            }
            EIndexPatternExt.OPS -> {
                l0 = o
                l1 = p
                l2 = s
            }
            else -> TODO()
        }
        val b0 = last0 xor l0
        val b1 = last1 xor l1
        val b2 = last2 xor l2
        var counter0 = DictionaryValueHelper.numberOfBytesUsed(b0)
        var counter1 = DictionaryValueHelper.numberOfBytesUsed(b1)
        var counter2 = DictionaryValueHelper.numberOfBytesUsed(b2)
var header=0
Compressor.encodeTripleHeader(counter0, counter1, counter2) { header0, corrected0, corrected1, corrected2 ->
            BufferManagerPage.writeInt1(node, offset, header)
            counter0 = corrected0
            counter1 = corrected1
            counter2 = corrected2
header=header0
        }
        if (header!=0) {
            count++
            val rel0 = counter0 + 1
            val rel1 = rel0 + counter1
            val rel2 = rel1 + counter2
            ByteArrayHelper.writeInt1(buf, 0, header)
            DictionaryValueHelper.toByteArrayX(buf, 1, b0, counter0)
            DictionaryValueHelper.toByteArrayX(buf, rel0, b1, counter1)
            DictionaryValueHelper.toByteArrayX(buf, rel1, b2, counter2)
            streamOut!!.write(buf, rel2)
            last0 = l0
            last1 = l1
            last2 = l2
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun close() {
        buf[0] = 0
        streamOut?.write(buf, 1)
        streamOut?.close()
        streamOut = null
    }
}
