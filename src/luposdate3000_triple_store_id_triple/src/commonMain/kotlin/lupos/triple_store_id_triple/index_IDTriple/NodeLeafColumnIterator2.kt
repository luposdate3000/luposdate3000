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
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

internal class NodeLeafColumnIterator2(node: BufferManagerPageWrapper, nodeid: Int, lock: MyReadWriteLock, nodeManager: NodeManager, timeout: Long) : NodeLeafColumnIterator(node, nodeid, lock, nodeManager, timeout) {
    @JvmField
    var value: DictionaryValueType = 0

    @JvmField
    var debugvalue0: DictionaryValueType = 0

    @JvmField
    var debugvalue1: DictionaryValueType = 0

    @JvmField
    var debugvalue2: DictionaryValueType = 0

    override /*suspend*/ fun next(): DictionaryValueType {
        if (label == 3) {
            label = 1
            __init()
        }
        return if (label != 0) {
            if (needsReset) {
                needsReset = false
                value = 0
  if(SanityCheck.enabled)                    {
                        debugvalue0 = 0
                        debugvalue1 = 0
                        debugvalue2 = 0
                    }
            }
            val len = NodeShared.readTriple001(node, offset, value) { v ->
                value = v
            }
            offset += len
            updateRemaining()
            value
        } else {
            DictionaryValueHelper.nullValue
        }
    }

    override /*suspend*/ fun nextSIP(minValue: DictionaryValueType, resultValue: DictionaryValueTypeArray, resultSkip: IntArray) {
        if (label == 3) {
            label = 1
            __init()
        }
        if (label != 0) {
            var counter = 0
            // try next few triples
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
                    resultSkip[0] = counter - 1
                    resultValue[0] = value
                    return
                } else {
                    remaining--
                }
            }
            // look at the next pages
            var nodeidTmp = NodeShared.getNextNode(node)
            var valueTmp: DictionaryValueType = 0
            var usedNextPage = false
            while (nodeidTmp != NodeManager.nodeNullPointer) {
                var nodeTmp = node
                nodeManager.getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator2.kt:136"/*SOURCE_FILE_END*/, nodeidTmp) {
if(SanityCheck.enabled){if(!( node != it )){throw Exception("SanityCheck failed")}}
                    nodeTmp = it
                }
                val remainingTmp = NodeShared.getTripleCount(nodeTmp)
if(SanityCheck.enabled){if(!( remainingTmp > 0 )){throw Exception("SanityCheck failed")}}
                var offsetTmp = NodeLeaf.START_OFFSET
                offsetTmp += NodeShared.readTriple001(nodeTmp, offsetTmp, 0) { v ->
                    valueTmp = v
                }
                if (valueTmp >= minValue) {
                    // dont accidentially skip some results at the end of this page
                    nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator2.kt:148"/*SOURCE_FILE_END*/, nodeidTmp)
                    break
                }
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator2.kt:151"/*SOURCE_FILE_END*/, nodeid)
                counter += remaining
                remaining = remainingTmp
                nodeid = nodeidTmp
                node = nodeTmp
                value = valueTmp
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
                    value = 0
                }
                offset += NodeShared.readTriple001(node, offset, value) { v ->
                    value = v
                }
                updateRemaining()
                if (value >= minValue) {
                    resultSkip[0] = counter - 1
                    resultValue[0] = value
                    return
                }
            }
            resultSkip[0] = 0
            resultValue[0] = DictionaryValueHelper.nullValue
        } else {
            resultSkip[0] = 0
            resultValue[0] = DictionaryValueHelper.nullValue
        }
    }

    override /*suspend*/ fun skipSIP(skipCount: Int): DictionaryValueType {
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
            remaining -= toSkip
            while (toSkip > 0) {
                offset += NodeShared.readTriple001(node, offset, value) { v ->
                    value = v
                }
                toSkip--
            }
            return value
        } else {
            return DictionaryValueHelper.nullValue
        }
    }
}
