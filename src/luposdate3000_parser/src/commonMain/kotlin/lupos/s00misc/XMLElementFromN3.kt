/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s00misc
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithStringTriples
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s00misc.MyStringStream
import kotlin.jvm.JvmField
public class XMLElementFromN3 : XMLElementParser {
    override operator fun invoke(data: String): XMLElement {
        var nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
        try {
            val nodeHead = XMLElement("head")
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeHead)
            nodeSparql.addContent(nodeResults)
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "s"))
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "p"))
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "o"))
            val inputstream =MyStringStream(data)
            val parser = object : Turtle2Parser(inputstream) {
                override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                    val nodeResult = XMLElement("result")
                    nodeResults.addContent(nodeResult)
                    XMLElement.parseBindingFromString(nodeResult, triple[0], "s")
                    XMLElement.parseBindingFromString(nodeResult, triple[1], "p")
                    XMLElement.parseBindingFromString(nodeResult, triple[2], "o")
                }
            }
            parser.turtleDoc()
        } catch (e: Throwable) {
            nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
            val nodeHead = XMLElement("head")
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeHead)
            nodeSparql.addContent(nodeResults)
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "s"))
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "p"))
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "o"))
            val lcit = LexerCharIterator(data)
            val tit = TurtleScanner(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val x = object : TurtleParserWithStringTriples() {
                /*suspend*/ override fun consume_triple(s: String, p: String, o: String) {
                    val nodeResult = XMLElement("result")
                    nodeResults.addContent(nodeResult)
                    XMLElement.parseBindingFromString(nodeResult, s, "s")
                    XMLElement.parseBindingFromString(nodeResult, p, "p")
                    XMLElement.parseBindingFromString(nodeResult, o, "o")
                }
            }
            x.ltit = ltit
            x.turtleDoc()
        }
        return nodeSparql
    }
}
