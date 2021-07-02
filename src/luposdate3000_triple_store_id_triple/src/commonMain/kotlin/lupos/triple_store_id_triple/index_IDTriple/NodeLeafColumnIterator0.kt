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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.MyReadWriteLock
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

internal class NodeLeafColumnIterator0(node: ByteArray, nodeid: Int, lock: MyReadWriteLock, nodeManager: NodeManager) : NodeLeafColumnIterator(node, nodeid, lock, nodeManager) {
    @JvmField
    var value: DictionaryValueType = 0
    override /*suspend*/ fun next(): DictionaryValueType {
        if (label == 3) {
            label = 1
            __init()
        }
        return if (label != 0) {
            if (needsReset) {
                needsReset = false
                value = 0
            }
            offset += NodeShared.readTriple100(node, offset, value) { v ->
                value = v
            }
            updateRemaining()
            value
        } else {
            DictionaryValueHelper.nullValue
        }
    }

    override /*suspend*/ fun nextSIP(minValue: Int, result: IntArray) {
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
                offset += NodeShared.readTriple100(node, offset, value) { v ->
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
            // look at the next pages
            val nodeidTmp = NodeShared.getNextNode(node)
            var valueTmp = 0
            var usedNextPage = false
            while (nodeidTmp != NodeManager.nodeNullPointer) {
                var nodeTmp = node
                nodeManager.getNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator0.kt:83", nodeidTmp) {
                    SanityCheck.check { node != it }
                    nodeTmp = it
                }
                val remainingTmp = NodeShared.getTripleCount(nodeTmp)
                SanityCheck.check { remainingTmp > 0 }
                var offsetTmp = NodeLeaf.START_OFFSET
                offsetTmp += NodeShared.readTriple100(nodeTmp, offsetTmp, 0) { v ->
                    valueTmp = v
                }
                if (valueTmp >= minValue) {
                    // dont accidentially skip some results at the end of this page
                    nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator0.kt:95", nodeidTmp)
                    break
                }
                nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator0.kt:98", nodeid)
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
            // search until the value is found
            while (remaining > 0) {
                counter++
                if (needsReset) {
                    needsReset = false
                    value = 0
                }
                offset += NodeShared.readTriple100(node, offset, value) { v ->
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
            result[1] = DictionaryValueHelper.nullValue
        } else {
            result[0] = 0
            result[1] = DictionaryValueHelper.nullValue
        }
    }

    override /*suspend*/ fun skipSIP(skipCount: Int): Int {
        if (label == 3) {
            label = 1
            __init()
        }
        if (label != 0) {
            var toSkip = skipCount
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
                offset += NodeShared.readTriple100(node, offset, value) { v ->
                    value = v
                }
                toSkip--
            }
            updateRemaining()
            return value
        } else {
            return DictionaryValueHelper.nullValue
        }
    }
}
