import lupos.s00misc.DateHelperRelative
import lupos.s00misc.File
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s16network.LuposdateEndpoint

enum class OptimizerMode {
    All, OnlyWith, OnlyWithout
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val datasourceFiles = args[0]
    val queryFiles = args[1].split(";")
    val minimumTime = args[2].toDouble()
    val numberOfTriples = args[3].toLong()
    var optimizerMode = OptimizerMode.All
    if (args.size > 4) {
        if (args[4] == "OnlyWith") {
            optimizerMode = OptimizerMode.OnlyWith
        } else if (args[4] == "OnlyWithout") {
            optimizerMode = OptimizerMode.OnlyWithout
        }
    }
    val timer = DateHelperRelative.markNow()
    LuposdateEndpoint.importIntermediateFiles(datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples,0,1,${numberOfTriples * 1000.0},${1.0 / time}")
    val groupSize = IntArray(queryFiles.size) { 1 }
    if (optimizerMode != OptimizerMode.OnlyWithout) {
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
    }
    if (optimizerMode != OptimizerMode.OnlyWith) {
        for (queryFileIdx in queryFiles.indices) {
            val queryFile = queryFiles[queryFileIdx]
            val query = File(queryFile).readAsString()
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(query, true)
            val writer = MyPrintWriter(false)
            LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
            val timerFirst = DateHelperRelative.markNow()
            LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
            val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
            groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
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
}
