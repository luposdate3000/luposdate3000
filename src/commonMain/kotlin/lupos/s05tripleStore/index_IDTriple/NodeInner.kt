package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s00misc.writeInt1
import lupos.s00misc.writeInt2
import lupos.s00misc.writeInt3
import lupos.s00misc.writeInt4
import lupos.s04logicalOperators.iterator.ColumnIterator

object NodeInner {
    const val startOffset = 16

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
    inline fun getFirstTriple(data: ByteArray, b: IntArray) {
        var node = data
        var done = false
        while (!done) {
            NodeManager.getNodeAny(getFirstChild(node), {
                NodeLeaf.getFirstTriple(it, b)
                done = true
            }, {
                node = it
            })
        }
    }

    inline fun setFirstChild(data: ByteArray, node: Int) {
        data.writeInt4(12, node)
    }

    inline fun getFirstChild(data: ByteArray): Int {
        return data.readInt4(12)
    }

    /*inline*/ fun writeChildPointers(data: ByteArray, offset: Int, count: Int, d: IntArray): Int {
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

    inline fun iterator(data: ByteArray): TripleIterator {
        var iterator: TripleIterator? = null
        var node = data
        while (iterator == null) {
            NodeManager.getNodeAny(getFirstChild(node), {
                iterator = NodeLeaf.iterator(it)
            }, {
                node = it
            })
        }
        return iterator!!
    }

    suspend inline fun iterator(data: ByteArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        var iterator: ColumnIterator? = null
        var node = data
        while (iterator == null) {
            NodeManager.getNodeAnySuspended(getFirstChild(node), {
                iterator = NodeLeaf.iterator(it, lock, component)
            }, {
                node = it
            })
        }
        return iterator!!
    }

    /*inline*/ suspend fun forEachChild(data: ByteArray,/*crossinline*/ action: suspend (Int) -> Unit) {
        var remaining = NodeShared.getTripleCount(data)
        var offset = startOffset
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

    suspend    /*inline*/  fun findIteratorN(data: ByteArray,/*crossinline*/ checkTooSmall: suspend (c: IntArray) -> Boolean, /*crossinline*/ action: suspend (Int) -> Unit): Unit {
        var lastHeaderOffset = -1 //invalid offset to start with
        var lastChildPointer = getFirstChild(data)
        var childLastPointerHeaderOffset = -1
        var remaining = NodeShared.getTripleCount(data)
        var offset = startOffset
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

    suspend inline fun iterator3(data: ByteArray, prefix: IntArray): TripleIterator {
        var node = data
        var iterator: TripleIterator? = null
        while (iterator == null) {
            findIteratorN(node, {
                /*return*/ (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1]) || (it[0] == prefix[0] && it[1] == prefix[1] && it[2] < prefix[2])
            }, {
                NodeManager.getNodeAny(it, {
                    iterator = NodeLeaf.iterator3(it, prefix)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    suspend inline fun iterator2(data: ByteArray, prefix: IntArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        var node = data
        var iterator: ColumnIterator? = null
        while (iterator == null) {
            findIteratorN(node, {
                /*return*/ (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1])
            }, {
                NodeManager.getNodeAnySuspended(it, {
                    iterator = NodeLeaf.iterator2(it, prefix, lock, component)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    suspend inline fun iterator1(data: ByteArray, prefix: IntArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        var node = data
        var iterator: ColumnIterator? = null
        while (iterator == null) {
            findIteratorN(node, {
                /*return*/ (it[0] < prefix[0])
            }, {
                NodeManager.getNodeAnySuspended(it, {
                    iterator = NodeLeaf.iterator1(it, prefix, lock, component)
                }, {
                    node = it
                })
            })
        }
        return iterator!!
    }

    inline fun initializeWith(data: ByteArray, childs: MutableList<Int>) {
        SanityCheck.check { childs.size > 0 }
        var current = childs.removeAt(0)
        var childLastPointer = current
        setFirstChild(data, childLastPointer)
        var offset = startOffset
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
                childPointers[i] = childLastPointer xor current
                childLastPointer = current
                NodeManager.getNodeAny(childLastPointer, {
                    NodeLeaf.getFirstTriple(it, tripleCurrent)
                }, {
                    NodeInner.getFirstTriple(it, tripleCurrent)
                })
                bytesWritten = NodeShared.writeDiffTriple(data, offset, tripleLast, tripleCurrent, tripleBuf)
                offset += bytesWritten
                i++
                triples++
            }
            bytesWritten = writeChildPointers(data, offset, i, childPointers)
            offset += bytesWritten
        }
        NodeShared.setTripleCount(data, triples)
        NodeShared.setNextNode(data, NodeManager.nodeNullPointer)
    }
}
