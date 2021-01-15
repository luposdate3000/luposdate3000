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

package lupos.s05tripleStore
public object TripleStoreBulkImportExt {
    private fun mergeSort(source: IntArray, target: IntArray, off: Int, mid: Int, count: Int, orderBy: IntArray) {
        // assuming that "off .. off + count / 2" and "off + count / 2 .. off + count" are sorted
        val aEnd = (off + mid) * 3
        val bEnd = (off + count) * 3
        var a = off * 3
        var b = aEnd
        var c = a
        if (count < mid) {
            b = a
            a = aEnd
        }
        loop@ while (a < aEnd && b < bEnd) {
            for (i in 0 until 3) {
                if (source[a + orderBy[i]] < source[b + orderBy[i]]) {
                    target[c++] = source[a++]
                    target[c++] = source[a++]
                    target[c++] = source[a++]
                    continue@loop
                } else if (source[a + orderBy[i]] > source[b + orderBy[i]]) {
                    target[c++] = source[b++]
                    target[c++] = source[b++]
                    target[c++] = source[b++]
                    continue@loop
                }
            }
            target[c++] = source[a++]
            target[c++] = source[a++]
            target[c++] = source[a++]
            target[c++] = source[b++]
            target[c++] = source[b++]
            target[c++] = source[b++]
        }
        while (a < aEnd) {
            target[c++] = source[a++]
            target[c++] = source[a++]
            target[c++] = source[a++]
        }
        while (b < bEnd) {
            target[c++] = source[b++]
            target[c++] = source[b++]
            target[c++] = source[b++]
        }
    }
    public fun sortUsingBuffers(firstIdx: Int, dataIdxA: Int, dataIdxB: Int, data: Array<IntArray>, total: Int, order: IntArray) {
        /*in the first step the data is moved into dataIdxB*/
        var off: Int
        var shift = 0
        var count = 1 shl shift
        var first = true
        while (count / 2 < total) {
            off = 0
            shift++
            count = 1 shl shift
            var sourceIdx = dataIdxA
            if (first) {
                first = false
                sourceIdx = firstIdx
            }
            while (off + count <= total) {
                mergeSort(data[sourceIdx], data[dataIdxB], off, count / 2, count, order)
                off += count
            }
            if (off < total) {
                mergeSort(data[sourceIdx], data[dataIdxB], off, count / 2, total - off, order)
            }
            val t = data[dataIdxA]
            data[dataIdxA] = data[dataIdxB]
            data[dataIdxB] = t
        }
    }
}
