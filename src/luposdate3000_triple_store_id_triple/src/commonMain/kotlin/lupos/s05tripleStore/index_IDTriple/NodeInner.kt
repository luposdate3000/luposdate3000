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
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.iterator.ColumnIterator
internal object NodeInner {
    const val START_OFFSET = 16
    const val MAX_POINTER_SIZE = 4
    @Suppress("NOTHING_TO_INLINE") internal inline fun getFirstTriple(data: ByteArray, b: IntArray) {
        var node = data
        var done = false
        var nodeid = getFirstChild(node)
        while (!done) {
            var nextnodeid = nodeid
            NodeManager.getNodeAny(
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
            NodeManager.releaseNode(nodeid)
            nodeid = nextnodeid
        }
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun setFirstChild(data: ByteArray, node: Int) {
        ByteArrayHelper.writeInt4(data, 12, node)
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun getFirstChild(data: ByteArray): Int {
        return ByteArrayHelper.readInt4(data, 12)
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun writeChildPointer(node: ByteArray, offset: Int, pointer: Int): Int {
        ByteArrayHelper.writeInt4(node, offset, pointer)
        return 4
    }
    internal inline fun readChildPointer(node: ByteArray, offset: Int, crossinline action: (pointer: Int) -> Unit): Int {
        action(ByteArrayHelper.readInt4(node, offset))
        return 4
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun iterator(_node: ByteArray): TripleIterator {
        var iterator: TripleIterator? = null
        var node = _node
        while (true) {
            val nodeid = getFirstChild(node)
            SanityCheck.println { "Outside.refcount($nodeid)  x19" }
            NodeManager.getNodeAny(
                nodeid,
                {
                    iterator = NodeLeaf.iterator(it, nodeid)
                },
                {
                    node = it
                }
            )
            if (iterator == null) {
                SanityCheck.println { "Outside.refcount($nodeid)  x25" }
                NodeManager.releaseNode(nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun iterator(_node: ByteArray, lock: MyReadWriteLock, component: Int): ColumnIterator {
        var iterator: ColumnIterator? = null
        var node = _node
        while (true) {
            val nodeid = getFirstChild(node)
            SanityCheck.println { "Outside.refcount($nodeid)  x20" }
            NodeManager.getNodeAnySuspended(
                nodeid,
                {
                    iterator = NodeLeaf.iterator(it, nodeid, lock, component)
                },
                {
                    node = it
                }
            )
            if (iterator == null) {
                SanityCheck.println { "Outside.refcount($nodeid)  x50" }
                NodeManager.releaseNode(nodeid)
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
    /*suspend*/ internal inline fun findIteratorN(node: ByteArray, crossinline checkTooSmall: /*suspend*/ (value0: Int, value1: Int, value2: Int) -> Boolean, crossinline action: /*suspend*/ (Int) -> Unit) {
        var remaining = NodeShared.getTripleCount(node)
        var offset = START_OFFSET
        var value0 = 0
        var value1 = 0
        var value2 = 0
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
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun iterator3(_node: ByteArray, prefix: IntArray, lock: MyReadWriteLock): ColumnIterator {
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
                    SanityCheck.println { "Outside.refcount($it)  x21" }
                    NodeManager.getNodeAnySuspended(
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator3(node, it, prefix, lock)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                SanityCheck.println { "Outside.refcount($nodeid)  x78" }
                NodeManager.releaseNode(nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun iterator2(_node: ByteArray, prefix: IntArray, lock: MyReadWriteLock): ColumnIterator {
        var node = _node
        var iterator: ColumnIterator? = null
        var nodeid = 0
        while (true) {
            findIteratorN(
                node,
                { value0, value1, value2 ->
                    (value0 < prefix[0]) || (value0 == prefix[0] && value1 < prefix[1])
                },
                { it ->
                    nodeid = it
                    SanityCheck.println { "Outside.refcount($it)  x22" }
                    NodeManager.getNodeAnySuspended(
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator2(node, it, prefix, lock)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                SanityCheck.println { "Outside.refcount($nodeid)  x79" }
                NodeManager.releaseNode(nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun iterator1(_node: ByteArray, prefix: IntArray, lock: MyReadWriteLock, component: Int): ColumnIterator {
        var node = _node
        var iterator: ColumnIterator? = null
        var nodeid = 0
        while (true) {
            findIteratorN(
                node,
                { value0, value1, value2 ->
                    (value0 < prefix[0])
                },
                { it ->
                    nodeid = it
                    SanityCheck.println { "Outside.refcount($it)  x23" }
                    NodeManager.getNodeAnySuspended(
                        it,
                        { node ->
                            iterator = NodeLeaf.iterator1(node, it, prefix, lock, component)
                        },
                        {
                            node = it
                        }
                    )
                }
            )
            if (iterator == null) {
                SanityCheck.println { "Outside.refcount($nodeid)  x82" }
                NodeManager.releaseNode(nodeid)
            } else {
                break
            }
        }
        return iterator!!
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun initializeWith(node: ByteArray, nodeid: Int, childs: MutableList<Int>) {
        SanityCheck.check { childs.size > 0 }
        var writtenHeaders: MutableList<Int>? = null
        var writtenTriples: MutableList<Int>? = null
        SanityCheck {
            writtenHeaders = mutableListOf()
            writtenTriples = mutableListOf()
        }
        var offset = START_OFFSET
        val offsetEnd = node.size - START_OFFSET - MAX_POINTER_SIZE
        var triples = 0
        val tripleLast = IntArray(3)
        val tripleCurrent = IntArray(3)
        var current = childs.removeAt(0)
        SanityCheck {
            writtenHeaders!!.add(current)
        }
        setFirstChild(node, current)
        while (childs.size > 0 && offset < offsetEnd) {
            current = childs.removeAt(0)
            NodeManager.getNodeAny(
                current,
                {
                    NodeLeaf.getFirstTriple(it, tripleCurrent)
                },
                {
                    getFirstTriple(it, tripleCurrent)
                }
            )
            NodeManager.releaseNode(current)
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
