package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIteratorPrefix2_2(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {
    @JvmField
    var value0 = 0

    @JvmField
    var value1 = 0

    @JvmField
    var value2 = 0

    init {
        label = 3
    }

    suspend override fun next(): Int {
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
                        value1 = 0
                        value2 = 0
                    }
                    offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                        value0 = v0
                        value1 = v1
                        value2 = v2
                    }
                    if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1])) {
                        _close()
                        return ResultSetDictionary.nullValue
                    } else {
                        done = value0 == prefix[0] && value1 == prefix[1]
                        updateRemaining() {
                            if (!done) {
                                value2 = ResultSetDictionary.nullValue
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
                    value1 = 0
                    value2 = 0
                }
                offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1])) {
                    _close()
                    return ResultSetDictionary.nullValue
                } else {
                    updateRemaining()
                }
                return value2
            }
            else -> {
                return ResultSetDictionary.nullValue
            }
        }
    }

    suspend override fun nextSIP(minValue: Int, result: IntArray) {
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
                value1 = 0
                value2 = 0
            }
            while (remaining > 0) {
                counter++
                offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1])) {
                    _close()
                    result[0] = 0
                    result[1] = ResultSetDictionary.nullValue
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
            var value1_tmp = 0
            var value2_tmp = 0
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
                offset_tmp += NodeShared.readTriple111(node_tmp, offset_tmp, 0, 0, 0) { v0, v1, v2 ->
                    value0_tmp = v0
                    value1_tmp = v1
                    value2_tmp = v2
                }
                if (value0_tmp > prefix[0] || (value0_tmp == prefix[0] && value1_tmp > prefix[1]) || value2_tmp >= minValue) {
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
                value1 = value1_tmp
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
                    value1 = 0
                    value2 = 0
                }
                offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1])) {
                    _close()
                    result[0] = 0
                    result[1] = ResultSetDictionary.nullValue
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
            result[1] = ResultSetDictionary.nullValue
        } else {
            result[0] = 0
            result[1] = ResultSetDictionary.nullValue
        }
    }

    override suspend open fun skipSIP(skipCount: Int): Int {
        if (label == 3) {
            label = 2
            __init()
        }
        if (skipCount == 0) {
            val value = next()
            return value
        }
        var toSkip = skipCount + 1
        if (label == 2) {
            next()
            toSkip--
            if (toSkip == 0) {
                val value = next()
                return value
            }
        }
        if (label != 0) {
            while (toSkip > remaining) {
                toSkip -= remaining
                var nodeid_tmp = NodeShared.getNextNode(node)
                SanityCheck.check { nodeid_tmp != NodeManager.nodeNullPointer }
                NodeManager.getNodeLeaf(nodeid_tmp, {
                    SanityCheck.check { node != it }
                    node = it
                })
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
                value1 = 0
                value2 = 0
            }
            remaining -= toSkip
            SanityCheck.check { remaining >= 0 }
            SanityCheck.check { toSkip > 0 }
            while (toSkip > 0) {
                offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                toSkip--
            }
            if (remaining == 0) {
                var nodeid_tmp = NodeShared.getNextNode(node)
                if (nodeid_tmp != NodeManager.nodeNullPointer) {
                    NodeManager.getNodeLeaf(nodeid_tmp, {
                        SanityCheck.check { node != it }
                        node = it
                    })
                    remaining = NodeShared.getTripleCount(node)
                    NodeManager.releaseNode(nodeid)
                    nodeid = nodeid_tmp
                    needsReset = true
                    offset = NodeLeaf.START_OFFSET
                } else {
                    _close()
                }
            }
            if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1])) {
//this must not happen?!?
                _close()
                return ResultSetDictionary.nullValue
            }
            return value2
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
