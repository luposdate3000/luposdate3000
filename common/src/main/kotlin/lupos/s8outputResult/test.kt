package lupos.s8outputResult

import lupos.s1buildSyntaxTree.LexerCharIterator
import lupos.s1buildSyntaxTree.LookAheadTokenIterator
import lupos.s1buildSyntaxTree.ParseError
import lupos.s1buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s1buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s2buildOperatorGraph.*
import lupos.s3logicalOptimisation.LogicalOptimizer
import lupos.s4resultRepresentation.*
import lupos.s5physicalOperators.*
import lupos.s6tripleStore.*
import lupos.s7physicalOptimisation.*

var sparql_test_input = arrayOf(arrayOf("a", "b", "c"))

val sparql_test = """
SELECT ?a WHERE { <a> <b> ?a }
"""

class ResultSetIteratorTestImpl : ResultSetIterator {
    private var index = 0
    private var data: Array<Array<String>>? = null
    private var resultSet = ResultSet()
    private var s = resultSet.createVariable("s")
    private var p = resultSet.createVariable("p")
    private var o = resultSet.createVariable("o")
    fun setData(data: Array<Array<String>>) {
        index = 0
        this.data = data
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun hasNext(): Boolean {
        return index < (data?.size ?: 0)
    }

    override fun next(): ResultRow {
        val value = data!![index]
        var res = resultSet.createResultRow()
        res[s] = resultSet.createValue(value[0])
        res[p] = resultSet.createValue(value[1])
        res[o] = resultSet.createValue(value[2])
        index++
        return res
    }
}


fun main(args: Array<String>) {
    val toParse = sparql_test
    val lcit = LexerCharIterator(toParse)
    val tit = TokenIteratorSPARQLParser(lcit)
    val ltit = LookAheadTokenIterator(tit, 3)
    try {
        println("----------Input Data")
        var iterator1 = ResultSetIteratorTestImpl()
        iterator1.setData(sparql_test_input)
        persistentTripleStore.addData(iterator1)
        printResult(persistentTripleStore.getIterator())
        println("----------String Query")
        println(toParse)
        println("----------Abstract Syntax Tree")
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        println(ast_node)
        println("----------Logical Operator Graph")
        val lop_node = ast_node.visit(OperatorGraphVisitor())
        println(lop_node)
        println("----------Logical Operator Graph optimized")
        val lop_node2 = LogicalOptimizer().optimize(lop_node)
        println(lop_node2)
        println("----------Physical Operator Graph")
        val pop_node = PhysicalOptimizer().optimize(lop_node2)
        println(pop_node)
        println("----------Query Result")
        printResult(pop_node)

    } catch (e: ParseError) {
        println(e.message)
        println("Error in the following line:")
        println(e.lineNumber)
//        e.printStackTrace();
    }
}
