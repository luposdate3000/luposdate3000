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
    internal inline fun releaseNode(nodeid: Int) {
        bufferManager.releasePage(nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun flushNode(nodeid: Int) {
        bufferManager.flushPage(nodeid)
    }

    internal inline fun getNodeLeaf(nodeid: Int, crossinline actionLeaf: (ByteArray) -> Unit) {
        val node = bufferManager.getPage(nodeid)
        actionLeaf(node)
    }

    internal inline fun getNodeAny(nodeid: Int, crossinline actionLeaf: (ByteArray) -> Unit, crossinline actionInner: (ByteArray) -> Unit) {
        val node = bufferManager.getPage(nodeid)
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

    /*suspend*/ internal inline fun getNodeAnySuspended(nodeid: Int, crossinline actionLeaf: /*suspend*/ (ByteArray) -> Unit, crossinline actionInner: /*suspend*/ (ByteArray) -> Unit) {
        val node = bufferManager.getPage(nodeid)
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

    internal inline /*suspend*/ fun allocateNodeLeaf(crossinline action: /*suspend*/ (ByteArray, Int) -> Unit) {
        var node: ByteArray? = null
        var nodeid = -1
        bufferManager.createPage { p, i ->
            node = p
            nodeid = i
        }
        NodeShared.setNodeType(node!!, nodeTypeLeaf)
        NodeShared.setNextNode(node!!, nodeNullPointer)
        NodeShared.setTripleCount(node!!, 0)
        action(node!!, nodeid)
    }

    internal inline /*suspend*/ fun allocateNodeInner(crossinline action: /*suspend*/ (ByteArray, Int) -> Unit) {
        var node: ByteArray? = null
        var nodeid = -1
        bufferManager.createPage { p, i ->
            node = p
            nodeid = i
        }
        NodeShared.setNodeType(node!!, nodeTypeInner)
        NodeShared.setNextNode(node!!, nodeNullPointer)
        NodeShared.setTripleCount(node!!, 0)
        action(node!!, nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline /*suspend*/ fun freeNode(nodeid: Int) {
        bufferManager.deletePage(nodeid)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline /*suspend*/ fun freeNodeAndAllRelated(nodeid: Int) {
        releaseNode(nodeid)
        freeNodeAndAllRelatedInternal(nodeid)
    }

    /*suspend*/ private fun freeNodeAndAllRelatedInternal(nodeid: Int) {
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
                    freeNodeAndAllRelatedInternal(it)
                }
            }
            freeNode(nodeid)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun freeAllLeaves(nodeid: Int) {
        var pageid = nodeid
        while (pageid != nodeNullPointer) {
            val id = pageid
            getNodeLeaf(pageid) { node ->
                val tmp = NodeShared.getNextNode(node)
                pageid = tmp
            }
            freeNode(id)
        }
    }
}
