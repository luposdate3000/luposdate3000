package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIteratorPrefix1_2(@JvmField var node: ByteArray, @JvmField var nodeid: Int, @JvmField val prefix: IntArray, @JvmField val lock: ReadWriteLock) : ColumnIterator() {
    @JvmField
    var remaining = 0

    @JvmField
    var offset = NodeLeaf.startOffset

    @JvmField
    var label = 1

    @JvmField
    var needsReset = true

    @JvmField
    var value0 = 0

    @JvmField
    var value2 = 0
    inline suspend fun _init() {
        SanityCheck.println { "readLock(${lock.uuid}) x42" }
        lock.readLock()
        remaining = NodeShared.getTripleCount(node)
    }

    suspend inline fun _close() {
        if (label != 0) {
            label = 0
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x196" })
                NodeManager.releaseNode(nodeid)
            }
            SanityCheck.println { "readUnlock(${lock.uuid}) x43" }
            lock.readUnlock()
        }
    }

    suspend override fun close() {
        _close()
    }

    suspend override fun next(): Int {
        if (label != 0) {
            while (true) {
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value2 = 0
                }
                var header = node.readInt1(offset)
                var headerA = header and 0b11000000
                val counter0: Int
                val counter1: Int
                val counter2: Int
                if (headerA == 0b0000000) {
                    counter0 = ((header and 0b00110000) shr 4) + 1
                    counter1 = ((header and 0b00001100) shr 2) + 1
                    counter2 = ((header and 0b00000011)) + 1
                } else if (headerA == 0b01000000) {
                    counter0 = 0
                    counter1 = ((header and 0b00001100) shr 2) + 1
                    counter2 = ((header and 0b00000011)) + 1
                } else {
                    SanityCheck.check { headerA == 0b10000000 }
                    counter0 = 0
                    counter1 = 0
                    counter2 = ((header and 0b00000011)) + 1
                }
                offset += 1
                when (counter0) {
                    1 -> {
                        value0 = value0 xor node.readInt1(offset)
                    }
                    2 -> {
                        value0 = value0 xor node.readInt2(offset)
                    }
                    3 -> {
                        value0 = value0 xor node.readInt3(offset)
                    }
                    4 -> {
                        value0 = value0 xor node.readInt4(offset)
                    }
                }
                val done: Boolean
                if (value0 > prefix[0]) {
                    _close()
                    return ResultSetDictionary.nullValue
                } else {
                    done = value0 == prefix[0]
                }
                offset += counter0 + counter1
                when (counter2) {
                    1 -> {
                        value2 = value2 xor node.readInt1(offset)
                    }
                    2 -> {
                        value2 = value2 xor node.readInt2(offset)
                    }
                    3 -> {
                        value2 = value2 xor node.readInt3(offset)
                    }
                    4 -> {
                        value2 = value2 xor node.readInt4(offset)
                    }
                }
                offset += counter2
                remaining--
                while (remaining == 0) {
                    needsReset = true
                    offset = NodeLeaf.startOffset
                    SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x197" })
                    NodeManager.releaseNode(nodeid)
                    nodeid = NodeShared.getNextNode(node)
                    if (nodeid != NodeManager.nodeNullPointer) {
                        SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x06" })
                        NodeManager.getNodeLeaf(nodeid, {
                            SanityCheck.check { node != it }
                            node = it
                            remaining = NodeShared.getTripleCount(node)
                        })
                    } else {
                        _close()
                        if (!done) {
                            return ResultSetDictionary.nullValue
                        } else {
                            return value2
                        }
                    }
                }
                if (done) {
                    break
                }
            }
            return value2
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
