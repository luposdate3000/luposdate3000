import lupos.s00misc.DateHelper
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.File
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s16network.HttpEndpoint
import lupos.s16network.ServerCommunicationSend

enum class Datasource {
    LOAD, IMPORT, IMPORT_INTERMEDIATE
}

fun printBenchmarkLine(title: String, time: Double, count: Int, numberOfTriples: Long, originalTripleSize: Long) {
    println("$title,$numberOfTriples,0,$count,${time * 1000.0},${count.toDouble() / time},$originalTripleSize")
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = Parallel.runBlocking {
    distributedTripleStore = DistributedTripleStore()
    ServerCommunicationSend.start()
    val datasourceType = Datasource.valueOf(args[0])
    val persistenceFolder = args[1]
    val datasourceFiles = args[2]
    val queryFiles = args[3].split(";")
    val minimumTime = args[4].toDouble()
    val numberOfTriples = args[5].toLong()
    val originalTripleSize = args[6].toLong()
    val datasourceBNodeFile = args[7]
    val benchmarkname = args[8]
    if (args.size > 9) {
        Partition.k = args[9].toInt()
    }
    when (datasourceType) {
        Datasource.LOAD -> {
            val timer = DateHelperRelative.markNow()
            distributedTripleStore.localStore.loadFromFolder()
            val time = DateHelperRelative.elapsedSeconds(timer)
            printBenchmarkLine("resources/${benchmarkname}/persistence-load.sparql", time, 1, numberOfTriples, originalTripleSize)
        }
        Datasource.IMPORT -> {
            val timer = DateHelperRelative.markNow()
            val dict = mutableMapOf<String, Int>()
            File(datasourceBNodeFile).forEachLine {
                dict[it] = nodeGlobalDictionary.createNewBNode()
            }
            HttpEndpoint.import_turtle_files(datasourceFiles, dict)
            val time = DateHelperRelative.elapsedSeconds(timer)
            printBenchmarkLine("resources/${benchmarkname}/persistence-import.sparql", time, 1, numberOfTriples, originalTripleSize)
/*
            val timer2 = DateHelperRelative.markNow()
            distributedTripleStore.localStore.safeToFolder()
            val time2 = DateHelperRelative.elapsedSeconds(timer2)
            printBenchmarkLine("resources/${benchmarkname}/persistence-store.sparql", time2, 1, numberOfTriples, originalTripleSize)
*/
        }
        Datasource.IMPORT_INTERMEDIATE -> {
            val timer = DateHelperRelative.markNow()
            HttpEndpoint.import_intermediate_files(datasourceFiles)
            val time = DateHelperRelative.elapsedSeconds(timer)
            printBenchmarkLine("resources/${benchmarkname}/persistence-import.sparql", time, 1, numberOfTriples, originalTripleSize)
/*
            val timer2 = DateHelperRelative.markNow()
            distributedTripleStore.localStore.safeToFolder()
            val time2 = DateHelperRelative.elapsedSeconds(timer2)
            printBenchmarkLine("resources/${benchmarkname}/persistence-store.sparql", time2, 1, numberOfTriples, originalTripleSize)
*/
        }
    }
    var groupSize = IntArray(queryFiles.size) { 1 }
    for (queryFileIdx in 0 until queryFiles.size) {
        val queryFile = queryFiles[queryFileIdx]
        val query = File(queryFile).readAsString()
        val timerFirst = DateHelperRelative.markNow()
        HttpEndpoint.evaluate_sparql_query_string(query, true)
        val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
        groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
        printBenchmarkTimesHelper()
        val timer = DateHelperRelative.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter += groupSize[queryFileIdx]
            for (i in 0 until groupSize[queryFileIdx]) {
                HttpEndpoint.evaluate_sparql_query_string(query)
            }
            time = DateHelperRelative.elapsedSeconds(timer)
            if (time > minimumTime) {
                break
            }
        }
        println("${queryFile},$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},$originalTripleSize,WithOptimizer")
        printBenchmarkTimesHelper()
    }
    for (queryFileIdx in 0 until queryFiles.size) {
        val queryFile = queryFiles[queryFileIdx]
        val query = File(queryFile).readAsString()
        val node = HttpEndpoint.evaluate_sparql_query_string_part1(query, true)
        val writer = MyPrintWriter(false)
        HttpEndpoint.evaluate_sparql_query_string_part2(node, writer)
        printBenchmarkTimesHelper()
        val timer = DateHelperRelative.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter += groupSize[queryFileIdx]
            for (i in 0 until groupSize[queryFileIdx]) {
                HttpEndpoint.evaluate_sparql_query_string_part2(node, writer)
            }
            time = DateHelperRelative.elapsedSeconds(timer)
            if (time > minimumTime) {
                break
            }
        }
        println("${queryFile},$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},$originalTripleSize,NoOptimizer")
        printBenchmarkTimesHelper()
    }
}
