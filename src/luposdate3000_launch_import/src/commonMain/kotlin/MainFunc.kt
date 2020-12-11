import lupos.s00misc.ETripleComponentType
import lupos.s00misc.File
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import lupos.s16network.LuposdateEndpoint
fun helperCleanString(s: String): String {
    var res: String = s
    while (true) {
        val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
        val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
        res = res.replace(match.value, replacement)
    }
    return res
}
@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    println("importing args ${args.map { it }}")
    LuposdateEndpoint.initialize()
    var cnt = 0
    val inputFileName = args[0]
    println("importing $inputFileName start")
    val inputFile = File(inputFileName)
    val dict = mutableMapOf<String, Int>()
    var dictCounter = 0
    val dictCounterByType = IntArray(ETripleComponentType.values().size)
    val iter = inputFile.readAsInputStream()
    val outputTriplesFile = File("$inputFileName.triples")
    val outputDictionaryFile = File("$inputFileName.dictionary")
    val outputDictionaryStatFile = File("$inputFileName.stat")
    val outputPartitionsFile = File("$inputFileName.partitions")
    val byteBuf = ByteArray(1)
    try {
        outputDictionaryFile.dataOutputStream { outDictionary ->
            outputTriplesFile.dataOutputStream { outTriples ->
                val x = object : Turtle2Parser(iter) {
                    override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                        for (i in 0 until 3) {
                            val tripleCleaned = helperCleanString(triple[i])
                            val v = dict[tripleCleaned]
                            if (v != null) {
                                outTriples.writeInt(v)
                            } else {
                                val v2 = dictCounter++
                                dictCounterByType[tripleType[i].ordinal]++
                                dict[tripleCleaned] = v2
                                outTriples.writeInt(v2)
                                var tripleCleaned2 = tripleCleaned
                                if (tripleType[i] == ETripleComponentType.IRI) {
                                    tripleCleaned2 = tripleCleaned.substring(1, tripleCleaned.length - 1)
                                }
                                val tmp = tripleCleaned2.encodeToByteArray()
                                byteBuf[0] = tripleType[i].ordinal.toByte()
                                outDictionary.writeInt(tmp.size)
                                outDictionary.write(byteBuf)
                                outDictionary.write(tmp)
                            }
                        }
                        cnt++
                        if (cnt % 10000 == 0) {
                            println("$cnt :: $dictCounter")
                        }
                    }
                }
                x.turtleDoc()
            }
        }
    } catch (e: lupos.s02buildSyntaxTree.ParseError) {
        println("error in file '$inputFileName'")
        throw e
    }
    outputDictionaryStatFile.printWriter { out ->
        out.println("total=$dictCounter")
        for (t in ETripleComponentType.values()) {
            out.println("$t=${dictCounterByType[t.ordinal]}")
        }
    }
    println("importing $inputFileName finish with $cnt triples")
    dict.clear()
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    println("partition-stats :: ")
    val lowerBoundToAnalyse = 256
    val labels = arrayOf("s", "p", "o")
    val partitionSizes = intArrayOf(2, 4, 8, 16)
    val tripleBuf = IntArray(3)
    val counters = Array(3) { IntArray(dictCounter) }
    val maxCounter = IntArray(3)
    outputTriplesFile.dataInputStream { fis ->
        for (c in 0 until cnt) {
            for (i in 0 until 3) {
                val tmp = fis.readInt()
                counters[i][tmp]++
                if (counters[i][tmp] > maxCounter[i]) {
                    maxCounter[i] = counters[i][tmp]
                }
            }
        }
    }
    val estimatedPartitionSizes = Array(6) { mutableMapOf<Int, Array<IntArray>>() }
    val minimumOccurences = IntArray(3) {
        val tmp = maxCounter[it] / 2
        if (lowerBoundToAnalyse > tmp) {
            lowerBoundToAnalyse
        } else {
            tmp
        }
    }
    outputTriplesFile.dataInputStream { fis ->
        for (c in 0 until cnt) {
            for (i in 0 until 3) {
                tripleBuf[i] = fis.readInt()
            }
            for (i in 0 until 3) {
                val constantPart = tripleBuf[i]
                if (counters[i][constantPart] > minimumOccurences[i]) {
                    for (j2 in 0 until 2) {
                        val j = (i + j2 + 1) % 3
                        val partitionPart = tripleBuf[j]
                        val x = estimatedPartitionSizes[i + j2 * 3]
                        var y = x[constantPart]
                        if (y == null) {
                            y = Array(partitionSizes.size) { IntArray(partitionSizes[it]) }
                            x[constantPart] = y
                        }
                        for (k in 0 until partitionSizes.size) {
                            y[k][Partition.hashFunction(partitionPart, partitionSizes[k])]++
                        }
                    }
                }
            }
        }
    }
    val configurations1 = mutableMapOf<String, MutableSet<Int>>()
    val configurations2 = mutableMapOf<String, MutableSet<Int>>()
    for (i in 0 until 3) {
        for (j2 in 0 until 2) {
            val j = (i + j2 + 1) % 3
            val x = estimatedPartitionSizes[i + j2 * 3]
            var lastMax = -1
            var maxPartition = partitionSizes[0]
            for (ki in 0 until partitionSizes.size) {
                val k = partitionSizes[ki]
                var min = -1
                var max = 0
                for ((xk, xv) in x) {
                    for (xx in xv[ki]) {
                        if (xx > max) {
                            max = xx
                        }
                        if (xx < min || min == -1) {
                            min = xx
                        }
                    }
                }
                if (max < lowerBoundToAnalyse) {
                    break
                } else if (lastMax == -1) {
                    lastMax = max
                } else if (max > lastMax * 0.55) {
                    break
                }
                maxPartition = k
            }
            val idxName: String
            val idxNameSecondary: String
            when (i + j2 * 3) {
                0 -> {
                    idxName = "SPO"
                    idxNameSecondary = "SOP"
                }
                1 -> {
                    idxName = "POS"
                    idxNameSecondary = "PSO"
                }
                2 -> {
                    idxName = "OSP"
                    idxNameSecondary = "OPS"
                }
                3 -> {
                    idxName = "SOP"
                    idxNameSecondary = "SPO"
                }
                4 -> {
                    idxName = "PSO"
                    idxNameSecondary = "POS"
                }
                5 -> {
                    idxName = "OPS"
                    idxNameSecondary = "OSP"
                }
                else -> throw Exception("unreachable")
            }
            if (maxPartition > 1) {
                if (configurations1[idxName] == null) {
                    configurations1[idxName] = mutableSetOf(maxPartition)
                } else {
                    configurations1[idxName]!!.add(maxPartition)
                }
                if (configurations2[idxNameSecondary] == null) {
                    configurations2[idxNameSecondary] = mutableSetOf(maxPartition)
                } else {
                    configurations2[idxNameSecondary]!!.add(maxPartition)
                }
            }
        }
    }
    val indicees = arrayOf("SPO", "SOP", "PSO", "POS", "OSP", "OPS")
    outputPartitionsFile.printWriter { out ->
        for (i in indicees) {
            val t1 = configurations1[i]
            val t2 = configurations2[i]
            if (t1 == null && t2 == null) {
                out.println("$i,-1,1")
// add smalles possible partitions to the other collation orders due to currently incomplete optimizer
                out.println("$i,1,${partitionSizes[0]}")
                out.println("$i,2,${partitionSizes[0]}")
            } else {
                if (t1 == null) {
                    out.println("$i,1,${partitionSizes[0]}")
                } else {
                    for (j in t1) {
                        out.println("$i,1,$j")
                    }
                }
                if (t2 == null) {
                    out.println("$i,2,${partitionSizes[0]}")
                } else {
                    for (j in t2) {
                        out.println("$i,2,$j")
                    }
                }
            }
        }
    }
}
