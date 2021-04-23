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
lupos.parser

import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.turtle.Turtle2Parser
import lupos.parser.turtle.TurtleParserWithStringTriples
import lupos.parser.turtle.TurtleScanner
import lupos.shared_inline.MyStringStream

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
            val inputstream = MyStringStream(data)
            val parser = object : Turtle2Parser(inputstream) {
                override fun onTriple() {
                    val nodeResult = XMLElement("result")
                    nodeResults.addContent(nodeResult)
                    XMLElement.parseBindingFromByteArrayWrapper(nodeResult, triple[0], "s")
                    XMLElement.parseBindingFromByteArrayWrapper(nodeResult, triple[1], "p")
                    XMLElement.parseBindingFromByteArrayWrapper(nodeResult, triple[2], "o")
                }
            }
            parser.parse()
        } catch (e: Throwable) {
            e.printStackTrace()
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
            x.parse()
        }
        return nodeSparql
    }
}
