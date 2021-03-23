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
import lupos.buffermanager.BufferManager
import lupos.s00misc.SanityCheck

internal class NodeManager(bufferManager: BufferManager) {
    private val bufferManager: BufferManager = bufferManager

    public companion object {
        const val nodeTypeLeaf = 1
        const val nodeTypeInner = 2
        const val nodeNullPointer = -1
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun releaseNode(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.releaseNode($nodeid) : $call_location" }
        bufferManager.releasePage(SOURCE_FILE, nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun flushNode(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.flushNode($nodeid) : $call_location" }
        bufferManager.flushPage(SOURCE_FILE, nodeid)
    }

    internal inline fun getNodeLeaf(call_location: String, nodeid: Int, crossinline actionLeaf: (ByteArray) -> Unit) {
        SanityCheck.println_nodemanager { "NodeManager.getNodeLeaf($nodeid) : $call_location" }
        val node = bufferManager.getPage(SOURCE_FILE, nodeid)
        actionLeaf(node)
    }

    internal inline fun getNodeAny(call_location: String, nodeid: Int, crossinline actionLeaf: (ByteArray) -> Unit, crossinline actionInner: (ByteArray) -> Unit) {
        SanityCheck.println_nodemanager { "NodeManager.getNodeAny($nodeid) : $call_location" }
        val node = bufferManager.getPage(SOURCE_FILE, nodeid)
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
        val node = bufferManager.getPage(SOURCE_FILE, nodeid)
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
        var node: ByteArray? = null
        var nodeid = -1
        bufferManager.createPage(SOURCE_FILE) { p, pageid ->
            node = p
            nodeid = pageid
        }
        NodeShared.setNodeType(node!!, nodeTypeLeaf)
        NodeShared.setNextNode(node!!, nodeNullPointer)
        NodeShared.setTripleCount(node!!, 0)
        SanityCheck.println_nodemanager { "NodeManager.allocateNodeLeaf($nodeid) : $call_location" }
        action(node!!, nodeid)
    }

    internal inline /*suspend*/ fun allocateNodeInner(call_location: String, crossinline action: /*suspend*/ (ByteArray, Int) -> Unit) {
        var node: ByteArray? = null
        var nodeid = -1
        bufferManager.createPage(SOURCE_FILE) { p, pageid ->
            node = p
            nodeid = pageid
        }
        NodeShared.setNodeType(node!!, nodeTypeInner)
        NodeShared.setNextNode(node!!, nodeNullPointer)
        NodeShared.setTripleCount(node!!, 0)
        SanityCheck.println_nodemanager { "NodeManager.allocateNodeInner($nodeid) : $call_location" }
        action(node!!, nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline /*suspend*/ fun freeNode(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeNode($nodeid) : $call_location" }
        bufferManager.deletePage(SOURCE_FILE, nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline /*suspend*/ fun freeNodeAndAllRelated(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeNodeAndAllRelated($nodeid) : $call_location" }
        releaseNode(SOURCE_FILE, nodeid)
        freeNodeAndAllRelatedInternal(SOURCE_FILE, nodeid)
    }

    /*suspend*/ private fun freeNodeAndAllRelatedInternal(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeNodeAndAllRelatedInternal($nodeid) : $call_location" }
        if (nodeid != nodeNullPointer) {
            var node: ByteArray? = null
            getNodeAny(
                nodeid,
                {
                },
                { n ->
                    node = n
                }
            )
            if (node != null) {
                NodeInner.forEachChild(node!!) {
                    freeNodeAndAllRelatedInternal(SOURCE_FILE, it)
                }
            }
            freeNode(SOURCE_FILE, nodeid)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun freeAllLeaves(call_location: String, nodeid: Int) {
        SanityCheck.println_nodemanager { "NodeManager.freeAllLeaves($nodeid) : $call_location" }
        var pageid = nodeid
        while (pageid != nodeNullPointer) {
            val id = pageid
            getNodeLeaf(pageid) { node ->
                val tmp = NodeShared.getNextNode(node)
                pageid = tmp
            }
            freeNode(SOURCE_FILE, id)
        }
    }
}
