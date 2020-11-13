package lupos.s00misc

import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import kotlin.jvm.JvmField

class XMLElementFromN3 : XMLElementParser {
    override operator fun invoke(data: String): XMLElement {
        val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
        val nodeHead = XMLElement("head")
        val nodeResults = XMLElement("results")
        nodeSparql.addContent(nodeHead)
        nodeSparql.addContent(nodeResults)
        nodeHead.addContent(XMLElement("variable").addAttribute("name", "s"))
        nodeHead.addContent(XMLElement("variable").addAttribute("name", "p"))
        nodeHead.addContent(XMLElement("variable").addAttribute("name", "o"))
        val inputstream = object : IMyInputStream {
            @JvmField
            val dataBytes = data.encodeToByteArray()

            @JvmField
            var offset = 0
            override fun read(buf: ByteArray): Int {
                var len = buf.size
                if (dataBytes.size - offset < len) {
                    len = dataBytes.size - offset
                }
                if (len > 0) {
                    dataBytes.copyInto(buf, 0, offset, offset + len)
                    offset += len
                }
                return len
            }
        }
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
        return nodeSparql
    }
}
