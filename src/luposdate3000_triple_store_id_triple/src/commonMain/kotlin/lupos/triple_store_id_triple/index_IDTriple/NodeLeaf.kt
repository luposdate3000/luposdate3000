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
package lupos.triple_store_id_triple.index_IDTriple

import lupos.shared.BufferManagerPage
import lupos.shared.BufferManagerPageWrapper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.inline.MyThreadReadWriteLock
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator

internal object NodeLeaf {
    const val START_OFFSET = 12

    @Suppress("NOTHING_TO_INLINE")
    internal fun getFirstTriple(node: BufferManagerPageWrapper, b: DictionaryValueTypeArray) {
        NodeShared.readTriple111(node, START_OFFSET, 0, 0, 0) { v0, v1, v2 ->
            b[0] = v0
            b[1] = v1
            b[2] = v2
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun iterator(node: BufferManagerPageWrapper, nodeid: Int, nodeManager: NodeManager): TripleIterator {
        return NodeLeafIterator(node, nodeid, nodeManager)
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal fun iterator(node: BufferManagerPageWrapper, nodeid: Int, lock: MyThreadReadWriteLock, component: Int, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        return when (component) {
            0 -> {
                NodeLeafColumnIterator0(node, nodeid, lock, nodeManager, timeout)
            }
            1 -> {
                NodeLeafColumnIterator1(node, nodeid, lock, nodeManager, timeout)
            }
            2 -> {
                NodeLeafColumnIterator2(node, nodeid, lock, nodeManager, timeout)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal fun iterator3(node: BufferManagerPageWrapper, nodeid: Int, prefix: DictionaryValueTypeArray, lock: MyThreadReadWriteLock, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        return NodeLeafColumnIteratorPrefix3(node, nodeid, prefix, lock, nodeManager, timeout)
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal fun iterator2(node: BufferManagerPageWrapper, nodeid: Int, prefix: DictionaryValueTypeArray, lock: MyThreadReadWriteLock, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        return NodeLeafColumnIteratorPrefix22(node, nodeid, prefix, lock, nodeManager, timeout)
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal fun iterator1(node: BufferManagerPageWrapper, nodeid: Int, prefix: DictionaryValueTypeArray, lock: MyThreadReadWriteLock, component: Int, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        return when (component) {
            1 -> {
                NodeLeafColumnIteratorPrefix11(node, nodeid, prefix, lock, nodeManager, timeout)
            }
            2 -> {
                NodeLeafColumnIteratorPrefix12(node, nodeid, prefix, lock, nodeManager, timeout)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun initializeWith(node: BufferManagerPageWrapper, iterator: TripleIterator) {
        var writtenTriples: MutableList<DictionaryValueType>? = null
        val tripleLast = DictionaryValueTypeArray(3)
        var offset = START_OFFSET
        val offsetEnd = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - NodeShared.MAX_TRIPLE_SIZE
        var triples = 0
        while (iterator.hasNext() && offset <= offsetEnd) {
            val tripleCurrent = iterator.next()
            offset += NodeShared.writeTriple(node, offset, tripleLast, tripleCurrent)
            triples++
        }
        NodeShared.setTripleCount(node, triples)
        NodeShared.setNextNode(node, NodeManager.nodeNullPointer)
    }
}
