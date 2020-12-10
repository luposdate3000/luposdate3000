package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
internal class NodeLeafColumnIteratorPrefix11(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {
    @JvmField
    var value0 = 0
    @JvmField
    var value1 = 0
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
                        value1 = 0
                    }
// println("node $nodeid :: read $offset $remaining A")
                    offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                        value0 = v0
                        value1 = v1
                    }
                    if (value0 > prefix[0]) {
                        _close()
                        return ResultSetDictionaryExt.nullValue
                    } else {
                        done = value0 == prefix[0]
                        updateRemaining {
                            if (!done) {
                                value1 = ResultSetDictionaryExt.nullValue
                            }
                            done = true
                        }
                    }
                }
                if (label == 2) {
                    label = 1
                }
                return value1
            }
            1 -> {
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value1 = 0
                }
// println("node $nodeid :: read $offset $remaining B")
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                if (value0 > prefix[0]) {
                    _close()
                    return ResultSetDictionaryExt.nullValue
                } else {
                    updateRemaining()
                }
                return value1
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
            if (value1 >= minValue) {
                result[0] = 0
                result[1] = value1
                return
            }
            counter++
        }
        if (label != 0) {
            // try next few triples
            if (needsReset) {
                needsReset = false
                value0 = 0
                value1 = 0
            }
            while (remaining > 0) {
                counter++
// println("node $nodeid :: read $offset $remaining C")
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                if (value0 > prefix[0]) {
                    _close()
                    result[0] = 0
                    result[1] = ResultSetDictionaryExt.nullValue
                    return
                }
                if (value1 >= minValue) {
                    updateRemaining()
                    result[0] = counter - 1
                    result[1] = value1
                    return
                } else {
                    remaining--
                }
            }
            // look at the next pages
            var nodeidTmp = NodeShared.getNextNode(node)
            var value0Tmp = 0
            var value1Tmp = 0
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
// println("node $nodeid :: read $offset_tmp $remaining_tmp D")
                offsetTmp += NodeShared.readTriple110(nodeTmp, offsetTmp, 0, 0) { v0, v1 ->
                    value0Tmp = v0
                    value1Tmp = v1
                }
                if (value0Tmp > prefix[0] || value1Tmp >= minValue) {
                    // dont accidentially skip some results at the end of this page
                    NodeManager.releaseNode(nodeidTmp)
                    break
                }
                NodeManager.releaseNode(nodeid)
                counter += remaining
                remaining = remainingTmp
                nodeid = nodeidTmp
                node = nodeTmp
                value0 = value0Tmp
                value1 = value1Tmp
                offset = offsetTmp
// println("node $nodeid :: init $offset $remaining A")
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
            // search until the value is found
            while (remaining > 0) {
                counter++
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value1 = 0
                }
// println("node $nodeid :: read $offset $remaining E")
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                if (value0 > prefix[0]) {
                    _close()
                    result[0] = 0
                    result[1] = ResultSetDictionaryExt.nullValue
                    return
                } else {
                    updateRemaining()
                }
                if (value1 >= minValue) {
                    result[0] = counter - 1
                    result[1] = value1
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
                NodeManager.releaseNode(nodeid)
                nodeid = nodeidTmp
                remaining = NodeShared.getTripleCount(node)
                needsReset = true
                offset = NodeLeaf.START_OFFSET
                // println("node $nodeid :: init $offset $remaining B")
                SanityCheck.check { remaining > 0 }
                SanityCheck.check { label != 0 }
            }
            if (needsReset) {
                needsReset = false
                value0 = 0
                value1 = 0
            }
            remaining -= toSkip
            SanityCheck.check { remaining >= 0 }
            SanityCheck.check { toSkip > 0 }
            while (toSkip > 0) {
                // println("node $nodeid :: read $offset $remaining F")
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
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
                    NodeManager.releaseNode(nodeid)
                    nodeid = nodeidTmp
                    needsReset = true
                    remaining = NodeShared.getTripleCount(node)
                    offset = NodeLeaf.START_OFFSET
                } else {
                    _close()
                }
            }
            if (value0 > prefix[0]) {
// this must not happen?!?
                _close()
                return ResultSetDictionaryExt.nullValue
            }
            return value1
        } else {
            return ResultSetDictionaryExt.nullValue
        }
    }
}
