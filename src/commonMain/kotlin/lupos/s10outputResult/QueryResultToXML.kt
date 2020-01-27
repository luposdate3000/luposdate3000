package lupos.s8outputResult
import lupos.misc.XMLElement

import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

object QueryResultToXML {
    fun toXML(query: POPBase): List<XMLElement> {
        val res = mutableListOf<XMLElement>()
        val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
        res.add(nodeSparql)
        val nodeHead = XMLElement("head")
        nodeSparql.addContent(nodeHead)
        val resultSet = query.getResultSet()
        val variableNames = resultSet.getVariableNames().toTypedArray()
        val variables = mutableListOf<Pair<String, Variable>>()
        if (variableNames.size == 1 && variableNames[0] == "?boolean") {
            require(query.hasNext())
            val resultRow = query.next()
            val value = resultSet.getValue(resultRow[resultSet.createVariable("?boolean")])
            val datatype = "http://www.w3.org/2001/XMLSchema#boolean"
            require(value.endsWith("\"^^<" + datatype + ">"))
            nodeSparql.addContent(XMLElement("boolean").addAttribute("datatype", datatype).addContent(value.substring(1, value.length - ("\"^^<" + datatype + ">").length)))
        } else {
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeResults)
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
                    val value = resultSet.getValue(resultRow[variable.second])
                    if (value != resultSet.getUndefValue()) {
                        if (value.startsWith("\"") && !value.endsWith("\"")) {
                            println("value:" + value)
                            val idx = value.lastIndexOf("\"^^<")
                            println("idx:" + idx)
                            if (idx >= 0) {
                                val data = value.substring(1, idx)
                                val type = value.substring(idx + 4, value.length - 1)
                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                            } else {
                                val idx2 = value.lastIndexOf("\"@")
                                println("idx2:" + idx2)
                                if (idx2 >= 0) {
                                    val data = value.substring(1, idx2)
                                    val lang = value.substring(idx2 + 2, value.length)
                                    nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("xml:lang", lang))
                                } else {
                                    nodeBinding.addContent(XMLElement("literal").addContent(value))
                                }
                            }
                        } else if (value.startsWith("<") && value.endsWith(">"))
                            nodeBinding.addContent(XMLElement("uri").addContent(value.substring(1, value.length - 1)))
                        else if (value.startsWith("_:"))
                            nodeBinding.addContent(XMLElement("bnode").addContent(value.substring(2, value.length)))
                        else
                            nodeBinding.addContent(XMLElement("literal").addContent(value.substring(1, value.length - 1)))
                        nodeResult.addContent(nodeBinding)
                    }
                }
            }
        }
        return res
    }
}
