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

import lupos.shared.IBufferManager
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

internal class NodeManager(bufferManager: IBufferManager) {
    @JvmField
    internal val bufferManager: IBufferManager = bufferManager

    internal companion object {
        internal const val nodeTypeLeaf = 1
        internal const val nodeTypeInner = 2
        internal const val nodeNullPointer = -1
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun releaseNode(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.releaseNode($nodeid) : $call_location" }
        bufferManager.releasePage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:35"/*SOURCE_FILE_END*/, nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun flushNode(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.flushNode($nodeid) : $call_location" }
        bufferManager.flushPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:41"/*SOURCE_FILE_END*/, nodeid)
    }

    internal inline fun getNodeLeaf(call_location: String, nodeid: Int, crossinline actionLeaf: (ByteArray) -> Unit) {
        SanityCheck.println_nodemanager { "NodeManager.getNodeLeaf($nodeid) : $call_location" }
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:46"/*SOURCE_FILE_END*/, nodeid)
        actionLeaf(node)
    }

    internal inline fun getNodeAny(call_location: String, nodeid: Int, crossinline actionLeaf: (ByteArray) -> Unit, crossinline actionInner: (ByteArray) -> Unit) {
        SanityCheck.println_nodemanager { "NodeManager.getNodeAny($nodeid) : $call_location" }
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:52"/*SOURCE_FILE_END*/, nodeid)
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

    /*suspend*/ internal inline fun getNodeAnySuspended(call_location: String, nodeid: Int, crossinline actionLeaf: /*suspend*/ (ByteArray) -> Unit, crossinline actionInner: /*suspend*/ (ByteArray) -> Unit) {
        SanityCheck.println_nodemanager { "NodeManager.getNodeAnySuspended($nodeid) : $call_location" }
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:68"/*SOURCE_FILE_END*/, nodeid)
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

    internal inline /*suspend*/ fun allocateNodeLeaf(call_location: String, crossinline action: /*suspend*/ (ByteArray, Int) -> Unit) {
        var nodeid = bufferManager.allocPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:83"/*SOURCE_FILE_END*/)
        var node = bufferManager.getPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:84"/*SOURCE_FILE_END*/, nodeid)
        NodeShared.setNodeType(node, nodeTypeLeaf)
        NodeShared.setNextNode(node, nodeNullPointer)
        NodeShared.setTripleCount(node, 0)
        SanityCheck.println_nodemanager { "NodeManager.allocateNodeLeaf($nodeid) : $call_location" }
        action(node, nodeid)
    }

    internal inline /*suspend*/ fun allocateNodeInner(call_location: String, crossinline action: /*suspend*/ (ByteArray, Int) -> Unit) {
        val nodeid = bufferManager.allocPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:93"/*SOURCE_FILE_END*/)
        val node = bufferManager.getPage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:94"/*SOURCE_FILE_END*/, nodeid)
        NodeShared.setNodeType(node, nodeTypeInner)
        NodeShared.setNextNode(node, nodeNullPointer)
        NodeShared.setTripleCount(node, 0)
        SanityCheck.println_nodemanager { "NodeManager.allocateNodeInner($nodeid) : $call_location" }
        action(node, nodeid)
    }

    /*suspend*/ @Suppress("NOTHING_TO_INLINE")
    internal inline
    fun freeNode(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeNode($nodeid) : $call_location" }
        bufferManager.deletePage(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:106"/*SOURCE_FILE_END*/, nodeid)
    }

    /*suspend*/ @Suppress("NOTHING_TO_INLINE")
    internal inline
    fun freeNodeAndAllRelated(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeNodeAndAllRelated($nodeid) : $call_location" }
        releaseNode(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:113"/*SOURCE_FILE_END*/, nodeid)
        freeNodeAndAllRelatedInternal(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:114"/*SOURCE_FILE_END*/, nodeid)
    }

    /*suspend*/ private fun freeNodeAndAllRelatedInternal(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeNodeAndAllRelatedInternal($nodeid) : $call_location" }
        if (nodeid != nodeNullPointer) {
            var node: ByteArray? = null
            getNodeAny(
                /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:122"/*SOURCE_FILE_END*/,
                nodeid,
                {
                },
                { n ->
                    node = n
                }
            )
            if (node != null) {
                NodeInner.forEachChild(node!!) {
                    freeNodeAndAllRelatedInternal(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:132"/*SOURCE_FILE_END*/, it)
                }
            }
            freeNode(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:135"/*SOURCE_FILE_END*/, nodeid)
        }
    }

    /*suspend*/ @Suppress("NOTHING_TO_INLINE")
    internal inline
    fun freeAllInner(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeAllInner($nodeid) : $call_location" }
        releaseNode(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:143"/*SOURCE_FILE_END*/, nodeid)
        freeAllInnerInternal(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:144"/*SOURCE_FILE_END*/, nodeid)
    }

    /*suspend*/ private fun freeAllInnerInternal(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeAllInnerInternal($nodeid) : $call_location" }
        if (nodeid != nodeNullPointer) {
            var node: ByteArray? = null
            getNodeAny(
                /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:152"/*SOURCE_FILE_END*/,
                nodeid,
                {
                },
                { n ->
                    node = n
                }
            )
            if (node != null) {
                NodeInner.forEachChild(node!!) {
                    freeAllInnerInternal(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:162"/*SOURCE_FILE_END*/, it)
                }
                freeNode(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:164"/*SOURCE_FILE_END*/, nodeid)
            } else {
                releaseNode(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:166"/*SOURCE_FILE_END*/, nodeid)
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun freeAllLeaves(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeAllLeaves($nodeid) : $call_location" }
        var pageid = nodeid
        while (pageid != nodeNullPointer) {
            val id = pageid
            getNodeLeaf(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:177"/*SOURCE_FILE_END*/, pageid) { node ->
                val tmp = NodeShared.getNextNode(node)
                pageid = tmp
            }
            freeNode(/*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:181"/*SOURCE_FILE_END*/, id)
        }
    }
}
