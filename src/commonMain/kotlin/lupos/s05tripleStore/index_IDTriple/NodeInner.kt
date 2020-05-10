package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.SanityCheck

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
        var node = this
        var done = false
        while (!done) {
            NodeManager.getNode(node.getFirstChild(), {
                it.getFirstTriple(b)
                done = true
            }, {
                node = it
            })
        }
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

    inline fun read4(offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 24) or ((data[offset + 1].toInt() and 0xFF) shl 16) or ((data[offset + 2].toInt() and 0xFF) shl 8) or ((data[offset + 3].toInt() and 0xFF)))
    }

    inline fun read3(offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 16) or ((data[offset + 1].toInt() and 0xFF) shl 8) or ((data[offset + 2].toInt() and 0xFF)))
    }

    inline fun read2(offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF)))
    }

    inline fun read1(offset: Int): Int {
        return (data[offset].toInt() and 0xFF)
    }

    inline fun write4(offset: Int, d: Int) {
        data[offset] = ((d shr 24) and 0xFF).toByte()
        data[offset + 1] = ((d shr 16) and 0xFF).toByte()
        data[offset + 2] = ((d shr 8) and 0xFF).toByte()
        data[offset + 3] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read4(offset) }, { "$d ${read4(offset)} ${data[offset].toString(16)} ${data[offset + 1].toString(16)} ${data[offset + 2].toString(16)} ${data[offset + 3].toString(16)}" })
    }

    inline fun write3(offset: Int, d: Int) {
        data[offset] = ((d shr 16) and 0xFF).toByte()
        data[offset + 1] = ((d shr 8) and 0xFF).toByte()
        data[offset + 2] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read3(offset) }, { "$d ${read3(offset)}" })
    }

    inline fun write2(offset: Int, d: Int) {
        data[offset] = ((d shr 8) and 0xFF).toByte()
        data[offset + 1] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read2(offset) }, { "$d ${read2(offset)}" })
    }

    inline fun write1(offset: Int, d: Int) {
        data[offset] = (d and 0xFF).toByte()
        SanityCheck.check({ d == read1(offset) }, { "$d ${read1(offset)}" })
    }

    inline fun writeFullTriple(offset: Int, d: IntArray): Int {
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

    inline fun writeChildPointers(offset: Int, count: Int, d: IntArray): Int {
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
                header = header or (0b00000011 shl (6 - i - i))
                write4(localOff, d[i])
                localOff += 4
            } else if (d[i] >= (1 shl 16)) {
                header = header or (0b00000010 shl (6 - i - i))
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

    inline fun writeDiffTriple(offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
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
        var iterator: TripleIterator? = null
        var node = this
        while (iterator == null) {
            NodeManager.getNode(node.getFirstChild(), {
                iterator = it.iterator()
            }, {
                node = it
            })
        }
        return iterator!!
    }

    inline fun forEachChild(crossinline action: (Int) -> Unit) {
        var remaining = getTripleCount()
        var offset = 12
        var lastChildPointer = getFirstChild()
        action(lastChildPointer)
        while (remaining > 0) {
            var i = 0
            while (i < 4 && remaining > 0) {
                var header = read1(offset)
                offset++
                var headerA = header and 0b11000000
                if (headerA == 0b0000000) {
                    offset += ((header and 0b00110000) shr 4) + ((header and 0b00001100) shr 2) + ((header and 0b00000011)) + 3
                } else if (headerA == 0b01000000) {
                    offset += ((header and 0b00001100) shr 2) + ((header and 0b00000011)) + 2
                } else {
                    offset += ((header and 0b00000011)) + 1
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

    inline fun findIteratorN(crossinline checkTooSmall: (c: IntArray) -> Boolean, crossinline action: (Int) -> Unit): Unit {
        var lastHeaderOffset = -1 //invalid offset to start with
        var lastChildPointer = getFirstChild()
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
                        action(lastChildPointer)
                        return
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
                action(childPointers[childToUse])
                return
            }
            lastHeaderOffset = currentHeaderOffset
        }
        action(lastChildPointer)
    }

    override fun iterator3(prefix: IntArray): TripleIterator {
        var node = this
        var iterator: TripleIterator? = null
        while (iterator == null) {
            node.findIteratorN({
                (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1]) || (it[0] == prefix[0] && it[1] == prefix[1] && it[2] < prefix[2])
            }, {
                NodeManager.getNode(it, {
                    iterator = it.iterator3(prefix)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    override fun iterator2(prefix: IntArray): TripleIterator {
        var node = this
        var iterator: TripleIterator? = null
        while (iterator == null) {
            node.findIteratorN({
                (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1])
            }, {
                NodeManager.getNode(it, {
                    iterator = it.iterator2(prefix)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    override fun iterator1(prefix: IntArray): TripleIterator {
        var node = this
        var iterator: TripleIterator? = null
        while (iterator == null) {
            node.findIteratorN({
                (it[0] < prefix[0])
            }, {
                NodeManager.getNode(it, {
                    iterator = it.iterator1(prefix)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    fun initializeWith(childs: MutableList<Pair<Int, Node>>) {
        var debugListChilds = mutableListOf<Int>()
        var debugListTriples = mutableListOf<IntArray>()
        SanityCheck.check { childs.size > 0 }
        var current = childs.removeAt(0)
        SanityCheck {
            debugListChilds.add(current.first)
        }
        setFirstChild(current.first)
        var childLastPointer = current.first
        var offset = 12
        val offsetEnd = data.size - (13 * 4 + 17) // reserve at least enough space to write !! 4 !! full triple-group AND !! 1 !! full child-pointer-group at the end, to prevent failing-writes
        var triples = 0
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
                SanityCheck {
                    debugListChilds.add(current.first)
                }
                childPointers[i] = current.first xor childLastPointer
                childLastPointer = current.first
                current.second.getFirstTriple(tripleCurrent)
                debugListTriples.add(intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2]))
                bytesWritten = writeDiffTriple(offset, tripleLast, tripleCurrent, tripleBuf)
                offset += bytesWritten
                i++
                triples++
            }
            bytesWritten = writeChildPointers(offset, i, childPointers)
            offset += bytesWritten
        }
        setTripleCount(triples)
        setNextNode(NodeManager.nodeNullPointer)
        SanityCheck {
            println(debugListChilds)
            println(debugListTriples.map { it.map { it } })
            require(debugListTriples.size == debugListChilds.size - 1)
            var i = 0
            this.forEachChild {
                println("it $it")
                require(debugListChilds.size >= i)
                require(it == debugListChilds[i])
                i++
            }
            require(i == debugListChilds.size)
            var j = -1
            var res = findIteratorN({
                println("xx ${it.map { it }}")
                j++
                require(j < debugListTriples.size)
                require(it[0] == debugListTriples[j][0])
                require(it[1] == debugListTriples[j][1])
                require(it[2] == debugListTriples[j][2])
                /*return*/true
            }, {
                println("$it")
                require(it == debugListChilds[debugListChilds.size - 1])
            })
            require(j == debugListTriples.size - 1)
            for (i in 0 until debugListTriples.size) {
                println("i $i")
                j = -1
                var res = findIteratorN({
                    println("xx ${it.map { it }}")
                    j++
                    require(j < debugListTriples.size)
                    require(it[0] == debugListTriples[j][0])
                    require(it[1] == debugListTriples[j][1])
                    require(it[2] == debugListTriples[j][2])
                    require(j < i + 4)/*read at most one block too much*/
                    /*return */ j < i
                }, {
                    println("$it")
                    require(it == debugListChilds[i])
                })
            }
        }
    }
}
