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

    inline fun getFirstTriple(node: ByteArray, b: IntArray) {
        b[0] = node.readInt4(startOffset + 1)
        b[1] = node.readInt4(startOffset + 5)
        b[2] = node.readInt4(startOffset + 9)
    }

    inline fun iterator(node: ByteArray, nodeid: Int): TripleIterator {
        return NodeLeafIterator(node, nodeid)
    }

    suspend inline fun iterator(node: ByteArray, nodeid: Int, lock: ReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            0 -> {
                val res = NodeLeafColumnIterator0(node, nodeid, lock)
                res._init()
                return res
            }
            1 -> {
                val res = NodeLeafColumnIterator1(node, nodeid, lock)
                res._init()
                return res
            }
            2 -> {
                val res = NodeLeafColumnIterator2(node, nodeid, lock)
                res._init()
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    suspend inline fun iterator3(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock): ColumnIterator {
        val res = NodeLeafColumnIteratorPrefix3(node, nodeid, prefix, lock)
        res._init()
        return res
    }

    suspend inline fun iterator2(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock): ColumnIterator {
        val res = NodeLeafColumnIteratorPrefix2_2(node, nodeid, prefix, lock)
        res._init()
        return res
    }

    suspend inline fun iterator1(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            1 -> {
                val res = NodeLeafColumnIteratorPrefix1_1(node, nodeid, prefix, lock)
                res._init()
                return res
            }
            2 -> {
                val res = NodeLeafColumnIteratorPrefix1_2(node, nodeid, prefix, lock)
                res._init()
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    inline fun initializeWith(node: ByteArray, iterator: TripleIterator) {
        SanityCheck.check { iterator.hasNext() }
        var tripleCurrent = iterator.next()
        val tripleLast = intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2])
        val tripleBuf = IntArray(3)
        var offset = startOffset
        var bytesWritten = NodeShared.writeFullTriple(node, offset, tripleLast)
        offset += bytesWritten
        val offsetEnd = node.size - bytesWritten // reserve at least enough space to write a full triple at the end
        var triples = 1
        while (iterator.hasNext() && offset <= offsetEnd) {
            bytesWritten = NodeShared.writeDiffTriple(node, offset, tripleLast, iterator.next(), tripleBuf)
            offset += bytesWritten
            triples++
        }
        NodeShared.setTripleCount(node, triples)
        NodeShared.setNextNode(node, NodeManager.nodeNullPointer)
    }
}
