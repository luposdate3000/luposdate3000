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
package lupos.triple_store_id_triple.index_IDTriple

import lupos.shared.BufferManagerPageWrapper
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.MyReadWriteLock
import kotlin.jvm.JvmField

internal class NodeLeafColumnIteratorPrefix22(node: BufferManagerPageWrapper, nodeid: Int, prefix: DictionaryValueTypeArray, lock: MyReadWriteLock, nodeManager: NodeManager, timeout: Long) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock, nodeManager, timeout) {
    @JvmField
    var value0: DictionaryValueType = 0

    @JvmField
    var value1: DictionaryValueType = 0

    @JvmField
    var value2: DictionaryValueType = 0

    init {
        label = 3
    }

    override /*suspend*/ fun next(): DictionaryValueType {
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
                        return DictionaryValueHelper.nullValue
                    } else {
                        done = value0 == prefix[0] && value1 == prefix[1]
                        updateRemaining {
                            if (!done) {
                                value2 = DictionaryValueHelper.nullValue
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
                    return DictionaryValueHelper.nullValue
                } else {
                    updateRemaining()
                }
                return value2
            }
            else -> {
                return DictionaryValueHelper.nullValue
            }
        }
    }

    override /*suspend*/ fun nextSIP(minValue: DictionaryValueType, resultValue: DictionaryValueTypeArray, resultSkip: IntArray) {
        if (label == 3) {
            label = 2
            __init()
        }
        var counter = 0
        if (label == 2) {
            next()
            if (value2 >= minValue) {
                resultSkip[0] = 0
                resultValue[0] = value2
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
                    resultSkip[0] = 0
                    resultValue[0] = DictionaryValueHelper.nullValue
                    return
                }
                if (value2 >= minValue) {
                    updateRemaining()
                    resultSkip[0] = counter - 1
                    resultValue[0] = value2
                    return
                } else {
                    remaining--
                }
            }
            // look at the next pages
            var nodeidTmp = NodeShared.getNextNode(node)
            var value0Tmp: DictionaryValueType = 0
            var value1Tmp: DictionaryValueType = 0
            var value2Tmp: DictionaryValueType = 0
            var usedNextPage = false
            while (nodeidTmp != NodeManager.nodeNullPointer) {
                var nodeTmp = node
                nodeManager.getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:156"/*SOURCE_FILE_END*/, nodeidTmp) {
                    nodeTmp = it
                }
                val remainingTmp = NodeShared.getTripleCount(nodeTmp)
                var offsetTmp = NodeLeaf.START_OFFSET
                offsetTmp += NodeShared.readTriple111(nodeTmp, offsetTmp, 0, 0, 0) { v0, v1, v2 ->
                    value0Tmp = v0
                    value1Tmp = v1
                    value2Tmp = v2
                }
                if (value0Tmp > prefix[0] || (value0Tmp == prefix[0] && value1Tmp > prefix[1]) || value2Tmp >= minValue) {
                    // dont accidentially skip some results at the end of this page
                    nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:168"/*SOURCE_FILE_END*/, nodeidTmp)
                    break
                }
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:171"/*SOURCE_FILE_END*/, nodeid)
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
                    resultSkip[0] = 0
                    resultValue[0] = DictionaryValueHelper.nullValue
                    return
                } else {
                    updateRemaining()
                }
                if (value2 >= minValue) {
                    resultSkip[0] = counter - 1
                    resultValue[0] = value2
                    return
                }
            }
            _close()
            resultSkip[0] = 0
            resultValue[0] = DictionaryValueHelper.nullValue
        } else {
            resultSkip[0] = 0
            resultValue[0] = DictionaryValueHelper.nullValue
        }
    }

    override /*suspend*/ fun skipSIP(skipCount: Int): DictionaryValueType {
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
                nodeManager.getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:248"/*SOURCE_FILE_END*/, nodeidTmp) {
                    node = it
                }
                remaining = NodeShared.getTripleCount(node)
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:252"/*SOURCE_FILE_END*/, nodeid)
                nodeid = nodeidTmp
                needsReset = true
                offset = NodeLeaf.START_OFFSET
            }
            if (needsReset) {
                needsReset = false
                value0 = 0
                value1 = 0
                value2 = 0
            }
            remaining -= toSkip
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
                    nodeManager.getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:275"/*SOURCE_FILE_END*/, nodeidTmp) {
                        node = it
                    }
                    remaining = NodeShared.getTripleCount(node)
                    nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:279"/*SOURCE_FILE_END*/, nodeid)
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
                return DictionaryValueHelper.nullValue
            }
            return value2
        } else {
            return DictionaryValueHelper.nullValue
        }
    }
}
