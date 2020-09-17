package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIterator1(node: ByteArray, nodeid: Int, lock: ReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock) {
    @JvmField
    var value = 0
    suspend override fun next(): Int {
        if (label != 0) {
            if (needsReset) {
                needsReset = false
                value = 0
            }
            offset += NodeShared.readTriple010(node, offset, value) { v ->
                value = v
            }
            updateRemaining()
            return value
        } else {
            return ResultSetDictionary.nullValue
        }
    }

    suspend override fun nextSIP(minValue: Int,  skippedElements: (counter: Int) -> Unit): Int {
        if (label != 0) {
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
                offset += NodeShared.readTriple010(node, offset, value) { v ->
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
                })
                    remaining_tmp = NodeShared.getTripleCount(node_tmp)
                SanityCheck.check { remaining_tmp > 0 }
                var offset_tmp = NodeLeaf.START_OFFSET
                offset_tmp += NodeShared.readTriple010(node_tmp, offset_tmp, 0) { v ->
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
                offset += NodeShared.readTriple010(node, offset, value) { v ->
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

    override suspend open fun skipSIP(skipCount: Int): Int {
        if (label != 0) {
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
                offset += NodeShared.readTriple010(node, offset, value) { v ->
                    value = v
                }
                toSkip--
            }
            return value
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
