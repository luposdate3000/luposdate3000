import kotlin.time.DurationUnit
import kotlin.time.TimeSource.Monotonic
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s12p2p.P2P
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.endpointServer
import lupos.s14endpoint.EndpointServerImpl

enum class Datasource {
    LOAD, IMPORT
}

fun printBenchmarkLine(title: String, time: Double, count: Int, numberOfTriples: Long, originalTripleSize: Long) {
    println("$title,$numberOfTriples,0,$count,${time * 1000.0},${count.toDouble() / time},$originalTripleSize")
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    endpointServer = EndpointServerImpl("localhost")
    P2P.start(null)
    val datasourceType = Datasource.valueOf(args[0])
    val persistenceFolder = args[1]
    val datasourceFiles = args[2]
    val queryFiles = args[3].split(";")
    val minimumTime = args[4].toDouble()
    val numberOfTriples = args[5].toLong()
    val originalTripleSize = args[6].toLong()
    when (datasourceType) {
        Datasource.LOAD -> {
            val timer = Monotonic.markNow()
            endpointServer!!.process_persistence_load(persistenceFolder)
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            printBenchmarkLine("resources/sp2b/persistence-load.sparql", time, 1, numberOfTriples, originalTripleSize)
        }
        Datasource.IMPORT -> {
            val timer = Monotonic.markNow()
            endpointServer!!.process_turtle_input(datasourceFiles)
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            printBenchmarkLine("resources/sp2b/persistence-import.sparql", time, 1, numberOfTriples, originalTripleSize)
            printBenchmarkLine("resources/sp2b/persistence-import-sort.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_SORT), 1, numberOfTriples, originalTripleSize)
            printBenchmarkLine("resources/sp2b/persistence-import-dict.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_DICT), 1, numberOfTriples, originalTripleSize)
            printBenchmarkLine("resources/sp2b/persistence-import-rebuild.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_REBUILD_MAP), 1, numberOfTriples, originalTripleSize)
            printBenchmarkLine("resources/sp2b/persistence-import-merge.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_MERGE_DATA), 1, numberOfTriples, originalTripleSize)
            printBenchmarkLine("resources/sp2b/persistence-import-parse.sparql", time - BenchmarkUtils.getTime(EBenchmark.IMPORT_MERGE_DATA) - BenchmarkUtils.getTime(EBenchmark.IMPORT_REBUILD_MAP) - BenchmarkUtils.getTime(EBenchmark.IMPORT_DICT) - BenchmarkUtils.getTime(EBenchmark.IMPORT_SORT), 1, numberOfTriples, originalTripleSize)
            val timer2 = Monotonic.markNow()
            endpointServer!!.process_persistence_store(persistenceFolder)
            val time2 = timer2.elapsedNow().toDouble(DurationUnit.SECONDS)
            printBenchmarkLine("resources/sp2b/persistence-store.sparql", time2, 1, numberOfTriples, originalTripleSize)
        }
    }
    for (queryFile in queryFiles) {
        val query = File(queryFile).readAsString()
            endpointServer!!.process_sparql_query(query,true).toString()
        val timer = Monotonic.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter++
            endpointServer!!.process_sparql_query(query).toString()
            time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            if (time > minimumTime) {
                break
            }
        }
        println("$queryFile,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},$originalTripleSize")
    }
}
