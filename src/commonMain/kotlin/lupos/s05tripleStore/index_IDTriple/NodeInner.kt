package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.*
import lupos.s00misc.Coverage

inline class NodeInner(val data: ByteArray) : Node { //ByteBuffer??
    /*
     * Bitlayout 7..0
     * Bytes 0..3  : Number of stored Triples
     * Bytes 4..7  : next-page-pointer, 0x8FFFFFFF is the "null"-pointer avoiding the highest bit because of the signedness behaviour of java/kotlin
     * Bytes 8..11 : first child-page-pointer
     *
     * triple-group:
     * bits 0..1: # Bytes _for S (00->1,01->2,10->3,11->4)
     * bits 2..3: # Bytes _for P (00->1,01->2,10->3,11->4)
     * bits 4..5: # Bytes _for O (00->1,01->2,10->3,11->4)
     * bits 6..7: (00->SPO,01->PO,10->O,11->undefined)
     * triple-group-data: 1-12 Bytes 
     *
     * child-pointer-group: (may be only partially used if Number of stored tiples is reached)
     * assuming consecutive child-pointers are never the same
     * bits 0..1 # Bytes _for 1st child-pointer (00->1,01->2,10->3,11->4)
     * bits 2..3 # Bytes _for 2st child-pointer (00->1,01->2,10->3,11->4)
     * bits 4..5 # Bytes _for 3st child-pointer (00->1,01->2,10->3,11->4)
     * bits 6..7 # Bytes _for 4st child-pointer (00->1,01->2,10->3,11->4)
     * child-pointer-group-data: 4-16 Bytes
     *
     * afterwards alternating 4x triple-group followed by 1x child-pointer-group
     * at the end there might be less than 4 triple-groups in front of the last child-pointer-group
     *
     * absolute minimum is 81 used bytes for exactly 4 Triple/Node
     */
    override fun getFirstTriple(b: IntArray) {
        NodeManager.getNode(getFirstChild()).getFirstTriple(b)
    }

    fun setFirstChild(node: Int) {
        write4(8, node)
    }

    fun getFirstChild(): Int {
        return read4(8)
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
        return 13
    }

    fun writeChildPointers(offset: Int, count: Int, d: IntArray): Int {
        SanityCheck.check { count > 0 }
        SanityCheck.check { count <= 4 }
        SanityCheck.check { d[0] > 0 }
        SanityCheck.check { d[1] > 0 || count < 2 }
        SanityCheck.check { d[2] > 0 || count < 3 }
        SanityCheck.check { d[3] > 0 || count < 4 }
        var header = 0b00000000
        var localOff = offset + 1
        for (i in 0 until count) {
            if (d[i] >= (1 shl 24)) {
                header = header or (0b00000011  shl (6 - i - i))
                write4(localOff, d[i])
                localOff += 4
            } else if (d[i] >= (1 shl 16)) {
                header = header or (0b00000010  shl (6 - i - i))
                write3(localOff, d[i])
                localOff += 3
            } else if (d[i] >= (1 shl 8)) {
                header = header or (0b00000001 shl (6 - i - i))
                write2(localOff, d[i])
                localOff += 2
            } else {
                SanityCheck.check { d[i] >= 0 }
                write1(localOff, d[i])
                localOff += 1
            }
        }
        write1(offset, header)
        return localOff - offset
    }

    fun writeDiffTriple(offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
        /*
         * assuming enough space
         * returns bytes written
         */
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

    override fun iterator(): TripleIterator {
        return NodeManager.getNode(getFirstChild()).iterator()
    }

fun forEachChild(action:(Int)->Unit){
        var remaining = getTripleCount()
        var offset = 12
var lastChildPointer=getFirstChild()
action(lastChildPointer)
        while (remaining > 0) {
            var i = 0
            while (i < 4 && remaining > 0) {
                var header = read1(offset)
                offset++
                var headerA = header and 0b11000000
                if (headerA == 0b0000000) {
offset+=((header and 0b00110000) shr 4) +((header and 0b00001100) shr 2)+ ((header and 0b00000011))+3
                } else if (headerA == 0b01000000) {
offset+=((header and 0b00001100) shr 2)+((header and 0b00000011))+2
                } else {
offset+=((header and 0b00000011)) + 1
                }
                remaining--
                i++
            }
            var headerB = read1(offset)
            offset++
            for (j in 0 until i) {
                var h = ((headerB shr (6 - j - j)) and 0x03) + 1
                when (h) {
                    1 -> {
                        lastChildPointer = lastChildPointer xor read1(offset)
                    }
                    2 -> {
                        lastChildPointer = lastChildPointer xor read2(offset)
                    }
                    3 -> {
                        lastChildPointer = lastChildPointer xor read3(offset)
                    }
                    4 -> {
                        lastChildPointer = lastChildPointer xor read4(offset)
                    }
                }
action(lastChildPointer)
                offset += h
            }
        }
    }

    fun findIteratorN(checkTooSmall: (c: IntArray) -> Boolean, action: (Node) -> TripleIterator): TripleIterator {
        var lastHeaderOffset = -1 //invalid offset to start with
        var lastChildPointer = -1
        var currentHeaderOffset = -1
        var remaining = getTripleCount()
        var offset = 12
        var childPointers = IntArray(4)
        var counter = IntArray(3)
        var value = IntArray(3)
        var childToUse = -1
        while (remaining > 0) {
            var i = 0
            while (i < 4 && remaining > 0) {
                currentHeaderOffset = offset
                var header = read1(offset)
                offset++
                var headerA = header and 0b11000000
                if (headerA == 0b0000000) {
                    counter[0] = ((header and 0b00110000) shr 4) + 1
                    counter[1] = ((header and 0b00001100) shr 2) + 1
                    counter[2] = ((header and 0b00000011)) + 1
                } else if (headerA == 0b01000000) {
                    counter[0] = 0
                    counter[1] = ((header and 0b00001100) shr 2) + 1
                    counter[2] = ((header and 0b00000011)) + 1
                } else {
                    require(headerA == 0b10000000)
                    counter[0] = 0
                    counter[1] = 0
                    counter[2] = ((header and 0b00000011)) + 1
                }
                for (i in 0 until 3) {
                    when (counter[i]) {
                        1 -> {
                            value[i] = value[i] xor read1(offset)
                        }
                        2 -> {
                            value[i] = value[i] xor read2(offset)
                        }
                        3 -> {
                            value[i] = value[i] xor read3(offset)
                        }
                        4 -> {
                            value[i] = value[i] xor read4(offset)
                        }
                    }
                    offset += counter[i]
                }
                if (!checkTooSmall(value)) {
                    var j = -1
                    if (i == 0) {
                        if (lastHeaderOffset < 0) {
                            lastChildPointer = getFirstChild()
                        }
                        return action(NodeManager.getNode(lastChildPointer))
                    }
                    if (childToUse < 0) {
                        childToUse = i - 1
                    }
                }
                remaining--
                i++
            }
            var headerB = read1(offset)
            offset++
            for (j in 0 until i) {
                var h = ((headerB shr (6 - j - j)) and 0x03) + 1
                when (h) {
                    1 -> {
                        childPointers[j] = lastChildPointer xor read1(offset)
                    }
                    2 -> {
                        childPointers[j] = lastChildPointer xor read2(offset)
                    }
                    3 -> {
                        childPointers[j] = lastChildPointer xor read3(offset)
                    }
                    4 -> {
                        childPointers[j] = lastChildPointer xor read4(offset)
                    }
                }
                lastChildPointer = childPointers[j]
                offset += h
            }
            if (childToUse >= 0) {
                return action(NodeManager.getNode(childPointers[childToUse]))
            }
            lastChildPointer = childPointers[3]
            lastHeaderOffset = currentHeaderOffset
        }
        require(offset == 12)
        return action(NodeManager.getNode(getFirstChild()))
    }

    override fun iterator3(prefix: IntArray): TripleIterator {
        return findIteratorN({ (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1]) || (it[0] == prefix[0] && it[1] == prefix[1] && it[2] < prefix[2]) }, { it.iterator3(prefix) })
    }

    override fun iterator2(prefix: IntArray): TripleIterator {
        return findIteratorN({ (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1]) }, { it.iterator2(prefix) })
    }

    override fun iterator1(prefix: IntArray): TripleIterator {
        return findIteratorN({ (it[0] < prefix[0]) }, { it.iterator1(prefix) })
    }

    fun initializeWith(childs: MutableList<Pair<Int, Node>>) {
        SanityCheck.check { childs.size > 0 }
        var current = childs.removeAt(0)
        setFirstChild(current.first)
        println("childPointer ${current.first}")
        var childLastPointer = current.first
        var offset = 12
        val offsetEnd = data.size - (13 * 4 + 17) // reserve at least enough space to write !! 4 !! full triple-group AND !! 1 !! full child-pointer-group at the end, to prevent failing-writes
        var triples = 0
        var first = true
        var i: Int
        var bytesWritten: Int
        var childPointers = IntArray(4)
        val tripleCurrent = IntArray(3)
        val tripleLast = IntArray(3)
        val tripleBuf = IntArray(3)
        while (childs.size > 0 && offset < offsetEnd) {
            i = 0
            while (i < 4 && childs.size > 0 && offset < offsetEnd) {
                current = childs.removeAt(0)
                println("childPointer ${current.first} $childLastPointer")
                childPointers[i] = current.first xor childLastPointer
                childLastPointer = current.first
                current.second.getFirstTriple(tripleCurrent)
                if (first) {
                    bytesWritten = writeFullTriple(offset, tripleCurrent)
                    first = false
                } else {
                    bytesWritten = writeDiffTriple(offset, tripleLast, tripleCurrent, tripleBuf)
                }
                offset += bytesWritten
                i++
            }
            println("i $i")
            bytesWritten = writeChildPointers(offset, i, childPointers)
            offset += bytesWritten
        }
        setTripleCount(triples)
        setNextNode(NodeManager.NodeNullPointer)
    }
}
