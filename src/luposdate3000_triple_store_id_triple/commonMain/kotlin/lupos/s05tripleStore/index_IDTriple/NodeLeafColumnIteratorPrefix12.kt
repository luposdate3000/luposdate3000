package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt

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
            var nodeidTmp = NodeShared.getNextNode(node)
            var value0Tmp = 0
            var value2Tmp = 0
            var usedNextPage = false
            while (nodeidTmp != NodeManager.nodeNullPointer) {
                var nodeTmp = node
                NodeManager.getNodeLeaf(nodeidTmp) {
                    SanityCheck.check { node != it }
                    nodeTmp = it
                }
val remainingTmp = NodeShared.getTripleCount(nodeTmp)
                SanityCheck.check { remainingTmp > 0 }
                var offsetTmp = NodeLeaf.START_OFFSET
                offsetTmp += NodeShared.readTriple101(nodeTmp, offsetTmp, 0, 0) { v0, v2 ->
                    value0Tmp = v0
                    value2Tmp = v2
                }
                if (value0Tmp > prefix[0] || value2Tmp >= minValue) {
                    //dont accidentially skip some results at the end of this page
                    NodeManager.releaseNode(nodeidTmp)
                    break
                }
                NodeManager.releaseNode(nodeid)
                counter += remaining
                remaining = remainingTmp
                nodeid = nodeidTmp
                node = nodeTmp
                value0 = value0Tmp
                value2 = value2Tmp
                offset = offsetTmp
                needsReset = false
                usedNextPage = true
                nodeidTmp = NodeShared.getNextNode(node)
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

     override /*suspend*/ fun skipSIP(skipCount: Int): Int {
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
                val nodeidTmp = NodeShared.getNextNode(node)
                SanityCheck.check { nodeidTmp != NodeManager.nodeNullPointer }
                NodeManager.getNodeLeaf(nodeidTmp) {
                    SanityCheck.check { node != it }
                    node = it
                }
                remaining = NodeShared.getTripleCount(node)
                NodeManager.releaseNode(nodeid)
                nodeid = nodeidTmp
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
                val nodeidTmp = NodeShared.getNextNode(node)
                if (nodeidTmp != NodeManager.nodeNullPointer) {
                    NodeManager.getNodeLeaf(nodeidTmp) {
                        SanityCheck.check { node != it }
                        node = it
                    }
                    remaining = NodeShared.getTripleCount(node)
                    NodeManager.releaseNode(nodeid)
                    nodeid = nodeidTmp
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
