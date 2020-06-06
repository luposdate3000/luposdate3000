package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.MyListGeneric
import lupos.s00misc.SanityCheck

object NodeManager {
    val nodePointerTypeNull = 0x00000000.toInt()
    val nodePointerTypeInner = 0x40000000.toInt()
    val nodePointerTypeLeaf = 0x20000000.toInt()
    val nodePointerTypeMask = 0x60000000.toInt()
    val nodePointerValueMask = (nodePointerTypeMask xor 0x7FFFFFFF).toInt()
    val nodeNullPointer = nodePointerValueMask
    val allNodesLeaf = MyListGeneric<NodeLeaf>()
    val allNodesInner = MyListGeneric<NodeInner>()
    var allNodesFreeListLeaf = mutableSetOf<Int>()
    var allNodesFreeListInner = mutableSetOf<Int>()
    inline fun <T> safeToFileHelper(filename: String, nodes: MyListGeneric<T>, freeList: IntArray, crossinline action: (T) -> ByteArray) {
        freeList.sort()
        println("freelist ${freeList.map { it }}")
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
                    } else {
                        idx = Int.MAX_VALUE
                    }
                }
                i++
            }
        }
    }

    inline fun <T> loadFromFileHelper(filename: String, nodes: MyListGeneric<T>, freeList: IntArray, count: Int, crossinline action: (ByteArray) -> T) {
        freeList.sort()
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
                    } else {
                        idx = Int.MAX_VALUE
                    }
                }
                var node = action(data)
                nodes.add(node)
            }
        }
    }

    fun debug() {
        SanityCheck {
            //check that there are no memory leaks ...
            var leaves = IntArray(allNodesLeaf.size)
            var leavesFromInner = IntArray(allNodesLeaf.size)
            var inner = IntArray(allNodesInner.size)
            for (i in allNodesFreeListLeaf) {
                leaves[i] = -10000
                leavesFromInner[i] = -10000
            }
            for (i in allNodesFreeListInner) {
                inner[i] = -10000
            }
            var nullpointers = 0
            for (i in 0 until allNodesLeaf.size) {
                if (!allNodesFreeListLeaf.contains(i)) {
                    getNode(i or nodePointerTypeLeaf, {
                        val x = it.getNextNode()
                        if (x == nodeNullPointer) {
                            nullpointers++
                        } else {
                            println("debug NodeManager iterating leaves .. ${(i or nodePointerTypeLeaf).toString(16)} -> ${x.toString(16)}")
                            leaves[x and nodePointerValueMask]++
                        }
                    }, {
                        SanityCheck.checkUnreachable()
                    })
                }
            }
            //each leaf must point to either null or a valid next node, but never to a free memory
            var count0 = 0
            var j = 0
            for (i in leaves) {
                if (i == 0) {
                    count0++
                } else {
                    println("debug NodeManager leaves ${(j or nodePointerTypeLeaf).toString(16)} $i")
                    SanityCheck.check { i == 1 || i == -10000 }
                }
                j++
            }
            SanityCheck.check { count0 == nullpointers }//there must be an equal amount of start and end leaves
            println("debug nullpointers $nullpointers")
            for (i in 0 until allNodesInner.size) {
                if (!allNodesFreeListInner.contains(i)) {
                    getNode(i or nodePointerTypeInner, {
                        SanityCheck.checkUnreachable()
                    }, {
                        it.forEachChild {
                            val nodePointerType = it and nodePointerTypeMask
                            val nodePointerValue = it and nodePointerValueMask
                            if (nodePointerType == nodePointerTypeInner) {
                                inner[nodePointerValue]++
                                println("debug NodeManager iterating inner leaves .. ${(i or nodePointerTypeInner).toString(16)} -> ${it.toString(16)}")
                            } else {
                                println("nodePointerType $nodePointerType $nodePointerTypeLeaf $nodeNullPointer")
                                SanityCheck.check { nodePointerType == nodePointerTypeLeaf }
                                leavesFromInner[nodePointerValue]++
                                println("debug NodeManager iterating inner .. ${(i or nodePointerTypeInner).toString(16)} -> ${it.toString(16)}")
                            }
                        }
                    })
                }
            }
            j = 0
            for (i in leavesFromInner) {
                println("debug NodeManager leavesFromInner ${(j or nodePointerTypeLeaf).toString(16)} $i")
                SanityCheck.check { i == 1 || i == -10000 }
                j++
            }
            count0 = 0
            j = 0
            for (i in inner) {
                if (i == 0) {
                    count0++
                } else {
                    println("debug NodeManager ${(j or nodePointerTypeInner).toString(16)} $i")
                    SanityCheck.check { i == 1 || i == -10000 }
                }
                j++
            }
        }
    }

    fun safeToFolder(foldername: String) {
        println("nodemanager saving to folder '$foldername'")
        File(foldername).mkdirs()
        debug()
        File(foldername + "/header").dataOutputStream { out ->
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
        safeToFileHelper(foldername + "/leaf", allNodesLeaf, allNodesFreeListLeaf.toIntArray(), { it.toByteArray() })
        safeToFileHelper(foldername + "/inner", allNodesInner, allNodesFreeListInner.toIntArray(), { it.toByteArray() })
    }

    inline fun loadFromFolder(foldername: String) {
        println("nodemanager loading from folder '$foldername'")
        allNodesLeaf.clear()
        allNodesInner.clear()
        allNodesFreeListLeaf.clear()
        allNodesFreeListInner.clear()
        File(foldername + "/header").dataInputStream { fis ->
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
            loadFromFileHelper(foldername + "/leaf", allNodesLeaf, allNodesFreeListLeaf.toIntArray(), leafSize, { NodeLeaf(it) })
            loadFromFileHelper(foldername + "/inner", allNodesInner, allNodesFreeListInner.toIntArray(), innerSize, { NodeInner(it) })
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
                SanityCheck.checkUnreachable()
            }
        }
    }

    inline fun allocateNodeLeaf(crossinline action: (NodeLeaf, Int) -> Unit) {
        var i = allNodesLeaf.size
        if (allNodesFreeListLeaf.size > 0) {
            var i = allNodesFreeListLeaf.first()
            allNodesFreeListLeaf.remove(i)
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
            i = allNodesFreeListInner.first()
            allNodesFreeListInner.remove(i)
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
                SanityCheck.check { !allNodesFreeListInner.contains(nodePointerValue) }
                allNodesFreeListInner.add(nodePointerValue)
            }
            nodePointerTypeLeaf -> {
                SanityCheck.check { !allNodesFreeListLeaf.contains(nodePointerValue) }
                allNodesFreeListLeaf.add(nodePointerValue)
            }
            else -> {
                SanityCheck.checkUnreachable()
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

    fun freeAllInnerNodes(nodeIdx: Int) {
        if (nodeIdx != nodeNullPointer) {
            getNode(nodeIdx, { node ->
                SanityCheck.checkUnreachable()
            }, { node ->
                node.forEachChild {
                    freeAllInnerNodes(it)
                }
                freeNode(nodeIdx)
            })
        }
    }

    fun freeAllLeaves(nodeIdx: Int) {
        var idx = nodeIdx
        while (idx != nodeNullPointer) {
            getNode(idx, { node ->
                val tmp = node.getNextNode()
                freeNode(idx)
                idx = tmp
            }, { node ->
                SanityCheck.checkUnreachable()
            })
        }
    }
}
