package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.File
import lupos.s00misc.MyListGeneric

object NodeManager {
    var firstFreeNode = 0
    val nodeNullPointer = 0x7FFFFFFF.toInt()
    val allNodes = MyListGeneric<Node?>()

    fun safeToFile(filename: String) {
        File(filename + ".type").dataOutputStream { outType ->
            File(filename + ".dat").dataOutputStream { out ->
                outType.writeInt(allNodes.size)
                var it = allNodes.iterator()
                while (it.hasNext()) {
                    var node = it.next()
                    when (node) {
                        is NodeLeaf -> {
                            out.write(node as ByteArray)
                            outType.writeInt(0)
                        }
                        is NodeInner -> {
                            out.write(node as ByteArray)
                            outType.writeInt(1)
                        }
                        else -> {
                            require(node == null)
                            outType.writeInt(2)
                        }
                    }
                }
            }
        }
    }

    fun loadFromFile(filename: String) {
        firstFreeNode = Int.MAX_VALUE
        File(filename + ".type").dataInputStream { fisType ->
            File(filename + ".dat").dataInputStream { fis ->
                val size = fisType.readInt()
                for (i in 0 until size) {
                    var type = fisType.readInt()
                    when (type) {
                        0 -> {
                            val data = ByteArray(PAGE_SIZE_IN_BYTES)
                            fis.read(data)
                            val tmp = NodeLeaf(data)
                            allNodes.add(tmp)
                        }
                        1 -> {
                            val data = ByteArray(PAGE_SIZE_IN_BYTES)
                            fis.read(data)
                            val tmp = NodeInner(data)
                            allNodes.add(tmp)
                        }
                        else -> {
                            require(type == 2)
                            if (allNodes.size < firstFreeNode) {
                                firstFreeNode = allNodes.size
                            }
                            allNodes.add(null)
                        }
                    }
                }
            }
        }
        if (allNodes.size < firstFreeNode) {
            firstFreeNode = allNodes.size
        }
    }

    inline fun getNode(idx: Int): Node {
        return allNodes[idx]!!
    }

    inline fun findFreeSlot(): Int {
        var i = firstFreeNode
        if (firstFreeNode < allNodes.size) {
            val it = allNodes.iterator(firstFreeNode)
            while (it.hasNext()) {
                var current = it.next()
                if (current == null) {
                    break
                }
                i++
            }
        }
        firstFreeNode = i + 1
        return i
    }

    inline fun allocateNodeLeaf(crossinline action: (NodeLeaf, Int) -> Unit) {
        var i = findFreeSlot()
        var tmp = NodeLeaf(ByteArray(PAGE_SIZE_IN_BYTES)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
        action(tmp, i)
    }

    inline fun allocateNodeInner(crossinline action: (NodeInner, Int) -> Unit) {
        var i = findFreeSlot()
        var tmp = NodeInner(ByteArray(PAGE_SIZE_IN_BYTES)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
        action(tmp, i)
    }

    inline fun freeNode(nodeIdx: Int) {
        if (nodeIdx != nodeNullPointer) {
            allNodes[nodeIdx] = null
            if (nodeIdx < firstFreeNode) {
                firstFreeNode = nodeIdx
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
