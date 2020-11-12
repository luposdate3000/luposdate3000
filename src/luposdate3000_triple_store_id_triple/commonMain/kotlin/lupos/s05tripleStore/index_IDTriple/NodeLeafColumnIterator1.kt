package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.iterator.ColumnIterator

internal class NodeLeafColumnIterator1(node: ByteArray, nodeid: Int, lock: MyReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock) {
    @JvmField
    var value = 0
    override /*suspend*/ fun next(): Int {
        if (label == 3) {
            label = 1
            __init()
        }
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
            return ResultSetDictionaryExt.nullValue
        }
    }

    override /*suspend*/ fun nextSIP(minValue: Int, result: IntArray) {
        if (label == 3) {
            label = 1
            __init()
        }
        if (label != 0) {
            var counter = 0
            //try next few triples
            if (needsReset) {
                needsReset = false
                value = 0
            }
            while (remaining > 0) {
                counter++
                offset += NodeShared.readTriple010(node, offset, value) { v ->
                    value = v
                }
                if (value >= minValue) {
                    updateRemaining()
                    result[0] = counter - 1
                    result[1] = value
                    return
                } else {
                    remaining--
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
            } else if (remaining == 0) {
                remaining = 1
                updateRemaining()
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
                    result[0] = counter - 1
                    result[1] = value
                    return
                }
            }
            result[0] = 0
            result[1] = ResultSetDictionaryExt.nullValue
        } else {
            result[0] = 0
            result[1] = ResultSetDictionaryExt.nullValue
        }
    }

    open override /*suspend*/ fun skipSIP(skipCount: Int): Int {
        if (label == 3) {
            label = 1
            __init()
        }
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
            return ResultSetDictionaryExt.nullValue
        }
    }
}
