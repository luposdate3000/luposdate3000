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
import lupos.shared.IBufferManager
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

internal class NodeManager(@JvmField internal val bufferManager: IBufferManager) {

    internal companion object {
        internal const val nodeTypeLeaf = 1
        internal const val nodeTypeInner = 2
        internal const val nodeNullPointer = -1
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun releaseNode(call_location: String, nodeid: Int) {
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:33"/*SOURCE_FILE_END*/, nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun flushNode(call_location: String, nodeid: Int) {
        bufferManager.flushPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:38"/*SOURCE_FILE_END*/, nodeid)
    }

    internal fun getNodeLeaf(call_location: String, nodeid: Int,  actionLeaf: (BufferManagerPageWrapper) -> Unit) {
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:42"/*SOURCE_FILE_END*/, nodeid)
        actionLeaf(node)
    }

    internal fun getNodeAny(call_location: String, nodeid: Int,  actionLeaf: (BufferManagerPageWrapper) -> Unit,  actionInner: (BufferManagerPageWrapper) -> Unit) {
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:47"/*SOURCE_FILE_END*/, nodeid)
        when (NodeShared.getNodeType(node)) {
            nodeTypeInner -> {
                actionInner(node)
            }
            nodeTypeLeaf -> {
                actionLeaf(node)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

     internal fun getNodeAnySuspended(call_location: String, nodeid: Int,  actionLeaf:  (BufferManagerPageWrapper) -> Unit,  actionInner:  (BufferManagerPageWrapper) -> Unit) {
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:62"/*SOURCE_FILE_END*/, nodeid)
        when (NodeShared.getNodeType(node)) {
            nodeTypeInner -> {
                actionInner(node)
            }
            nodeTypeLeaf -> {
                actionLeaf(node)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    internal fun allocateNodeLeaf(call_location: String,  action:  (BufferManagerPageWrapper, Int) -> Unit) {
        val nodeid = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:77"/*SOURCE_FILE_END*/)
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:78"/*SOURCE_FILE_END*/, nodeid)
        NodeShared.setNodeType(node, nodeTypeLeaf)
        NodeShared.setNextNode(node, nodeNullPointer)
        NodeShared.setTripleCount(node, 0)
        action(node, nodeid)
    }

    internal fun allocateNodeInner(call_location: String,  action:  (BufferManagerPageWrapper, Int) -> Unit) {
        val nodeid = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:86"/*SOURCE_FILE_END*/)
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:87"/*SOURCE_FILE_END*/, nodeid)
        NodeShared.setNodeType(node, nodeTypeInner)
        NodeShared.setNextNode(node, nodeNullPointer)
        NodeShared.setTripleCount(node, 0)
        action(node, nodeid)
    }

     @Suppress("NOTHING_TO_INLINE")
    internal fun freeNode(call_location: String, nodeid: Int) {
        bufferManager.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:96"/*SOURCE_FILE_END*/, nodeid)
    }

     @Suppress("NOTHING_TO_INLINE")
    internal    fun freeNodeAndAllRelated(call_location: String, nodeid: Int) {
        releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:101"/*SOURCE_FILE_END*/, nodeid)
        freeNodeAndAllRelatedInternal(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:102"/*SOURCE_FILE_END*/, nodeid)
    }

     private fun freeNodeAndAllRelatedInternal(call_location: String, nodeid: Int) {
        if (nodeid != nodeNullPointer) {
            var node: BufferManagerPageWrapper? = null
            getNodeAny(
                /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:109"/*SOURCE_FILE_END*/,
                nodeid,
                {
                },
                { n ->
                    node = n
                }
            )
            if (node != null) {
                NodeInner.forEachChild(node!!) {
                    freeNodeAndAllRelatedInternal(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:119"/*SOURCE_FILE_END*/, it)
                }
            }
            freeNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:122"/*SOURCE_FILE_END*/, nodeid)
        }
    }

     @Suppress("NOTHING_TO_INLINE")
    internal    fun freeAllInner(call_location: String, nodeid: Int) {
        releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:128"/*SOURCE_FILE_END*/, nodeid)
        freeAllInnerInternal(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:129"/*SOURCE_FILE_END*/, nodeid)
    }

     private fun freeAllInnerInternal(call_location: String, nodeid: Int) {
        if (nodeid != nodeNullPointer) {
            var node: BufferManagerPageWrapper? = null
            getNodeAny(
                /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:136"/*SOURCE_FILE_END*/,
                nodeid,
                {
                },
                { n ->
                    node = n
                }
            )
            if (node != null) {
                NodeInner.forEachChild(node!!) {
                    freeAllInnerInternal(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:146"/*SOURCE_FILE_END*/, it)
                }
                freeNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:148"/*SOURCE_FILE_END*/, nodeid)
            } else {
                releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:150"/*SOURCE_FILE_END*/, nodeid)
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
     internal fun freeAllLeaves(call_location: String, nodeid: Int) {
        var pageid = nodeid
        while (pageid != nodeNullPointer) {
            val id = pageid
            getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:160"/*SOURCE_FILE_END*/, pageid) { node ->
                val tmp = NodeShared.getNextNode(node)
                pageid = tmp
            }
            freeNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:164"/*SOURCE_FILE_END*/, id)
        }
    }
}
