import kotlin.time.DurationUnit
import kotlin.time.TimeSource.Monotonic
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s16network.HttpEndpoint
import lupos.s16network.ServerCommunicationSend
enum class Datasource {
    LOAD, IMPORT
}
fun printBenchmarkLine(title: String, time: Double, count: Int, numberOfTriples: Long, originalTripleSize: Long) {
Coverage.funStart(13819)
    println("$title,$numberOfTriples,0,$count,${time * 1000.0},${count.toDouble() / time},$originalTripleSize")
Coverage.statementStart(13820)
}
@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
Coverage.funStart(13821)
    ServerCommunicationSend.start()
Coverage.statementStart(13822)
    val datasourceType = Datasource.valueOf(args[0])
Coverage.statementStart(13823)
    val persistenceFolder = args[1]
Coverage.statementStart(13824)
    val datasourceFiles = args[2]
Coverage.statementStart(13825)
    val queryFiles = args[3].split(";")
Coverage.statementStart(13826)
    val minimumTime = args[4].toDouble()
Coverage.statementStart(13827)
    val numberOfTriples = args[5].toLong()
Coverage.statementStart(13828)
    val originalTripleSize = args[6].toLong()
Coverage.statementStart(13829)
    val datasourceBNodeFile = args[7]
Coverage.statementStart(13830)
    when (datasourceType) {
        Datasource.LOAD -> {
Coverage.whenCaseStart(13832)
            val timer = Monotonic.markNow()
Coverage.statementStart(13833)
            HttpEndpoint.persistence_load(persistenceFolder)
Coverage.statementStart(13834)
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
Coverage.statementStart(13835)
            printBenchmarkLine("resources/sp2b/persistence-load.sparql", time, 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13836)
        }
        Datasource.IMPORT -> {
Coverage.whenCaseStart(13837)
            val timer = Monotonic.markNow()
Coverage.statementStart(13838)
            val dict = MyMapStringIntPatriciaTrie()
Coverage.statementStart(13839)
            File(datasourceBNodeFile).forEachLine {
Coverage.statementStart(13840)
                dict[it] = nodeGlobalDictionary.createNewBNode()
Coverage.statementStart(13841)
            }
Coverage.statementStart(13842)
            HttpEndpoint.import_turtle_files(datasourceFiles, dict)
Coverage.statementStart(13843)
            val time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
Coverage.statementStart(13844)
            printBenchmarkLine("resources/sp2b/persistence-import.sparql", time, 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13845)
            printBenchmarkLine("resources/sp2b/persistence-import-sort.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_SORT), 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13846)
            printBenchmarkLine("resources/sp2b/persistence-import-dict.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_DICT), 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13847)
            printBenchmarkLine("resources/sp2b/persistence-import-rebuild.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_REBUILD_MAP), 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13848)
            printBenchmarkLine("resources/sp2b/persistence-import-merge.sparql", BenchmarkUtils.getTime(EBenchmark.IMPORT_MERGE_DATA), 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13849)
            printBenchmarkLine("resources/sp2b/persistence-import-parse.sparql", time - BenchmarkUtils.getTime(EBenchmark.IMPORT_MERGE_DATA) - BenchmarkUtils.getTime(EBenchmark.IMPORT_REBUILD_MAP) - BenchmarkUtils.getTime(EBenchmark.IMPORT_DICT) - BenchmarkUtils.getTime(EBenchmark.IMPORT_SORT), 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13850)
            val timer2 = Monotonic.markNow()
Coverage.statementStart(13851)
            HttpEndpoint.persistence_store(persistenceFolder)
Coverage.statementStart(13852)
            val time2 = timer2.elapsedNow().toDouble(DurationUnit.SECONDS)
Coverage.statementStart(13853)
            printBenchmarkLine("resources/sp2b/persistence-store.sparql", time2, 1, numberOfTriples, originalTripleSize)
Coverage.statementStart(13854)
        }
    }
Coverage.statementStart(13855)
    for (queryFile in queryFiles) {
Coverage.forLoopStart(13856)
        val query = File(queryFile).readAsString()
Coverage.statementStart(13857)
        HttpEndpoint.evaluate_sparql_query_string(query, true).toString()
Coverage.statementStart(13858)
        val timer = Monotonic.markNow()
Coverage.statementStart(13859)
        var time: Double
Coverage.statementStart(13860)
        var counter = 0
Coverage.statementStart(13861)
        while (true) {
Coverage.whileLoopStart(13862)
            counter++
Coverage.statementStart(13863)
            HttpEndpoint.evaluate_sparql_query_string(query).toString()
Coverage.statementStart(13864)
            time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
Coverage.statementStart(13865)
            if (time > minimumTime) {
Coverage.ifStart(13866)
                break
            }
Coverage.statementStart(13867)
        }
Coverage.statementStart(13868)
        println("$queryFile,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},$originalTripleSize")
Coverage.statementStart(13869)
    }
Coverage.statementStart(13870)
}
