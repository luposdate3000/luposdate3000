import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EIndexPattern
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s16network.LuposdateEndpoint
@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val datasourceFiles = args[0]
    val minimumTime = args[1].toDouble()
    val numberOfTriples = args[2].toLong()
    val trash = args[3].toLong()
    val join = args[4].toLong()
    val joincount = args[5].toInt()
    val timer = DateHelperRelative.markNow()
    LuposdateEndpoint.importIntermediateFiles(datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples,0,1,${numberOfTriples * 1000.0},${1.0 / time}")
    val allpartitions = listOf(1, 2, 4, 8, 16)
    val partitionTimes = DoubleArray(allpartitions.size)
    for (partitionC in 0 until allpartitions.size) {
//            if (partitionC > 1 && partitionTimes[partitionC - 1] < partitionTimes[partitionC - 2]) {
//                break
//            }
        val partitions = allpartitions[partitionC]
        val variables = mutableListOf("j", "a")
        val query = Query()
        val p = Partition()
        p.limit["j"] = partitions
        var op: IOPBase = TripleStoreIteratorGlobal(query, listOf("j", "a"), "", arrayOf(AOPVariable(query, "j"), AOPConstant(query, ValueIri("a")), AOPVariable(query, "a")), EIndexPattern.PSO, p)
        if (partitions > 1) {
            op = POPSplitPartitionFromStore(query, listOf("j", "a"), "j", partitions, 1, op)
        }
        for (j in 0 until joincount) {
            val cc = 'b' + j
            var op2: IOPBase = TripleStoreIteratorGlobal(query, listOf("j", "$cc"), "", arrayOf(AOPVariable(query, "j"), AOPConstant(query, ValueIri("$cc")), AOPVariable(query, "$cc")), EIndexPattern.PSO, p)
            if (partitions > 1) {
                op2 = POPSplitPartitionFromStore(query, listOf("j", "$cc"), "j", partitions, 1, op2)
            }
            variables.add("$cc")
            val v = mutableListOf<String>()
            v.addAll(variables)
            op = POPJoinMerge(query, v, op, op2, false)
        }
        if (partitions > 1) {
            op = POPMergePartition(query, variables, "j", partitions, 1, op)
        }
        val node = op
        println("------------------------------")
        println(node.toXMLElement().toPrettyString())
        val writer = MyPrintWriter(false)
        LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
        val timerFirst = DateHelperRelative.markNow()
        LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
        val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
//        val groupSize = 100
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
        partitionTimes[partitionC] = counter / time
        println("${trash}_${join}_${joincount}_$partitions,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},NoOptimizer,$trash,$join,$joincount,$partitions")
    }
}
