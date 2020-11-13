package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt

internal class NodeLeafColumnIterator2(node: ByteArray, nodeid: Int, lock: MyReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock) {
    @JvmField
    var value = 0
    override /*suspend*/ fun next(): Int {
        if (label == 3) {
            label = 1
            __init()
        }
        return if (label != 0) {
            if (needsReset) {
                needsReset = false
                value = 0
            }
            offset += NodeShared.readTriple001(node, offset, value) { v ->
                value = v
            }
            updateRemaining()
            value
        } else {
            ResultSetDictionaryExt.nullValue
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
                offset += NodeShared.readTriple001(node, offset, value) { v ->
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
            val nodeidTmp = NodeShared.getNextNode(node)
            var valueTmp = 0
            var usedNextPage = false
            while (nodeidTmp != NodeManager.nodeNullPointer) {
                var nodeTmp = node
                var remainingTmp = 0
                NodeManager.getNodeLeaf(nodeidTmp) {
                    SanityCheck.check { node != it }
                    nodeTmp = it
                }
                remainingTmp = NodeShared.getTripleCount(nodeTmp)
                SanityCheck.check { remainingTmp > 0 }
                var offsetTmp = NodeLeaf.START_OFFSET
                offsetTmp += NodeShared.readTriple001(nodeTmp, offsetTmp, 0) { v ->
                    valueTmp = v
                }
                if (valueTmp >= minValue) {
                    //dont accidentially skip some results at the end of this page
                    NodeManager.releaseNode(nodeidTmp)
                    break
                }
                NodeManager.releaseNode(nodeid)
                counter += remaining
                remaining = remainingTmp
                nodeid = nodeidTmp
                node = nodeTmp
                value = valueTmp
                offset = offsetTmp
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
                offset += NodeShared.readTriple001(node, offset, value) { v ->
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
                offset += NodeShared.readTriple001(node, offset, value) { v ->
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
