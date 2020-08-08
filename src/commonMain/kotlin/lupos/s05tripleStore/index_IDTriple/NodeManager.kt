package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.BufferManager
import lupos.s00misc.File
import lupos.s00misc.SanityCheck

object NodeManager {
    const val nodeTypeLeaf = 1
    const val nodeTypeInner = 2
    const val nodeNullPointer = -1

    @JvmField
    val bufferManager = BufferManager("id_triples")

    @JvmField
    var allNodesLeafSize = 0

    @JvmField
    var allNodesInnerSize = 0
    fun debug() {
    }

    suspend fun safeToFolder() {
        SanityCheck.println { "debug NodeManager saving to folder '${BufferManager.bufferPrefix + "nodemanager/"}'" }
        File(BufferManager.bufferPrefix + "nodemanager/").mkdirs()
        debug()
        File(BufferManager.bufferPrefix + "nodemanager/header").dataOutputStream { out ->
            out.writeInt(allNodesLeafSize)
            out.writeInt(allNodesInnerSize)
        }
        bufferManager.safeToFolder()
    }

    suspend    /*inline*/ fun loadFromFolder() {
        SanityCheck.println({ "debug NodeManager loading from folder '${BufferManager.bufferPrefix + "nodemanager/"}'" })
        bufferManager.loadFromFolder()
        File(BufferManager.bufferPrefix + "nodemanager/header").dataInputStream { fis ->
            allNodesLeafSize = fis.readInt()
            allNodesInnerSize = fis.readInt()
        }
    }

    inline fun getNodeLeaf(pageid: Int, crossinline actionLeaf: (ByteArray) -> Unit) {
        SanityCheck.println({ "debug NodeManager getNode ${pageid.toString(16)}" })
        val node = bufferManager.getPage(pageid)
        actionLeaf(node)
    }

    inline fun getNodeInner(pageid: Int, crossinline actionInner: (ByteArray) -> Unit) {
        SanityCheck.println({ "debug NodeManager getNode ${pageid.toString(16)}" })
        val node = bufferManager.getPage(pageid)
        actionInner(node)
    }

    inline fun getNodeAny(pageid: Int, crossinline actionLeaf: (ByteArray) -> Unit, crossinline actionInner: (ByteArray) -> Unit) {
        SanityCheck.println({ "debug NodeManager getNode ${pageid.toString(16)}" })
        val node = bufferManager.getPage(pageid)
        when (NodeShared.getNodeType(node!!)) {
            nodeTypeInner -> {
                actionInner(node!!)
            }
            nodeTypeLeaf -> {
                actionLeaf(node!!)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    suspend inline fun getNodeAnySuspended(pageid: Int, crossinline actionLeaf: suspend (ByteArray) -> Unit, crossinline actionInner: suspend (ByteArray) -> Unit) {
        SanityCheck.println({ "debug NodeManager getNode ${pageid.toString(16)}" })
        val node = bufferManager.getPage(pageid)
        when (NodeShared.getNodeType(node!!)) {
            nodeTypeInner -> {
                actionInner(node!!)
            }
            nodeTypeLeaf -> {
                actionLeaf(node!!)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    /*inline*/ suspend fun allocateNodeLeaf(/*crossinline*/ action: suspend (ByteArray, Int) -> Unit) {
        SanityCheck.println({ "NodeManager.allocateNodeLeaf A" })
        var node: ByteArray? = null
        var pageid = -1
        bufferManager.createPage { p, i ->
            node = p
            pageid = i
        }
        allNodesLeafSize++
        SanityCheck.println({ "debug NodeManager allocateNodeLeafB ${pageid.toString(16)}" })
        NodeShared.setNodeType(node!!, nodeTypeLeaf)
        NodeShared.setNextNode(node!!, nodeNullPointer)
        NodeShared.setTripleCount(node!!, 0)
        action(node!!, pageid)
        SanityCheck.println({ "NodeManager.allocateNodeLeaf B" })
    }

    /*inline*/ suspend fun allocateNodeInner(/*crossinline*/ action: suspend (ByteArray, Int) -> Unit) {
        SanityCheck.println({ "NodeManager.allocateNodeInner A" })
        var node: ByteArray? = null
        var pageid = -1
        bufferManager.createPage { p, i ->
            node = p
            pageid = i
        }
        allNodesInnerSize++
        SanityCheck.println({ "debug NodeManager allocateNodeInnerB ${pageid.toString(16)}" })
        NodeShared.setNodeType(node!!, nodeTypeInner)
        NodeShared.setNextNode(node!!, nodeNullPointer)
        NodeShared.setTripleCount(node!!, 0)
        action(node!!, pageid)
        SanityCheck.println({ "NodeManager.allocateNodeInner B" })
    }

    /*inline*/ suspend fun freeNode(pageid: Int) {
        SanityCheck.println({ "NodeManager.freeNode A" })
        SanityCheck.println({ "debug NodeManager freeNode ${pageid.toString(16)}" })
        bufferManager.deletePage(pageid)
        SanityCheck.println({ "NodeManager.freeNode B" })
    }

    suspend fun freeNodeAndAllRelated(nodeid: Int) {
        SanityCheck.println({ "NodeManager.freeNodeAndAllRelated A" })
        SanityCheck.println({ "debug NodeManager freeNodeAndAllRelated ${nodeid.toString(16)}" })
        if (nodeid != nodeNullPointer) {
            var node: ByteArray? = null
            getNodeAny(nodeid, { node ->
            }, { n ->
                node = n
            })
            if (node != null) {
                NodeInner.forEachChild(node!!, {
                    freeNodeAndAllRelated(it)
                })
            }
            freeNode(nodeid)
        }
        SanityCheck.println({ "NodeManager.freeNodeAndAllRelated B" })
    }

    suspend inline fun freeAllLeaves(nodeid: Int) {
        SanityCheck.println({ "NodeManager.freeAllLeaves A" })
        SanityCheck.println({ "debug NodeManager freeAllLeaves ${nodeid.toString(16)}" })
        var pageid = nodeid
        while (pageid != nodeNullPointer) {
            var id = pageid
            getNodeLeaf(pageid, { node ->
                val tmp = NodeShared.getNextNode(node)
                pageid = tmp
            })
            freeNode(id)
        }
        SanityCheck.println({ "NodeManager.freeAllLeaves B" })
    }

    suspend fun freeAllInnerNodes(nodeid: Int) {
        SanityCheck.println({ "NodeManager.freeAllInnerNodes A" })
        SanityCheck.println({ "debug NodeManager freeAllInnerNodes ${nodeid.toString(16)}" })
        if (nodeid != nodeNullPointer) {
            var node: ByteArray? = null
            getNodeAny(nodeid, { node ->
            }, { n ->
                node = n
            })
            if (node != null) {
                NodeInner.forEachChild(node!!, {
                    freeAllInnerNodes(it)
                })
                freeNode(nodeid)
            }
        }
        SanityCheck.println({ "NodeManager.freeAllInnerNodes B" })
    }
}
