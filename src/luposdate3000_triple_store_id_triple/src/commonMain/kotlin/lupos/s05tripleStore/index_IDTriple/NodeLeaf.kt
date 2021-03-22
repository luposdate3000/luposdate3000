/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.iterator.ColumnIterator

internal object NodeLeaf {
    const val START_OFFSET = 12

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getFirstTriple(node: ByteArray, b: IntArray) {
        NodeShared.readTriple111(node, START_OFFSET, 0, 0, 0) { v0, v1, v2 ->
            b[0] = v0
            b[1] = v1
            b[2] = v2
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun iterator(node: ByteArray, nodeid: Int, nodeManager: NodeManager): TripleIterator {
        return NodeLeafIterator(node, nodeid, nodeManager)
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun iterator(node: ByteArray, nodeid: Int, lock: MyReadWriteLock, component: Int, nodeManager: NodeManager): ColumnIterator {
        return when (component) {
            0 -> {
                NodeLeafColumnIterator0(node, nodeid, lock, nodeManager)
            }
            1 -> {
                NodeLeafColumnIterator1(node, nodeid, lock, nodeManager)
            }
            2 -> {
                NodeLeafColumnIterator2(node, nodeid, lock, nodeManager)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun iterator3(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock, nodeManager: NodeManager): ColumnIterator {
        return NodeLeafColumnIteratorPrefix3(node, nodeid, prefix, lock, nodeManager)
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun iterator2(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock, nodeManager: NodeManager): ColumnIterator {
        return NodeLeafColumnIteratorPrefix22(node, nodeid, prefix, lock, nodeManager)
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun iterator1(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock, component: Int, nodeManager: NodeManager): ColumnIterator {
        return when (component) {
            1 -> {
                NodeLeafColumnIteratorPrefix11(node, nodeid, prefix, lock, nodeManager)
            }
            2 -> {
                NodeLeafColumnIteratorPrefix12(node, nodeid, prefix, lock, nodeManager)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun initializeWith(node: ByteArray, nodeid: Int, iterator: TripleIterator) {
        SanityCheck.check { iterator.hasNext() }
        var writtenTriples: MutableList<Int>? = null
        SanityCheck {
            writtenTriples = mutableListOf()
        }
        val tripleLast = IntArray(3)
        var offset = START_OFFSET
        val offsetEnd = node.size - NodeShared.MAX_TRIPLE_SIZE
        var triples = 0
        while (iterator.hasNext() && offset <= offsetEnd) {
            val tripleCurrent = iterator.next()
            SanityCheck {
                writtenTriples!!.add(tripleCurrent[0])
                writtenTriples!!.add(tripleCurrent[1])
                writtenTriples!!.add(tripleCurrent[2])
            }
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
