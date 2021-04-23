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
lupos.shared.fileformat

import lupos.shared_inline.ByteArrayHelper
import lupos.shared_inline.File
import lupos.shared_inline.IntegerExt
import kotlin.jvm.JvmField

public class TriplesIntermediateWriter : TriplesIntermediate {

    @JvmField
    internal var count = 0L

    @JvmField
    internal var lastS: Int = 0

    @JvmField
    internal var lastP: Int = 0

    @JvmField
    internal var lastO: Int = 0

    @JvmField
    internal val buf: ByteArray = ByteArray(13)

    public constructor(filename: String) : super(filename) {
        streamOut = File("$filename$filenameEnding").openOutputStream(false)
    }

    public fun getCount(): Long = count
    public fun write(s: Int, p: Int, o: Int) {
        val b0 = lastS xor s
        val b1 = lastP xor p
        val b2 = lastO xor o
        val counter0 = (((32 + 7 - IntegerExt.numberOfLeadingZeros(b0))) shr 3)
        val counter1 = (((32 + 7 - IntegerExt.numberOfLeadingZeros(b1))) shr 3)
        val counter2 = (((32 + 7 - IntegerExt.numberOfLeadingZeros(b2))) shr 3)
        val header = counter0 + counter1 * 5 + counter2 * 25
        if (header != 0) {
            count++
            val rel0 = counter0 + 1
            val rel1 = rel0 + counter1
            val rel2 = rel1 + counter2
            ByteArrayHelper.writeInt1(buf, 0, header)
            ByteArrayHelper.writeIntX(buf, 1, b0, counter0)
            ByteArrayHelper.writeIntX(buf, rel0, b1, counter1)
            ByteArrayHelper.writeIntX(buf, rel1, b2, counter2)
            streamOut!!.write(buf, rel2)
            lastS = s
            lastP = p
            lastO = o
        }
    }

    public override fun close() {
        buf[0] = 125
        streamOut?.write(buf, 1)
        streamOut?.close()
        streamOut = null
    }
}
