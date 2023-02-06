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
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

internal class NodeLeafIterator(@JvmField var node: BufferManagerPageWrapper, @JvmField var nodeid: Int, @JvmField internal val nodeManager: NodeManager) : TripleIterator() {
    @JvmField
    var remaining = NodeShared.getTripleCount(node)

    @JvmField
    var offset = NodeLeaf.START_OFFSET

    @JvmField
    var needsReset = true
    override fun hasNext() = remaining > 0
    override fun next(component: Int): DictionaryValueType {
        if (needsReset) {
            needsReset = false
            value[0] = DictionaryValueHelper.NULL
            value[1] = DictionaryValueHelper.NULL
            value[2] = DictionaryValueHelper.NULL
        }
        offset += NodeShared.readTriple111(node, offset, value[0], value[1], value[2]) { v0, v1, v2 ->
            value[0] = v0
            value[1] = v1
            value[2] = v2
        }
        updateRemaining()
        return value[component]
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun updateRemaining() {
        remaining--
        if (remaining == 0) {
            needsReset = true
            offset = NodeLeaf.START_OFFSET
            val nextid = NodeShared.getNextNode(node)
            nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafIterator.kt:57"/*SOURCE_FILE_END*/, nodeid)
            nodeid = nextid
            if (nodeid != NodeManager.nodeNullPointer) {
                nodeManager.getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafIterator.kt:60"/*SOURCE_FILE_END*/, nodeid) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafIterator.kt:61"/*SOURCE_FILE_END*/ }, { node != it })
                    node = it
                    remaining = NodeShared.getTripleCount(node)
                }
            }
        }
    }
}
