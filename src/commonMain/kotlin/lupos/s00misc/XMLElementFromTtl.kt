package lupos.s00misc

import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.turtle.TurtleParser
import lupos.s02buildSyntaxTree.turtle.TurtleScanner


fun XMLElement.Companion.parseFromTtl(ttl: String): List<XMLElement>? {
    val res = mutableListOf<XMLElement>()
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
    res.add(nodeSparql)
    val nodeHead = XMLElement("head")
    val nodeResults = XMLElement("results")
    nodeSparql.addContent(nodeHead)
    nodeSparql.addContent(nodeResults)
    nodeHead.addContent(XMLElement("variable").addAttribute("name", "s"))
    nodeHead.addContent(XMLElement("variable").addAttribute("name", "p"))
    nodeHead.addContent(XMLElement("variable").addAttribute("name", "o"))
    val ltit = LookAheadTokenIterator(TurtleScanner(LexerCharIterator(ttl)), 3)
    val parser = TurtleParser({ triple ->
        val nodeResult = XMLElement("result")
        nodeResults.addContent(nodeResult)
        parseBindingFromString(nodeResult, triple.s.toN3String(), "s")
        parseBindingFromString(nodeResult, triple.p.toN3String(), "p")
        parseBindingFromString(nodeResult, triple.o.toN3String(), "o")
    }, ltit).turtleDoc()
    return res
}
