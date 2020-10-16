package lupos.s14endpoint

import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.noinput.POPValuesImportBase

class POPValuesImportTurtle : POPValuesImportBase {
    fun consume_triple(triple_s: Long, triple_p: Long, triple_o: Long) {
        addRow(arrayOf(Dictionary[triple_s]!!.toN3String(), Dictionary[triple_p]!!.toN3String(), Dictionary[triple_o]!!.toN3String()))
    }

    constructor(query: Query, projectedVariables: List<String>, data: String) : super(query, projectedVariables, listOf("s", "p", "o")) {
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
    }
}
