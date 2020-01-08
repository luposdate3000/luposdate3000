package lupos.s7physicalOptimisation

import lupos.s1buildSyntaxTree.LexerCharIterator
import lupos.s1buildSyntaxTree.LookAheadTokenIterator
import lupos.s1buildSyntaxTree.ParseError
import lupos.s1buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s1buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s2buildOperatorGraph.*
import lupos.s5physicalOperators.*
import lupos.s6tripleStore.*

val sparql_test = """
SELECT ?a WHERE { <a> <b> ?a }
"""

fun main(args: Array<String>) {
    val toParse = sparql_test
    val lcit = LexerCharIterator(toParse)
    val tit = TokenIteratorSPARQLParser(lcit)
    val ltit = LookAheadTokenIterator(tit, 3)
    try {
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        println(ast_node)
        val lop_node = ast_node.visit(OperatorGraphVisitor())
        println(lop_node)
        val pop_node = transformToPhysicalOperators(lop_node)
        println(pop_node)
    } catch (e: ParseError) {
        println(e.message)
        println("Error in the following line:")
        println(e.lineNumber)
//        e.printStackTrace();
    }
}
