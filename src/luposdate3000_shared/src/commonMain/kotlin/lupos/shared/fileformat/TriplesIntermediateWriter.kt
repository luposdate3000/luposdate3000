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

import lupos.shared.inline.ByteArrayHelper
import lupos.shared.inline.File
import lupos.shared.inline.IntegerExt
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

public class TriplesIntermediateWriter : TriplesIntermediate {

    @JvmField
    internal var count = 0L

    @JvmField
    internal var last0: Int = 0

    @JvmField
    internal var last1: Int = 0

    @JvmField
    internal var last2: Int = 0

    @JvmField
    internal val buf: ByteArray = ByteArray(13)
private val writeOrder:EIndexPattern

    public constructor(filename: String, writeOrder:EIndexPattern) : super(filename) {
this.writeOrder=writeOrder
        streamOut = File("$filename$filenameEnding").openOutputStream(false)
streamOut.writeInt4(writeOrder)
    }

    public fun getCount(): Long = count
    public fun write(s: Int, p: Int, o: Int) {
SanityCheck.check{SanityCheck.ignoreTripleFlag||((s and SanityCheck.TRIPLE_FLAG_S) != SanityCheck.TRIPLE_FLAG_S)}
SanityCheck.check{SanityCheck.ignoreTripleFlag||((p and SanityCheck.TRIPLE_FLAG_P) != SanityCheck.TRIPLE_FLAG_P)}
SanityCheck.check{SanityCheck.ignoreTripleFlag||((o and SanityCheck.TRIPLE_FLAG_O) != SanityCheck.TRIPLE_FLAG_O)}
val l0:Int
val l1:Int
val l2:Int
when(writeOrder){
EIndexPatternExt.SPO->{
l0=s
l1=p
l2=o
}
EIndexPatternExt.SOP->{ 
l0=s
l1=o
l2=p
}
EIndexPatternExt.PSO->{ 
l0=p
l1=s
l2=o
}
EIndexPatternExt.POS->{
l0=p
l1=o
l2=s
}
EIndexPatternExt.OSP->{ 
l0=o
l1=s
l2=p
}
EIndexPatternExt.OPS->{
l0=o
l1=p
l2=s
}
}
        val b0 = last0 xor l0
        val b1 = last1 xor l1
        val b2 = last2 xor l2
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
            last0 = l0
            last1 = l1
            last2 = l2
        }
    }

    public override fun close() {
        buf[0] = 125
        streamOut?.write(buf, 1)
        streamOut?.close()
        streamOut = null
    }
}
