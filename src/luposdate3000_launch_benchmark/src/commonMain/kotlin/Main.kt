import lupos.s00misc.*
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s16network.LuposdateEndpoint

enum class Datasource {
    LOAD, IMPORT, IMPORT_INTERMEDIATE
}

fun printBenchmarkLine(title: String, time: Double, count: Int, numberOfTriples: Long) {
    println("$title,$numberOfTriples,0,$count,${time * 1000.0},${count.toDouble() / time}")
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
inline fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val datasourceFiles = args[0]
    val queryFiles = args[1].split(";")
    val minimumTime = args[2].toDouble()
    val numberOfTriples = args[3].toLong()
            val timer = DateHelperRelative.markNow()
            LuposdateEndpoint.importIntermediateFiles(datasourceFiles)
            val time = DateHelperRelative.elapsedSeconds(timer)
            printBenchmarkLine("$datasourceFiles/persistence-import.sparql", time, 1, numberOfTriples)
    val groupSize = IntArray(queryFiles.size) { 1 }
    for (queryFileIdx in queryFiles.indices) {
        val queryFile = queryFiles[queryFileIdx]
        val query = File(queryFile).readAsString()
        val timerFirst = DateHelperRelative.markNow()
        LuposdateEndpoint.evaluateSparqlToResultC(query, true)
        val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
        groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
        val timer = DateHelperRelative.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter += groupSize[queryFileIdx]
            for (i in 0 until groupSize[queryFileIdx]) {
                LuposdateEndpoint.evaluateSparqlToResultB(query)
            }
            time = DateHelperRelative.elapsedSeconds(timer)
            if (time > minimumTime) {
                break
            }
        }
        println("${queryFile},$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},WithOptimizer")
    }
    for (queryFileIdx in queryFiles.indices) {
        val queryFile = queryFiles[queryFileIdx]
        val query = File(queryFile).readAsString()
        val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(query, true)
        val writer = MyPrintWriter(false)
        LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
        val timer = DateHelperRelative.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter += groupSize[queryFileIdx]
            for (i in 0 until groupSize[queryFileIdx]) {
                LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
            }
            time = DateHelperRelative.elapsedSeconds(timer)
            if (time > minimumTime) {
                break
            }
        }
        println("${queryFile},$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},NoOptimizer")
    }
}
