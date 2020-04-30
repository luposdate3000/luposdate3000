package lupos.s14endpoint

import kotlin.jvm.JvmField
import kotlin.time.DurationUnit
import kotlin.time.TimeMark
import kotlin.time.TimeSource.Monotonic
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.*
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.*
import lupos.s12p2p.P2P
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.Endpoint
import lupos.s15tripleStoreDistributed.DistributedTripleStore

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
abstract class EndpointServer(@JvmField val hostname: String = "localhost", @JvmField val port: Int = 80) {
    /*
    incoming requests from outside the database. Distribution algorithms must calculate the intended target nodes.
    */
    @JvmField
    val fullname = hostname + ":" + port

    fun skipToLineEnd(data: String, idx: Int): Int {
        val tmp = data.indexOf('\n', idx)
        if (tmp < idx) {
            return data.length
        } else {
            return tmp
        }
    }

    fun skipAllSepatators(data: String, idx: Int): Int {
        var column = idx
        loop@ while (column < data.length) {
            when (data[column]) {
                ' ', '\n', '\t', '\r', '\u0085', '\u2028', '\u2029' -> {
                    column++
                }
                '#' -> {
                    column = skipToLineEnd(data, column)
                }
                else -> {
                    break@loop
                }
            }
        }
        return column
    }

    suspend fun iterateTurtleData(fileName: String, bulk: TripleStoreBulkImport, onFull: suspend () -> Unit) {
        var withinGeneratedOBnode = false
        val data = File(fileName).readAsString()
        try {
            var nextType = 0 /* 0:S,1:P,2:O*/
            val currentTriple = Array(3) { 0 }
            var prefixes = mutableMapOf<String, String>()
            var column = skipAllSepatators(data, 0)
            while (column < data.length) {
                if (data.startsWith("@prefix", column)) {
                    column = skipAllSepatators(data, column + "@prefix".length)
                    var t = data.indexOf(':', column)
                    require(t >= column)
                    val key = data.substring(column, t)
                    column = skipAllSepatators(data, t + 1)
                    require(data[column] == '<')
                    column++
                    t = data.indexOf('>', column)
                    require(t > column)
                    var value = data.substring(column, t)
                    column = skipAllSepatators(data, t + 1)
                    require(data[column] == '.')
                    column++
                    prefixes[key] = value
                } else if (data.startsWith("@base", column)) {
                    column = skipAllSepatators(data, column + "@base".length)
                    require(data[column] == '<')
                    column++
                    var t = data.indexOf('>', column)
                    require(t > column)
                    var value = data.substring(column, t)
                    column = skipAllSepatators(data, t + 1)
                    require(data[column] == '.')
                    column++
                    prefixes[""] = value
                } else {
                    var start = column
                    var qouteCount = 0
                    var containsDot = false
                    var containsExponent = false
                    var isNumeric = true
                    loop@ while (column < data.length) {
                        when (data[column]) {
                            '"' /*"*/ -> {
                                if (column == 0 || data[column - 1] != '\\') {
                                    qouteCount++
                                }
                                isNumeric = false
                            }
                            'e', 'E' -> {
                                containsExponent = true
                            }
                            '<' -> {
                                if (qouteCount % 2 == 0) {
                                    qouteCount++
                                }
                                isNumeric = false
                            }
                            '>' -> {
                                if (qouteCount % 2 == 1) {
                                    qouteCount++
                                }
                                isNumeric = false
                            }
                            ' ', '\n', '\t' -> {
                                if (qouteCount % 2 == 0) {
                                    break@loop
                                }
                                isNumeric = false
                            }
                            '.' -> {
                                if ((!isNumeric || containsDot) && qouteCount % 2 == 0 && nextType == 2) {
                                    break@loop
                                }
                                containsDot = true
                            }
                            ',', ';' -> {
                                if (qouteCount % 2 == 0 && nextType == 2) {
                                    break@loop
                                }
                                isNumeric = false
                            }
                            '+', '-', in '0'..'9' -> {
                            }
                            else -> {
                                isNumeric = false
                            }
                        }
                        column++
                    }
                    when (data[start]) {
                        '[' -> {
                            withinGeneratedOBnode = true
                            require(nextType == 2)
                            currentTriple[2] = nodeGlobalDictionary.createNewBNode()
                            bulk.insert(currentTriple[0], currentTriple[1], currentTriple[2])
                            if (bulk.full()) {
                                onFull()
                            }
                            currentTriple[0] = currentTriple[2]
                            nextType = 0
                        }
                        'a' -> {
                            require(column == start + 1)
                            currentTriple[nextType] = nodeGlobalDictionary.createIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
                        }
                        '_' -> {
                            currentTriple[nextType] = bulk.dictionaryBNode.getOrCreate(data.substring(start, column), { nodeGlobalDictionary.createNewBNode() })
                        }
                        '<' -> {
                            currentTriple[nextType] = nodeGlobalDictionary.createIri(data.substring(start + 1, column - 1))
                        }
                        ':' -> {
                            val value = prefixes[""]
                            require(value != null)
                            currentTriple[nextType] = nodeGlobalDictionary.createIri(value + data.substring(start + 1, column))
                        }
                        '"' /*"*/ -> {
                            var ttt = data.indexOf("\"@", start + 1)
                            if (data[column - 1] == '"' || data[column - 1] == '>' || (ttt < column && ttt > start + 1)) {
                                currentTriple[nextType] = nodeGlobalDictionary.createValue(data.substring(start, column))
                            } else {
                                var tt = data.indexOf("^^", start)
                                while (true) {
                                    val t = data.indexOf("^^", tt)
                                    if (t > tt && t < column) {
                                        tt = t
                                    } else {
                                        break
                                    }
                                }
                                val t = data.indexOf(":", tt)
                                var key = data.substring(tt + 2, t)
                                var value = prefixes[key]
                                if (value != null) {
                                    currentTriple[nextType] = nodeGlobalDictionary.createTyped(data.substring(start + 1, tt - 1), value + data.substring(t + 1, column))
                                } else {
                                    throw Exception("unknown Prefix '${data.substring(start, column)}'")
                                }
                            }
                        }
                        else -> {
                            var t = data.indexOf(':', start)
                            if (t > start && t < column) {
                                var key = data.substring(start, t)
                                var value = prefixes[key]
                                if (value != null) {
                                    currentTriple[nextType] = nodeGlobalDictionary.createIri(value + data.substring(t + 1, column))
                                } else {
                                    throw Exception("unknown Prefix '${data.substring(start, column)}'")
                                }
                            } else if (data.startsWith("true", start)) {
                                currentTriple[nextType] = ResultSetDictionary.booleanTrueValue
                            } else if (data.startsWith("false", start)) {
                                currentTriple[nextType] = ResultSetDictionary.booleanFalseValue
                            } else {
                                try {
                                    if (isNumeric) {
                                        if (containsExponent) {
                                            currentTriple[nextType] = nodeGlobalDictionary.createDouble(data.substring(start, column).toDouble())
                                        } else if (containsDot) {
                                            currentTriple[nextType] = nodeGlobalDictionary.createDecimal(data.substring(start, column).toDouble())
                                        } else {
                                            currentTriple[nextType] = nodeGlobalDictionary.createInteger(data.substring(start, column).toInt())
                                        }
                                    } else {
                                        var l = start - 10
                                        var r = column + 10
                                        if (l < 0) {
                                            l = 0
                                        }
                                        if (r > data.length) {
                                            r = data.length
                                        }
                                        throw Exception("unable to parse '${data.substring(start, column)}' context: '${data.substring(l, r)}' $l $r $start $column")
                                    }
                                } catch (e: Throwable) {
                                    var l = start - 10
                                    var r = column + 10
                                    if (l < 0) {
                                        l = 0
                                    }
                                    if (r > data.length) {
                                        r = data.length
                                    }
                                    e.printStackTrace()
                                    throw Exception("unable to parse after error $isNumeric $containsExponent $containsDot '${data.substring(start, column)}' context: '${data.substring(l, r)}' $l $r $start $column")
                                }
                            }
                        }
                    }
                    if (nextType == 2) {
                        bulk.insert(currentTriple[0], currentTriple[1], currentTriple[2])
                        if (bulk.full()) {
                            onFull()
                        }
			column = skipAllSepatators(data, column)
                        if (withinGeneratedOBnode) {
                            if (data[column] == ']') {
                                withinGeneratedOBnode = false
                                column = skipAllSepatators(data, column + 1)
                            } else {
                                require(data[column] != '.')
                            }
                        }
                        when (data[column]) {
                            '.' -> {
                                nextType = 0
                            }
                            ';' -> {
                                nextType = 1
                            }
                            ',' -> {
                                nextType = 2
                            }
                            else -> {
var l = start - 10
                                    var r = column + 10
                                    if (l < 0) {
                                        l = 0
                                    }
                                    if (r > data.length) {
                                        r = data.length
                                    }
                                    
                                require(false, { "not allowed termination sign ${data[column].toInt()} context: '${data.substring(l, r)}'" })
                            }
                        }
                        column++
                    } else {
                        nextType++
                    }
                }
                column = skipAllSepatators(data, column)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            throw e
        }
    }

    /*
    incoming bulk import
    */
    suspend fun process_turtle_input(fileNames: String): String {
        val query = Query()
        var bulk = TripleStoreBulkImport()
        var counter = 0
        var store = DistributedTripleStore.getDefaultGraph(query)
        for (fileName in fileNames.split(";")) {
            iterateTurtleData(fileName, bulk, { bulk.sort();store.bulkImport(bulk);bulk.reset() })
        }
//        println("ready")
//        Thread.sleep(20000)
//        println("not ready")
        bulk.sort()
        store.bulkImport(bulk)
        return XMLElement("success $counter").toString()
    }

    /*
    incoming bulk import
    */
    suspend fun process_xml_input(data: String): String {
        val query = Query()
        val import = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElement.parseFromXml(data)!!).evaluate()
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        DistributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyType.INSERT)
        query.commit()
        return XMLElement("success").toString()
    }

    /*
    incoming sparql benchmark
    */
    suspend fun process_sparql_benchmark(query: String, timeoutMilliSeconds: Double): String {
        BenchmarkUtils.start(EBenchmark.QUERY)
        var time: Double
        var counter = 0
        while (true) {
            counter++
            process_sparql_query(query).toString()
            time = BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY)
            if (time * 1000.0 > timeoutMilliSeconds) {
                break
            }
        }
        return XMLElement("benchmark").addAttribute("time", "" + time).addAttribute("count", "" + counter).toString()
    }

    /*
    incoming sparql benchmark for_ jena db compare
    */
    suspend fun jena_process_sparql_benchmark(query: String, timeoutMilliSeconds: Double): String {
        BenchmarkUtils.start(EBenchmark.QUERY)
        var time: Double
        var counter = 0
        while (true) {
            counter++
            val str = JenaWrapper.execQuery(query)
            time = BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY)
            if (time * 1000.0 > timeoutMilliSeconds) {
                break
            }
        }
        return XMLElement("benchmark").addAttribute("time", "" + time).addAttribute("count", "" + counter).toString()
    }

    /*
    incoming bulk import
    */
    suspend fun jena_process_turtle_input(filenames: String): String {
        JenaWrapper.loadFromFile(filenames)
        return XMLElement("success").toString()
    }

    /*
    incoming sparql
    */
    suspend fun process_sparql_query(query: String, logOperatorGraph: Boolean = false): String {
//BenchmarkUtils.start(EBenchmark.QUERY)
//BenchmarkUtils.start(EBenchmark.QUERY_GENERATE)
        val q = Query()
        GlobalLogger.log(ELoggerType.DEBUG, { "----------String Query" })
        GlobalLogger.log(ELoggerType.DEBUG, { query })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Abstract Syntax Tree" })
//BenchmarkUtils.start(EBenchmark.QUERY_STRING2AST)
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY_STRING2AST)
//BenchmarkUtils.start(EBenchmark.QUERY_AST2OPERATOR)
        GlobalLogger.log(ELoggerType.DEBUG, { ast_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph" })
        val lop_node = ast_node.visit(OperatorGraphVisitor(q))
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY_AST2OPERATOR)
//BenchmarkUtils.start(EBenchmark.QUERY_OPTIMIZE)
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph optimized" })
        val lop_node2 = LogicalOptimizer(q).optimizeCall(lop_node)
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node2 })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Physical Operator Graph" })
        val pop_optimizer = PhysicalOptimizer(q)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Distributed Operator Graph" })
        val pop_distributed_node = KeyDistributionOptimizer(q).optimizeCall(pop_node) as POPBase
        GlobalLogger.log(ELoggerType.DEBUG, { pop_distributed_node })
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(pop_distributed_node.toXMLElement().toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(pop_distributed_node.toXMLElement().toString(), ""))
        }
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY_OPTIMIZE)
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY_GENERATE)
        val res = QueryResultToXMLString(pop_distributed_node)
        q.commit()
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY)
        return res
    }

    /*
    incoming sparql
    */
    suspend fun process_sparql_query_operator(query: String, logOperatorGraph: Boolean = false): String {
        val q = Query()
        val pop_node = XMLElement.convertToOPBase(q, XMLElement.parseFromXml(query)!!) as POPBase
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(pop_node.toXMLElement().toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(pop_node.toXMLElement().toString(), ""))
        }
        val res = QueryResultToXMLString(pop_node)
        q.commit()
        return res
    }

    abstract suspend fun start()
    /*
    any incoming request - further selection, what actually is the request
    */
    suspend fun receive(path: String, isPost: Boolean, data: String, params: Map<String, String>): ByteArray {
        when (path) {
            "/stacktrace" -> {
            }
            "/sparql/query" -> {
                if (isPost) {
                    return process_sparql_query(data, true).encodeToByteArray()
                } else {
                    return process_sparql_query(params["query"]!!, true).encodeToByteArray()
                }
            }
            "/sparql/operator" -> {
                if (isPost) {
                    return process_sparql_query_operator(data, true).encodeToByteArray()
                } else {
                    return process_sparql_query_operator(params["query"]!!, true).encodeToByteArray()
                }
            }
            "/sparql/benchmark" -> {
                if (isPost) {
                    return process_sparql_benchmark(data, params["timeout"]!!.toDouble()).encodeToByteArray()
                } else {
                    return process_sparql_benchmark(params["query"]!!, params["timeout"]!!.toDouble()).encodeToByteArray()
                }
            }
            "/jena/benchmark" -> {
                if (isPost) {
                    return jena_process_sparql_benchmark(data, params["timeout"]!!.toDouble()).encodeToByteArray()
                } else {
                    return jena_process_sparql_benchmark(params["query"]!!, params["timeout"]!!.toDouble()).encodeToByteArray()
                }
            }
            "/jena/turtle" -> {
                if (isPost) {
                    return jena_process_turtle_input(data).encodeToByteArray()
                } else {
                    return jena_process_turtle_input(params["query"]!!).encodeToByteArray()
                }
            }
            "/import/turtle" -> {
                if (isPost) {
                    return process_turtle_input(data).encodeToByteArray()
                } else {
                    return process_turtle_input(params["query"]!!).encodeToByteArray()
                }
            }
            "/import/xml" -> {
                if (isPost) {
                    return process_xml_input(data).encodeToByteArray()
                } else {
                    return process_xml_input(params["query"]!!).encodeToByteArray()
                }
            }
            "/persistence/store" -> {
                return process_persistence_store(data).encodeToByteArray()
            }
            "/persistence/load" -> {
                return process_persistence_load(data).encodeToByteArray()
            }
        }
        TODO("unreachable")
    }

    fun process_persistence_store(foldername: String): String {
        BenchmarkUtils.start(EBenchmark.SAVE_DICTIONARY)
        nodeGlobalDictionary.safeToFolder(foldername)
        var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_DICTIONARY)
        BenchmarkUtils.start(EBenchmark.SAVE_TRIPLE_STORE)
        val stores = DistributedTripleStore.localStore.stores
        var idx = 0
        File(foldername + "/stores.txt").printWriter { out ->
            stores.keys.forEach { name ->
                val store = stores[name]!!
                store.safeToFolder(foldername + "/$idx")
                out.println(name)
                idx++
            }
        }
        var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_TRIPLE_STORE)
        return XMLElement("success $timeDict $timeStore").toString()
    }

    fun process_persistence_load(foldername: String): String {
        MemoryStatistics.print("before load")
        BenchmarkUtils.start(EBenchmark.LOAD_DICTIONARY)
        nodeGlobalDictionary.loadFromFolder(foldername)
        var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_DICTIONARY)
        MemoryStatistics.print("after load dictionary")
        BenchmarkUtils.start(EBenchmark.LOAD_TRIPLE_STORE)
        val stores = DistributedTripleStore.localStore.stores
        var idx = 0
        File(foldername + "/stores.txt").forEachLine { name ->
            val store = stores[name]!!
            store.loadFromFolder(foldername + "/$idx")
            idx++
        }
        var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_TRIPLE_STORE)
        MemoryStatistics.print("after load triplestore")
        return XMLElement("success $timeDict $timeStore").toString()
    }
}

var endpointServer: EndpointServer? = null
