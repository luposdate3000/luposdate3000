package lupos.s14endpoint

import kotlin.jvm.JvmField
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


import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
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

@UseExperimental(ExperimentalStdlibApi::class)
abstract class EndpointServer(@JvmField val hostname: String = "localhost", @JvmField val port: Int = 80) {
    @JvmField
    val fullname = hostname + ":" + port

    fun process_turtle_input(data: String): XMLElement = Trace.trace({ "process_turtle_input" }, {
        val query = Query()
        val import = POPValuesImportTurtle(query, data)
        CoroutinesHelper.runBlock {
            DistributedTripleStore.getDefaultGraph(query).addData(import)
        }
        query.commit()
        return XMLElement("success")
    })

    fun process_xml_input(data: String): XMLElement = Trace.trace({ "process_xml_input" }, {
        val query = Query()
        val import = POPValuesImportXML(query, XMLElement.parseFromXml(data)!!.first())
        CoroutinesHelper.runBlock {
            val node2 = DistributedTripleStore.getDefaultGraph(query).addData(import)
        }
        query.commit()
        return XMLElement("success")
    })

    fun process_sparql_query(query: String): XMLElement = Trace.trace({ "process_sparql_query" }, {
        val q = Query()
        GlobalLogger.log(ELoggerType.DEBUG, { "----------String Query" })
        GlobalLogger.log(ELoggerType.DEBUG, { query })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Abstract Syntax Tree" })
        Trace.trace({ "process_abstract_syntax_tree" }, {
            val lcit = LexerCharIterator(query)
            val tit = TokenIteratorSPARQLParser(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val parser = SPARQLParser(ltit)
            val ast_node = parser.expr()
            GlobalLogger.log(ELoggerType.DEBUG, { ast_node })
            GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph" })
            Trace.trace({ "process_logical_operator_graph" }, {
                val lop_node = ast_node.visit(OperatorGraphVisitor(q))
                GlobalLogger.log(ELoggerType.DEBUG, { lop_node })
                GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph optimized" })
                Trace.trace({ "process_logical_operator_graph_optimisation" }, {
                    val lop_node2 = LogicalOptimizer(q).optimizeCall(lop_node)
                    GlobalLogger.log(ELoggerType.DEBUG, { lop_node2 })
                    GlobalLogger.log(ELoggerType.DEBUG, { "----------Physical Operator Graph" })
                    Trace.trace({ "process_physical_operator_graph" }, {
                        val pop_optimizer = PhysicalOptimizer(q)
                        val pop_node = pop_optimizer.optimizeCall(lop_node2)
                        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
                        GlobalLogger.log(ELoggerType.DEBUG, { "----------Distributed Operator Graph" })
                        Trace.trace({ "process_distributed_operator_graph" }, {
                            val pop_distributed_node = KeyDistributionOptimizer(q).optimizeCall(pop_node) as POPBase
                            GlobalLogger.log(ELoggerType.DEBUG, { pop_distributed_node })
                            q.commit()
                            Trace.trace({ "process_query" }, {
                                return QueryResultToXML.toXML(pop_distributed_node).first()
                            })
                        })
                    })
                })
            })
        })
    })

    fun process_operatorgraph_query(query: String): XMLElement = Trace.trace({ "process_operatorgraph_query" }, {
        val q = Query()
        val pop_node = XMLElement.convertToOPBase(q, XMLElement.parseFromXml(query)!!.first()) as POPBase
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        val res = QueryResultToXML.toXML(pop_node).first()
        q.commit()
        return res
    })

    suspend fun receive(path: String, data: ByteArray): ByteArray = Trace.traceSuspend({ "EndpointServer.receiveA" }, {
        when (path) {
            Endpoint.REQUEST_BINARY[0] -> {
                return TransferHelperNetwork.processBinary(data!!)
            }
            else -> throw Exception("unknown for binary $path")
        }
    })

    suspend fun receive(path: String, isPost: Boolean, data: String, params: Map<String, String>): ByteArray = Trace.traceSuspend({ "EndpointServer.receiveB $path" }, {
        var responseStr = ""
        var responseBytes: ByteArray? = null
        when (path) {
            Endpoint.REQUEST_TRIPLE_GET[0] -> {
                val query = Query(transactionID = params[Endpoint.REQUEST_TRIPLE_GET[2]]!!.toLong())
                val param = Array(3) {
                    if (params[Endpoint.REQUEST_TRIPLE_GET[6 + it]]!!.toBoolean())
                        AOPConstant(query, ValueDefinition(params[Endpoint.REQUEST_TRIPLE_GET[3 + it]]!!))
                    else
                        AOPVariable(query, params[Endpoint.REQUEST_TRIPLE_GET[3 + it]]!!)
                }
                responseBytes = ResultRepresenationNetwork.toNetworkPackage(Endpoint.process_local_triple_get(query, ResultSet(query.dictionary), params[Endpoint.REQUEST_TRIPLE_GET[1]]!!,
                        param,
                        EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_GET[9]]!!)))
            }
            Endpoint.REQUEST_TRIPLE_DELETE[0] -> {
                val query = Query(transactionID = params[Endpoint.REQUEST_TRIPLE_DELETE[2]]!!.toLong())
                val param = Array(3) { ValueDefinition(params[Endpoint.REQUEST_TRIPLE_GET[3 + it]]!!) }
                responseStr = Endpoint.process_local_triple_delete(query, params[Endpoint.REQUEST_TRIPLE_DELETE[1]]!!,
                        param,
                        EIndexPattern.valueOf(params[Endpoint.REQUEST_TRIPLE_DELETE[6]]!!)).toPrettyString()
            }
            Endpoint.REQUEST_STACKTRACE[0] -> Trace.print()
            Endpoint.REQUEST_PEERS_LIST[0] -> responseStr = P2P.process_peers_list()
            Endpoint.REQUEST_PEERS_SELF_TEST[0] -> responseStr = P2P.process_peers_self_test()
            Endpoint.REQUEST_COMMIT[0] -> {
                val query = Query(transactionID = params[Endpoint.REQUEST_COMMIT[1]]!!.toLong())
                responseStr = Endpoint.process_local_commit(query).toPrettyString()
            }
            Endpoint.REQUEST_PEERS_JOIN[0] -> responseStr = P2P.process_peers_join(params[Endpoint.REQUEST_PEERS_JOIN[1]]!!)
            Endpoint.REQUEST_PEERS_JOIN_INTERNAL[0] -> responseStr = P2P.process_peers_join_internal(params[Endpoint.REQUEST_PEERS_JOIN_INTERNAL[1]]!!)
            Endpoint.REQUEST_GRAPH_OPERATION[0] -> {
                val query = Query(transactionID = params[Endpoint.REQUEST_GRAPH_OPERATION[2]]!!.toLong())
                responseStr = Endpoint.process_local_graph_operation(query, params[Endpoint.REQUEST_GRAPH_OPERATION[1]]!!, EGraphOperationType.valueOf(params[Endpoint.REQUEST_GRAPH_OPERATION[3]]!!)).toPrettyString()
            }
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
        Trace.traceSuspend({ "EndpointServer.encodeResult" }, {
            if (responseBytes != null)
                return responseBytes
            else
                return responseStr.encodeToByteArray()
        })
    })

    abstract suspend fun start()
}

var endpointServer: EndpointServer? = null
