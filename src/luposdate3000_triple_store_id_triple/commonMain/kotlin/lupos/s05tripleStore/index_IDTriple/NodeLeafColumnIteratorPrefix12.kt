package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField

internal class NodeLeafColumnIteratorPrefix12(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {
    @JvmField
    var value0 = 0

    @JvmField
    var value2 = 0

    init {
        label = 3
    }

    override /*suspend*/ fun next(): Int {
        if (label == 3) {
            label = 2
            __init()
        }
        when (label) {
            2 -> {
                var done = false
                while (!done) {
                    if (needsReset) {
                        needsReset = false
                        value0 = 0
                        value2 = 0
                    }
                    offset += NodeShared.readTriple101(node, offset, value0, value2) { v0, v2 ->
                        value0 = v0
                        value2 = v2
                    }
                    if (value0 > prefix[0]) {
                        _close()
                        return ResultSetDictionaryExt.nullValue
                    } else {
                        done = value0 == prefix[0]
                        updateRemaining {
                            if (!done) {
                                value2 = ResultSetDictionaryExt.nullValue
                            }
                            done = true
                        }
                    }
                }
                if (label == 2) {
                    label = 1
                }
                return value2
            }
            1 -> {
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value2 = 0
                }
                offset += NodeShared.readTriple101(node, offset, value0, value2) { v0, v2 ->
                    value0 = v0
                    value2 = v2
                }
                if (value0 > prefix[0]) {
                    _close()
                    return ResultSetDictionaryExt.nullValue
                } else {
                    updateRemaining()
                }
                return value2
            }
            else -> {
                return ResultSetDictionaryExt.nullValue
            }
        }
    }

    override /*suspend*/ fun nextSIP(minValue: Int, result: IntArray) {
        if (label == 3) {
            label = 2
            __init()
        }
        var counter = 0
        if (label == 2) {
            next()
            if (value2 >= minValue) {
                result[0] = 0
                result[1] = value2
                return
            }
            counter++
        }
        if (label != 0) {
            //try next few triples
            if (needsReset) {
                needsReset = false
                value0 = 0
                value2 = 0
            }
            while (remaining > 0) {
                counter++
                offset += NodeShared.readTriple101(node, offset, value0, value2) { v0, v2 ->
                    value0 = v0
                    value2 = v2
                }
                if (value0 > prefix[0]) {
                    _close()
                    result[0] = 0
                    result[1] = ResultSetDictionaryExt.nullValue
                    return
                }
                if (value2 >= minValue) {
                    updateRemaining()
                    result[0] = counter - 1
                    result[1] = value2
                    return
                } else {
                    remaining--
                }
            }
            //look at the next pages
            var nodeid_tmp = NodeShared.getNextNode(node)
            var value0_tmp = 0
            var value2_tmp = 0
            var usedNextPage = false
            while (nodeid_tmp != NodeManager.nodeNullPointer) {
                var node_tmp = node
                var remaining_tmp = 0
                NodeManager.getNodeLeaf(nodeid_tmp) {
                    SanityCheck.check { node != it }
                    node_tmp = it
                }
                remaining_tmp = NodeShared.getTripleCount(node_tmp)
                SanityCheck.check { remaining_tmp > 0 }
                var offset_tmp = NodeLeaf.START_OFFSET
                offset_tmp += NodeShared.readTriple101(node_tmp, offset_tmp, 0, 0) { v0, v2 ->
                    value0_tmp = v0
                    value2_tmp = v2
                }
                if (value0_tmp > prefix[0] || value2_tmp >= minValue) {
                    //dont accidentially skip some results at the end of this page
                    NodeManager.releaseNode(nodeid_tmp)
                    break
                }
                NodeManager.releaseNode(nodeid)
                counter += remaining
                remaining = remaining_tmp
                nodeid = nodeid_tmp
                node = node_tmp
                value0 = value0_tmp
                value2 = value2_tmp
                offset = offset_tmp
                needsReset = false
                usedNextPage = true
                nodeid_tmp = NodeShared.getNextNode(node)
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
                    value0 = 0
                    value2 = 0
                }
                offset += NodeShared.readTriple101(node, offset, value0, value2) { v0, v2 ->
                    value0 = v0
                    value2 = v2
                }
                if (value0 > prefix[0]) {
                    _close()
                    result[0] = 0
                    result[1] = ResultSetDictionaryExt.nullValue
                    return
                } else {
                    updateRemaining()
                }
                if (value2 >= minValue) {
                    result[0] = counter - 1
                    result[1] = value2
                    return
                }
            }
            _close()
            result[0] = 0
            result[1] = ResultSetDictionaryExt.nullValue
        } else {
            result[0] = 0
            result[1] = ResultSetDictionaryExt.nullValue
        }
    }

    open override /*suspend*/ fun skipSIP(skipCount: Int): Int {
        if (label == 3) {
            label = 2
            __init()
        }
        if (skipCount == 0) {
            return next()
        }
        var toSkip = skipCount + 1
        if (label == 2) {
            next()
            toSkip--
            if (toSkip == 0) {
                return next()
            }
        }
        if (label != 0) {
            while (toSkip > remaining) {
                toSkip -= remaining
                val nodeid_tmp = NodeShared.getNextNode(node)
                SanityCheck.check { nodeid_tmp != NodeManager.nodeNullPointer }
                NodeManager.getNodeLeaf(nodeid_tmp) {
                    SanityCheck.check { node != it }
                    node = it
                }
                remaining = NodeShared.getTripleCount(node)
                NodeManager.releaseNode(nodeid)
                nodeid = nodeid_tmp
                needsReset = true
                offset = NodeLeaf.START_OFFSET
                SanityCheck.check { remaining > 0 }
                SanityCheck.check { label != 0 }
            }
            if (needsReset) {
                needsReset = false
                value0 = 0
                value2 = 0
            }
            remaining -= toSkip
            SanityCheck.check { remaining >= 0 }
            SanityCheck.check { toSkip > 0 }
            while (toSkip > 0) {
                offset += NodeShared.readTriple101(node, offset, value0, value2) { v0, v2 ->
                    value0 = v0
                    value2 = v2
                }
                toSkip--
            }
            if (remaining == 0) {
                val nodeid_tmp = NodeShared.getNextNode(node)
                if (nodeid_tmp != NodeManager.nodeNullPointer) {
                    NodeManager.getNodeLeaf(nodeid_tmp) {
                        SanityCheck.check { node != it }
                        node = it
                    }
                    remaining = NodeShared.getTripleCount(node)
                    NodeManager.releaseNode(nodeid)
                    nodeid = nodeid_tmp
                    needsReset = true
                    offset = NodeLeaf.START_OFFSET
                } else {
                    _close()
                }
            }
            if (value0 > prefix[0]) {
//this must not happen?!?
                _close()
                return ResultSetDictionaryExt.nullValue
            }
            return value2
        } else {
            return ResultSetDictionaryExt.nullValue
        }
    }
}
