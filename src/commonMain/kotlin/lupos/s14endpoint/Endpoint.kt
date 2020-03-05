package lupos.s14endpoint

import kotlinx.coroutines.channels.Channel
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
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class TripleInsertIterator : POPBase {
    override val operatorID = EOperatorID.TripleInsertIteratorID
    override val classname = "TripleInsertIterator"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    var result: ResultRow

    override fun equals(other: Any?): Boolean {
        if (other !is TripleInsertIterator)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (result != other.result)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    fun cleanString(s: String): String = Trace.trace({ "TripleInsertIterator.cleanString" }, {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    })

    override fun cloneOP() = throw Exception("not implemented")

    constructor(dictionary: ResultSetDictionary, triple: ID_Triple) {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        result = resultSet.createResultRow()
        result[resultSet.createVariable("s")] = resultSet.createValue(cleanString(Dictionary[triple.s]!!.toN3String()))
        result[resultSet.createVariable("p")] = resultSet.createValue(cleanString(Dictionary[triple.p]!!.toN3String()))
        result[resultSet.createVariable("o")] = resultSet.createValue(cleanString(Dictionary[triple.o]!!.toN3String()))
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "TripleInsertIterator.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                channel.send(result)
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })
}

fun consume_triple(triple_s: Long, triple_p: Long, triple_o: Long) {
    val triple = ID_Triple(triple_s, triple_p, triple_o)
    val transactionID = DistributedTripleStore.nextTransactionID()
    val dictionary = ResultSetDictionary()
    CoroutinesHelper.runBlock {
        DistributedTripleStore.getDefaultGraph().addData(transactionID, TripleInsertIterator(dictionary, triple))
    }
    DistributedTripleStore.commit(transactionID)
}

object Endpoint {
    val REQUEST_BINARY = arrayOf("/binary")
    val REQUEST_TRIPLE_ADD = arrayOf("/triple/add", "graph", "id", "s", "p", "o", "idx")
    val REQUEST_TRIPLE_GET = arrayOf("/triple/get", "graph", "id", "s", "p", "o", "sv", "pv", "ov", "idx")
    val REQUEST_TRIPLE_DELETE = arrayOf("/triple/delete", "graph", "id", "s", "p", "o", "sv", "pv", "ov", "idx")
    val REQUEST_COMMIT = arrayOf("/commit", "id")
    val REQUEST_TRACE_PRINT = arrayOf("/trace/print")
    val REQUEST_SPARQL_QUERY = arrayOf("/sparql/query", "query")
    val REQUEST_GRAPH_OPERATION = arrayOf("/graph/operation", "name", "type")
    val REQUEST_TURTLE_INPUT = arrayOf("/import/turtle", "data")
    val REQUEST_XML_INPUT = arrayOf("/import/xml", "data")
    val REQUEST_PEERS_LIST = arrayOf("/peers/list")
    val REQUEST_PEERS_JOIN = arrayOf("/peers/join", "hostname")
    val REQUEST_PEERS_JOIN_INTERNAL = arrayOf("/peers/join_internal", "hostname")
    val REQUEST_PEERS_SELF_TEST = arrayOf("/peers/self_test")
    val REQUEST_OPERATOR_QUERY = arrayOf("operator/query", "query")
    fun process_local_triple_add(graphName: String, transactionID: Long, s: AOPConstant, p: AOPConstant, o: AOPConstant, idx: EIndexPattern): XMLElement = Trace.trace({ "Endpoint.process_local_triple_add" }, {
        val g = DistributedTripleStore.localStore.getNamedGraph(graphName)
        g.addData(transactionID, s, p, o, idx)
        return XMLElement("success")
    })

    fun process_local_triple_delete(graphName: String, transactionID: Long, s: AOPBase, p: AOPBase, o: AOPBase, idx: EIndexPattern): XMLElement = Trace.trace({ "Endpoint.process_local_triple_delete" }, {
        val g = DistributedTripleStore.localStore.getNamedGraph(graphName)
        g.deleteDataVar(transactionID, s, p, o, idx)
        return XMLElement("success")
    })

    fun process_local_triple_get(graphName: String, resultSet: ResultSet, transactionID: Long, s: AOPBase, p: AOPBase, o: AOPBase, idx: EIndexPattern): POPBase = Trace.trace({ "Endpoint.process_local_triple_get" }, {
        val g = DistributedTripleStore.localStore.getNamedGraph(graphName)
        return g.getIterator(transactionID, resultSet, s, p, o, idx)
    })

    fun process_local_graph_clear_all(transactionID: Long): XMLElement = Trace.trace({ "Endpoint.process_local_graph_clear_all" }, {
        DistributedTripleStore.localStore.getDefaultGraph().clear()
        for (g in DistributedTripleStore.getGraphNames())
            DistributedTripleStore.dropGraph(g)
        return XMLElement("success")
    })

    fun process_local_commit(transactionID: Long): XMLElement = Trace.trace({ "Endpoint.process_local_commit" }, {
        DistributedTripleStore.localStore.commit(transactionID)
        return XMLElement("success")
    })

    fun process_local_graph_operation(name: String, type: EGraphOperationType): XMLElement = Trace.trace({ "Endpoint.process_local_graph_operation" }, {
        when (type) {
            EGraphOperationType.CLEAR -> DistributedTripleStore.localStore.clearGraph(name)
            EGraphOperationType.CREATE -> DistributedTripleStore.localStore.createGraph(name)
            EGraphOperationType.DROP -> DistributedTripleStore.localStore.dropGraph(name)
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "process_local_graph_operation bb" })
        return XMLElement("success")
    })

    fun process_turtle_input(data: String): XMLElement = Trace.trace({ "Endpoint.process_turtle_input" }, {
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
        return XMLElement("done")
    })

    fun process_xml_input(data: String): XMLElement = Trace.trace({ "Endpoint.process_xml_input" }, {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        CoroutinesHelper.runBlock {
            DistributedTripleStore.getDefaultGraph().addData(transactionID, POPImportFromXml(dictionary, XMLElement.parseFromXml(data)!!.first()))
        }
        DistributedTripleStore.commit(transactionID)
        return XMLElement("done")
    })

    fun process_sparql_query(query: String): XMLElement = Trace.trace({ "Endpoint.process_sparql_query" }, {
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

    fun process_operatorgraph_query(query: String): XMLElement = Trace.trace({ "Endpoint.process_operatorgraph_query" }, {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        val pop_node = XMLElement.convertToOPBase(dictionary, transactionID, XMLElement.parseFromXml(query)!!.first()) as POPBase
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        val res = QueryResultToXML.toXML(pop_node).first()
        DistributedTripleStore.commit(transactionID)
        return res
    })
}
