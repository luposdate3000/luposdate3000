package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.readIntX
import lupos.s00misc.SanityCheck
import lupos.s00misc.writeInt1
import lupos.s00misc.writeInt2
import lupos.s00misc.writeInt3
import lupos.s00misc.writeInt4
import lupos.s00misc.writeIntX

object NodeShared {
    const val MAX_TRIPLE_SIZE = 13
    inline fun setNodeType(node: ByteArray, type: Int) {
        node.writeInt4(0, type)
    }

    inline fun getNodeType(node: ByteArray): Int {
        return node.readInt4(0)
    }

    inline fun setNextNode(node: ByteArray, nextNode: Int) {
        node.writeInt4(8, nextNode)
    }

    inline fun getNextNode(node: ByteArray): Int {
        return node.readInt4(8)
    }

    inline fun setTripleCount(node: ByteArray, count: Int) {
        node.writeInt4(4, count)
    }

    inline fun getTripleCount(node: ByteArray): Int {
        return node.readInt4(4)
    }

    inline fun decodeTripleHeader(header: Int, crossinline action: (counter0: Int, counter1: Int, counter2: Int) -> Unit) {
        action(header.rem(5), (header / 5).rem(5), header / 25)
    }

    inline fun encodeTripleHeader(counter0: Int, counter1: Int, counter2: Int, crossinline action: (header: Int) -> Unit) {
        var header = counter0 + counter1 * 5 + counter2 * 25
        action(header)
        SanityCheck {
            decodeTripleHeader(header) { c0, c1, c2 ->
                SanityCheck.check { c0 == counter0 }
                SanityCheck.check { c1 == counter1 }
                SanityCheck.check { c2 == counter2 }
            }
        }
    }

    inline fun numberOfBytesUsed(value: Int): Int {
        return (((32 + 7 - Integer.numberOfLeadingZeros(value))) shr 3)
    }

    inline fun readTriple000(node: ByteArray, offset: Int): Int {
        var header = node.readInt1(offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0 + counter1 + counter2
        }
        return localOff - offset
    }

    inline fun readTriple111(node: ByteArray, offset: Int, d0: Int, d1: Int, d2: Int, crossinline action: (d0: Int, d1: Int, d2: Int) -> Unit): Int {
        var header = node.readInt1(offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor node.readIntX(localOff, counter0)
            localOff += counter0
            val v1 = d1 xor node.readIntX(localOff, counter1)
            localOff += counter1
            val v2 = d2 xor node.readIntX(localOff, counter2)
            localOff += counter2
            action(v0, v1, v2)
        }
        return localOff - offset
    }

    inline fun readTriple010(node: ByteArray, offset: Int, d1: Int, crossinline action: (d1: Int) -> Unit): Int {
        var header = node.readInt1(offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0
            val v1 = d1 xor node.readIntX(localOff, counter1)
            localOff += counter1 + counter2
            action(v1)
        }
        return localOff - offset
    }

    inline fun readTriple001(node: ByteArray, offset: Int, d2: Int, crossinline action: (d2: Int) -> Unit): Int {
        var header = node.readInt1(offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0 + counter1
            val v2 = d2 xor node.readIntX(localOff, counter2)
            localOff += counter2
            action(v2)
        }
        return localOff - offset
    }

    inline fun readTriple100(node: ByteArray, offset: Int, d0: Int, crossinline action: (d0: Int) -> Unit): Int {
        var header = node.readInt1(offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor node.readIntX(localOff, counter0)
            localOff += counter0 + counter1 + counter2
            action(v0)
        }
        return localOff - offset
    }

    inline fun readTriple110(node: ByteArray, offset: Int, d0: Int, d1: Int, crossinline action: (d0: Int, d1: Int) -> Unit): Int {
        var header = node.readInt1(offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor node.readIntX(localOff, counter0)
            localOff += counter0
            val v1 = d1 xor node.readIntX(localOff, counter1)
            localOff += counter1 + counter2
            action(v0, v1)
        }
        return localOff - offset
    }

    inline fun readTriple101(node: ByteArray, offset: Int, d0: Int, d2: Int, crossinline action: (d0: Int, d2: Int) -> Unit): Int {
        var header = node.readInt1(offset)
        var localOff = offset + 1
        decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor node.readIntX(localOff, counter0)
            localOff += counter0 + counter1
            val v2 = d2 xor node.readIntX(localOff, counter2)
            localOff += counter2
            action(v0, v2)
        }
        return localOff - offset
    }


    /*inline*/ fun writeTriple(node: ByteArray, offset: Int, l: IntArray, d: IntArray): Int {
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
            node.writeInt1(offset, it)
        }
        var localOff = offset + 1
        node.writeIntX(localOff, b0, counter0)
        localOff += counter0
        node.writeIntX(localOff, b1, counter1)
        localOff += counter1
        node.writeIntX(localOff, b2, counter2)
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
