package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.File
import lupos.s00misc.MyListGeneric

object NodeManager {
var firstFreeNode=0
    val nodeNullPointer = 0x7FFFFFFF.toInt()
    val allNodes = MyListGeneric<Node?>()
    val allNodesTypes = MyListGeneric<NodeType>()

    enum class NodeType {
        LEAF,
        INNER,
        NULL
    }

    fun safeToFile(filename: String) {
        File(filename + ".type").dataOutputStream { out ->
            out.writeInt(allNodesTypes.size)
            allNodesTypes.forEach {
                out.writeInt(it.ordinal)
            }
        }
        File(filename + ".dat").dataOutputStream { out ->
            var it = allNodes.iterator()
            var it2 = allNodesTypes.iterator()
            while (it.hasNext()) {
                val type = it2.next()
                when (type) {
                    NodeType.LEAF -> {
                        out.write(it.next() as ByteArray)
                    }
                    NodeType.INNER -> {
                        out.write(it.next() as ByteArray)
                    }
                }
            }
        }
    }

    fun loadFromFile(filename: String) {
        var size = 0
        File(filename + ".type").dataInputStream { fis ->
            size = fis.readInt()
            for (i in 0 until size) {
                allNodesTypes.add(NodeType.values()[fis.readInt()])
            }
        }
firstFreeNode=Int.MAX_VALUE
        File(filename + ".dat").dataInputStream { fis ->
            var it = allNodesTypes.iterator()
            while (it.hasNext()) {
                val type = it.next()
                when (type) {
                    NodeType.LEAF -> {
                        val data = ByteArray(PAGE_SIZE_IN_BYTES)
                        fis.read(data)
                        val tmp = NodeLeaf(data)
                        allNodes.add(tmp)
                    }
                    NodeType.INNER -> {
                        val data = ByteArray(PAGE_SIZE_IN_BYTES)
                        fis.read(data)
                        val tmp = NodeInner(data)
                        allNodes.add(tmp)
                    }
                    NodeType.NULL -> {
if(allNodes.size<firstFreeNode){
firstFreeNode=allNodes.size
}
                        allNodes.add(null)
                    }
                }
            }
        }
if(allNodes.size<firstFreeNode){
firstFreeNode=allNodes.size
}
    }

    inline fun getNode(idx: Int): Node {
        return allNodes[idx]!!
    }

inline fun findFreeSlot():Int{
var i = firstFreeNode
if(firstFreeNode<allNodes.size){
        val it = allNodes.iterator(firstFreeNode)
        while (it.hasNext()) {
            var current = it.next()
            if (current == null) {
                break
            }
            i++
        }
}
firstFreeNode=i+1
return i
}

    inline fun allocateNodeLeaf(crossinline action: (NodeLeaf, Int) -> Unit) {
        var i = findFreeSlot()
        var tmp = NodeLeaf(ByteArray(PAGE_SIZE_IN_BYTES)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
        allNodesTypes[i] = NodeType.LEAF
        action(tmp, i)
    }

    inline fun allocateNodeInner(crossinline action: (NodeInner, Int) -> Unit) {
        var i = findFreeSlot()
        var tmp = NodeInner(ByteArray(PAGE_SIZE_IN_BYTES)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
        allNodesTypes[i] = NodeType.INNER
        action(tmp, i)
    }

    inline fun freeNode(nodeIdx: Int) {
        if (nodeIdx != nodeNullPointer) {
            allNodes[nodeIdx] = null
            allNodesTypes[nodeIdx] = NodeType.NULL
if(nodeIdx<firstFreeNode){
firstFreeNode=nodeIdx
}
        }
    }

    fun freeNodeAndAllRelated(nodeIdx: Int) {
        if (nodeIdx != nodeNullPointer) {
            var node = allNodes[nodeIdx]!!
            when (node) {
                is NodeLeaf -> {
                    freeNode(nodeIdx)
                }
                is NodeInner -> {
                    node.forEachChild {
                        freeNodeAndAllRelated(it)
                    }
                    freeNode(nodeIdx)
                }
                else -> {
                    require(false)
                }
            }
        }
    }
}
