package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.iterator.ColumnIterator

internal object NodeLeaf {
    const val START_OFFSET = 12
    inline fun getFirstTriple(node: ByteArray, b: IntArray) {
        NodeShared.readTriple111(node, START_OFFSET, 0, 0, 0) { v0, v1, v2 ->
            b[0] = v0
            b[1] = v1
            b[2] = v2
        }
    }

    inline fun iterator(node: ByteArray, nodeid: Int): TripleIterator {
        return NodeLeafIterator(node, nodeid)
    }

    /*suspend*/ inline fun iterator(node: ByteArray, nodeid: Int, lock: MyReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            0 -> {
                val res = NodeLeafColumnIterator0(node, nodeid, lock)
                return res
            }
            1 -> {
                val res = NodeLeafColumnIterator1(node, nodeid, lock)
                return res
            }
            2 -> {
                val res = NodeLeafColumnIterator2(node, nodeid, lock)
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    /*suspend*/ inline fun iterator3(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock): ColumnIterator {
        val res = NodeLeafColumnIteratorPrefix3(node, nodeid, prefix, lock)
        return res
    }

    /*suspend*/ inline fun iterator2(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock): ColumnIterator {
        val res = NodeLeafColumnIteratorPrefix22(node, nodeid, prefix, lock)
        return res
    }

    /*suspend*/ inline fun iterator1(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock, component: Int): ColumnIterator {
        when (component) {
            1 -> {
                val res = NodeLeafColumnIteratorPrefix11(node, nodeid, prefix, lock)
                return res
            }
            2 -> {
                val res = NodeLeafColumnIteratorPrefix12(node, nodeid, prefix, lock)
                return res
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    inline fun initializeWith(node: ByteArray, nodeid: Int, iterator: TripleIterator) {
        SanityCheck.check { iterator.hasNext() }
        var writtenTriples: MutableList<Int>? = null
        SanityCheck {
            writtenTriples = mutableListOf<Int>()
        }
        val tripleLast = IntArray(3)
        var offset = START_OFFSET
        val offsetEnd = node.size - NodeShared.MAX_TRIPLE_SIZE
        var triples = 0
        while (iterator.hasNext() && offset <= offsetEnd) {
            var tripleCurrent = iterator.next()
            SanityCheck {
                writtenTriples!!.add(tripleCurrent[0])
                writtenTriples!!.add(tripleCurrent[1])
                writtenTriples!!.add(tripleCurrent[2])
            }
//if(nodeid>7000&&nodeid<8000){
//println("node $nodeid :: write $offset $triples A")
//}
            offset += NodeShared.writeTriple(node, offset, tripleLast, tripleCurrent)
            triples++
        }
        NodeShared.setTripleCount(node, triples)
        NodeShared.setNextNode(node, NodeManager.nodeNullPointer)
        SanityCheck {
            var remaining = NodeShared.getTripleCount(node)
            var offset2 = START_OFFSET
            var i = 0
            var value0 = 0
            var value1 = 0
            var value2 = 0
            while (remaining > 0) {
                offset2 += NodeShared.readTriple111(node, offset2, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                SanityCheck.check { value0 == writtenTriples!![i * 3] }
                SanityCheck.check { value1 == writtenTriples!![i * 3 + 1] }
                SanityCheck.check { value2 == writtenTriples!![i * 3 + 2] }
                remaining--
                i++
            }
        }
    }
}
