package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.*
import lupos.s00misc.Coverage

var debugListLeaf = mutableListOf<Int>()

inline class NodeLeaf(val data: ByteArray) : Node { //ByteBuffer??
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
     * absolute minimum is 21 used bytes for exactly 1 Triple/Node
     */
    override fun getFirstTriple(b: IntArray) {
        b[0] = read4(9)
        b[1] = read4(13)
        b[2] = read4(17)
    }

    override fun setNextNode(node: Int) {
        write4(4, node)
    }

    override fun getNextNode(): Int {
        return read4(4)
    }

    override fun setTripleCount(count: Int) {
        write4(0, count)
    }

    override fun getTripleCount(): Int {
        return read4(0)
    }

    override fun iterator(): TripleIterator {
        return NodeLeafIterator(this)
    }

    override fun iterator3(prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix3(this, prefix)
    }

    override fun iterator2(prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix2(this, prefix)
    }

    override fun iterator1(prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix1(this, prefix)
    }

    fun read4(offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 24) or ((data[offset + 1].toInt() and 0xFF) shl 16) or ((data[offset + 2].toInt() and 0xFF) shl 8) or ((data[offset + 3].toInt() and 0xFF)))
    }

    fun read3(offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 16) or ((data[offset + 1].toInt() and 0xFF) shl 8) or ((data[offset + 2].toInt() and 0xFF)))
    }

    fun read2(offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF)))
    }

    fun read1(offset: Int): Int {
        return (data[offset].toInt() and 0xFF)
    }

    fun write4(offset: Int, d: Int) {
        data[offset] = ((d shr 24) and 0xFF).toByte()
        data[offset + 1] = ((d shr 16) and 0xFF).toByte()
        data[offset + 2] = ((d shr 8) and 0xFF).toByte()
        data[offset + 3] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read4(offset) }, { "$d ${read4(offset)} ${data[offset].toString(16)} ${data[offset + 1].toString(16)} ${data[offset + 2].toString(16)} ${data[offset + 3].toString(16)}" })
    }

    fun write3(offset: Int, d: Int) {
        data[offset] = ((d shr 16) and 0xFF).toByte()
        data[offset + 1] = ((d shr 8) and 0xFF).toByte()
        data[offset + 2] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read3(offset) }, { "$d ${read3(offset)}" })
    }

    fun write2(offset: Int, d: Int) {
        data[offset] = ((d shr 8) and 0xFF).toByte()
        data[offset + 1] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read2(offset) }, { "$d ${read2(offset)}" })
    }

    fun write1(offset: Int, d: Int) {
        data[offset] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read1(offset) }, { "$d ${read1(offset)}" })
    }

    fun writeFullTriple(offset: Int, d: IntArray): Int {
        /*
         * assuming enough space
         * return bytes written
         */
        write1(offset, 0b00111111)
        write4(offset + 1, d[0])
        write4(offset + 5, d[1])
        write4(offset + 9, d[2])
        debugListLeaf.add(d[0])
        debugListLeaf.add(d[1])
        debugListLeaf.add(d[2])
        return 13
    }

    fun writeDiffTriple(offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
        /*
         * assuming enough space
         * returns bytes written
         */
        debugListLeaf.add(d[0])
        debugListLeaf.add(d[1])
        debugListLeaf.add(d[2])
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
            write4(localOff, b[0])
            localOff += 4
            flag = true
        } else if (b[0] >= (1 shl 16)) {
            header = 0b00100000
            write3(localOff, b[0])
            localOff += 3
            flag = true
        } else if (b[0] >= (1 shl 8)) {
            header = 0b00010000
            write2(localOff, b[0])
            localOff += 2
            flag = true
        } else if (b[0] >= 0) {
            write1(localOff, b[0])
            localOff += 1
            flag = true
        }
        if (b[1] >= (1 shl 24)) {
            if (flag) {
                header = header or 0b00001100
            } else {
                header = 0b01001100
            }
            write4(localOff, b[1])
            localOff += 4
            flag = true
        } else if (b[1] >= (1 shl 16)) {
            if (flag) {
                header = header or 0b00001000
            } else {
                header = 0b01001000
            }
            write3(localOff, b[1])
            localOff += 3
            flag = true
        } else if (b[1] >= (1 shl 8)) {
            if (flag) {
                header = header or 0b00000100
            } else {
                header = 0b01000100
            }
            write2(localOff, b[1])
            localOff += 2
            flag = true
        } else {
            SanityCheck.check { b[1] >= 0 || flag }
            if (!flag) {
                header = 0b01000000
            }
            write1(localOff, b[1])
            localOff += 1
            flag = true
        }
        if (b[2] >= (1 shl 24)) {
            if (flag) {
                header = header or 0b00000011
            } else {
                header = 0b10000011
            }
            write4(localOff, b[2])
            localOff += 4
            flag = true
        } else if (b[2] >= (1 shl 16)) {
            if (flag) {
                header = header or 0b00000010
            } else {
                header = 0b10000010
            }
            write3(localOff, b[2])
            localOff += 3
            flag = true
        } else if (b[2] >= (1 shl 8)) {
            if (flag) {
                header = header or 0b00000001
            } else {
                header = 0b10000001
            }
            write2(localOff, b[2])
            localOff += 2
            flag = true
        } else {
            SanityCheck.check { b[2] >= 0 || flag }
            if (!flag) {
                header = 0b10000000
            }
            write1(localOff, b[2])
            localOff += 1
            flag = true
        }
        write1(offset, header)
        SanityCheck.check { flag }//otherwise this triple would equal the last one
        SanityCheck.check { localOff > offset + 1 }//at least ony byte must have been written additionally to the header
        return localOff - offset
    }

    fun initializeWith(iterator: TripleIterator) {
        SanityCheck {
            debugListLeaf.clear()
        }
        SanityCheck.check { iterator.hasNext() }
        var tripleCurrent = iterator.next()
        val tripleLast = intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2])
        val tripleBuf = IntArray(3)
        var offset = 8
        var bytesWritten = writeFullTriple(offset, tripleLast)
        offset += bytesWritten
        val offsetEnd = data.size - bytesWritten // reserve at least enough space to write a full triple at the end
        var triples = 1
        while (iterator.hasNext() && offset <= offsetEnd) {
            bytesWritten = writeDiffTriple(offset, tripleLast, iterator.next(), tripleBuf)
            offset += bytesWritten
            triples++
        }
        setTripleCount(triples)
        setNextNode(NodeManager.NodeNullPointer)
        SanityCheck {
            var it = iterator()
            var offset2 = 0
            for (i in debugListLeaf) {
//                println("original -> $i")
            }
            while (offset2 < debugListLeaf.size) {
                SanityCheck.check { it.hasNext() }
                var tmp = it.next()
//                println("retrieve -> ${tmp[0]}")
//                println("retrieve -> ${tmp[1]}")
//                println("retrieve -> ${tmp[2]}")
                SanityCheck.check { tmp[0] == debugListLeaf[offset2] }
                SanityCheck.check { tmp[1] == debugListLeaf[offset2 + 1] }
                SanityCheck.check { tmp[2] == debugListLeaf[offset2 + 2] }
                offset2 += 3
            }
            SanityCheck.check { !it.hasNext() }
        }
    }
}
