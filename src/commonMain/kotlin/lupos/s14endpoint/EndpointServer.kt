package lupos.s14endpoint

import kotlin.concurrent.thread
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
import lupos.s00misc.Trace
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
import lupos.s03resultRepresentation.ResultRepresenationNetwork
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s12p2p.P2P
import lupos.s12p2p.TransferHelperNetwork
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.Endpoint
import lupos.s15tripleStoreDistributed.DistributedTripleStore


fun consume_triple(triple_s: Long, triple_p: Long, triple_o: Long) {
    val triple = ID_Triple(triple_s, triple_p, triple_o)
    val transactionID = DistributedTripleStore.nextTransactionID()
    val dictionary = ResultSetDictionary()
    CoroutinesHelper.runBlock {
        DistributedTripleStore.getDefaultGraph().addData(transactionID, TripleInsertIterator(dictionary, triple))
    }
    DistributedTripleStore.commit(transactionID)
}

@UseExperimental(ExperimentalStdlibApi::class)
abstract class EndpointServer(val hostname: String = "localhost", val port: Int = 80) {
    val fullname = hostname + ":" + port


    fun process_turtle_input(data: String): XMLElement = Trace.trace({ "process_turtle_input" }, {
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
        return XMLElement("done")
    })

    fun process_xml_input(data: String): XMLElement = Trace.trace({ "process_xml_input" }, {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        CoroutinesHelper.runBlock {
            DistributedTripleStore.getDefaultGraph().addData(transactionID, POPImportFromXml(dictionary, XMLElement.parseFromXml(data)!!.first()))
        }
        DistributedTripleStore.commit(transactionID)
        return XMLElement("done")
    })

    fun process_sparql_query(query: String): XMLElement = Trace.trace({ "process_sparql_query" }, {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
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
        val lop_node = ast_node.visit(OperatorGraphVisitor())
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph optimized" })
        val lop_node2 = LogicalOptimizer(transactionID, dictionary).optimizeCall(lop_node)
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node2 })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Physical Operator Graph" })
        val pop_optimizer = PhysicalOptimizer(transactionID, dictionary)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Distributed Operator Graph" })
        val pop_distributed_node = KeyDistributionOptimizer(transactionID, dictionary).optimizeCall(pop_node) as POPBase
        GlobalLogger.log(ELoggerType.DEBUG, { pop_distributed_node })
        DistributedTripleStore.commit(transactionID)
        return QueryResultToXML.toXML(pop_distributed_node).first()
    })

    fun process_operatorgraph_query(query: String): XMLElement = Trace.trace({ "process_operatorgraph_query" }, {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        val pop_node = XMLElement.convertToOPBase(dictionary, transactionID, XMLElement.parseFromXml(query)!!.first()) as POPBase
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        val res = QueryResultToXML.toXML(pop_node).first()
        DistributedTripleStore.commit(transactionID)
        return res
    })


    suspend fun process_print_traces(): String {
        return Trace.toString()
    }

    suspend fun receive(path: String, data: ByteArray): ByteArray {
        when (path) {
            Endpoint.REQUEST_BINARY[0] -> {
                return TransferHelperNetwork.processBinary(data!!)
            }
            else -> throw Exception("unknown for binary $path")
        }
    }

    suspend fun receive(path: String, isPost: Boolean, data: String, params: Map<String, String>): ByteArray {
        var responseStr = ""
        var responseBytes: ByteArray? = null
        when (path) {
            Endpoint.REQUEST_TRIPLE_ADD[0] -> {
                val s: AOPConstant = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_ADD[3]]!!)
                val p: AOPConstant = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_ADD[4]]!!)
                val o: AOPConstant = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_ADD[5]]!!)
                responseStr = Endpoint.process_local_triple_add(params[Endpoint.REQUEST_TRIPLE_ADD[1]]!!,
                        params[Endpoint.REQUEST_TRIPLE_ADD[2]]!!.toLong(),
                        s, p, o,
                        EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_ADD[6]]!!)).toPrettyString()
            }
            Endpoint.REQUEST_TRIPLE_GET[0] -> {
                val s: AOPBase
                if (params[Endpoint.REQUEST_TRIPLE_GET[6]]!!.toBoolean())
                    s = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                else
                    s = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                val p: AOPBase
                if (params[Endpoint.REQUEST_TRIPLE_GET[7]]!!.toBoolean())
                    p = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                else
                    p = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                val o: AOPBase
                if (params[Endpoint.REQUEST_TRIPLE_GET[8]]!!.toBoolean())
                    o = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                else
                    o = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                responseBytes = ResultRepresenationNetwork.toNetworkPackage(Endpoint.process_local_triple_get(params[Endpoint.REQUEST_TRIPLE_GET[1]]!!,
                        ResultSet(ResultSetDictionary()),
                        params[Endpoint.REQUEST_TRIPLE_GET[2]]!!.toLong(),
                        s, p, o,
                        EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_GET[9]]!!)))
            }
            Endpoint.REQUEST_TRIPLE_DELETE[0] -> {
                val s: AOPBase
                if (params[Endpoint.REQUEST_TRIPLE_DELETE[6]]!!.toBoolean())
                    s = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                else
                    s = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[3]]!!)
                val p: AOPBase
                if (params[Endpoint.REQUEST_TRIPLE_GET[7]]!!.toBoolean())
                    p = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                else
                    p = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[4]]!!)
                val o: AOPBase
                if (params[Endpoint.REQUEST_TRIPLE_GET[8]]!!.toBoolean())
                    o = AOPVariable.calculate(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                else
                    o = AOPVariable(params[Endpoint.REQUEST_TRIPLE_GET[5]]!!)
                responseStr = Endpoint.process_local_triple_delete(params[Endpoint.REQUEST_TRIPLE_DELETE[1]]!!,
                        params[Endpoint.REQUEST_TRIPLE_DELETE[2]]!!.toLong(),
                        s, p, o,
                        EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_DELETE[9]]!!)).toPrettyString()
            }
            Endpoint.REQUEST_TRACE_PRINT[0] -> responseStr = process_print_traces()
            Endpoint.REQUEST_PEERS_LIST[0] -> responseStr = P2P.process_peers_list()
            Endpoint.REQUEST_PEERS_SELF_TEST[0] -> responseStr = P2P.process_peers_self_test()
            Endpoint.REQUEST_COMMIT[0] -> responseStr = Endpoint.process_local_commit(params[Endpoint.REQUEST_COMMIT[1]]!!.toLong()).toPrettyString()
            Endpoint.REQUEST_PEERS_JOIN[0] -> responseStr = P2P.process_peers_join(params[Endpoint.REQUEST_PEERS_JOIN[1]]!!)
            Endpoint.REQUEST_PEERS_JOIN_INTERNAL[0] -> responseStr = P2P.process_peers_join_internal(params[Endpoint.REQUEST_PEERS_JOIN_INTERNAL[1]]!!)
            Endpoint.REQUEST_GRAPH_OPERATION[0] -> responseStr = Endpoint.process_local_graph_operation(params[Endpoint.REQUEST_GRAPH_OPERATION[1]]!!, EGraphOperationType.valueOf(params[Endpoint.REQUEST_GRAPH_OPERATION[2]]!!)).toPrettyString()
            Endpoint.REQUEST_OPERATOR_QUERY[0] -> {
                if (isPost)
                    responseStr = process_operatorgraph_query(data).toPrettyString()
                else
                    responseStr = process_operatorgraph_query(params[Endpoint.REQUEST_OPERATOR_QUERY[1]]!!).toPrettyString()
            }
            Endpoint.REQUEST_SPARQL_QUERY[0] -> {
                if (isPost)
                    responseStr = process_sparql_query(data).toPrettyString()
                else
                    responseStr = process_sparql_query(params[Endpoint.REQUEST_SPARQL_QUERY[1]]!!).toPrettyString()
            }
            Endpoint.REQUEST_XML_INPUT[0] -> {
                if (isPost)
                    responseStr = process_xml_input(data).toPrettyString()
                else
                    responseStr = process_xml_input(params[Endpoint.REQUEST_XML_INPUT[1]]!!).toPrettyString()
            }
            Endpoint.REQUEST_TURTLE_INPUT[0] -> {
                if (isPost)
                    responseStr = process_turtle_input(data).toPrettyString()
                else
                    responseStr = process_turtle_input(params[Endpoint.REQUEST_TURTLE_INPUT[1]]!!).toPrettyString()
            }
            else -> throw Exception("unknown for text $path")
        }
        if (responseBytes != null)
            return responseBytes
        else
            return responseStr.encodeToByteArray()
    }

    abstract suspend fun start()
}

var endpointServer: EndpointServer? = null
