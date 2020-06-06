import kotlin.time.DurationUnit
import kotlin.time.TimeSource.Monotonic
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s03resultRepresentation.nodeGlobalDictionary
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
    val datasourceType = Datasource.valueOf(args[0])
    val persistenceFolder = args[1]
    val datasourceFiles = args[2]
    val queryFiles = args[3].split(";")
    val minimumTime = args[4].toDouble()
    val numberOfTriples = args[5].toLong()
    val originalTripleSize = args[6].toLong()
    val datasourceBNodeFile = args[7]
            val timer = Monotonic.markNow()
JenaWrapper.loadFromFile(datasourceFiles)
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            printBenchmarkLine("resources/sp2b/persistence-import.sparql.jena", time, 1, numberOfTriples, originalTripleSize)
    for (queryFile in queryFiles) {
        val query = File(queryFile).readAsString()
        val timer = Monotonic.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter++
JenaWrapper.execQuery(query,false)
            time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            if (time > minimumTime) {
                break
            }
        }
        println("$queryFile.jena,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},$originalTripleSize")
    }
}
