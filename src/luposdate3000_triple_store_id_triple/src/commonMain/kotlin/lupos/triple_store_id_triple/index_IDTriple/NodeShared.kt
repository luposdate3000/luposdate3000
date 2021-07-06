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
package lupos.triple_store_id_triple.index_IDTriple

import lupos.shared.SanityCheck
import lupos.shared.inline.BufferManagerPage
import lupos.shared.inline.IntegerExt

internal object NodeShared {
    const val MAX_TRIPLE_SIZE = 13

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setNodeType(node: ByteArray, type: Int) {
        BufferManagerPage.writeInt4(node, 0, type)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getNodeType(node: ByteArray): Int {
        return BufferManagerPage.readInt4(node, 0)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setNextNode(node: ByteArray, nextNode: Int) {
        BufferManagerPage.writeInt4(node, 8, nextNode)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getNextNode(node: ByteArray): Int {
        return BufferManagerPage.readInt4(node, 8)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setTripleCount(node: ByteArray, count: Int) {
        BufferManagerPage.writeInt4(node, 4, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getTripleCount(node: ByteArray): Int {
        return BufferManagerPage.readInt4(node, 4)
    }

    internal inline fun decodeTripleHeader(header: Int, crossinline action: (counter0: Int, counter1: Int, counter2: Int) -> Unit) {
        action(header.rem(5), (header / 5).rem(5), header / 25)
    }

    private inline fun encodeTripleHeader(counter0: Int, counter1: Int, counter2: Int, crossinline action: (header: Int) -> Unit) {
        val header = counter0 + counter1 * 5 + counter2 * 25
        action(header)
        SanityCheck {
            decodeTripleHeader(header) { c0, c1, c2 ->
                SanityCheck.check { c0 == counter0 }
                SanityCheck.check { c1 == counter1 }
                SanityCheck.check { c2 == counter2 }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun numberOfBytesUsed(value: Int): Int {
        return (((32 + 7 - IntegerExt.numberOfLeadingZeros(value))) shr 3)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readTriple000(node: ByteArray, offset: Int): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0 + counter1 + counter2
        }
        return localOff - offset
    }

    internal inline fun readTriple111(node: ByteArray, offset: Int, d0: Int, d1: Int, d2: Int, crossinline action: (d0: Int, d1: Int, d2: Int) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor BufferManagerPage.readIntX(node, localOff, counter0)
            localOff += counter0
            val v1 = d1 xor BufferManagerPage.readIntX(node, localOff, counter1)
            localOff += counter1
            val v2 = d2 xor BufferManagerPage.readIntX(node, localOff, counter2)
            localOff += counter2
            action(v0, v1, v2)
        }
        return localOff - offset
    }

    internal inline fun readTriple010(node: ByteArray, offset: Int, d1: Int, crossinline action: (d1: Int) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0
            val v1 = d1 xor BufferManagerPage.readIntX(node, localOff, counter1)
            localOff += counter1 + counter2
            action(v1)
        }
        return localOff - offset
    }

    internal inline fun readTriple001(node: ByteArray, offset: Int, d2: Int, crossinline action: (d2: Int) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0 + counter1
            val v2 = d2 xor BufferManagerPage.readIntX(node, localOff, counter2)
            localOff += counter2
            action(v2)
        }
        return localOff - offset
    }

    internal inline fun readTriple100(node: ByteArray, offset: Int, d0: Int, crossinline action: (d0: Int) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor BufferManagerPage.readIntX(node, localOff, counter0)
            localOff += counter0 + counter1 + counter2
            action(v0)
        }
        return localOff - offset
    }

    internal inline fun readTriple110(node: ByteArray, offset: Int, d0: Int, d1: Int, crossinline action: (d0: Int, d1: Int) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor BufferManagerPage.readIntX(node, localOff, counter0)
            localOff += counter0
            val v1 = d1 xor BufferManagerPage.readIntX(node, localOff, counter1)
            localOff += counter1 + counter2
            action(v0, v1)
        }
        return localOff - offset
    }

    internal inline fun readTriple101(node: ByteArray, offset: Int, d0: Int, d2: Int, crossinline action: (d0: Int, d2: Int) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor BufferManagerPage.readIntX(node, localOff, counter0)
            localOff += counter0 + counter1
            val v2 = d2 xor BufferManagerPage.readIntX(node, localOff, counter2)
            localOff += counter2
            action(v0, v2)
        }
        return localOff - offset
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeTriple(node: ByteArray, offset: Int, l: IntArray, d: IntArray): Int {
        val b0 = l[0] xor d[0]
        val b1 = l[1] xor d[1]
        val b2 = l[2] xor d[2]
        SanityCheck.check { d[0] >= 0 }
        SanityCheck.check { d[1] >= 0 }
        SanityCheck.check { d[2] >= 0 }
        SanityCheck.check { l[0] >= 0 }
        SanityCheck.check { l[1] >= 0 }
        SanityCheck.check { l[2] >= 0 }
        SanityCheck.check { b0 >= 0 }
        SanityCheck.check { b1 >= 0 }
        SanityCheck.check { b2 >= 0 }
        val counter0 = numberOfBytesUsed(b0)
        val counter1 = numberOfBytesUsed(b1)
        val counter2 = numberOfBytesUsed(b2)
        encodeTripleHeader(counter0, counter1, counter2) {
            BufferManagerPage.writeInt1(node, offset, it)
        }
        var localOff = offset + 1
        BufferManagerPage.writeIntX(node, localOff, b0, counter0)
        localOff += counter0
        BufferManagerPage.writeIntX(node, localOff, b1, counter1)
        localOff += counter1
        BufferManagerPage.writeIntX(node, localOff, b2, counter2)
        localOff += counter2
        SanityCheck {
            var size = readTriple000(node, offset)
            SanityCheck.check { size == localOff - offset }
            size = readTriple100(node, offset, l[0]) { n0 ->
                SanityCheck.check { n0 == d[0] }
            }
            SanityCheck.check { size == localOff - offset }
            size = readTriple010(node, offset, l[1]) { n1 ->
                SanityCheck.check { n1 == d[1] }
            }
            SanityCheck.check { size == localOff - offset }
            size = readTriple001(node, offset, l[2]) { n2 ->
                SanityCheck.check { n2 == d[2] }
            }
            SanityCheck.check { size == localOff - offset }
            size = readTriple110(node, offset, l[0], l[1]) { n0, n1 ->
                SanityCheck.check { n0 == d[0] }
                SanityCheck.check { n1 == d[1] }
            }
            SanityCheck.check { size == localOff - offset }
            size = readTriple101(node, offset, l[0], l[2]) { n0, n2 ->
                SanityCheck.check { n0 == d[0] }
                SanityCheck.check { n2 == d[2] }
            }
            SanityCheck.check { size == localOff - offset }
            size = readTriple111(node, offset, l[0], l[1], l[2]) { n0, n1, n2 ->
                SanityCheck.check { n0 == d[0] }
                SanityCheck.check { n1 == d[1] }
                SanityCheck.check { n2 == d[2] }
            }
            SanityCheck.check { size == localOff - offset }
        }
        l[0] = d[0]
        l[1] = d[1]
        l[2] = d[2]
        return localOff - offset
    }
}
