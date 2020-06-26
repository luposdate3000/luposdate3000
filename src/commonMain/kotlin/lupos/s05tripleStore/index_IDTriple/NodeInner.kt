package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage
import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.SanityCheck
import lupos.s00misc.writeInt1
import lupos.s00misc.writeInt2
import lupos.s00misc.writeInt3
import lupos.s00misc.writeInt4

object NodeInnerFunctions {
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
     * child-pointer-group: (may be only partially used if_ Number of stored tiples is reached)
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
     * absolute minimum is 81 used bytes _for exactly 4 Triple/Node
     */
    fun getFirstTriple(data: ByteArray, b: IntArray) {
        var node = data
        var done = false
        while (!done) {
            NodeManager.getNode(getFirstChild(node), {
                NodeLeafFunctions.getFirstTriple(it,b)
                done = true
            }, {
                node = it
            })
        }
    }

    fun setFirstChild(data: ByteArray,node: Int) {
        data.writeInt4(8, node)
    }

    fun getFirstChild(data: ByteArray): Int {
        return data.readInt4(8)
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

    /*inline*/ fun writeFullTriple(data: ByteArray,offset: Int, d: IntArray): Int {
        /*
         * assuming enough space
         * return bytes written
         */
        data.writeInt1(offset, 0b00111111)
        data.writeInt4(offset + 1, d[0])
        data.writeInt4(offset + 5, d[1])
        data.writeInt4(offset + 9, d[2])
        return 13
    }

    /*inline*/ fun writeChildPointers(data: ByteArray,offset: Int, count: Int, d: IntArray): Int {
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
                data.writeInt4(localOff, d[i])
                localOff += 4
            } else if (d[i] >= (1 shl 16)) {
                header = header or (0b00000010 shl (6 - i - i))
                data.writeInt3(localOff, d[i])
                localOff += 3
            } else if (d[i] >= (1 shl 8)) {
                header = header or (0b00000001 shl (6 - i - i))
                data.writeInt2(localOff, d[i])
                localOff += 2
            } else {
                SanityCheck.check { d[i] >= 0 }
                data.writeInt1(localOff, d[i])
                localOff += 1
            }
        }
        data.writeInt1(offset, header)
        return localOff - offset
    }

    /*inline*/ fun writeDiffTriple(data: ByteArray,offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
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

    fun iterator(data: ByteArray): TripleIterator {
        var iterator: TripleIterator? = null
        var node = data
        while (iterator == null) {
            NodeManager.getNode(getFirstChild(node), {
                iterator = NodeLeafFunctions.iterator(it)
            }, {
                node = it
            })
        }
        return iterator!!
    }

    /*inline*/ fun forEachChild(data: ByteArray,/*crossinline*/ action: (Int) -> Unit) {
        var remaining = getTripleCount(data)
        var offset = 12
        var lastChildPointer = getFirstChild(data)
        action(lastChildPointer)
        while (remaining > 0) {
            var i = 0
            while (i < 4 && remaining > 0) {
                var header = data.readInt1(offset)
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
            var headerB = data.readInt1(offset)
            offset++
            for (j in 0 until i) {
                var h = ((headerB shr (6 - j - j)) and 0x03) + 1
                when (h) {
                    1 -> {
                        lastChildPointer = lastChildPointer xor data.readInt1(offset)
                    }
                    2 -> {
                        lastChildPointer = lastChildPointer xor data.readInt2(offset)
                    }
                    3 -> {
                        lastChildPointer = lastChildPointer xor data.readInt3(offset)
                    }
                    4 -> {
                        lastChildPointer = lastChildPointer xor data.readInt4(offset)
                    }
                }
                action(lastChildPointer)
                offset += h
            }
        }
    }

    /*inline*/ fun findIteratorN(data: ByteArray,/*crossinline*/ checkTooSmall: (c: IntArray) -> Boolean, /*crossinline*/ action: (Int) -> Unit): Unit {
        var lastHeaderOffset = -1 //invalid offset to start with
        var lastChildPointer = getFirstChild(data)
        var childLastPointerHeaderOffset = -1
        var remaining = getTripleCount(data)
        var offset = 12
        var childPointers = IntArray(4)
        var counter = IntArray(3)
        var value = IntArray(3)
        var childToUse = -1
        while (remaining > 0) {
            var i = 0
            while (i < 4 && remaining > 0) {
                childLastPointerHeaderOffset = offset
                var header = data.readInt1(offset)
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
                    SanityCheck.check { headerA == 0b10000000 }
                    counter[0] = 0
                    counter[1] = 0
                    counter[2] = ((header and 0b00000011)) + 1
                }
                for (j in 0 until 3) {
                    when (counter[j]) {
                        1 -> {
                            value[j] = value[j] xor data.readInt1(offset)
                        }
                        2 -> {
                            value[j] = value[j] xor data.readInt2(offset)
                        }
                        3 -> {
                            value[j] = value[j] xor data.readInt3(offset)
                        }
                        4 -> {
                            value[j] = value[j] xor data.readInt4(offset)
                        }
                    }
                    offset += counter[j]
                }
                if (!checkTooSmall(value)) {
                    if (i == 0) {
                        if (lastHeaderOffset < 0) {
                            lastChildPointer = getFirstChild(data)
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
            var headerB = data.readInt1(offset)
            offset++
            for (j in 0 until i) {
                var h = ((headerB shr (6 - j - j)) and 0x03) + 1
                when (h) {
                    1 -> {
                        childPointers[j] = lastChildPointer xor data.readInt1(offset)
                    }
                    2 -> {
                        childPointers[j] = lastChildPointer xor data.readInt2(offset)
                    }
                    3 -> {
                        childPointers[j] = lastChildPointer xor data.readInt3(offset)
                    }
                    4 -> {
                        childPointers[j] = lastChildPointer xor data.readInt4(offset)
                    }
                }
                lastChildPointer = childPointers[j]
                offset += h
            }
            if (childToUse >= 0) {
                action(childPointers[childToUse])
                return
            }
            lastHeaderOffset = childLastPointerHeaderOffset
        }
        action(lastChildPointer)
    }

    fun iterator3(data: ByteArray,prefix: IntArray): TripleIterator {
        var node = data
        var iterator: TripleIterator? = null
        while (iterator == null) {
            findIteratorN(node,{
                /*return*/ (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1]) || (it[0] == prefix[0] && it[1] == prefix[1] && it[2] < prefix[2])
            }, {
                NodeManager.getNode(it, {
                    iterator = NodeLeafFunctions.iterator3(it,prefix)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    fun iterator2(data: ByteArray,prefix: IntArray): TripleIterator {
        var node = data
        var iterator: TripleIterator? = null
        while (iterator == null) {
            findIteratorN(node,{
                /*return*/ (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1])
            }, {
                NodeManager.getNode(it, {
                    iterator = NodeLeafFunctions.iterator2(it,prefix)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    fun iterator1(data: ByteArray,prefix: IntArray): TripleIterator {
        var node = data
        var iterator: TripleIterator? = null
        while (iterator == null) {
            findIteratorN(node,{
                /*return*/ (it[0] < prefix[0])
            }, {
                NodeManager.getNode(it, {
                    iterator = NodeLeafFunctions.iterator1(it,prefix)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    fun initializeWith(data: ByteArray,childs: MutableList<Int>) {
        var debugListChilds = mutableListOf<Int>()
        var debugListTriples = mutableListOf<IntArray>()
        SanityCheck.check { childs.size > 0 }
        var current = childs.removeAt(0)
        var childLastPointer = current
        SanityCheck {
            debugListChilds.add(childLastPointer)
        }
        setFirstChild(data,childLastPointer)
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
                    debugListChilds.add(current)
                }
                childPointers[i] = childLastPointer xor current
                childLastPointer = current
                NodeManager.getNode(childLastPointer, {
NodeLeafFunctions.getFirstTriple(it,tripleCurrent)
                }, {
                    NodeInnerFunctions.getFirstTriple(it,tripleCurrent)
                })
                debugListTriples.add(intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2]))
                bytesWritten = writeDiffTriple(data,offset, tripleLast, tripleCurrent, tripleBuf)
                offset += bytesWritten
                i++
                triples++
            }
            bytesWritten = writeChildPointers(data,offset, i, childPointers)
            offset += bytesWritten
        }
        setTripleCount(data,triples)
        setNextNode(data,NodeManager.nodeNullPointer)
        SanityCheck {
            println(debugListChilds)
            println(debugListTriples.map { it.map { it } })
            SanityCheck.check { debugListTriples.size == debugListChilds.size - 1 }
            var k = 0
            forEachChild (data,{
                println("debug it $it")
                SanityCheck.check { debugListChilds.size >= k }
                SanityCheck.check { it == debugListChilds[k] }
                k++
            })
            SanityCheck.check { k == debugListChilds.size }
            var j = -1
            findIteratorN(data,{
                println("debug xx ${it.map { it }}")
                j++
                SanityCheck.check { j < debugListTriples.size }
                SanityCheck.check { it[0] == debugListTriples[j][0] }
                SanityCheck.check { it[1] == debugListTriples[j][1] }
                SanityCheck.check { it[2] == debugListTriples[j][2] }
                /*return*/true
            }, {
                println("debug $it")
                SanityCheck.check { it == debugListChilds[debugListChilds.size - 1] }
            })
            SanityCheck.check { j == debugListTriples.size - 1 }
            for (l in 0 until debugListTriples.size) {
                println("debug l $l")
                j = -1
                findIteratorN(data,{
                    println("debug xx ${it.map { it }}")
                    j++
                    SanityCheck.check { j < debugListTriples.size }
                    SanityCheck.check { it[0] == debugListTriples[j][0] }
                    SanityCheck.check { it[1] == debugListTriples[j][1] }
                    SanityCheck.check { it[2] == debugListTriples[j][2] }
                    SanityCheck.check { j < l + 4 }
/*read at most one block too much*/
                    /*return*/ j < l
                }, {
                    println("debug $it")
                    SanityCheck.check { it == debugListChilds[l] }
                })
            }
        }
    }
}
