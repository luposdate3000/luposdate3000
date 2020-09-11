package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

abstract class NodeLeafColumnIterator(@JvmField var node: ByteArray, @JvmField var nodeid: Int, @JvmField val lock: ReadWriteLock) : ColumnIterator() {
    @JvmField
    var remaining = 0

    @JvmField
    var offset = NodeLeaf.START_OFFSET

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

    suspend inline fun updateRemaining() {
        remaining--
        while (remaining == 0) {
            needsReset = true
            offset = NodeLeaf.START_OFFSET
            SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x194" })
            NodeManager.releaseNode(nodeid)
            nodeid = NodeShared.getNextNode(node)
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x05" })
                NodeManager.getNodeLeaf(nodeid, {
                    SanityCheck.check { node != it }
                    node = it
                    remaining = NodeShared.getTripleCount(node)
                })
            } else {
                _close()
                break
            }
        }
    }
}
