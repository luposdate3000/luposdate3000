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

import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.MyReadWriteLock
import lupos.shared.SanityCheck
import lupos.shared.inline.BufferManagerPage
import lupos.shared.operator.iterator.ColumnIterator

internal object NodeInner {
    const val START_OFFSET = 16
    const val MAX_POINTER_SIZE = 4

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getFirstTriple(data: ByteArray, b: DictionaryValueTypeArray, nodeManager: NodeManager) {
        var node = data
        var done = false
        var nodeid = getFirstChild(node)
        while (!done) {
            var nextnodeid = nodeid
            nodeManager.getNodeAny(
                "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:35",
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
            nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:46", nodeid)
            nodeid = nextnodeid
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setFirstChild(node: ByteArray, data: Int) {
        BufferManagerPage.writeInt4(node, 12, data)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getFirstChild(node: ByteArray): Int {
        return BufferManagerPage.readInt4(node, 12)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeChildPointer(node: ByteArray, offset: Int, pointer: Int): Int {
        BufferManagerPage.writeInt4(node, offset, pointer)
        return 4
    }

    internal inline fun readChildPointer(node: ByteArray, offset: Int, crossinline action: (pointer: Int) -> Unit): Int {
        action(BufferManagerPage.readInt4(node, offset))
        return 4
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun iterator(_node: ByteArray, nodeManager: NodeManager): TripleIterator {
        var iterator: TripleIterator? = null
        var node = _node
        while (true) {
            val nodeid = getFirstChild(node)
            nodeManager.getNodeAny(
                "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:79",
                nodeid,
                {
                    iterator = NodeLeaf.iterator(it, nodeid, nodeManager)
                },
                {
                    node = it
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:89", nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun iterator(_node: ByteArray, lock: MyReadWriteLock, component: Int, nodeManager: NodeManager): ColumnIterator {
        var iterator: ColumnIterator? = null
        var node = _node
        while (true) {
            val nodeid = getFirstChild(node)
            nodeManager.getNodeAnySuspended(
                "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:104",
                nodeid,
                {
                    iterator = NodeLeaf.iterator(it, nodeid, lock, component, nodeManager)
                },
                {
                    node = it
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:114", nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    internal inline /*suspend*/ fun forEachChild(node: ByteArray, crossinline action: /*suspend*/ (Int) -> Unit) {
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

    /*suspend*/ internal inline fun findIteratorN(node: ByteArray, crossinline checkTooSmall: /*suspend*/ (value0: DictionaryValueType, value1: DictionaryValueType, value2: DictionaryValueType) -> Boolean, crossinline action: /*suspend*/ (Int) -> Unit) {
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
    /*suspend*/ internal inline fun iterator3(_node: ByteArray, prefix: DictionaryValueTypeArray, lock: MyReadWriteLock, nodeManager: NodeManager): ColumnIterator {
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
                        "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:175",
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator3(node, it, prefix, lock, nodeManager)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:187", nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun iterator2(_node: ByteArray, prefix: DictionaryValueTypeArray, lock: MyReadWriteLock, nodeManager: NodeManager): ColumnIterator {
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
                        "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:209",
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator2(node, it, prefix, lock, nodeManager)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:221", nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun iterator1(_node: ByteArray, prefix: DictionaryValueTypeArray, lock: MyReadWriteLock, component: Int, nodeManager: NodeManager): ColumnIterator {
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
                        "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:243",
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator1(node, it, prefix, lock, component, nodeManager)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:255", nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun initializeWith(node: ByteArray, childs: MutableList<Int>, nodeManager: NodeManager) {
        SanityCheck.check { childs.size > 0 }
        var writtenHeaders: MutableList<Int>? = null
        var writtenTriples: MutableList<DictionaryValueType>? = null
        SanityCheck {
            writtenHeaders = mutableListOf()
            writtenTriples = mutableListOf()
        }
        var offset = START_OFFSET
        val offsetEnd = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - START_OFFSET - MAX_POINTER_SIZE
        var triples = 0
        val tripleLast = DictionaryValueTypeArray(3)
        val tripleCurrent = DictionaryValueTypeArray(3)
        var current = childs.removeAt(0)
        SanityCheck {
            writtenHeaders!!.add(current)
        }
        setFirstChild(node, current)
        while (childs.size > 0 && offset < offsetEnd) {
            current = childs.removeAt(0)
            nodeManager.getNodeAny(
                "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:285",
                current,
                {
                    NodeLeaf.getFirstTriple(it, tripleCurrent)
                },
                {
                    getFirstTriple(it, tripleCurrent, nodeManager)
                }
            )
            nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:294", current)
            SanityCheck {
                writtenHeaders!!.add(current)
                writtenTriples!!.add(tripleCurrent[0])
                writtenTriples!!.add(tripleCurrent[1])
                writtenTriples!!.add(tripleCurrent[2])
            }
            offset += NodeShared.writeTriple(node, offset, tripleLast, tripleCurrent)
            offset += writeChildPointer(node, offset, current)
            triples++
        }
        NodeShared.setTripleCount(node, triples)
        NodeShared.setNextNode(node, NodeManager.nodeNullPointer)
        SanityCheck {
            var remaining = NodeShared.getTripleCount(node)
            var offset2 = START_OFFSET
            var lastChildPointer = getFirstChild(node)
            SanityCheck.check { lastChildPointer == writtenHeaders!![0] }
            var i = 0
            var value0: DictionaryValueType = 0
            var value1: DictionaryValueType = 0
            var value2: DictionaryValueType = 0
            while (remaining > 0) {
                offset2 += NodeShared.readTriple111(node, offset2, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                SanityCheck.check { value0 == writtenTriples!![i * 3] }
                SanityCheck.check { value1 == writtenTriples!![i * 3 + 1] }
                SanityCheck.check { value2 == writtenTriples!![i * 3 + 2] }
                offset2 += readChildPointer(node, offset2) {
                    lastChildPointer = it
                }
                SanityCheck.check { lastChildPointer == writtenHeaders!![i + 1] }
                remaining--
                i++
            }
        }
    }
}
