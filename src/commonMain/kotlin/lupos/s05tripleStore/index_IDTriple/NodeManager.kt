package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.BufferManager
import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck

object NodeManager {
    const val nodePointerTypeNull = 0x00000000.toInt()
    const val nodePointerTypeInner = 0x40000000.toInt()
    const val nodePointerTypeLeaf = 0x20000000.toInt()
    const val nodePointerTypeMask = 0x60000000.toInt()
    const val nodePointerValueMask = (nodePointerTypeMask xor 0x7FFFFFFF).toInt()
    const val nodeNullPointer = nodePointerValueMask

    @JvmField
    val bufferManager = BufferManager("id_triples")

    @JvmField
    var allNodesLeafSize = 0

    @JvmField
    var allNodesInnerSize = 0

    @JvmField
    var allNodesFreeListLeaf = mutableSetOf<Int>()

    @JvmField
    var allNodesFreeListInner = mutableSetOf<Int>()

    @JvmField
    val lockInner = ReadWriteLock()

    @JvmField
    val lockLeaf = ReadWriteLock()
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
                        val x = NodeShared.getNextNode(it)
                        if (x == nodeNullPointer) {
                            nullpointers++
                        } else {
                            SanityCheck.println { "debug NodeManager iterating leaves .. ${(i or nodePointerTypeLeaf).toString(16)} -> ${x.toString(16)}" }
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
                    SanityCheck.println { "debug NodeManager leaves ${(j or nodePointerTypeLeaf).toString(16)} $i" }
                    SanityCheck.check { i == 1 || i == -10000 }
                }
                j++
            }
            SanityCheck.check { count0 == nullpointers }//there must be an equal amount of start and end leaves
            SanityCheck.println { "debug nullpointers $nullpointers" }
            for (i in 0 until allNodesInnerSize) {
                if (!allNodesFreeListInner.contains(i or nodePointerTypeInner)) {
                    getNode(i or nodePointerTypeInner, {
                        SanityCheck.checkUnreachable()
                    }, {
                        NodeInner.forEachChild(it, {
                            val nodePointerType = it and nodePointerTypeMask
                            val nodePointerValue = it and nodePointerValueMask
                            if (nodePointerType == nodePointerTypeInner) {
                                inner[nodePointerValue]++
                                SanityCheck.println { "debug NodeManager iterating inner leaves .. ${(i or nodePointerTypeInner).toString(16)} -> ${it.toString(16)}" }
                            } else {
                                SanityCheck.println { "nodePointerType $nodePointerType $nodePointerTypeLeaf $nodeNullPointer" }
                                SanityCheck.check { nodePointerType == nodePointerTypeLeaf }
                                leavesFromInner[nodePointerValue]++
                                SanityCheck.println { "debug NodeManager iterating inner .. ${(i or nodePointerTypeInner).toString(16)} -> ${it.toString(16)}" }
                            }
                        })
                    })
                }
            }
            j = 0
            for (i in leavesFromInner) {
                SanityCheck.println { "debug NodeManager leavesFromInner ${(j or nodePointerTypeLeaf).toString(16)} $i" }
                SanityCheck.check { i == 1 || i == -10000 }
                j++
            }
            count0 = 0
            j = 0
            for (i in inner) {
                if (i == 0) {
                    count0++
                } else {
                    SanityCheck.println { "debug NodeManager ${(j or nodePointerTypeInner).toString(16)} $i" }
                    SanityCheck.check { i == 1 || i == -10000 }
                }
                j++
            }
        }
    }

    fun safeToFolder() {
        SanityCheck.println { "debug NodeManager saving to folder '${BufferManager.bufferPrefix + "nodemanager/"}'" }
        File(BufferManager.bufferPrefix + "nodemanager/").mkdirs()
        debug()
        lockInner.withWriteLock {
            lockLeaf.withWriteLock {
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
        }
    }

    /*inline*/ fun loadFromFolder() {
        SanityCheck.println({ "debug NodeManager loading from folder '${BufferManager.bufferPrefix + "nodemanager/"}'" })
        lockInner.withWriteLock {
            lockLeaf.withWriteLock {
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
        }
    }

    /*inline*/ fun getNode(idx: Int, /*crossinline*/ actionLeaf: (ByteArray) -> Unit, /*crossinline*/ actionInner: (ByteArray) -> Unit) {
        SanityCheck.println({ "debug NodeManager getNode ${idx.toString(16)}" })
        val nodePointerType = idx and nodePointerTypeMask
        val nodePointerValue = idx and nodePointerValueMask
        when (nodePointerType) {
            nodePointerTypeInner -> {
                var node: ByteArray? = null
                lockInner.withReadLock {
                    SanityCheck.check { !allNodesFreeListInner.contains(idx) }
                    node = bufferManager.getPage(idx)
                }
                actionInner(node!!)
            }
            nodePointerTypeLeaf -> {
                var node: ByteArray? = null
                lockLeaf.withReadLock {
                    SanityCheck.check { !allNodesFreeListLeaf.contains(idx) }
                    node = bufferManager.getPage(idx)
                }
                actionLeaf(node!!)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    val reuseOldIDs = false
    /*inline*/ fun allocateNodeLeaf(/*crossinline*/ action: (ByteArray, Int) -> Unit) {
        SanityCheck.println({ "NodeManager.allocateNodeLeaf A" })
        var node: ByteArray? = null
        var idx = -1
        lockLeaf.withWriteLock {
            idx = allNodesLeafSize or nodePointerTypeLeaf
            if (reuseOldIDs && allNodesFreeListLeaf.size > 0) {
                idx = allNodesFreeListLeaf.first()
                allNodesFreeListLeaf.remove(idx)
                node = bufferManager.createPage(idx)
                NodeShared.setNextNode(node!!, nodeNullPointer)
                NodeShared.setTripleCount(node!!, 0)
                SanityCheck.println({ "debug NodeManager allocateNodeLeafA ${idx.toString(16)}" })
            } else {
                node = bufferManager.createPage(idx)
                allNodesLeafSize++
                SanityCheck.println({ "debug NodeManager allocateNodeLeafB ${idx.toString(16)}" })
            }
        }
        action(node!!, idx)
        SanityCheck.println({ "NodeManager.allocateNodeLeaf B" })
    }

    /*inline*/ fun allocateNodeInner(/*crossinline*/ action: (ByteArray, Int) -> Unit) {
        SanityCheck.println({ "NodeManager.allocateNodeInner A" })
        var node: ByteArray? = null
        var idx = -1
        lockInner.withWriteLock {
            idx = allNodesInnerSize or nodePointerTypeInner
            if (reuseOldIDs && allNodesFreeListInner.size > 0) {
                idx = allNodesFreeListInner.first()
                allNodesFreeListInner.remove(idx)
                node = bufferManager.createPage(idx)
                NodeShared.setNextNode(node!!, nodeNullPointer)
                NodeShared.setTripleCount(node!!, 0)
                SanityCheck.println({ "debug NodeManager allocateNodeInnerA ${idx.toString(16)}" })
            } else {
                node = bufferManager.createPage(idx)
                allNodesInnerSize++
                SanityCheck.println({ "debug NodeManager allocateNodeInnerB ${idx.toString(16)}" })
            }
        }
        action(node!!, idx)
        SanityCheck.println({ "NodeManager.allocateNodeInner B" })
    }

    /*inline*/ fun freeNode(idx: Int) {
        SanityCheck.println({ "NodeManager.freeNode A" })
        SanityCheck.println({ "debug NodeManager freeNode ${idx.toString(16)}" })
        val nodePointerType = idx and nodePointerTypeMask
        val nodePointerValue = idx and nodePointerValueMask
        when (nodePointerType) {
            nodePointerTypeInner -> {
                lockInner.withWriteLock {
                    SanityCheck.check { !allNodesFreeListInner.contains(idx) }
                    allNodesFreeListInner.add(idx)
                    bufferManager.deletePage(idx)
                }
            }
            nodePointerTypeLeaf -> {
                lockLeaf.withWriteLock {
                    SanityCheck.check { !allNodesFreeListLeaf.contains(idx) }
                    allNodesFreeListLeaf.add(idx)
                    bufferManager.deletePage(idx)
                }
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
        SanityCheck.println({ "NodeManager.freeNode B" })
    }

    fun freeNodeAndAllRelated(nodeIdx: Int) {
        SanityCheck.println({ "NodeManager.freeNodeAndAllRelated A" })
        SanityCheck.println({ "debug NodeManager freeNodeAndAllRelated ${nodeIdx.toString(16)}" })
        if (nodeIdx != nodeNullPointer) {
            getNode(nodeIdx, { node ->
                freeNode(nodeIdx)
            }, { node ->
                NodeInner.forEachChild(node, {
                    freeNodeAndAllRelated(it)
                })
                freeNode(nodeIdx)
            })
        }
        SanityCheck.println({ "NodeManager.freeNodeAndAllRelated B" })
    }

    fun freeAllLeaves(nodeIdx: Int) {
        SanityCheck.println({ "NodeManager.freeAllLeaves A" })
        SanityCheck.println({ "debug NodeManager freeAllLeaves ${nodeIdx.toString(16)}" })
        var idx = nodeIdx
        while (idx != nodeNullPointer) {
            getNode(idx, { node ->
                val tmp = NodeShared.getNextNode(node)
                freeNode(idx)
                idx = tmp
            }, { node ->
                SanityCheck.checkUnreachable()
            })
        }
        SanityCheck.println({ "NodeManager.freeAllLeaves B" })
    }

    fun freeAllInnerNodes(nodeIdx: Int) {
        SanityCheck.println({ "NodeManager.freeAllInnerNodes A" })
        SanityCheck.println({ "debug NodeManager freeAllInnerNodes ${nodeIdx.toString(16)}" })
        if (nodeIdx != nodeNullPointer) {
            getNode(nodeIdx, { node ->
                //dont touch leaves
            }, { node ->
                NodeInner.forEachChild(node, {
                    freeAllInnerNodes(it)
                })
                freeNode(nodeIdx)
            })
        }
        SanityCheck.println({ "NodeManager.freeAllInnerNodes B" })
    }
}
