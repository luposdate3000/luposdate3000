package lupos.s13endpoint

import lupos.s00misc.*
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03buildOperatorGraph.OperatorGraphVisitor
import lupos.s05logicalOptimisation.LogicalOptimizer
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.*
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s08tripleStore.*
import lupos.s09physicalOptimisation.*
import lupos.s10outputResult.QueryResultToXML
import lupos.s12keyDistributionOptimizer.*
import lupos.s13endpoint.*


class TripleInsertIterator : POPBaseNullableIterator {
    var result: ResultRow?

    private val resultSet = ResultSet()
    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleInsertIterator")
    }

    fun cleanString(s: String): String {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                return res
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    }

    constructor(triple: ID_Triple) {
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

fun consume_triple(triple_s: Long, triple_p: Long, triple_o: Long) {
    val triple = ID_Triple(triple_s, triple_p, triple_o)
    val transactionID = globalStore.nextTransactionID()
    globalStore.getDefaultGraph().addData(transactionID, TripleInsertIterator(triple))
    globalStore.commit(transactionID)
}

object Endpoint {
    fun process_truncate(): XMLElement {
        globalStore.getDefaultGraph().truncate()
        return XMLElement("success")
    }

    fun process_turtle_input(data: String): XMLElement {
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
        return XMLElement("done")
    }

    fun process_xml_input(data: String): XMLElement {
        val transactionID = globalStore.nextTransactionID()
        globalStore.getDefaultGraph().addData(transactionID, POPImportFromXml(XMLElement.parseFromXml(data)!!.first()))
        globalStore.commit(transactionID)
        return XMLElement("done")
    }

    fun process_sparql_query(query: String): XMLElement {
        val transactionID = globalStore.nextTransactionID()
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
        val lop_node2 = LogicalOptimizer(transactionID).optimize(lop_node)
        println(lop_node2)
        println("----------Physical Operator Graph")
        val pop_optimizer = PhysicalOptimizer(transactionID)
        val pop_node = pop_optimizer.optimize(lop_node2)
        println(pop_node)
        println("----------Distributed Operator Graph")
        val pop_distributed_node = KeyDistributionOptimizer(transactionID).optimize(pop_node) as POPBase
        println(pop_distributed_node)
        globalStore.commit(transactionID)
        return QueryResultToXML.toXML(pop_distributed_node).first()
    }

    fun process_operatorgraph_query(query: String): XMLElement {
        val transactionID = globalStore.nextTransactionID()
        val pop_node = XMLElement.convertToOPBase(transactionID, XMLElement.parseFromXml(query)!!.first()!!) as POPBase
        println(pop_node)
        val res = QueryResultToXML.toXML(pop_node).first()
        globalStore.commit(transactionID)
        return res
    }
}
