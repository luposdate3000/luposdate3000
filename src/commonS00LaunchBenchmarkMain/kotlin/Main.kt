import kotlin.time.DurationUnit
import kotlin.time.TimeSource.Monotonic
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.BufferManager
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.*
import lupos.s16network.HttpEndpoint
import lupos.s16network.ServerCommunicationSend

enum class Datasource {
    LOAD, IMPORT
}

fun printBenchmarkLine(title: String, time: Double, count: Int, numberOfTriples: Long, originalTripleSize: Long) {
    println("$title,$numberOfTriples,0,$count,${time * 1000.0},${count.toDouble() / time},$originalTripleSize")
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
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
    when (datasourceType) {
        Datasource.LOAD -> {
            val timer = Monotonic.markNow()
            DistributedTripleStore.localStore.loadFromFolder()
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            printBenchmarkLine("resources/${benchmarkname}/persistence-load.sparql", time, 1, numberOfTriples, originalTripleSize)
        }
        Datasource.IMPORT -> {
            val timer = Monotonic.markNow()
            val dict = MyMapStringIntPatriciaTrie()
            File(datasourceBNodeFile).forEachLine {
                dict[it] = nodeGlobalDictionary.createNewBNode()
            }
            HttpEndpoint.import_turtle_files(datasourceFiles, dict)
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            printBenchmarkLine("resources/${benchmarkname}/persistence-import.sparql", time, 1, numberOfTriples, originalTripleSize)
            val timer2 = Monotonic.markNow()
            DistributedTripleStore.localStore.safeToFolder()
            val time2 = timer2.elapsedNow().toDouble(DurationUnit.SECONDS)
            printBenchmarkLine("resources/${benchmarkname}/persistence-store.sparql", time2, 1, numberOfTriples, originalTripleSize)
        }
    }
    for (queryFile in queryFiles) {
        val query = File(queryFile).readAsString()
        val timer = Monotonic.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter++
            HttpEndpoint.evaluate_sparql_query_string(query).toString()
            time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            if (time > minimumTime) {
                break
            }
        }
        println("$queryFile,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},$originalTripleSize")
    }
}
