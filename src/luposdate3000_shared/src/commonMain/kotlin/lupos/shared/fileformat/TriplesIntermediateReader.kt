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

package lupos.shared.fileformat

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternHelper
import lupos.shared.SanityCheck
import lupos.shared.inline.File
import kotlin.jvm.JvmField
public class TriplesIntermediateReader(filename: String) : TriplesIntermediate(filename) {
    private val writeOrder: EIndexPattern
    private val i0: Int
    private val i1: Int
    private val i2: Int
    init {
        streamIn = File("$filename$filenameEnding").openInputStream()
        val version = streamIn!!.readInt()
        if (version != TriplesIntermediate.version) {
            throw Exception("incompatible file format version in '$filename'. Expected ${TriplesIntermediate.version}, but found $version")
        }
        writeOrder = streamIn!!.readInt()
        i0 = EIndexPatternHelper.tripleIndicees[writeOrder][0]
        i1 = EIndexPatternHelper.tripleIndicees[writeOrder][1]
        i2 = EIndexPatternHelper.tripleIndicees[writeOrder][2]
    }

    public inline fun readAll(crossinline action: (DictionaryValueTypeArray) -> Unit) {
        var tmp = next()
        while (tmp != null) {
            action(tmp)
            tmp = next()
        }
    }

    @JvmField
    internal val buf: ByteArray = ByteArray(12)

    @JvmField
    internal val buffer: DictionaryValueTypeArray = DictionaryValueTypeArray(3)

    public fun next(): DictionaryValueTypeArray? {
        val header = streamIn!!.readByte()
        if (header == 125.toByte()) {
            close()
            return null
        } else {
            val counter0 = header.rem(5)
            val counter1 = (header / 5).rem(5)
            val counter2 = header / 25
            val rel0 = counter0
            val rel1 = rel0 + counter1
            val rel2 = rel1 + counter2
            streamIn!!.read(buf, rel2)
            val b0 = DictionaryValueHelper.fromByteArrayX(buf, 0, counter0)
            val b1 = DictionaryValueHelper.fromByteArrayX(buf, rel0, counter1)
            val b2 = DictionaryValueHelper.fromByteArrayX(buf, rel1, counter2)
            buffer[i0] = buffer[i0] xor b0
            buffer[i1] = buffer[i1] xor b1
            buffer[i2] = buffer[i2] xor b2
            return buffer
        }
    }

    public override fun close() {
        streamIn?.close()
        streamIn = null
    }
}
