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
import lupos.s11outputResult.QueryResultToXML
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

    /*
    incoming bulk import
    */
    suspend fun process_turtle_input(data: String): XMLElement {
        try {
            BenchmarkUtils.start(EBenchmark.IMPORT_COMPLETE)
            val query = Query()
            BenchmarkUtils.start(EBenchmark.IMPORT_INIT)
            val lcit = LexerCharIterator(data)
            val tit = TurtleScanner(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val bulk = TripleStoreBulkImport()
            val timeInit = BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_INIT)
            BenchmarkUtils.start(EBenchmark.IMPORT_TURTLE_PARSER)
            var counter = 0
            TurtleParserWithDictionary({ triple_s, triple_p, triple_o ->
                val s = Dictionary[triple_s]!!.toN3String()
                val p = Dictionary[triple_p]!!.toN3String()
                val o = Dictionary[triple_o]!!.toN3String()
                bulk.insert(s, p, o)
                counter++
            }, ltit).turtleDoc()
            val timeParser = BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_TURTLE_PARSER)
            DistributedTripleStore.getDefaultGraph(query).bulkImport(bulk)
            val timeComplete = BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_COMPLETE)
            return XMLElement("success $counter $timeInit $timeParser $timeComplete")
        } catch (e: Throwable) {
            e.printStackTrace()
            throw e
        }
    }

    /*
    incoming bulk import
    */
    suspend fun process_xml_input(data: String): XMLElement {
        val query = Query()
        val import = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElement.parseFromXml(data)!!).evaluate()
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        DistributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyType.INSERT)
        query.commit()
        return XMLElement("success")
    }

    /*
    incoming sparql benchmark
    */
    suspend fun process_sparql_benchmark(query: String, timeoutMilliSeconds: Double): XMLElement {
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
        return XMLElement("benchmark").addAttribute("time", "" + time).addAttribute("count", "" + counter)
    }

    /*
    incoming sparql benchmark for jena db compare
    */
    suspend fun jena_process_sparql_benchmark(query: String, timeoutMilliSeconds: Double): XMLElement {
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
        return XMLElement("benchmark").addAttribute("time", "" + time).addAttribute("count", "" + counter)
    }

    /*
    incoming bulk import
    */
    suspend fun jena_process_turtle_input(filename: String): XMLElement {
        JenaWrapper.loadFromFile(filename)
        return XMLElement("success")
    }

    /*
    incoming sparql
    */
    suspend fun process_sparql_query(query: String, logOperatorGraph: Boolean = false): XMLElement {
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
        val res = QueryResultToXML.toXML(pop_distributed_node)
        q.commit()
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY)
        return res
    }

    /*
    incoming sparql
    */
    suspend fun process_sparql_query_operator(query: String, logOperatorGraph: Boolean = false): XMLElement {
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
        val res = QueryResultToXML.toXML(pop_node)
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
                    return process_sparql_query(data, true).toPrettyString().encodeToByteArray()
                } else {
                    return process_sparql_query(params["query"]!!, true).toPrettyString().encodeToByteArray()
                }
            }
            "/sparql/operator" -> {
                if (isPost) {
                    return process_sparql_query_operator(data, true).toPrettyString().encodeToByteArray()
                } else {
                    return process_sparql_query_operator(params["query"]!!, true).toPrettyString().encodeToByteArray()
                }
            }
            "/sparql/benchmark" -> {
                if (isPost) {
                    return process_sparql_benchmark(data, params["timeout"]!!.toDouble()).toPrettyString().encodeToByteArray()
                } else {
                    return process_sparql_benchmark(params["query"]!!, params["timeout"]!!.toDouble()).toPrettyString().encodeToByteArray()
                }
            }
            "/jena/benchmark" -> {
                if (isPost) {
                    return jena_process_sparql_benchmark(data, params["timeout"]!!.toDouble()).toPrettyString().encodeToByteArray()
                } else {
                    return jena_process_sparql_benchmark(params["query"]!!, params["timeout"]!!.toDouble()).toPrettyString().encodeToByteArray()
                }
            }
            "/jena/turtle" -> {
                if (isPost) {
                    return jena_process_turtle_input(data).toPrettyString().encodeToByteArray()
                } else {
                    return jena_process_turtle_input(params["query"]!!).toPrettyString().encodeToByteArray()
                }
            }
            "/import/turtle" -> {
                if (isPost) {
                    return process_turtle_input(data).toPrettyString().encodeToByteArray()
                } else {
                    return process_turtle_input(params["query"]!!).toPrettyString().encodeToByteArray()
                }
            }
            "/import/xml" -> {
                if (isPost) {
                    return process_xml_input(data).toPrettyString().encodeToByteArray()
                } else {
                    return process_xml_input(params["query"]!!).toPrettyString().encodeToByteArray()
                }
            }
            "/persistence/store" -> {
                BenchmarkUtils.start(EBenchmark.SAVE_DICTIONARY)
                nodeGlobalDictionary.safeToFile(data + "/dictionary.txt")
                var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_DICTIONARY)
                BenchmarkUtils.start(EBenchmark.SAVE_TRIPLE_STORE)
                val stores = DistributedTripleStore.localStore.stores
                var idx = 0
                File(data + "/stores.txt").printWriter { out ->
                    stores.keys.forEach { name ->
                        val store = stores[name]!!
                        store.safeToFolder(data + "/$idx")
                        out.println(name)
                        idx++
                    }
                }
                var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_TRIPLE_STORE)
                return XMLElement("success $timeDict $timeStore").toPrettyString().encodeToByteArray()
            }
            "/persistence/load" -> {
                BenchmarkUtils.start(EBenchmark.LOAD_DICTIONARY)
                nodeGlobalDictionary.loadFromFile(data + "/dictionary.txt")
                var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_DICTIONARY)
                BenchmarkUtils.start(EBenchmark.LOAD_TRIPLE_STORE)
                val stores = DistributedTripleStore.localStore.stores
                var idx = 0
                File(data + "/stores.txt").forEachLine { name ->
                    val store = stores[name]!!
                    store.loadFromFolder(data + "/$idx")
                    idx++
                }
                var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_TRIPLE_STORE)
                return XMLElement("success $timeDict $timeStore").toPrettyString().encodeToByteArray()
            }
        }
        TODO("unreachable")
    }
}

var endpointServer: EndpointServer? = null
