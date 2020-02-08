package lupos.s09physicalOperators.noinput
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPImportFromXml : POPBaseNullableIterator {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    private val resultSet: ResultSet
    val data: XMLElement
    var iterator: Iterator<XMLElement>? = null
    val variables = mutableMapOf<String, Variable>()
    override fun toXMLElement(): XMLElement {
        return XMLElement("POPImportFromXML").addContent(data)
    }

    constructor(dictionary: ResultSetDictionary, data: XMLElement) {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.data = data
        if (data.tag != "sparql")
            throw Exception("can only parse sparql xml into an iterator")
        for (r in data.childs) {
            if (r.tag == "results") {
                iterator = r.childs.iterator()
            }
            if (r.tag == "head") {
                for (v in r.childs) {
                    variables[v.attributes["name"]!!] = resultSet.createVariable(v.attributes["name"]!!)
                }
            }
        }
        if (iterator == null)
            throw Exception("can only parse sparql xml into an iterator")
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>("s", "p", "o")
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    inline fun cleanString(s: String): String {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                return res
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    }

    override fun nnext(): ResultRow? {
        if (!iterator!!.hasNext())
            return null
        val node = iterator!!.next()
        val result = resultSet.createResultRow()
        for (v in node.childs) {
            val name = v.attributes["name"]
            val child = v.childs.first()
            val content = cleanString(child.content)

            val value = when {
                child.tag == "uri" -> "<" + content + ">"
                child.tag == "literal" && child.attributes["datatype"] != null -> "\"" + content + "\"^^<" + child.attributes["datatype"] + ">"
                child.tag == "literal" && child.attributes["xml:lang"] != null -> "\"" + content + "\"@" + child.attributes["xml:lang"]
                child.tag == "bnode" -> "_:" + content
                else -> "\"" + content + "\""
            }
            result[variables[name]!!] = resultSet.createValue(value)
        }
        return result
    }
}
