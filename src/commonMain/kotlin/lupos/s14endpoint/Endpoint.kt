package lupos.s14endpoint

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

    inline fun cleanString(s: String): String {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                return res
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    }

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

    override fun nnext(): ResultRow? {
        var res = result
        result = null
        return res
    }

}

inline fun consume_triple(triple_s: Long, triple_p: Long, triple_o: Long) {
    val triple = ID_Triple(triple_s, triple_p, triple_o)
    val transactionID = DistributedTripleStore.nextTransactionID()
    val dictionary = ResultSetDictionary()
    DistributedTripleStore.getDefaultGraph().addData(transactionID, TripleInsertIterator(dictionary, triple))
    DistributedTripleStore.commit(transactionID)
}

object Endpoint {
    inline fun process_local_triple_add(graphName: String, transactionID: Long, s: String?, p: String?, o: String?): XMLElement {
        DistributedTripleStore.localStore.getNamedGraph(graphName).addData(transactionID, listOf(s, p, o))
        return XMLElement("success")
    }

    inline fun process_local_triple_delete(graphName: String, transactionID: Long, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean): XMLElement {
        DistributedTripleStore.localStore.getNamedGraph(graphName).deleteDataVar(transactionID, listOf(Pair(s, sv), Pair(p, pv), Pair(o, ov)))
        return XMLElement("success")
    }

    inline fun process_local_triple_get(graphName: String, transactionID: Long): XMLElement {
        return QueryResultToXML.toXML(DistributedTripleStore.localStore.getNamedGraph(graphName).getIterator(ResultSetDictionary(), "s", "p", "o")).first()
    }

    inline fun process_local_graph_clear_all(): XMLElement {
        DistributedTripleStore.localStore.getDefaultGraph().clear()
        return XMLElement("success")
    }

    inline fun process_local_commit(transactionID: Long): XMLElement {
        DistributedTripleStore.localStore.commit(transactionID)
        return XMLElement("success")
    }

    inline fun process_local_graph_operation(name: String, type: GraphOperationType): XMLElement {
        println("process_local_graph_operation aa $name $type")
        println("${DistributedTripleStore}")
        println("${DistributedTripleStore.localStore}")
        when (type) {
            GraphOperationType.CLEAR -> DistributedTripleStore.localStore.clearGraph(name)
            GraphOperationType.CREATE -> DistributedTripleStore.localStore.createGraph(name)
            GraphOperationType.DROP -> DistributedTripleStore.localStore.dropGraph(name)
        }
        println("process_local_graph_operation bb")
        return XMLElement("success")
    }

    inline fun process_turtle_input(data: String): XMLElement {
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
        return XMLElement("done")
    }

    inline fun process_xml_input(data: String): XMLElement {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        DistributedTripleStore.getDefaultGraph().addData(transactionID, POPImportFromXml(dictionary, XMLElement.parseFromXml(data)!!.first()))
        DistributedTripleStore.commit(transactionID)
        return XMLElement("done")
    }

    inline fun process_sparql_query(query: String): XMLElement {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        println("----------String Query")
        println(query)
        println("----------Abstract Syntax Tree")
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        println(ast_node)
        println("----------Logical Operator Graph")
        val lop_node = ast_node.visit(OperatorGraphVisitor())
        println(lop_node)
        println("----------Logical Operator Graph optimized")
        val lop_node2 = LogicalOptimizer(transactionID, dictionary).optimizeCall(lop_node)
        println(lop_node2)
        println("----------Physical Operator Graph")
        val pop_optimizer = PhysicalOptimizer(transactionID, dictionary)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
        println(pop_node)
        println("----------Distributed Operator Graph")
        val pop_distributed_node = KeyDistributionOptimizer(transactionID, dictionary).optimizeCall(pop_node) as POPBase
        println(pop_distributed_node)
        DistributedTripleStore.commit(transactionID)
        return QueryResultToXML.toXML(pop_distributed_node).first()
    }

    inline fun process_operatorgraph_query(query: String): XMLElement {
        val transactionID = DistributedTripleStore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        val pop_node = XMLElement.convertToOPBase(dictionary, transactionID, XMLElement.parseFromXml(query)!!.first()) as POPBase
        println(pop_node)
        val res = QueryResultToXML.toXML(pop_node).first()
        DistributedTripleStore.commit(transactionID)
        return res
    }
}
