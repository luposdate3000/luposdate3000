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
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternHelper
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.Compressor
import lupos.shared.inline.File
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public class TriplesIntermediateWriter : TriplesIntermediate {
    public val i0: Int
    public val i1: Int
    public val i2: Int

    @JvmField
    public var count: Long = 0L

    @JvmField
    public var last0: DictionaryValueType = 0

    @JvmField
    public var last1: DictionaryValueType = 0

    @JvmField
    public var last2: DictionaryValueType = 0

    @JvmField
    public val buf: ByteArray = ByteArray(25)

    @JvmField
    public val bufWrapper: ByteArrayWrapper = ByteArrayWrapper(buf, 24)
    public val writeOrder: EIndexPattern

    public constructor(filename: String, writeOrder: EIndexPattern) : super(filename) {
        this.writeOrder = writeOrder
        streamOut = File("$filename$filenameEnding").openOutputStream(false)
        streamOut!!.writeInt(TriplesIntermediate.version)
        streamOut!!.writeInt(writeOrder)
        i0 = EIndexPatternHelper.tripleIndicees[writeOrder][0]
        i1 = EIndexPatternHelper.tripleIndicees[writeOrder][1]
        i2 = EIndexPatternHelper.tripleIndicees[writeOrder][2]
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun getCount(): Long = count

    @Suppress("NOTHING_TO_INLINE")
    public inline fun write(row: DictionaryValueTypeArray) {
        val b0 = last0 xor row[i0]
        val b1 = last1 xor row[i1]
        val b2 = last2 xor row[i2]
        var counter0 = DictionaryValueHelper.numberOfBytesUsed(b0)
        var counter1 = DictionaryValueHelper.numberOfBytesUsed(b1)
        var counter2 = DictionaryValueHelper.numberOfBytesUsed(b2)
        var header = 0
        Compressor.encodeTripleHeader(counter0, counter1, counter2) { header0, corrected0, corrected1, corrected2 ->
            counter0 = corrected0
            counter1 = corrected1
            counter2 = corrected2
            header = header0
        }
        if (header != 0) {
            count++
            val rel0 = counter0 + 1
            val rel1 = rel0 + counter1
            val rel2 = rel1 + counter2
            ByteArrayWrapperExt.writeInt1(bufWrapper, 0, header)
            DictionaryValueHelper.toByteArrayX(bufWrapper, 1, b0, counter0)
            DictionaryValueHelper.toByteArrayX(bufWrapper, rel0, b1, counter1)
            DictionaryValueHelper.toByteArrayX(bufWrapper, rel1, b2, counter2)
            streamOut!!.write(buf, rel2)
            last0 = row[i0]
            last1 = row[i1]
            last2 = row[i2]
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun close() {
        buf[0] = 0
        streamOut?.write(buf, 1)
        streamOut?.close()
        streamOut = null
    }
}
