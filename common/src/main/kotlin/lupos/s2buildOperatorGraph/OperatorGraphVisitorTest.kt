package lupos.s2operatorgraph

import lupos.s1parser.LexerCharIterator
import lupos.s1parser.LookAheadTokenIterator
import lupos.s1parser.ParseError
import lupos.s1parser.sparql1_1.SPARQLParser
import lupos.s1parser.sparql1_1.TokenIteratorSPARQLParser

val sparql_test = """
SELECT ?a ?b ?c WHERE { <a> <b> <c>. <a> <b> ?a }
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
        val op_node = ast_node.visit(OperatorGraphVisitor())
        println(op_node)
    } catch (e: ParseError) {
        println(e.message)
        println("Error in the following line:")
        println(e.lineNumber)
//        e.printStackTrace();
    }
}