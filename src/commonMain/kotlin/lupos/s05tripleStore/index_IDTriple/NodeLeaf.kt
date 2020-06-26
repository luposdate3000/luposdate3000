package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage
import lupos.s00misc.readInt4
import lupos.s00misc.SanityCheck
import lupos.s00misc.writeInt1
import lupos.s00misc.writeInt2
import lupos.s00misc.writeInt3
import lupos.s00misc.writeInt4

var debugListLeaf = mutableListOf<Int>()

object NodeLeafFunctions {
    /*
     * Bytes 0..3 : Number of stored Triples
     * Bytes 4..7 : next-page-pointer, 0x8FFFFFFF is the "null"-pointer avoiding the highest bit because of the signedness behaviour of java/kotlin
     * afterwards :
     *
     * header (Bitlayout 7..0)
     * bits 0..1: # Bytes _for S (00->1,01->2,10->3,11->4)
     * bits 2..3: # Bytes _for P (00->1,01->2,10->3,11->4)
     * bits 4..5: # Bytes _for O (00->1,01->2,10->3,11->4)
     * bits 6..7: (00->SPO,01->PO,10->O,11->undefined)
     *
     * absolute minimum is 21 used bytes for_ exactly 1 Triple/Node
     */
    fun getFirstTriple(data: ByteArray,b: IntArray) {
        b[0] = data.readInt4(9)
        b[1] = data.readInt4(13)
        b[2] = data.readInt4(17)
    }

    fun setNextNode(data: ByteArray,node: Int) {
        data.writeInt4(4, node)
    }

    fun getNextNode(data: ByteArray): Int {
        return data.readInt4(4)
    }

    fun setTripleCount(data: ByteArray,count: Int) {
        data.writeInt4(0, count)
    }

    fun getTripleCount(data: ByteArray): Int {
        return data.readInt4(0)
    }

    fun iterator(data: ByteArray): TripleIterator {
        return NodeLeafIterator(data)
    }

    fun iterator3(data: ByteArray,prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix3(data, prefix)
    }

    fun iterator2(data: ByteArray,prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix2(data, prefix)
    }

    fun iterator1(data: ByteArray,prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix1(data, prefix)
    }

    /*inline*/ fun writeFullTriple(data: ByteArray,offset: Int, d: IntArray): Int {
        /*
         * assuming enough space
         * return bytes written
         */
        data.writeInt1(offset, 0b00111111)
        data.writeInt4(offset + 1, d[0])
        data.writeInt4(offset + 5, d[1])
        data.writeInt4(offset + 9, d[2])
        SanityCheck {
            debugListLeaf.add(d[0])
            debugListLeaf.add(d[1])
            debugListLeaf.add(d[2])
        }
        return 13
    }

    /*inline*/ fun writeDiffTriple(data: ByteArray,offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
        /*
         * assuming enough space
         * returns bytes written
         */
        SanityCheck {
            debugListLeaf.add(d[0])
            debugListLeaf.add(d[1])
            debugListLeaf.add(d[2])
        }
        b[0] = l[0] xor d[0]
        b[1] = l[1] xor d[1]
        b[2] = l[2] xor d[2]
        l[0] = d[0]
        l[1] = d[1]
        l[2] = d[2]
        SanityCheck {
            SanityCheck.check { d[0] >= 0 }
            SanityCheck.check { d[1] >= 0 }
            SanityCheck.check { d[2] >= 0 }
            SanityCheck.check { l[0] >= 0 }
            SanityCheck.check { l[1] >= 0 }
            SanityCheck.check { l[2] >= 0 }
            SanityCheck.check { b[0] >= 0 }
            SanityCheck.check { b[1] >= 0 }
            SanityCheck.check { b[2] >= 0 }
        }
        var header = 0b00000000
        var localOff = offset + 1
        var flag = false
        if (b[0] >= (1 shl 24)) {
            header = 0b00110000
            data.writeInt4(localOff, b[0])
            localOff += 4
            flag = true
        } else if (b[0] >= (1 shl 16)) {
            header = 0b00100000
            data.writeInt3(localOff, b[0])
            localOff += 3
            flag = true
        } else if (b[0] >= (1 shl 8)) {
            header = 0b00010000
            data.writeInt2(localOff, b[0])
            localOff += 2
            flag = true
        } else if (b[0] >= 0) {
            data.writeInt1(localOff, b[0])
            localOff += 1
            flag = true
        }
        if (b[1] >= (1 shl 24)) {
            if (flag) {
                header = header or 0b00001100
            } else {
                header = 0b01001100
            }
            data.writeInt4(localOff, b[1])
            localOff += 4
            flag = true
        } else if (b[1] >= (1 shl 16)) {
            if (flag) {
                header = header or 0b00001000
            } else {
                header = 0b01001000
            }
            data.writeInt3(localOff, b[1])
            localOff += 3
            flag = true
        } else if (b[1] >= (1 shl 8)) {
            if (flag) {
                header = header or 0b00000100
            } else {
                header = 0b01000100
            }
            data.writeInt2(localOff, b[1])
            localOff += 2
            flag = true
        } else {
            SanityCheck.check { b[1] >= 0 || flag }
            if (!flag) {
                header = 0b01000000
            }
            data.writeInt1(localOff, b[1])
            localOff += 1
            flag = true
        }
        if (b[2] >= (1 shl 24)) {
            if (flag) {
                header = header or 0b00000011
            } else {
                header = 0b10000011
            }
            data.writeInt4(localOff, b[2])
            localOff += 4
            flag = true
        } else if (b[2] >= (1 shl 16)) {
            if (flag) {
                header = header or 0b00000010
            } else {
                header = 0b10000010
            }
            data.writeInt3(localOff, b[2])
            localOff += 3
            flag = true
        } else if (b[2] >= (1 shl 8)) {
            if (flag) {
                header = header or 0b00000001
            } else {
                header = 0b10000001
            }
            data.writeInt2(localOff, b[2])
            localOff += 2
            flag = true
        } else {
            SanityCheck.check { b[2] >= 0 || flag }
            if (!flag) {
                header = 0b10000000
            }
            data.writeInt1(localOff, b[2])
            localOff += 1
            flag = true
        }
        data.writeInt1(offset, header)
        SanityCheck.check { flag }//otherwise this triple would equal the last one
        SanityCheck.check { localOff > offset + 1 }//at least ony byte must have been written additionally to the header
        return localOff - offset
    }

    fun initializeWith(data: ByteArray,iterator: TripleIterator) {
        SanityCheck {
            debugListLeaf.clear()
        }
        SanityCheck.check { iterator.hasNext() }
        var tripleCurrent = iterator.next()
        val tripleLast = intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2])
        val tripleBuf = IntArray(3)
        var offset = 8
        var bytesWritten = writeFullTriple(data,offset, tripleLast)
        offset += bytesWritten
        val offsetEnd = data.size - bytesWritten // reserve at least enough space to write a full triple at the end
        var triples = 1
        while (iterator.hasNext() && offset <= offsetEnd) {
            bytesWritten = writeDiffTriple(data,offset, tripleLast, iterator.next(), tripleBuf)
            offset += bytesWritten
            triples++
        }
        setTripleCount(data,triples)
        setNextNode(data,NodeManager.nodeNullPointer)
        SanityCheck {
            var it = iterator(data)
            var offset2 = 0
            while (offset2 < debugListLeaf.size) {
                SanityCheck.check { it.hasNext() }
                var tmp = it.next()
                SanityCheck.check { tmp[0] == debugListLeaf[offset2] }
                SanityCheck.check { tmp[1] == debugListLeaf[offset2 + 1] }
                SanityCheck.check { tmp[2] == debugListLeaf[offset2 + 2] }
                offset2 += 3
            }
            SanityCheck.check { !it.hasNext() }
        }
    }
}
