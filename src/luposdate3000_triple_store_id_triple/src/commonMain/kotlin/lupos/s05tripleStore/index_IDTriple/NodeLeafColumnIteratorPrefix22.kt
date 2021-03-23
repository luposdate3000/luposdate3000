/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s05tripleStore.index_IDTriple

import lupos.SOURCE_FILE
import lupos.dictionary.DictionaryExt
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField

internal class NodeLeafColumnIteratorPrefix22(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock, nodeManager: NodeManager) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock, nodeManager) {
    @JvmField
    var value0 = 0

    @JvmField
    var value1 = 0

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
                        return DictionaryExt.nullValue
                    } else {
                        done = value0 == prefix[0] && value1 == prefix[1]
                        updateRemaining {
                            if (!done) {
                                value2 = DictionaryExt.nullValue
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
                    return DictionaryExt.nullValue
                } else {
                    updateRemaining()
                }
                return value2
            }
            else -> {
                return DictionaryExt.nullValue
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
            // try next few triples
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
                    result[1] = DictionaryExt.nullValue
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
            // look at the next pages
            var nodeidTmp = NodeShared.getNextNode(node)
            var value0Tmp = 0
            var value1Tmp = 0
            var value2Tmp = 0
            var usedNextPage = false
            while (nodeidTmp != NodeManager.nodeNullPointer) {
                var nodeTmp = node
                nodeManager.getNodeLeaf(SOURCE_FILE, nodeidTmp) {
                    SanityCheck.check { node != it }
                    nodeTmp = it
                }
                val remainingTmp = NodeShared.getTripleCount(nodeTmp)
                SanityCheck.check { remainingTmp > 0 }
                var offsetTmp = NodeLeaf.START_OFFSET
                offsetTmp += NodeShared.readTriple111(nodeTmp, offsetTmp, 0, 0, 0) { v0, v1, v2 ->
                    value0Tmp = v0
                    value1Tmp = v1
                    value2Tmp = v2
                }
                if (value0Tmp > prefix[0] || (value0Tmp == prefix[0] && value1Tmp > prefix[1]) || value2Tmp >= minValue) {
                    // dont accidentially skip some results at the end of this page
                    nodeManager.releaseNode(SOURCE_FILE, nodeidTmp)
                    break
                }
                nodeManager.releaseNode(SOURCE_FILE, nodeid)
                counter += remaining
                remaining = remainingTmp
                nodeid = nodeidTmp
                node = nodeTmp
                value0 = value0Tmp
                value1 = value1Tmp
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
            // search until the value is found
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
                    result[1] = DictionaryExt.nullValue
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
            result[1] = DictionaryExt.nullValue
        } else {
            result[0] = 0
            result[1] = DictionaryExt.nullValue
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
                nodeManager.getNodeLeaf(SOURCE_FILE, nodeidTmp) {
                    SanityCheck.check { node != it }
                    node = it
                }
                remaining = NodeShared.getTripleCount(node)
                nodeManager.releaseNode(SOURCE_FILE, nodeid)
                nodeid = nodeidTmp
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
                val nodeidTmp = NodeShared.getNextNode(node)
                if (nodeidTmp != NodeManager.nodeNullPointer) {
                    nodeManager.getNodeLeaf(SOURCE_FILE, nodeidTmp) {
                        SanityCheck.check { node != it }
                        node = it
                    }
                    remaining = NodeShared.getTripleCount(node)
                    nodeManager.releaseNode(SOURCE_FILE, nodeid)
                    nodeid = nodeidTmp
                    needsReset = true
                    offset = NodeLeaf.START_OFFSET
                } else {
                    _close()
                }
            }
            if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1])) {
// this must not happen?!?
                _close()
                return DictionaryExt.nullValue
            }
            return value2
        } else {
            return DictionaryExt.nullValue
        }
    }
}
