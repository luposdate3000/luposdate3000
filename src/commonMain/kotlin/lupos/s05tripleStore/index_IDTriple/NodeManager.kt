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

    fun freeNode(nodeIdx: Int) {
if(nodeIdx!=NodeNullPointer){
        allNodes[nodeIdx] = null
}
    }

    fun freeNodeAndAllRelated(nodeIdx: Int) {
println("releasing :: $nodeIdx")
if(nodeIdx!=NodeNullPointer){
            var node = allNodes[nodeIdx]!!
            when (node) {
                is NodeLeaf -> {
freeNode(nodeIdx)
                }
                is NodeInner -> {
freeNode(nodeIdx)
node.forEachChild{
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
