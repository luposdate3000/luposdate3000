package lupos.s09physicalOperators.noinput

import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPImportFromXml(override val dictionary: ResultSetDictionary, val data: XMLElement) : POPBase() {
    override val operatorID = EOperatorID.POPImportFromXmlID
    override val classname = "POPImportFromXml"
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf()

    override fun equals(other: Any?): Boolean {
        if (other !is POPImportFromXml)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (!data.equals(other.data))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPImportFromXml(dictionary, data)

    override fun toXMLElement() = super.toXMLElement().addContent(data)

    override fun getProvidedVariableNames() = mutableListOf("s", "p", "o")

    fun cleanString(s: String): String {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                return res
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPImportFromXml.evaluate" }, {
        val variables = mutableMapOf<String, Variable>()
        if (data.tag != "sparql")
            throw Exception("can only parse sparql xml into an iterator")
        for (v in data["head"]!!.childs)
            variables[v.attributes["name"]!!] = resultSet.createVariable(v.attributes["name"]!!)
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (node in data["results"]!!.childs) {
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
                    channel.send(resultFlowProduce({ this@POPImportFromXml }, { result }))
                }
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })
}
