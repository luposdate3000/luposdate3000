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

package lupos.fileformat

import lupos.modulename.ByteArrayHelper
import lupos.modulename.File

public class TriplesIntermediateReader(filename: String) : TriplesIntermediate(filename) {
    init {
        streamIn = File("$filename$filenameEnding").openInputStream()
    }

    public inline fun readAll(crossinline action: (IntArray) -> Unit) {
        var tmp = next()
        while (tmp != null) {
            action(tmp)
            tmp = next()
        }
    }

    private val buf: ByteArray = ByteArray(12)
    private val buffer: IntArray = IntArray(3)

    public fun next(): IntArray? {
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
            buffer[0] = buffer[0] xor ByteArrayHelper.readIntX(buf, 0, counter0)
            buffer[1] = buffer[1] xor ByteArrayHelper.readIntX(buf, rel0, counter1)
            buffer[2] = buffer[2] xor ByteArrayHelper.readIntX(buf, rel1, counter2)
            return buffer
        }
    }

    public override fun close() {
        streamIn?.close()
        streamIn = null
    }
}
