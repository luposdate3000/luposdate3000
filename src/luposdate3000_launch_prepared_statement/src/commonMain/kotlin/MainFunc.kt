import lupos.s00misc.File
import lupos.s00misc.MemoryTable
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s11outputResult.EQueryResultToStream
import lupos.s16network.LuposdateEndpoint
internal fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val buf = MyPrintWriter() // You can use any implementation of lupos.s00misc.IMyPrintWriter
/*
    val preparedStatement = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?s ?p ?o {?s ?p ?o.}")
    LuposdateEndpoint.evaluateOperatorgraphToResult(preparedStatement, buf,EQueryResultToStream.XML_STREAM)
    println(buf.toString())
*/
    var mode = args[0].toInt()
    val argsl = args.toMutableList()
    argsl.removeFirst()
    for (f in argsl) {
        println("xxx going to import intermediate $f")
        when (mode) {
            0 -> {
                LuposdateEndpoint.evaluateSparqlToResultB("CLEAR DEFAULT")
                LuposdateEndpoint.importTurtleString(File(f).readAsString(), mutableMapOf<String, Int>())
            }
            1 -> {
                LuposdateEndpoint.evaluateSparqlToResultB("CLEAR DEFAULT")
                LuposdateEndpoint.importIntermediateFiles(f, true)
            }
            2 -> {
// use stored data from previous run
            }
        }
        val q_s = "SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?s } GROUP BY ?occurences ORDER BY ?occurences ?cnt"
        val q_p = "SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?p } GROUP BY ?occurences ORDER BY ?occurences ?cnt"
        val q_o = "SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?o } GROUP BY ?occurences ORDER BY ?occurences ?cnt"
        val q_sp = "SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?s ?p } GROUP BY ?occurences ORDER BY ?occurences ?cnt"
        val q_so = "SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?s ?o } GROUP BY ?occurences ORDER BY ?occurences ?cnt"
        val q_po = "SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?p ?o } GROUP BY ?occurences ORDER BY ?occurences ?cnt"
        for (q in listOf(Pair("s", q_s), Pair("p", q_p), Pair("o", q_o), Pair("sp", q_sp), Pair("so", q_so), Pair("po", q_po),)) {
            try {
                println("xxx query :: " + q.first)
                val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(q.second)
                println(node.toXMLElement().toPrettyString())
                val list = LuposdateEndpoint.evaluateOperatorgraphToResultA(node, buf, EQueryResultToStream.MEMORY_TABLE) as List<MemoryTable>
                for (l in list) {
                    println("xxx in list")
                    val dict = l.query!!.getDictionary()
                    val column_ID_occurences = l.columns.indexOf("occurences")
                    val column_ID_cnt = l.columns.indexOf("cnt")
                    val data = l.data.map { it.map { dict.getValue(it).valueToString()!!.split('"')[1].toInt() } }
                    var total = 0L
                    for (d in data) {
                        total += d[column_ID_occurences] * d[column_ID_cnt]
                    }
                    var sum = 0L
                    File("stat_" + f.replace("/", "_") + "_" + q.first + ".csv").printWriter { out ->
                        println("xxx opened file stat_" + f.replace("/", "_") + "_" + q.first + ".csv")
                        out.println("0.0,0.0")
                        for (d in data) {
                            sum += d[column_ID_occurences] * d[column_ID_cnt]
                            out.println("${d[column_ID_occurences].toDouble()},${sum.toDouble() / total}")
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
