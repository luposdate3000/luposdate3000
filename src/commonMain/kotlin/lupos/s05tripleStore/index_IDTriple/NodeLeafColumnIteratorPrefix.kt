package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.readIntX

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

abstract class NodeLeafColumnIteratorPrefix(@JvmField var node: ByteArray, @JvmField var nodeid: Int, @JvmField val prefix: IntArray, @JvmField val lock: ReadWriteLock) : ColumnIterator() {

    @JvmField
    var remaining = 0

    @JvmField
    var offset = NodeLeaf.startOffset

    @JvmField
    var label = 1

    @JvmField
    var needsReset = true

    inline suspend fun _init() {
        SanityCheck.println { "readLock(${lock.uuid}) x200" }
        lock.readLock()
        remaining = NodeShared.getTripleCount(node)
    }

    suspend inline fun _close() {
        if (label != 0) {
            label = 0
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x193" })
                NodeManager.releaseNode(nodeid)
            }
            SanityCheck.println { "readUnlock(${lock.uuid}) x192" }
            lock.readUnlock()
        }
    }

    suspend override fun close() {
        _close()
    }

    suspend inline fun updateRemaining( crossinline setDone: () -> Unit) {
        remaining--
        while (remaining == 0) {
            needsReset = true
            offset = NodeLeaf.startOffset
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
                    setDone()
return
            }
        }
    }
}
