import lupos.s00misc.*
import lupos.s16network.HttpEndpointLauncher
import lupos.s16network.LuposdateEndpoint
import lupos.s11outputResult.*

fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val buf = MyPrintWriter() //You can use any implementation of lupos.s00misc.IMyPrintWriter
/*
    val preparedStatement = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?s ?p ?o {?s ?p ?o.}")
    LuposdateEndpoint.evaluateOperatorgraphToResult(preparedStatement, buf,EQueryResultToStream.XML_STREAM)
    println(buf.toString())
*/

    for (f in listOf(
            "/mnt/luposdate-testdata/sp2b/1024/complete.n3",
//"/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.ttl",
//"/mnt/luposdate-testdata/yago2s/yago-2.5.3-turtle-simple.ttl",
//"/mnt/luposdate-testdata/yago2/yago-2.n3",
//"/mnt/luposdate-testdata/yago3/yago3.ttl",
    )) {
        println("xxx going to import intermediate $f")
        LuposdateEndpoint.evaluateSparqlToResultB("CLEAR DEFAULT")
        LuposdateEndpoint.importIntermediateFiles(f)
        val q_s = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?s } GROUP BY ?occurences ORDER BY ?occurences ?cnt")
        val q_p = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?p } GROUP BY ?occurences ORDER BY ?occurences ?cnt")
        val q_o = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?o } GROUP BY ?occurences ORDER BY ?occurences ?cnt")
        val q_sp = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?s ?p } GROUP BY ?occurences ORDER BY ?occurences ?cnt")
        val q_so = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?s ?o } GROUP BY ?occurences ORDER BY ?occurences ?cnt")
        val q_po = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?occurences (COUNT(*) AS ?cnt) ((?occurences * ?cnt) AS ?sum) { SELECT (COUNT(*) AS ?occurences) { ?s ?p ?o . } GROUP BY ?p ?o } GROUP BY ?occurences ORDER BY ?occurences ?cnt")
        for (q in listOf(                Pair("s", q_s),                Pair("p", q_p),                Pair("o", q_o),                Pair("sp", q_sp),                Pair("so", q_so),                Pair("po", q_po),        )) {
            val list = LuposdateEndpoint.evaluateOperatorgraphToResultA(q.second, buf, EQueryResultToStream.MEMORY_TABLE) as List<MemoryTable>
            for (l in list) {
                val dict = l.query!!.getDictionary()
                val column_ID_occurences = l.columns.indexOf("occurences")
                val column_ID_cnt = l.columns.indexOf("cnt")
                val data = l.data.map { it.map { dict.getValue(it).valueToString()!!.split('"')[1].toInt() } }
                var total = 0L
                for (d in data) {
                    total += d[column_ID_occurences] * d[column_ID_cnt]
                }
                var max = data[data.size - 1][column_ID_occurences]
                var sum = 0L
File("stat_"+f.replace("/","_")+"_"+q.first+".csv").printWriter{out->
out.println("0.0,0.0")
                for (d in data) {
                    sum += d[column_ID_occurences] * d[column_ID_cnt]
out.                    println("${d[column_ID_occurences].toDouble() / max},${sum.toDouble() / total}")
                }
            }
}
        }
    }
}
