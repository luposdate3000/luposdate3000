import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ESortType
import lupos.s00misc.File
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s00misc.SortHelper
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.partition.POPSplitPartitionPassThrough
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s16network.LuposdateEndpoint

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    var debugMode = false
    val partitionOptions = listOf(1, 2, 4, 8, 16)
    val enableDisable = listOf(0, 1)
    val datasourceFiles = args[0]
    val minimumTime = args[1].toDouble()
    val numberOfTriples = args[2].toLong()
    val timer = DateHelperRelative.markNow()
    LuposdateEndpoint.importIntermediateFiles(datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples,0,1,${numberOfTriples * 1000.0},${1.0 / time}")
    for (x in partitionOptions) {
        for (y in partitionOptions) {
            for (z in partitionOptions) {
                for (a in partitionOptions) {
                    for (b in partitionOptions) {
                        for (zPt in enableDisable) {
// fast->
                            if (a != x || a != y) continue
                            if (b > a) continue
                            if (zPt == 0 && z != b) continue
//
                            if (a != x && a != y) {
                                continue
                            }
                            if (b != a && b != z && b != 1) {
                                continue
                            }
                            if (zPt == 1 && b == 1) {
                                continue
                            }
                            if (zPt == 1 && z != 1) {
                                continue
                            }
                            if (zPt == 1 && a != b) {
                                continue
                            }
                            val query = Query()
                            var partitionID = 1
                            val xP = Partition()
                            var aPartitionID = partitionID++
                            var bPartitionID = partitionID++
                            var xPartitionID = if (x != a) partitionID++ else aPartitionID
                            xP.limit["a"] = x
                            var opX: IOPBase = TripleStoreIteratorGlobal(query, listOf("a", "b"), "", arrayOf(AOPVariable(query, "a"), AOPConstant(query, ValueIri("a")), AOPVariable(query, "b")), EIndexPattern.PSO, xP)
                            if (x > 1) {
                                opX = POPSplitPartitionFromStore(query, listOf("a", "b"), "a", x, xPartitionID, opX)
                            }
                            val yP = Partition()
                            var yPartitionID = if (y != a) partitionID++ else aPartitionID
                            yP.limit["a"] = y
                            var opY: IOPBase = TripleStoreIteratorGlobal(query, listOf("a", "c"), "", arrayOf(AOPVariable(query, "a"), AOPConstant(query, ValueIri("b")), AOPVariable(query, "c")), EIndexPattern.PSO, yP)
                            if (y > 1) {
                                opY = POPSplitPartitionFromStore(query, listOf("a", "c"), "a", y, yPartitionID, opY)
                            }
                            val zP = Partition()
                            var zPartitionID = if (z != b) partitionID++ else bPartitionID
                            zP.limit["b"] = z
                            var opZ: IOPBase = TripleStoreIteratorGlobal(query, listOf("c", "d"), "", arrayOf(AOPVariable(query, "c"), AOPConstant(query, ValueIri("c")), AOPVariable(query, "d")), EIndexPattern.PSO, zP)
                            if (zPt == 1) {
                                opZ = POPSplitPartitionPassThrough(query, listOf("c", "d"), "a", b, zPartitionID, opZ)
                            } else {
                                if (z > 1) {
                                    opZ = POPSplitPartitionFromStore(query, listOf("c", "d"), "c", z, zPartitionID, opZ)
                                }
                            }
                            if (x != a) {
                                if (x > 1) {
                                    if (a > 1) {
                                        opX = POPMergePartitionOrderedByIntId(query, listOf("a", "b"), "a", x, xPartitionID, opX)
                                        opX.mySortPriority = mutableListOf(SortHelper("a", ESortType.FAST))
                                        opX = POPSplitPartition(query, listOf("a", "b"), "a", a, aPartitionID, opX)
                                    } else {
                                        opX = POPMergePartitionOrderedByIntId(query, listOf("a", "b"), "a", x, xPartitionID, opX)
                                        opX.mySortPriority = mutableListOf(SortHelper("a", ESortType.FAST))
                                    }
                                } else {
                                    if (a > 1) {
                                        opX = POPSplitPartition(query, listOf("a", "b"), "a", a, aPartitionID, opX)
                                    }
                                }
                            }
                            if (y != a) {
                                if (y > 1) {
                                    if (a > 1) {
                                        opY = POPMergePartitionOrderedByIntId(query, listOf("a", "c"), "a", y, yPartitionID, opY)
                                        opY.mySortPriority = mutableListOf(SortHelper("a", ESortType.FAST))
                                        opY = POPSplitPartition(query, listOf("a", "c"), "a", a, aPartitionID, opY)
                                    } else {
                                        opY = POPMergePartitionOrderedByIntId(query, listOf("a", "c"), "a", y, yPartitionID, opY)
                                        opY.mySortPriority = mutableListOf(SortHelper("a", ESortType.FAST))
                                    }
                                } else {
                                    if (a > 1) {
                                        opY = POPSplitPartition(query, listOf("a", "c"), "a", a, aPartitionID, opY)
                                    }
                                }
                            }
                            if (zPt != 1) {
                                if (z != b) {
                                    if (z > 1) {
                                        if (b > 1) {
                                            opZ = POPMergePartitionOrderedByIntId(query, listOf("c", "d"), "c", z, zPartitionID, opZ)
                                            opZ.mySortPriority = mutableListOf(SortHelper("c", ESortType.FAST))
                                            opZ = POPSplitPartition(query, listOf("c", "d"), "b", b, bPartitionID, opZ)
                                        } else {
                                            opZ = POPMergePartitionOrderedByIntId(query, listOf("c", "d"), "c", z, zPartitionID, opZ)
                                            opZ.mySortPriority = mutableListOf(SortHelper("c", ESortType.FAST))
                                        }
                                    } else {
                                        if (b > 1) {
                                            opZ = POPSplitPartition(query, listOf("c", "d"), "c", b, bPartitionID, opZ)
                                        }
                                    }
                                }
                            }
                            var opA: IOPBase = POPJoinMerge(query, listOf("a", "b", "c"), opX, opY, false)
                            if (zPt != 1) {
                                if (a > 1) {
                                    opA = POPMergePartition(query, listOf("a", "b", "c"), "a", a, aPartitionID, opA)
                                }
                                if (b > 1) {
                                    opA = POPSplitPartition(query, listOf("a", "b", "c"), "c", b, bPartitionID, opA)
                                }
                            }
                            var opB: IOPBase = POPJoinHashMap(query, listOf("a", "b", "c", "d"), opZ, opA, false)
                            if (zPt == 1) {
                                if (a > 1) {
                                    opB = POPMergePartition(query, listOf("a", "b", "c", "d"), "a", a, aPartitionID, opB)
                                }
                            } else {
                                if (b > 1) {
                                    opB = POPMergePartition(query, listOf("a", "b", "c", "d"), "c", b, bPartitionID, opB)
                                }
                            }
                            val node = opB
                            println("------------------------------")
                            println("${x}_${y}_${z}__${a}_${b}__$zPt")
                            println(node.toXMLElement().toPrettyString())
                            val writer1 = MyPrintWriter(debugMode)
                            LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer1)
                            if (debugMode) {
                                File("/tmp/result_${x}_${y}_${z}__${a}_${b}__$zPt.xml").printWriter {
                                    it.println(writer1.toString())
                                }
                            } else {
                                val writer = MyPrintWriter(false)
                                val timerFirst = DateHelperRelative.markNow()
                                LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
                                val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
//                            val groupSize = 100
                                val groupSize = 1 + (1.0 / timeFirst).toInt()
                                println("groupSize $groupSize")
                                val timer = DateHelperRelative.markNow()
                                var time: Double
                                var counter = 0
                                while (true) {
                                    counter += groupSize
                                    for (i in 0 until groupSize) {
                                        LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
                                    }
                                    time = DateHelperRelative.elapsedSeconds(timer)
                                    if (time > minimumTime) {
                                        break
                                    }
                                }
                                println("${x}_${y}_${z}__${a}_${b}__$zPt,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},NoOptimizer")
                            }
                        }
                    }
                }
            }
        }
    }
}
