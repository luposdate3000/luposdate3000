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

package lupos.shared.inline

import lupos.shared.SanityCheck

public object Compressor {
    public val decodeTripleHeaderMapA :IntArray= intArrayOf(0, 1, 2, 8)
    public val decodeTripleHeaderMapBC :IntArray= intArrayOf(0, 1, 2, 3, 4, 5, 6, 8)
    public inline fun decodeTripleHeader(header: Int, crossinline action: (counter0: Int, counter1: Int, counter2: Int) -> Unit) {
        val a = decodeTripleHeaderMapA[(header shr 6) and 0x3]
        val b = decodeTripleHeaderMapBC[(header shr 3) and 0x7]
        val c = decodeTripleHeaderMapBC[header and 0x7]
        action(a, b, c)
    }

    public val encodeTripleHeaderMapA :IntArray= intArrayOf(0, 0x40, 0x80, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0)
    public val encodeTripleHeaderMapB :IntArray= intArrayOf(0, 0x08, 0x10, 0x18, 0x20, 0x28, 0x30, 0x38, 0x38)
    public val encodeTripleHeaderMapC :IntArray= intArrayOf(0, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x07)
    public val encodeTripleHeaderCorrectA :IntArray= intArrayOf(0, 1, 2, 8, 8, 8, 8, 8, 8)
    public val encodeTripleHeaderCorrectBC :IntArray= intArrayOf(0, 1, 2, 3, 4, 5, 6, 8, 8)
    public inline fun encodeTripleHeader(counter0: Int, counter1: Int, counter2: Int, crossinline action: (header: Int, corrected0: Int, corrected1: Int, corrected2: Int) -> Unit) {
        val header = encodeTripleHeaderMapA[counter0] or encodeTripleHeaderMapB[counter1] or encodeTripleHeaderMapC[counter2]
        val corrected0: Int = encodeTripleHeaderCorrectA[counter0]
        val corrected1: Int = encodeTripleHeaderCorrectBC[counter1]
        val corrected2: Int = encodeTripleHeaderCorrectBC[counter2]
        action(header, corrected0, corrected1, corrected2)
    }
}
