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

class NodeLeafColumnIterator1(@JvmField var node: ByteArray, @JvmField var nodeid: Int, @JvmField val lock: ReadWriteLock) : ColumnIterator() {
    @JvmField
    var remaining = 0

    @JvmField
    var offset = NodeLeaf.startOffset

    @JvmField
    var label = 1

    @JvmField
    var needsReset = true

    @JvmField
    var value = 0
    inline suspend fun _init() {
        SanityCheck.println { "readLock(${lock.uuid}) x44" }
        lock.readLock()
        remaining = NodeShared.getTripleCount(node)
    }

    suspend inline fun _close() {
        if (label != 0) {
            label = 0
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x38" })
                NodeManager.releaseNode(nodeid)
            }
            SanityCheck.println { "readUnlock(${lock.uuid}) x45" }
            lock.readUnlock()
        }
    }

    suspend override fun close() {
        _close()
    }

    suspend override fun next(): Int {
        if (label != 0) {
            if (needsReset) {
                needsReset = false
                value = 0
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
            offset += 1 + counter0
            when (counter1) {
                1 -> {
                    value = value xor node.readInt1(offset)
                }
                2 -> {
                    value = value xor node.readInt2(offset)
                }
                3 -> {
                    value = value xor node.readInt3(offset)
                }
                4 -> {
                    value = value xor node.readInt4(offset)
                }
            }
            offset += counter1 + counter2
            remaining--
            if (remaining == 0) {
                loop@ while (remaining == 0) {
                    needsReset = true
                    offset = NodeLeaf.startOffset
                    SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x39" })
                    NodeManager.releaseNode(nodeid)
                    nodeid = NodeShared.getNextNode(node)
                    if (nodeid != NodeManager.nodeNullPointer) {
                        SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x07" })
                        NodeManager.getNodeLeaf(nodeid, {
                            SanityCheck.check { node != it }
                            node = it
                            remaining = NodeShared.getTripleCount(node)
                        })
                    } else {
                        _close()
                        break@loop
                    }
                }
            }
            return value
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
