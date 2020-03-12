package lupos.s11outputResult

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


object QueryResultToXML {
    fun toXML(query: POPBase): List<XMLElement> {
        val child = query.evaluate()
        val res = mutableListOf<XMLElement>()
        val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
        res.add(nodeSparql)
        val nodeHead = XMLElement("head")
        nodeSparql.addContent(nodeHead)
        val variableNames = query.getProvidedVariableNames().toTypedArray()
        val variables = mutableListOf<Pair<String, Variable>>()
        if (variableNames.size == 1 && variableNames[0] == "?boolean") {
            CoroutinesHelper.runBlock {
                Trace.traceSuspend({ "QueryResultToXML.runBlock" }, {
                    child.forEach { row ->
                        val value = query.resultSet.getValueObject(row, "?boolean")!!.valueToString()!!
                        val datatype = "http://www.w3.org/2001/XMLSchema#boolean"
                        SanityCheck.check({ value.endsWith("\"^^<" + datatype + ">") })
                        nodeSparql.addContent(XMLElement("boolean").addContent(value.substring(1, value.length - ("\"^^<" + datatype + ">").length)))
                        child.close()
                    }
                })
            }
        } else {
            val bnodeMap = mutableMapOf<String, String>()
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeResults)
            for (variableName in variableNames) {
                nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName))
                variables.add(Pair(variableName, query.resultSet.createVariable(variableName)))
            }
            CoroutinesHelper.runBlock {
                Trace.traceSuspend({ "QueryResultToXML.runBlock" }, {
                    child.forEach { row ->
                        val nodeResult = XMLElement("result")
                        nodeResults.addContent(nodeResult)
                        for (variable in variables) {
                            if (!query.resultSet.isUndefValue(row, variable.second)) {
                                val value = query.resultSet.getValueObject(row, variable.second)!!.valueToString()!!
                                val nodeBinding = XMLElement("binding").addAttribute("name", variable.first)
                                if (value.length > 1) {
                                    if (value.startsWith("\"") && !value.endsWith("\"")) {
                                        val idx = value.lastIndexOf("\"^^<")
                                        if (idx >= 0) {
                                            val data = value.substring(1, idx)
                                            val type = value.substring(idx + 4, value.length - 1)
                                            nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                                        } else {
                                            val idx2 = value.lastIndexOf("\"@")
                                            if (idx2 >= 0) {
                                                val data = value.substring(1, idx2)
                                                val lang = value.substring(idx2 + 2, value.length)
                                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("xml:lang", lang))
                                            } else
                                                nodeBinding.addContent(XMLElement("literal").addContent(value))
                                        }
                                    } else if (value.startsWith("<") && value.endsWith(">"))
                                        nodeBinding.addContent(XMLElement("uri").addContent(value.substring(1, value.length - 1)))
                                    else if (value.startsWith("_:")) {
                                        if (bnodeMap[value] == null)
                                            bnodeMap[value] = "" + bnodeMap.keys.size
                                        val name = bnodeMap[value]!!
                                        nodeBinding.addContent(XMLElement("bnode").addContent(name))
                                    } else
                                        nodeBinding.addContent(XMLElement("literal").addContent(value.substring(1, value.length - 1)))
                                }
                                nodeResult.addContent(nodeBinding)
                            }
                        }
                    }
                })
            }
        }
        return res
    }
}
