package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

abstract class NodeLeafColumnIterator(@JvmField var node: ByteArray, @JvmField var nodeid: Int, @JvmField val lock: ReadWriteLock) : ColumnIterator() {
    companion object {
        const val SIP_LOCAL_LIMIT = 3
    }

    @JvmField
    var remaining = 0

    @JvmField
    var offset = NodeLeaf.START_OFFSET

    @JvmField
    var label = 1

    @JvmField
    var needsReset = true


    inline suspend fun __init() {
        SanityCheck.println { "readLock(${lock.uuid}) x44" }
        lock.readLock()
        remaining = NodeShared.getTripleCount(node)
    }

    inline suspend fun _init() {
        __init()
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

    suspend inline fun updateRemaining(crossinline setDone: () -> Unit = {}) {
        SanityCheck.check { remaining > 0 }
        remaining--
        if (remaining == 0) {
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
                setDone()
            }
        }
        SanityCheck.check { remaining > 0 || label == 0 }
    }

    suspend inline fun next_helper(_value: Int, crossinline readTriple: (node: ByteArray, offset: Int, value: Int, crossinline action: (value: Int) -> Unit) -> Int): Int {
        if (label != 0) {
            var value = _value
            if (needsReset) {
                needsReset = false
                value = 0
            }
            offset += readTriple(node, offset, value) { v ->
                value = v
            }
            updateRemaining()
            return value
        } else {
            return ResultSetDictionary.nullValue
        }
    }

    inline suspend fun nextSIP_helper(_value: Int, skipCount: Int, crossinline readTriple: (node: ByteArray, offset: Int, value: Int, crossinline action: (value: Int) -> Unit) -> Int): Int {
        if (label != 0) {
            var value = _value
            var toSkip = skipCount + 1
            while (toSkip >= remaining) {
                toSkip -= remaining
                remaining = 1
                updateRemaining()
            }
            if (needsReset) {
                needsReset = false
                value = 0
            }
            while (toSkip > 0) {
                offset += readTriple(node, offset, value) { v ->
                    value = v
                }
                toSkip--
            }
            return value
        } else {
            return ResultSetDictionary.nullValue
        }
    }

    suspend inline fun nextSIP_helper(_value: Int, minValue: Int, crossinline skippedElements: (counter: Int) -> Unit, crossinline readTriple: (node: ByteArray, offset: Int, value: Int, crossinline action: (value: Int) -> Unit) -> Int): Int {
        if (label != 0) {
            var value = _value
            var counter = 0
            var limit = remaining
            if (limit > SIP_LOCAL_LIMIT) {
                limit = SIP_LOCAL_LIMIT
            }
            //try next few triples
            for (i in 0 until limit) {
                counter++
                if (needsReset) {
                    needsReset = false
                    value = 0
                }
                offset += readTriple(node, offset, value) { v ->
                    value = v
                }
                updateRemaining()
                if (value >= minValue) {
                    skippedElements(counter - 1)
                    return value
                }
            }
            //look at the next pages
            var nodeid_tmp = NodeShared.getNextNode(node)
            var value_tmp = 0
            var usedNextPage = false
            while (nodeid_tmp != NodeManager.nodeNullPointer) {
                var node_tmp = node
                var remaining_tmp = 0
                NodeManager.getNodeLeaf(nodeid_tmp, {
                    SanityCheck.check { node != it }
                    node_tmp = it
                    remaining_tmp = NodeShared.getTripleCount(node)
                })
                SanityCheck.check { remaining_tmp > 0 }
                var offset_tmp = NodeLeaf.START_OFFSET
                offset_tmp += readTriple(node_tmp, offset_tmp, 0) { v ->
                    value_tmp = v
                }
                if (value_tmp >= minValue) {
                    //dont accidentially skip some results at the end of this page
                    NodeManager.releaseNode(nodeid_tmp)
                    break
                }
                NodeManager.releaseNode(nodeid)
                counter += remaining
                remaining = remaining_tmp
                nodeid = nodeid_tmp
                node = node_tmp
                value = value_tmp
                offset = offset_tmp
                needsReset = false
                usedNextPage = true
            }
            if (usedNextPage) {
                updateRemaining()
                counter++
            }
            //search until the value is found
            while (remaining > 0) {
                counter++
                if (needsReset) {
                    needsReset = false
                    value = 0
                }
                offset += readTriple(node, offset, value) { v ->
                    value = v
                }
                updateRemaining()
                if (value >= minValue) {
                    skippedElements(counter - 1)
                    return value
                }
            }
            return ResultSetDictionary.nullValue
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
