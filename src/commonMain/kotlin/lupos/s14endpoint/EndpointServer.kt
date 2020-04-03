package lupos.s14endpoint

import kotlin.jvm.JvmField
import kotlin.time.ClockMark
import kotlin.time.DurationUnit
import kotlin.time.MonoClock
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
        val query = Query()
        val import = POPValuesImportTurtle(query, data).evaluate()
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        DistributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyType.INSERT)
        query.commit()
        return XMLElement("success")
    }

    /*
    incoming bulk import
    */
    suspend fun process_xml_input(data: String): XMLElement {
        val query = Query()
        val import = POPValuesImportXML(query, XMLElement.parseFromXml(data)!!.first()).evaluate()
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        DistributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyType.INSERT)
        query.commit()
        return XMLElement("success")
    }

    /*
    incoming sparql benchmark
    */
    suspend fun process_sparql_benchmark(query: String, timeoutMilliSeconds: Double): XMLElement {
        val timer = MonoClock.markNow()
        var time = 0.0
        var counter = 0
        while (true) {
            counter++
            process_sparql_query(query).toString()
            time = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            if (time * 1000.0 > timeoutMilliSeconds) {
                break
            }
        }
        return XMLElement("benchmark").addAttribute("time", "" + time).addAttribute("count", "" + counter)
    }

    /*
    incoming sparql
    */
    suspend fun process_sparql_query(query: String): XMLElement {
        val q = Query()
        GlobalLogger.log(ELoggerType.DEBUG, { "----------String Query" })
        GlobalLogger.log(ELoggerType.DEBUG, { query })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Abstract Syntax Tree" })
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        GlobalLogger.log(ELoggerType.DEBUG, { ast_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph" })
        val lop_node = ast_node.visit(OperatorGraphVisitor(q))
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
        q.commit()
        return QueryResultToXML.toXML(pop_distributed_node)
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
                    return process_sparql_query(data).toPrettyString().encodeToByteArray()
                } else {
                    return process_sparql_query(params["query"]!!).toPrettyString().encodeToByteArray()
                }
            }
            "/sparql/benchmark" -> {
                if (isPost) {
                    return process_sparql_benchmark(data, params["timeout"]!!.toDouble()).toPrettyString().encodeToByteArray()
                } else {
                    return process_sparql_benchmark(params["query"]!!, params["timeout"]!!.toDouble()).toPrettyString().encodeToByteArray()
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
        }
        TODO("unreachable")
    }
}

var endpointServer: EndpointServer? = null
