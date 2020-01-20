package lupos.s8outputResult

import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.misc.*

object QueryResultToXML {
    fun toXML(query: POPBase): List<XMLElement> {
        val res = mutableListOf<XMLElement>()
        val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
        res.add(nodeSparql)
        val nodeHead = XMLElement("head")
        val nodeResults = XMLElement("results")
        nodeSparql.addContent(nodeHead)
        nodeSparql.addContent(nodeResults)
        val resultSet = query.getResultSet()
        val variableNames = resultSet.getVariableNames().toTypedArray()
        val variables = mutableListOf<Pair<String, Variable>>()
        for (variableName in variableNames) {
            nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName))
            variables.add(Pair(variableName, resultSet.createVariable(variableName)))
        }
        while (query.hasNext()) {
            val nodeResult = XMLElement("result")
            nodeResults.addContent(nodeResult)
            val resultRow = query.next()
            for (variable in variables) {
                val nodeBinding = XMLElement("binding").addAttribute("name", variable.first)
                nodeResult.addContent(nodeBinding)
                val value = resultSet.getValue(resultRow[variable.second])
                if (value != resultSet.getUndefValue()) {
                    if (value.startsWith("\"") && !value.endsWith("\"")) {
                        val idx = value.lastIndexOf("\"^^<")
                        if (idx >= 0) {
                            val data = value.substring(1, idx)
                            val type = value.substring(idx + 4, value.length - 1)
                            nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                        } else {
                            val idx2 = value.lastIndexOf("\"@")
                            if (idx >= 0) {
                                val data = value.substring(1, idx)
                                val lang = value.substring(idx + 2, value.length)
                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("xml:lang", lang))
                            } else {
                                nodeBinding.addContent(XMLElement("literal").addContent(value))
                            }
                        }
                    } else if (value.startsWith("<") && value.endsWith(">"))
                        nodeBinding.addContent(XMLElement("uri").addContent(value.substring(1, value.length - 1)))
                } else
                    nodeBinding.addContent(XMLElement("literal").addContent(value))
            }
        }
        return res
    }
}
