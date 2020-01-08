package lupos.s1buildSyntaxTree.sparql1_1

import lupos.s1buildSyntaxTree.LexerCharIterator
import lupos.s1buildSyntaxTree.LookAheadTokenIterator
import lupos.s1buildSyntaxTree.ParseError

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
        val node = parser.expr()
        println(node)
    } catch (e: ParseError) {
        println(e.message)
        println("Error in the following line:")
        println(e.lineNumber)
//        e.printStackTrace();
    }
}
