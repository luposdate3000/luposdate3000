package lupos.s14endpoint

import lupos.s00misc.*
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.ELoggerType
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
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.*


class TripleInsertIterator : POPBaseNullableIterator {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    var result: ResultRow?

    private val resultSet: ResultSet

    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleInsertIterator")
    }

    inline fun cleanString(s: String): String = Trace.trace({ "TripleInsertIterator.cleanString" }, {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                return res
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    }) as String

    constructor(dictionary: ResultSetDictionary, triple: ID_Triple) {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        result = resultSet.createResultRow()
        result!![resultSet.createVariable("s")] = resultSet.createValue(cleanString(Dictionary[triple.s]!!.toN3String()))
        result!![resultSet.createVariable("p")] = resultSet.createValue(cleanString(Dictionary[triple.p]!!.toN3String()))
        result!![resultSet.createVariable("o")] = resultSet.createValue(cleanString(Dictionary[triple.o]!!.toN3String()))
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun nnext(): ResultRow? = Trace.trace({ "TripleInsertIterator.nnext" }, {
        var res = result
        result = null
        return res
    }) as ResultRow?

}

inline fun consume_triple(triple_s: Long, triple_p: Long, triple_o: Long) {
    val triple = ID_Triple(triple_s, triple_p, triple_o)
    val transactionID = DistributedTripleStore.nextTransactionID()
    val dictionary = ResultSetDictionary()
    DistributedTripleStore.getDefaultGraph().addData(transactionID, TripleInsertIterator(dictionary, triple))
    DistributedTripleStore.commit(transactionID)
}

object Endpoint {
    fun process_local_triple_add(graphName: String, transactionID: Long, s: String, p: String, o: String, idx: EIndexPattern): XMLElement = Trace.trace({ "Endpoint.process_local_triple_add" }, {
println("process_local_triple_add")
println("graphName : $graphName")
println("transactionID : $transactionID")
println("s :$s")
println("p :$p")
println("o :$o")
println("idx :$idx")
	val g=DistributedTripleStore.localStore.getNamedGraph(graphName,true)
println("g : $g")
	g.addData(transactionID, s, p, o, idx)
println("...")
        return XMLElement("success")
    }) as XMLElement

    fun process_local_triple_delete(graphName: String, transactionID: Long, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): XMLElement = Trace.trace({ "Endpoint.process_local_triple_delete" }, {
        DistributedTripleStore.localStore.getNamedGraph(graphName).deleteDataVar(transactionID, s, p, o, sv, pv, ov, idx)
        return XMLElement("success")
    }) as XMLElement

    fun process_local_triple_get(graphName: String, dictionary: ResultSetDictionary, transactionID: Long, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): POPBase = Trace.trace({ "Endpoint.process_local_triple_get" }, {
        val g = DistributedTripleStore.localStore.getNamedGraph(graphName)
        return g.getIterator(transactionID, dictionary, s, p, o, sv, pv, ov, idx)
    }) as POPBase

    fun process_local_graph_clear_all(): XMLElement = Trace.trace({ "Endpoint.process_local_graph_clear_all" }, {
        DistributedTripleStore.localStore.getDefaultGraph().clear()
        return XMLElement("success")
    }) as XMLElement

    fun process_local_commit(transactionID: Long): XMLElement = Trace.trace({ "Endpoint.process_local_commit" }, {
        DistributedTripleStore.localStore.commit(transactionID)
        return XMLElement("success")
    }) as XMLElement

    fun process_local_graph_operation(name: String, type: EGraphOperationType): XMLElement = Trace.trace({ "Endpoint.process_local_graph_operation" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "process_local_graph_operation aa $name $type" })
        GlobalLogger.log(ELoggerType.DEBUG, { "${DistributedTripleStore}" })
        GlobalLogger.log(ELoggerType.DEBUG, { "${DistributedTripleStore.localStore}" })
        when (type) {
            EGraphOperationType.CLEAR -> DistributedTripleStore.localStore.clearGraph(name)
            EGraphOperationType.CREATE -> DistributedTripleStore.localStore.createGraph(name)
            EGraphOperationType.DROP -> DistributedTripleStore.localStore.dropGraph(name)
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "process_local_graph_operation bb" })
        return XMLElement("success")
    }) as XMLElement

    fun process_turtle_input(data: String): XMLElement = Trace.trace({ "Endpoint.process_turtle_input" }, {
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
        return XMLElement("done")
    }) as XMLElement

    fun process_xml_input(data: String): XMLElement = Trace.trace({ "Endpoint.process_xml_input" }, {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        DistributedTripleStore.getDefaultGraph().addData(transactionID, POPImportFromXml(dictionary, XMLElement.parseFromXml(data)!!.first()))
        DistributedTripleStore.commit(transactionID)
        return XMLElement("done")
    }) as XMLElement

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
    }) as XMLElement

    fun process_operatorgraph_query(query: String): XMLElement = Trace.trace({ "Endpoint.process_operatorgraph_query" }, {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        val pop_node = XMLElement.convertToOPBase(dictionary, transactionID, XMLElement.parseFromXml(query)!!.first()) as POPBase
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        val res = QueryResultToXML.toXML(pop_node).first()
        DistributedTripleStore.commit(transactionID)
        return res
    }) as XMLElement
}
