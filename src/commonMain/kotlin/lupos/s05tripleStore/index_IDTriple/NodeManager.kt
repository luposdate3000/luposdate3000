package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.*
import lupos.s00misc.Coverage

object NodeManager {
    val blockSize = 100
    val nodeNullPointer = 0x7FFFFFFF.toInt()
    val allNodes = MyListGeneric<Node?>()

    enum class NodeType {
        LEAF,
        INNER,
        NULL
    }

    val allNodesTypes = mutableListOf<NodeType>()
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
        File(filename + ".dat").dataInputStream { fis ->
            var it = allNodesTypes.iterator()
            while (it.hasNext()) {
                val type = it.next()
                when (type) {
                    NodeType.LEAF -> {
                        val data = ByteArray(blockSize)
                        fis.read(data)
                        val tmp = NodeLeaf(data)
                        allNodes.add(tmp)
                    }
                    NodeType.INNER -> {
                        val data = ByteArray(blockSize)
                        fis.read(data)
                        val tmp = NodeInner(data)
                        allNodes.add(tmp)
                    }
                    NodeType.NULL -> {
                        allNodes.add(null)
                    }
                }
            }
        }
    }

    fun getNode(idx: Int): Node {
        return allNodes[idx]!!
    }

    fun allocateNodeLeaf(action: (NodeLeaf, Int) -> Unit) {
        val it = allNodes.iterator()
        var i = 0
        while (it.hasNext()) {
            var current = it.next()
            if (current == null) {
                break
            }
            i++
        }
        var tmp = NodeLeaf(ByteArray(blockSize)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
        allNodesTypes[i] = NodeType.LEAF
        action(tmp, i)
    }

    fun allocateNodeInner(action: (NodeInner, Int) -> Unit) {
        val it = allNodes.iterator()
        var i = 0
        while (it.hasNext()) {
            var current = it.next()
            if (current == null) {
                break
            }
            i++
        }
        var tmp = NodeInner(ByteArray(blockSize)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
        allNodesTypes[i] = NodeType.INNER
        action(tmp, i)
    }

    fun freeNode(nodeIdx: Int) {
        if (nodeIdx != nodeNullPointer) {
            allNodes[nodeIdx] = null
            allNodesTypes[nodeIdx] = NodeType.NULL
        }
    }

    fun freeNodeAndAllRelated(nodeIdx: Int) {
        println("releasing :: $nodeIdx")
        if (nodeIdx != nodeNullPointer) {
            var node = allNodes[nodeIdx]!!
            when (node) {
                is NodeLeaf -> {
                    freeNode(nodeIdx)
                }
                is NodeInner -> {
                    freeNode(nodeIdx)
                    node.forEachChild {
                        freeNodeAndAllRelated(it)
                    }
                }
                else -> {
                    require(false)
                }
            }
        }
    }
}
