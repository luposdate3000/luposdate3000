package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.MyListGeneric

object NodeManager {
    val nodePointerTypeNull = 0x00000000.toInt()
    val nodePointerTypeInner = 0x40000000.toInt()
    val nodePointerTypeLeaf = 0x20000000.toInt()
    val nodePointerTypeMask = 0x60000000.toInt()
    val nodePointerValueMask = (nodePointerTypeMask xor 0x7FFFFFFF).toInt()
    val nodeNullPointer = nodePointerValueMask
    val allNodesLeaf = MyListGeneric<NodeLeaf>()
    val allNodesInner = MyListGeneric<NodeInner>()
    var allNodesFreeListLeaf = mutableListOf<Int>()
    var allNodesFreeListInner = mutableListOf<Int>()
    inline fun <T> safeToFileHelper(filename: String, nodes: MyListGeneric<T>, freeList: List<Int>, crossinline action: (T) -> ByteArray) {
        val it1 = freeList.iterator()
        val it2 = nodes.iterator()
        var idx = Int.MAX_VALUE
        var i = 0
        if (it1.hasNext()) {
            idx = it1.next()
        }
        File(filename).dataOutputStream { out ->
            while (it2.hasNext()) {
                var node = it2.next()
                if (i < idx) {
                    out.write(action(node))
                } else {
                    if (it1.hasNext()) {
                        idx = it1.next()
                    }
                }
                i++
            }
        }
    }

    inline fun <T> loadFromFileHelper(filename: String, nodes: MyListGeneric<T>, freeList: List<Int>, count: Int, crossinline action: (ByteArray) -> T) {
        val it1 = freeList.iterator()
        var idx = Int.MAX_VALUE
        if (it1.hasNext()) {
            idx = it1.next()
        }
        File(filename).dataInputStream { fis ->
            for (i in 0 until count) {
                val data = ByteArray(PAGE_SIZE_IN_BYTES)
                if (i < idx) {
                    fis.read(data)
                } else {
                    if (it1.hasNext()) {
                        idx = it1.next()
                    }
                }
                var node = action(data)
                nodes.add(node)
            }
        }
    }

    inline fun safeToFile(filename: String) {
        File(filename + ".header").dataOutputStream { out ->
            out.writeInt(allNodesLeaf.size)
            out.writeInt(allNodesInner.size)
            out.writeInt(allNodesFreeListLeaf.size)
            out.writeInt(allNodesFreeListInner.size)
            for (i in allNodesFreeListLeaf) {
                out.writeInt(i)
            }
            for (i in allNodesFreeListInner) {
                out.writeInt(i)
            }
        }
        safeToFileHelper(filename + ".leaf", allNodesLeaf, allNodesFreeListLeaf, { it.toByteArray() })
        safeToFileHelper(filename + ".inner", allNodesInner, allNodesFreeListInner, { it.toByteArray() })
    }

    inline fun loadFromFile(filename: String) {
        File(filename + ".header").dataInputStream { fis ->
            val leafSize = fis.readInt()
            val innerSize = fis.readInt()
            val allNodesFreeListLeafSize = fis.readInt()
            val allNodesFreeListInnerSize = fis.readInt()
            for (i in 0 until allNodesFreeListLeafSize) {
                allNodesFreeListLeaf.add(fis.readInt())
            }
            for (i in 0 until allNodesFreeListInnerSize) {
                allNodesFreeListInner.add(fis.readInt())
            }
            loadFromFileHelper(filename + ".leaf", allNodesLeaf, allNodesFreeListLeaf, leafSize, { NodeLeaf(it) })
            loadFromFileHelper(filename + ".inner", allNodesInner, allNodesFreeListInner, innerSize, { NodeInner(it) })
        }
    }

    inline fun getNode(idx: Int, crossinline actionLeaf: (NodeLeaf) -> Unit, crossinline actionInner: (NodeInner) -> Unit) {
        val nodePointerType = idx and nodePointerTypeMask
        val nodePointerValue = idx and nodePointerValueMask
        when (nodePointerType) {
            nodePointerTypeInner -> {
                actionInner(allNodesInner[nodePointerValue])
            }
            nodePointerTypeLeaf -> {
                actionLeaf(allNodesLeaf[nodePointerValue])
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    inline fun allocateNodeLeaf(crossinline action: (NodeLeaf, Int) -> Unit) {
        var i = allNodesLeaf.size
        if (allNodesFreeListLeaf.size > 0) {
            i = allNodesFreeListLeaf.removeAt(0)
            val node = allNodesLeaf[i]
            node.setNextNode(nodeNullPointer)
            node.setTripleCount(0)
            action(node, i or nodePointerTypeLeaf)
        } else {
            var node = NodeLeaf(ByteArray(PAGE_SIZE_IN_BYTES))
            allNodesLeaf[i] = node
            action(node, i or nodePointerTypeLeaf)
        }
    }

    inline fun allocateNodeInner(crossinline action: (NodeInner, Int) -> Unit) {
        var i = allNodesInner.size
        if (allNodesFreeListInner.size > 0) {
            i = allNodesFreeListInner.removeAt(0)
            val node = allNodesInner[i]
            node.setNextNode(nodeNullPointer)
            node.setTripleCount(0)
            action(node, i or nodePointerTypeInner)
        } else {
            var node = NodeInner(ByteArray(PAGE_SIZE_IN_BYTES))
            allNodesInner[i] = node
            action(node, i or nodePointerTypeInner)
        }
    }

    inline fun freeNode(idx: Int) {
        val nodePointerType = idx and nodePointerTypeMask
        val nodePointerValue = idx and nodePointerValueMask
        when (nodePointerType) {
            nodePointerTypeInner -> {
                allNodesFreeListInner.add(nodePointerValue)
            }
            nodePointerTypeLeaf -> {
                allNodesFreeListLeaf.add(nodePointerValue)
            }
            else -> {
                throw Exception("unreachable")
            }
        }
    }

    fun freeNodeAndAllRelated(nodeIdx: Int) {
        if (nodeIdx != nodeNullPointer) {
            getNode(nodeIdx, { node ->
                freeNode(nodeIdx)
            }, { node ->
                node.forEachChild {
                    freeNodeAndAllRelated(it)
                }
                freeNode(nodeIdx)
            })
        }
    }
}
