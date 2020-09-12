package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIteratorPrefix1_1(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {
    @JvmField
    var value0 = 0

    @JvmField
    var value1 = 0

    init {
        label = 2
    }

    suspend override fun next(): Int {
        println("next ${lock.uuid} 1")
        when (label) {
            2 -> {
                var done = false
                while (!done) {
                    if (needsReset) {
                        needsReset = false
                        value0 = 0
                        value1 = 0
                    }
                    offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                        value0 = v0
                        value1 = v1
                    }
                    if (value0 > prefix[0]) {
                        _close()
                        println("close ${lock.uuid} 1")
                        return ResultSetDictionary.nullValue
                    } else {
                        done = value0 == prefix[0]
                        updateRemaining() {
                            if (!done) {
                                println("close ${lock.uuid} 2")
                                value1 = ResultSetDictionary.nullValue
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
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                if (value0 > prefix[0]) {
                    _close()
                    println("close ${lock.uuid} 3")
                    return ResultSetDictionary.nullValue
                } else {
                    updateRemaining()
                }
                return value1
            }
            else -> {
                println("close ${lock.uuid} 4")
                return ResultSetDictionary.nullValue
            }
        }
    }

    suspend override fun nextSIP(minValue: Int, crossinline skippedElements: (counter: Int) -> Unit): Int {
        println("next ${lock.uuid} 2")
        var counter = 0
        if (label == 2) {
            next()
            if (value1 >= minValue) {
                return value1
            }
            counter++
        }
        if (label != 0) {
            var limit = remaining
            if (limit > SIP_LOCAL_LIMIT) {
                limit = SIP_LOCAL_LIMIT
            }
            //try next few triples
            for (i in 0 until limit) {
                counter++
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value1 = 0
                }
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                if (value0 > prefix[0]) {
                    _close()
                    println("close ${lock.uuid} 5")
                    return ResultSetDictionary.nullValue
                } else {
                    updateRemaining()
                }
                if (value1 >= minValue) {
                    skippedElements(counter - 1)
                    return value1
                }
            }
            //look at the next pages
            var nodeid_tmp = NodeShared.getNextNode(node)
            var value0_tmp = 0
            var value1_tmp = 0
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
                offset_tmp += NodeShared.readTriple110(node_tmp, offset_tmp, 0, 0) { v0, v1 ->
                    value0_tmp = v0
                    value1_tmp = v1
                }
                if (value0_tmp > prefix[0] || value1_tmp >= minValue) {
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
                    value0 = 0
                    value1 = 0
                }
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                if (value0 > prefix[0]) {
                    _close()
                    println("close ${lock.uuid} 6")
                    return ResultSetDictionary.nullValue
                } else {
                    updateRemaining()
                }
                if (value1 >= minValue) {
                    skippedElements(counter - 1)
                    return value1
                }
            }
            _close()
            println("close ${lock.uuid} 7")
            return ResultSetDictionary.nullValue
        } else {
            println("close ${lock.uuid} 8")
            return ResultSetDictionary.nullValue
        }
    }

/*    override suspend open fun nextSIP(skipCount: Int): Int {
        println("next ${lock.uuid} 3")
        var toSkip = skipCount + 1
        if (label == 2) {
            next()
            toSkip--
        }
        if (label != 0) {
            while (toSkip > remaining) {
                toSkip -= remaining
                remaining = 1
                updateRemaining()
            }
            if (needsReset) {
                needsReset = false
                value0 = 0
                value1 = 0
            }
            while (toSkip > 0) {
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                toSkip--
                remaining--
            }
            if (remaining == 0) {
                remaining = 1
                updateRemaining()
            }
            if (value0 > prefix[0]) {
                _close()
                println("close ${lock.uuid} 9")
                return ResultSetDictionary.nullValue
            }
            return value1
        } else {
            println("close ${lock.uuid} 10")
            return ResultSetDictionary.nullValue
        }
    }*/
}
