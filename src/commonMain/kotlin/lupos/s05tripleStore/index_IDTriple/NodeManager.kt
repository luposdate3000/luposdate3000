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
    var allNodesFreeListLeaf = mutableListOf<Int>()
    var allNodesFreeListInner = mutableListOf<Int>()
    inline fun <T> safeToFileHelper(filename: String, nodes: MyListGeneric<T>, freeList: List<Int>, crossinline action: (T) -> ByteArray) {
Coverage.funStart(6280)
        val it1 = freeList.iterator()
Coverage.statementStart(6281)
        val it2 = nodes.iterator()
Coverage.statementStart(6282)
        var idx = Int.MAX_VALUE
Coverage.statementStart(6283)
        var i = 0
Coverage.statementStart(6284)
        if (it1.hasNext()) {
Coverage.ifStart(6285)
            idx = it1.next()
Coverage.statementStart(6286)
        }
Coverage.statementStart(6287)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(6288)
            while (it2.hasNext()) {
Coverage.whileLoopStart(6289)
                var node = it2.next()
Coverage.statementStart(6290)
                if (i < idx) {
Coverage.ifStart(6291)
                    out.write(action(node))
Coverage.statementStart(6292)
                } else {
Coverage.ifStart(6293)
                    if (it1.hasNext()) {
Coverage.ifStart(6294)
                        idx = it1.next()
Coverage.statementStart(6295)
                    }
Coverage.statementStart(6296)
                }
Coverage.statementStart(6297)
                i++
Coverage.statementStart(6298)
            }
Coverage.statementStart(6299)
        }
Coverage.statementStart(6300)
    }
    inline fun <T> loadFromFileHelper(filename: String, nodes: MyListGeneric<T>, freeList: List<Int>, count: Int, crossinline action: (ByteArray) -> T) {
Coverage.funStart(6301)
        val it1 = freeList.iterator()
Coverage.statementStart(6302)
        var idx = Int.MAX_VALUE
Coverage.statementStart(6303)
        if (it1.hasNext()) {
Coverage.ifStart(6304)
            idx = it1.next()
Coverage.statementStart(6305)
        }
Coverage.statementStart(6306)
        File(filename).dataInputStream { fis ->
Coverage.statementStart(6307)
            for (i in 0 until count) {
Coverage.forLoopStart(6308)
                val data = ByteArray(PAGE_SIZE_IN_BYTES)
Coverage.statementStart(6309)
                if (i < idx) {
Coverage.ifStart(6310)
                    fis.read(data)
Coverage.statementStart(6311)
                } else {
Coverage.ifStart(6312)
                    if (it1.hasNext()) {
Coverage.ifStart(6313)
                        idx = it1.next()
Coverage.statementStart(6314)
                    }
Coverage.statementStart(6315)
                }
Coverage.statementStart(6316)
                var node = action(data)
Coverage.statementStart(6317)
                nodes.add(node)
Coverage.statementStart(6318)
            }
Coverage.statementStart(6319)
        }
Coverage.statementStart(6320)
    }
    fun debug() {
Coverage.funStart(6321)
        SanityCheck {
Coverage.statementStart(6322)
            //check that there are no memory leaks ...
Coverage.statementStart(6323)
            var leaves = IntArray(allNodesLeaf.size)
Coverage.statementStart(6324)
            var leavesFromInner = IntArray(allNodesLeaf.size)
Coverage.statementStart(6325)
            var inner = IntArray(allNodesInner.size)
Coverage.statementStart(6326)
            for (i in allNodesFreeListLeaf) {
Coverage.forLoopStart(6327)
                leaves[i] = -10000
Coverage.statementStart(6328)
                leavesFromInner[i] = -10000
Coverage.statementStart(6329)
            }
Coverage.statementStart(6330)
            for (i in allNodesFreeListInner) {
Coverage.forLoopStart(6331)
                inner[i] = -10000
Coverage.statementStart(6332)
            }
Coverage.statementStart(6333)
            var nullpointers = 0
Coverage.statementStart(6334)
            for (i in 0 until allNodesLeaf.size) {
Coverage.forLoopStart(6335)
                if (!allNodesFreeListLeaf.contains(i)) {
Coverage.ifStart(6336)
                    getNode(i or nodePointerTypeLeaf, {
Coverage.statementStart(6337)
                        val x = it.getNextNode()
Coverage.statementStart(6338)
                        if (x == nodeNullPointer) {
Coverage.ifStart(6339)
                            nullpointers++
Coverage.statementStart(6340)
                        } else {
Coverage.ifStart(6341)
                            println("debug NodeManager iterating leaves .. ${(i or nodePointerTypeLeaf).toString(16)} -> ${x.toString(16)}")
Coverage.statementStart(6342)
                            leaves[x and nodePointerValueMask]++
Coverage.statementStart(6343)
                        }
Coverage.statementStart(6344)
                    }, {
Coverage.statementStart(6345)
                        throw Exception("unreachable")
                    })
Coverage.statementStart(6346)
                }
Coverage.statementStart(6347)
            }
Coverage.statementStart(6348)
            //each leaf must point to either null or a valid next node, but never to a free memory
Coverage.statementStart(6349)
            var count0 = 0
Coverage.statementStart(6350)
            var j = 0
Coverage.statementStart(6351)
            for (i in leaves) {
Coverage.forLoopStart(6352)
                if (i == 0) {
Coverage.ifStart(6353)
                    count0++
Coverage.statementStart(6354)
                } else {
Coverage.ifStart(6355)
                    println("debug NodeManager leaves ${(j or nodePointerTypeLeaf).toString(16)} $i")
Coverage.statementStart(6356)
                    SanityCheck.check { i == 1 || i == -10000 }
Coverage.statementStart(6357)
                }
Coverage.statementStart(6358)
                j++
Coverage.statementStart(6359)
            }
Coverage.statementStart(6360)
            SanityCheck.check { count0 == nullpointers }//there must be an equal amount of start and end leaves
Coverage.statementStart(6361)
            println("debug nullpointers $nullpointers")
Coverage.statementStart(6362)
            for (i in 0 until allNodesInner.size) {
Coverage.forLoopStart(6363)
                if (!allNodesFreeListInner.contains(i)) {
Coverage.ifStart(6364)
                    getNode(i or nodePointerTypeInner, {
Coverage.statementStart(6365)
                        throw Exception("unreachable")
                    }, {
Coverage.statementStart(6366)
                        it.forEachChild {
Coverage.statementStart(6367)
                            val nodePointerType = it and nodePointerTypeMask
Coverage.statementStart(6368)
                            val nodePointerValue = it and nodePointerValueMask
Coverage.statementStart(6369)
                            if (nodePointerType == nodePointerTypeInner) {
Coverage.ifStart(6370)
                                inner[nodePointerValue]++
Coverage.statementStart(6371)
                                println("debug NodeManager iterating inner leaves .. ${(i or nodePointerTypeInner).toString(16)} -> ${it.toString(16)}")
Coverage.statementStart(6372)
                            } else {
Coverage.ifStart(6373)
                                SanityCheck.check { nodePointerType == nodePointerTypeLeaf }
Coverage.statementStart(6374)
                                leavesFromInner[nodePointerValue]++
Coverage.statementStart(6375)
                                println("debug NodeManager iterating inner .. ${(i or nodePointerTypeInner).toString(16)} -> ${it.toString(16)}")
Coverage.statementStart(6376)
                            }
Coverage.statementStart(6377)
                        }
Coverage.statementStart(6378)
                    })
Coverage.statementStart(6379)
                }
Coverage.statementStart(6380)
            }
Coverage.statementStart(6381)
            j = 0
Coverage.statementStart(6382)
            for (i in leavesFromInner) {
Coverage.forLoopStart(6383)
                println("debug NodeManager leavesFromInner ${(j or nodePointerTypeLeaf).toString(16)} $i")
Coverage.statementStart(6384)
                SanityCheck.check { i == 1 || i == -10000 }
Coverage.statementStart(6385)
                j++
Coverage.statementStart(6386)
            }
Coverage.statementStart(6387)
            count0 = 0
Coverage.statementStart(6388)
            j = 0
Coverage.statementStart(6389)
            for (i in inner) {
Coverage.forLoopStart(6390)
                if (i == 0) {
Coverage.ifStart(6391)
                    count0++
Coverage.statementStart(6392)
                } else {
Coverage.ifStart(6393)
                    println("debug NodeManager ${(j or nodePointerTypeInner).toString(16)} $i")
Coverage.statementStart(6394)
                    SanityCheck.check { i == 1 || i == -10000 }
Coverage.statementStart(6395)
                }
Coverage.statementStart(6396)
                j++
Coverage.statementStart(6397)
            }
Coverage.statementStart(6398)
        }
Coverage.statementStart(6399)
    }
    fun safeToFile(filename: String) {
Coverage.funStart(6400)
        debug()
Coverage.statementStart(6401)
        File(filename + ".header").dataOutputStream { out ->
Coverage.statementStart(6402)
            out.writeInt(allNodesLeaf.size)
Coverage.statementStart(6403)
            out.writeInt(allNodesInner.size)
Coverage.statementStart(6404)
            out.writeInt(allNodesFreeListLeaf.size)
Coverage.statementStart(6405)
            out.writeInt(allNodesFreeListInner.size)
Coverage.statementStart(6406)
            for (i in allNodesFreeListLeaf) {
Coverage.forLoopStart(6407)
                out.writeInt(i)
Coverage.statementStart(6408)
            }
Coverage.statementStart(6409)
            for (i in allNodesFreeListInner) {
Coverage.forLoopStart(6410)
                out.writeInt(i)
Coverage.statementStart(6411)
            }
Coverage.statementStart(6412)
        }
Coverage.statementStart(6413)
        safeToFileHelper(filename + ".leaf", allNodesLeaf, allNodesFreeListLeaf, { it.toByteArray() })
Coverage.statementStart(6414)
        safeToFileHelper(filename + ".inner", allNodesInner, allNodesFreeListInner, { it.toByteArray() })
Coverage.statementStart(6415)
    }
    inline fun loadFromFile(filename: String) {
Coverage.funStart(6416)
        allNodesLeaf.clear()
Coverage.statementStart(6417)
        allNodesInner.clear()
Coverage.statementStart(6418)
        allNodesFreeListLeaf.clear()
Coverage.statementStart(6419)
        allNodesFreeListInner.clear()
Coverage.statementStart(6420)
        File(filename + ".header").dataInputStream { fis ->
Coverage.statementStart(6421)
            val leafSize = fis.readInt()
Coverage.statementStart(6422)
            val innerSize = fis.readInt()
Coverage.statementStart(6423)
            val allNodesFreeListLeafSize = fis.readInt()
Coverage.statementStart(6424)
            val allNodesFreeListInnerSize = fis.readInt()
Coverage.statementStart(6425)
            for (i in 0 until allNodesFreeListLeafSize) {
Coverage.forLoopStart(6426)
                allNodesFreeListLeaf.add(fis.readInt())
Coverage.statementStart(6427)
            }
Coverage.statementStart(6428)
            for (i in 0 until allNodesFreeListInnerSize) {
Coverage.forLoopStart(6429)
                allNodesFreeListInner.add(fis.readInt())
Coverage.statementStart(6430)
            }
Coverage.statementStart(6431)
            loadFromFileHelper(filename + ".leaf", allNodesLeaf, allNodesFreeListLeaf, leafSize, { NodeLeaf(it) })
Coverage.statementStart(6432)
            loadFromFileHelper(filename + ".inner", allNodesInner, allNodesFreeListInner, innerSize, { NodeInner(it) })
Coverage.statementStart(6433)
        }
Coverage.statementStart(6434)
    }
    inline fun getNode(idx: Int, crossinline actionLeaf: (NodeLeaf) -> Unit, crossinline actionInner: (NodeInner) -> Unit) {
Coverage.funStart(6435)
        val nodePointerType = idx and nodePointerTypeMask
Coverage.statementStart(6436)
        val nodePointerValue = idx and nodePointerValueMask
Coverage.statementStart(6437)
        when (nodePointerType) {
            nodePointerTypeInner -> {
Coverage.whenCaseStart(6439)
                actionInner(allNodesInner[nodePointerValue])
Coverage.statementStart(6440)
            }
            nodePointerTypeLeaf -> {
Coverage.whenCaseStart(6441)
                actionLeaf(allNodesLeaf[nodePointerValue])
Coverage.statementStart(6442)
            }
            else -> {
Coverage.whenCaseStart(6443)
                throw Exception("unreachable")
            }
        }
Coverage.statementStart(6444)
    }
    inline fun allocateNodeLeaf(crossinline action: (NodeLeaf, Int) -> Unit) {
Coverage.funStart(6445)
        var i = allNodesLeaf.size
Coverage.statementStart(6446)
        if (allNodesFreeListLeaf.size > 0) {
Coverage.ifStart(6447)
            i = allNodesFreeListLeaf.removeAt(0)
Coverage.statementStart(6448)
            val node = allNodesLeaf[i]
Coverage.statementStart(6449)
            node.setNextNode(nodeNullPointer)
Coverage.statementStart(6450)
            node.setTripleCount(0)
Coverage.statementStart(6451)
            action(node, i or nodePointerTypeLeaf)
Coverage.statementStart(6452)
        } else {
Coverage.ifStart(6453)
            var node = NodeLeaf(ByteArray(PAGE_SIZE_IN_BYTES))
Coverage.statementStart(6454)
            allNodesLeaf[i] = node
Coverage.statementStart(6455)
            action(node, i or nodePointerTypeLeaf)
Coverage.statementStart(6456)
        }
Coverage.statementStart(6457)
    }
    inline fun allocateNodeInner(crossinline action: (NodeInner, Int) -> Unit) {
Coverage.funStart(6458)
        var i = allNodesInner.size
Coverage.statementStart(6459)
        if (allNodesFreeListInner.size > 0) {
Coverage.ifStart(6460)
            i = allNodesFreeListInner.removeAt(0)
Coverage.statementStart(6461)
            val node = allNodesInner[i]
Coverage.statementStart(6462)
            node.setNextNode(nodeNullPointer)
Coverage.statementStart(6463)
            node.setTripleCount(0)
Coverage.statementStart(6464)
            action(node, i or nodePointerTypeInner)
Coverage.statementStart(6465)
        } else {
Coverage.ifStart(6466)
            var node = NodeInner(ByteArray(PAGE_SIZE_IN_BYTES))
Coverage.statementStart(6467)
            allNodesInner[i] = node
Coverage.statementStart(6468)
            action(node, i or nodePointerTypeInner)
Coverage.statementStart(6469)
        }
Coverage.statementStart(6470)
    }
    inline fun freeNode(idx: Int) {
Coverage.funStart(6471)
        val nodePointerType = idx and nodePointerTypeMask
Coverage.statementStart(6472)
        val nodePointerValue = idx and nodePointerValueMask
Coverage.statementStart(6473)
        when (nodePointerType) {
            nodePointerTypeInner -> {
Coverage.whenCaseStart(6475)
                SanityCheck.check { !allNodesFreeListInner.contains(nodePointerValue) }
Coverage.statementStart(6476)
                allNodesFreeListInner.add(nodePointerValue)
Coverage.statementStart(6477)
            }
            nodePointerTypeLeaf -> {
Coverage.whenCaseStart(6478)
                SanityCheck.check { !allNodesFreeListLeaf.contains(nodePointerValue) }
Coverage.statementStart(6479)
                allNodesFreeListLeaf.add(nodePointerValue)
Coverage.statementStart(6480)
            }
            else -> {
Coverage.whenCaseStart(6481)
                throw Exception("unreachable")
            }
        }
Coverage.statementStart(6482)
    }
    fun freeNodeAndAllRelated(nodeIdx: Int) {
Coverage.funStart(6483)
        if (nodeIdx != nodeNullPointer) {
Coverage.ifStart(6484)
            getNode(nodeIdx, { node ->
Coverage.statementStart(6485)
                freeNode(nodeIdx)
Coverage.statementStart(6486)
            }, { node ->
Coverage.statementStart(6487)
                node.forEachChild {
Coverage.statementStart(6488)
                    freeNodeAndAllRelated(it)
Coverage.statementStart(6489)
                }
Coverage.statementStart(6490)
                freeNode(nodeIdx)
Coverage.statementStart(6491)
            })
Coverage.statementStart(6492)
        }
Coverage.statementStart(6493)
    }
    fun freeAllInnerNodes(nodeIdx: Int) {
Coverage.funStart(6494)
        if (nodeIdx != nodeNullPointer) {
Coverage.ifStart(6495)
            getNode(nodeIdx, { node ->
Coverage.statementStart(6496)
                throw Exception("unreachable")
            }, { node ->
Coverage.statementStart(6497)
                node.forEachChild {
Coverage.statementStart(6498)
                    freeAllInnerNodes(it)
Coverage.statementStart(6499)
                }
Coverage.statementStart(6500)
                freeNode(nodeIdx)
Coverage.statementStart(6501)
            })
Coverage.statementStart(6502)
        }
Coverage.statementStart(6503)
    }
    fun freeAllLeaves(nodeIdx: Int) {
Coverage.funStart(6504)
        var idx = nodeIdx
Coverage.statementStart(6505)
        while (idx != nodeNullPointer) {
Coverage.whileLoopStart(6506)
            getNode(idx, { node ->
Coverage.statementStart(6507)
                val tmp = node.getNextNode()
Coverage.statementStart(6508)
                freeNode(idx)
Coverage.statementStart(6509)
                idx = tmp
Coverage.statementStart(6510)
            }, { node ->
Coverage.statementStart(6511)
                throw Exception("unreachable")
            })
Coverage.statementStart(6512)
        }
Coverage.statementStart(6513)
    }
}
