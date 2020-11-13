import lupos.s00misc.DateHelperRelative
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.Parallel
import lupos.s16network.LuposdateEndpoint

enum class Datasource {
    LOAD, IMPORT
}

fun printBenchmarkLine(title: String, time: Double, count: Int, numberOfTriples: Long, originalTripleSize: Long) {
    println("$title,$numberOfTriples,0,$count,${time * 1000.0},${count.toDouble() / time},$originalTripleSize")
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val datasourceType = Datasource.valueOf(args[0])
    val persistenceFolder = args[1]
    val datasourceFiles = args[2]
    val queryFiles = args[3].split(";")
    val minimumTime = args[4].toDouble()
    val numberOfTriples = args[5].toLong()
    val originalTripleSize = args[6].toLong()
    val datasourceBNodeFile = args[7]
    val benchmarkname = args[8]
    val timer = DateHelperRelative.markNow()
    JenaWrapper.loadFromFile(datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    printBenchmarkLine("resources/${benchmarkname}/persistence-import.sparql", time, 1, numberOfTriples, originalTripleSize)
    for (queryFile in queryFiles) {
        val query = File(queryFile).readAsString()
        val timer = DateHelperRelative.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter++
            JenaWrapper.execQuery(query)
            time = DateHelperRelative.elapsedSeconds(timer)
            if (time > minimumTime) {
                break
            }
        }
        println("$queryFile,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},$originalTripleSize")
    }
}
