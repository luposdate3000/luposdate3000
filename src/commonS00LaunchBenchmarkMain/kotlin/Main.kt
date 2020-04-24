import kotlin.time.DurationUnit
import kotlin.time.TimeMark
import kotlin.time.TimeSource.Monotonic
import lupos.*
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s12p2p.P2P
import lupos.s14endpoint.*

enum class Datasource {
    LOAD, IMPORT
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    endpointServer = EndpointServerImpl("localhost")
    P2P.start(null)

    val datasourceType = Datasource.valueOf(args[0])
    val persistenceFolder = args[1]
    val datasourceFiles = args[2].split(";")
    val queryFiles = args[3].split(";")
    val minimumTime = args[4].toDouble()
    val numberOfTriples = args[5].toLong()
    val originalTripleSize = args[6].toLong()
    when (datasourceType) {
        Datasource.LOAD -> {
            val timer = Monotonic.markNow()
            endpointServer!!.process_persistence_load(persistenceFolder)
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            println("resources/sp2b/persistence-load.sparql,$numberOfTriples,0,1,${time * 1000.0},${1.0 / time},$originalTripleSize")
        }
        Datasource.IMPORT -> {
            val timer = Monotonic.markNow()
            for (fileName in datasourceFiles) {
                endpointServer!!.process_turtle_input(File(fileName).readAsString())
            }
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            println("resources/sp2b/persistence-import.sparql,$numberOfTriples,0,1,${time * 1000.0},${1.0 / time},$originalTripleSize")
            val timer2 = Monotonic.markNow()
            endpointServer!!.process_persistence_store(persistenceFolder)
            val time2 = timer2.elapsedNow().toDouble(DurationUnit.SECONDS)
            println("resources/sp2b/persistence-store.sparql,$numberOfTriples,0,1,${time * 1000.0}2,${1.0 / time2},$originalTripleSize")

        }
    }
    for (queryFile in queryFiles) {
        val query = File(queryFile).readAsString()
        val timer = Monotonic.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter++
endpointServer!!.            process_sparql_query(query).toString()
            time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            if (time > minimumTime) {
                break
            }
        }
        println("$queryFile,$numberOfTriples,0,$counter,${time * 1000.0},${counter/time},$originalTripleSize")
    }
}
