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
import lupos.shared.MyReadWriteLock
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator

internal object NodeInner {
    const val START_OFFSET = 16
    const val MAX_POINTER_SIZE = 4

    @Suppress("NOTHING_TO_INLINE")
    internal fun getFirstTriple(data: BufferManagerPageWrapper, b: DictionaryValueTypeArray, nodeManager: NodeManager) {
        var node = data
        var done = false
        var nodeid = getFirstChild(node)
        while (!done) {
            var nextnodeid = nodeid
            nodeManager.getNodeAny(
                /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:38"/*SOURCE_FILE_END*/,
                nodeid,
                {
                    NodeLeaf.getFirstTriple(it, b)
                    done = true
                },
                {
                    node = it
                    nextnodeid = getFirstChild(node)
                }
            )
            nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:49"/*SOURCE_FILE_END*/, nodeid)
            nodeid = nextnodeid
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun setFirstChild(node: BufferManagerPageWrapper, data: Int) {
        BufferManagerPage.writeInt4(node, 12, data)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun getFirstChild(node: BufferManagerPageWrapper): Int {
        return BufferManagerPage.readInt4(node, 12)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun writeChildPointer(node: BufferManagerPageWrapper, offset: Int, pointer: Int): Int {
        BufferManagerPage.writeInt4(node, offset, pointer)
        return 4
    }

    internal fun readChildPointer(node: BufferManagerPageWrapper, offset: Int, action: (pointer: Int) -> Unit): Int {
        action(BufferManagerPage.readInt4(node, offset))
        return 4
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun iterator(_node: BufferManagerPageWrapper, nodeManager: NodeManager): TripleIterator {
        var iterator: TripleIterator? = null
        var node = _node
        while (true) {
            val nodeid = getFirstChild(node)
            nodeManager.getNodeAny(
                /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:82"/*SOURCE_FILE_END*/,
                nodeid,
                {
                    iterator = NodeLeaf.iterator(it, nodeid, nodeManager)
                },
                {
                    node = it
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:92"/*SOURCE_FILE_END*/, nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun iterator(_node: BufferManagerPageWrapper, lock: MyReadWriteLock, component: Int, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        var iterator: ColumnIterator? = null
        var node = _node
        while (true) {
            val nodeid = getFirstChild(node)
            nodeManager.getNodeAnySuspended(
                /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:107"/*SOURCE_FILE_END*/,
                nodeid,
                {
                    iterator = NodeLeaf.iterator(it, nodeid, lock, component, nodeManager, timeout)
                },
                {
                    node = it
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:117"/*SOURCE_FILE_END*/, nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    internal fun forEachChild(node: BufferManagerPageWrapper, action: (Int) -> Unit) {
        var remaining = NodeShared.getTripleCount(node)
        var offset = START_OFFSET
        var lastChildPointer = getFirstChild(node)
        action(lastChildPointer)
        while (remaining > 0) {
            offset += NodeShared.readTriple000(node, offset)
            offset += readChildPointer(node, offset) {
                lastChildPointer = it
            }
            action(lastChildPointer)
            remaining--
        }
    }

    internal fun findIteratorN(node: BufferManagerPageWrapper, checkTooSmall: (value0: DictionaryValueType, value1: DictionaryValueType, value2: DictionaryValueType) -> Boolean, action: (Int) -> Unit) {
        var remaining = NodeShared.getTripleCount(node)
        var offset = START_OFFSET
        var value0: DictionaryValueType = 0
        var value1: DictionaryValueType = 0
        var value2: DictionaryValueType = 0
        var lastChildPointer = getFirstChild(node)
        while (remaining > 0) {
            offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                value0 = v0
                value1 = v1
                value2 = v2
            }
            if (!checkTooSmall(value0, value1, value2)) {
                break
            }
            offset += readChildPointer(node, offset) {
                lastChildPointer = it
            }
            remaining--
        }
        action(lastChildPointer)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun iterator3(_node: BufferManagerPageWrapper, prefix: DictionaryValueTypeArray, lock: MyReadWriteLock, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        var node = _node
        var iterator: ColumnIterator? = null
        var nodeid = 0
        while (true) {
            findIteratorN(
                node,
                { value0, value1, value2 ->
                    (value0 < prefix[0]) || (value0 == prefix[0] && value1 < prefix[1]) || (value0 == prefix[0] && value1 == prefix[1] && value2 < prefix[2])
                },
                { it ->
                    nodeid = it
                    nodeManager.getNodeAnySuspended(
                        /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:178"/*SOURCE_FILE_END*/,
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator3(node, it, prefix, lock, nodeManager, timeout)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:190"/*SOURCE_FILE_END*/, nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun iterator2(_node: BufferManagerPageWrapper, prefix: DictionaryValueTypeArray, lock: MyReadWriteLock, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        var node = _node
        var iterator: ColumnIterator? = null
        var nodeid = 0
        while (true) {
            findIteratorN(
                node,
                { value0, value1, _ ->
                    (value0 < prefix[0]) || (value0 == prefix[0] && value1 < prefix[1])
                },
                { it ->
                    nodeid = it
                    nodeManager.getNodeAnySuspended(
                        /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:212"/*SOURCE_FILE_END*/,
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator2(node, it, prefix, lock, nodeManager, timeout)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:224"/*SOURCE_FILE_END*/, nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun iterator1(_node: BufferManagerPageWrapper, prefix: DictionaryValueTypeArray, lock: MyReadWriteLock, component: Int, nodeManager: NodeManager, timeout: Long): ColumnIterator {
        var node = _node
        var iterator: ColumnIterator? = null
        var nodeid = 0
        while (true) {
            findIteratorN(
                node,
                { value0, _, _ ->
                    (value0 < prefix[0])
                },
                { it ->
                    nodeid = it
                    nodeManager.getNodeAnySuspended(
                        /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:246"/*SOURCE_FILE_END*/,
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator1(node, it, prefix, lock, component, nodeManager, timeout)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:258"/*SOURCE_FILE_END*/, nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun initializeWith(node: BufferManagerPageWrapper, childs: MutableList<Int>, nodeManager: NodeManager) {
        var writtenHeaders: MutableList<Int>? = null
        var writtenTriples: MutableList<DictionaryValueType>? = null
        var offset = START_OFFSET
        val offsetEnd = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - START_OFFSET - MAX_POINTER_SIZE
        var triples = 0
        val tripleLast = DictionaryValueTypeArray(3)
        val tripleCurrent = DictionaryValueTypeArray(3)
        var current = childs.removeAt(0)
        setFirstChild(node, current)
        while (childs.size > 0 && offset < offsetEnd) {
            current = childs.removeAt(0)
            nodeManager.getNodeAny(
                /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:280"/*SOURCE_FILE_END*/,
                current,
                {
                    NodeLeaf.getFirstTriple(it, tripleCurrent)
                },
                {
                    getFirstTriple(it, tripleCurrent, nodeManager)
                }
            )
            nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:289"/*SOURCE_FILE_END*/, current)
            offset += NodeShared.writeTriple(node, offset, tripleLast, tripleCurrent)
            offset += writeChildPointer(node, offset, current)
            triples++
        }
        NodeShared.setTripleCount(node, triples)
        NodeShared.setNextNode(node, NodeManager.nodeNullPointer)
    }
}
