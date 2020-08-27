package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.readInt4
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.iterator.ColumnIterator

object NodeLeaf {
    const val startOffset = 12

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
    inline fun getFirstTriple(data: ByteArray, b: IntArray) {
        b[0] = data.readInt4(startOffset + 1)
        b[1] = data.readInt4(startOffset + 5)
        b[2] = data.readInt4(startOffset + 9)
    }

    inline fun iterator(data: ByteArray): TripleIterator {
        return NodeLeafIterator(data)
    }

    suspend inline fun iterator(data: ByteArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            0 -> {
                val res = NodeLeafColumnIterator0(data, lock)
                res._init()
                return res
            }
            1 -> {
                val res = NodeLeafColumnIterator1(data, lock)
                res._init()
                return res
            }
            2 -> {
                val res = NodeLeafColumnIterator2(data, lock)
                res._init()
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    suspend inline fun iterator3(data: ByteArray, prefix: IntArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            2 -> {
                val res = NodeLeafColumnIteratorPrefix3(data, prefix, lock)
                res._init()
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    suspend inline fun iterator2(data: ByteArray, prefix: IntArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            2 -> {
                val res = NodeLeafColumnIteratorPrefix2_2(data, prefix, lock)
                res._init()
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    suspend inline fun iterator1(data: ByteArray, prefix: IntArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            1 -> {
                val res = NodeLeafColumnIteratorPrefix1_1(data, prefix, lock)
                res._init()
                return res
            }
            2 -> {
                val res = NodeLeafColumnIteratorPrefix1_2(data, prefix, lock)
                res._init()
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    inline fun initializeWith(data: ByteArray, iterator: TripleIterator) {
        SanityCheck.check { iterator.hasNext() }
        var tripleCurrent = iterator.next()
        val tripleLast = intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2])
        val tripleBuf = IntArray(3)
        var offset = startOffset
        var bytesWritten = NodeShared.writeFullTriple(data, offset, tripleLast)
        offset += bytesWritten
        val offsetEnd = data.size - bytesWritten // reserve at least enough space to write a full triple at the end
        var triples = 1
        while (iterator.hasNext() && offset <= offsetEnd) {
            bytesWritten = NodeShared.writeDiffTriple(data, offset, tripleLast, iterator.next(), tripleBuf)
            offset += bytesWritten
            triples++
        }
        NodeShared.setTripleCount(data, triples)
        NodeShared.setNextNode(data, NodeManager.nodeNullPointer)
    }
}
