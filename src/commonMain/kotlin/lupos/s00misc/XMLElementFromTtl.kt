package lupos.s00misc
import lupos.s00misc.Coverage
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.turtle.TurtleParser
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
fun XMLElement.Companion.parseFromTtl(ttl: String): XMLElement? {
Coverage.funStart(1437)
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
Coverage.statementStart(1438)
    val nodeHead = XMLElement("head")
Coverage.statementStart(1439)
    val nodeResults = XMLElement("results")
Coverage.statementStart(1440)
    nodeSparql.addContent(nodeHead)
Coverage.statementStart(1441)
    nodeSparql.addContent(nodeResults)
Coverage.statementStart(1442)
    nodeHead.addContent(XMLElement("variable").addAttribute("name", "s"))
Coverage.statementStart(1443)
    nodeHead.addContent(XMLElement("variable").addAttribute("name", "p"))
Coverage.statementStart(1444)
    nodeHead.addContent(XMLElement("variable").addAttribute("name", "o"))
Coverage.statementStart(1445)
    val ltit = LookAheadTokenIterator(TurtleScanner(LexerCharIterator(ttl)), 3)
Coverage.statementStart(1446)
    TurtleParser({ triple ->
Coverage.statementStart(1447)
        val nodeResult = XMLElement("result")
Coverage.statementStart(1448)
        nodeResults.addContent(nodeResult)
Coverage.statementStart(1449)
        parseBindingFromString(nodeResult, triple.s.toN3String(), "s")
Coverage.statementStart(1450)
        parseBindingFromString(nodeResult, triple.p.toN3String(), "p")
Coverage.statementStart(1451)
        parseBindingFromString(nodeResult, triple.o.toN3String(), "o")
Coverage.statementStart(1452)
    }, ltit).turtleDoc()
Coverage.statementStart(1453)
    return nodeSparql
}
