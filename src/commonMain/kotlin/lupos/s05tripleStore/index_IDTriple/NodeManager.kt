package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.BufferManager
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
    val bufferManager = BufferManager("id_triples")
    var allNodesLeafSize = 0
    var allNodesInnerSize = 0
    var allNodesFreeListLeaf = mutableSetOf<Int>()
    var allNodesFreeListInner = mutableSetOf<Int>()
    fun debug() {
        SanityCheck {
            //check that there are no memory leaks ...
            var leaves = IntArray(allNodesLeafSize)
            var leavesFromInner = IntArray(allNodesLeafSize)
            var inner = IntArray(allNodesInnerSize)
            for (i in allNodesFreeListLeaf) {
                leaves[i and nodePointerValueMask] = -10000
                leavesFromInner[i and nodePointerValueMask] = -10000
            }
            for (i in allNodesFreeListInner) {
                inner[i and nodePointerValueMask] = -10000
            }
            var nullpointers = 0
            for (i in 0 until allNodesLeafSize) {
                if (!allNodesFreeListLeaf.contains(i or nodePointerTypeLeaf)) {
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
            for (i in 0 until allNodesInnerSize) {
                if (!allNodesFreeListInner.contains(i or nodePointerTypeInner)) {
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

    fun safeToFolder() {
        println("nodemanager saving to folder '${BufferManager.bufferPrefix + "nodemanager/"}'")
        File(BufferManager.bufferPrefix + "nodemanager/").mkdirs()
        debug()
        File(BufferManager.bufferPrefix + "nodemanager/header").dataOutputStream { out ->
            out.writeInt(allNodesLeafSize)
            out.writeInt(allNodesInnerSize)
            out.writeInt(allNodesFreeListLeaf.size)
            out.writeInt(allNodesFreeListInner.size)
            for (i in allNodesFreeListLeaf) {
                out.writeInt(i)
            }
            for (i in allNodesFreeListInner) {
                out.writeInt(i)
            }
        }
        bufferManager.safeToFolder()
    }

    inline fun loadFromFolder() {
        println("nodemanager loading from folder '${BufferManager.bufferPrefix + "nodemanager/"}'")
        bufferManager.loadFromFolder()
        allNodesFreeListLeaf.clear()
        allNodesFreeListInner.clear()
        File(BufferManager.bufferPrefix + "nodemanager/header").dataInputStream { fis ->
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
        }
    }

    inline fun getNode(idx: Int, crossinline actionLeaf: (NodeLeaf) -> Unit, crossinline actionInner: (NodeInner) -> Unit) {
        val nodePointerType = idx and nodePointerTypeMask
        val nodePointerValue = idx and nodePointerValueMask
        when (nodePointerType) {
            nodePointerTypeInner -> {
                actionInner(NodeInner(bufferManager.getPage(idx)))
            }
            nodePointerTypeLeaf -> {
                actionLeaf(NodeLeaf(bufferManager.getPage(idx)))
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    inline fun allocateNodeLeaf(crossinline action: (NodeLeaf, Int) -> Unit) {
        var idx = allNodesLeafSize or nodePointerTypeLeaf
        if (allNodesFreeListLeaf.size > 0) {
            var idx = allNodesFreeListLeaf.first()
            allNodesFreeListLeaf.remove(idx)
            val node = NodeLeaf(bufferManager.createPage(idx))
            node.setNextNode(nodeNullPointer)
            node.setTripleCount(0)
            action(node, idx)
        } else {
            val node = NodeLeaf(bufferManager.createPage(idx))
            allNodesLeafSize++
            action(node, idx)
        }
    }

    inline fun allocateNodeInner(crossinline action: (NodeInner, Int) -> Unit) {
        var idx = allNodesInnerSize or nodePointerTypeInner
        if (allNodesFreeListInner.size > 0) {
            var idx = allNodesFreeListInner.first()
            allNodesFreeListInner.remove(idx)
            val node = NodeInner(bufferManager.createPage(idx))
            node.setNextNode(nodeNullPointer)
            node.setTripleCount(0)
            action(node, idx)
        } else {
            val node = NodeInner(bufferManager.createPage(idx))
            allNodesInnerSize++
            action(node, idx)
        }
    }

    inline fun freeNode(idx: Int) {
        val nodePointerType = idx and nodePointerTypeMask
        val nodePointerValue = idx and nodePointerValueMask
        when (nodePointerType) {
            nodePointerTypeInner -> {
                SanityCheck.check { !allNodesFreeListInner.contains(idx) }
                allNodesFreeListInner.add(idx)
            }
            nodePointerTypeLeaf -> {
                SanityCheck.check { !allNodesFreeListLeaf.contains(idx) }
                allNodesFreeListLeaf.add(idx)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
        bufferManager.deletePage(idx)
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

    fun freeAllInnerNodes(nodeIdx: Int) {
        if (nodeIdx != nodeNullPointer) {
            getNode(nodeIdx, { node ->
                //dont touch leaves
            }, { node ->
                node.forEachChild {
                    freeAllInnerNodes(it)
                }
                freeNode(nodeIdx)
            })
        }
    }
}
