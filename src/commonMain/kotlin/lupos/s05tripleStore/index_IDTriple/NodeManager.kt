package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.*
import lupos.s00misc.Coverage

object NodeManager {
    val NodeNullPointer = 0x7FFFFFFF.toInt()
    val allNodes = MyListGeneric<Node?>()
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
        var tmp = NodeLeaf(ByteArray(50)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
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
        var tmp = NodeInner(ByteArray(100)) /*somethig small for tests, something large for real data*/
        allNodes[i] = tmp
        action(tmp, i)
    }

    fun freeNode(node: Int) {
        allNodes[node] = null
    }

    fun freeNodeAndAllRelated(nodeIdx: Int) {
        var idx = nodeIdx
        while (idx != NodeNullPointer) {
            var node = allNodes[idx]!!
            when (node) {
                is NodeLeaf -> {
                    allNodes[idx] = null
                    idx = node.getNextNode()
                }
                is NodeInner -> {
                    allNodes[idx] = null
                    //TODO release childs ...
                }
                else -> {
                    require(false)
                }
            }
        }
    }
}
