package lupos.s11outputResult

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s09physicalOperators.POPBase

object QueryResultToXMLElement {
    suspend fun toXML(node: POPBase): XMLElement {
//BenchmarkUtils.start(EBenchmark.QUERY_EVALUATE_INIT)
        val child = node.evaluate()
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY_EVALUATE_INIT)
//BenchmarkUtils.start(EBenchmark.QUERY_ITERATE_RESULTS)
        val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
        val nodeHead = XMLElement("head")
        nodeSparql.addContent(nodeHead)
        val variables = node.getProvidedVariableNames().toTypedArray()
        if (variables.size == 1 && variables[0] == "?boolean") {
            val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next()!!).valueToString()!!
            val datatype = "http://www.w3.org/2001/XMLSchema#boolean"
println(value)
            SanityCheck.check({ value.endsWith("\"^^<" + datatype + ">") })
            nodeSparql.addContent(XMLElement("boolean").addContent(value.substring(1, value.length - ("\"^^<" + datatype + ">").length)))
            child.columns["?boolean"]!!.close()
        } else {
            val bnodeMap = mutableMapOf<String, String>()
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeResults)
            for (variable in variables) {
                nodeHead.addContent(XMLElement("variable").addAttribute("name", variable))
            }
            val columns = variables.map { child.columns[it] }.toTypedArray()
            if (variables.size == 0) {
                for (i in 0 until child.count) {
                    val nodeResult = XMLElement("result")
                    nodeResults.addContent(nodeResult)
                }
            } else {
                loop@ while (true) {
                    val nodeResult = XMLElement("result")
                    for (variableIndex in 0 until variables.size) {
                        val valueID = columns[variableIndex]!!.next()
                        if (valueID == null) {
                            break@loop
                        }
                        if (valueID != ResultSetDictionary.undefValue && valueID != ResultSetDictionary.errorValue) {
                            val value = node.query.dictionary.getValue(valueID).valueToString()
                            require(value != null, { "QueryResultToXML unexpected null" })
                            val nodeBinding = XMLElement("binding").addAttribute("name", variables[variableIndex])
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
                                        } else {
                                            nodeBinding.addContent(XMLElement("literal").addContent(value))
                                        }
                                    }
                                } else if (value.startsWith("<") && value.endsWith(">")) {
                                    nodeBinding.addContent(XMLElement("uri").addContent(value.substring(1, value.length - 1)))
                                } else if (value.startsWith("_:")) {
                                    if (bnodeMap[value] == null) {
                                        bnodeMap[value] = "" + bnodeMap.keys.size
                                    }
                                    val name = bnodeMap[value]!!
                                    nodeBinding.addContent(XMLElement("bnode").addContent(name))
                                } else {
                                    nodeBinding.addContent(XMLElement("literal").addContent(value.substring(1, value.length - 1)))
                                }
                            }
                            nodeResult.addContent(nodeBinding)
                        }
                    }
                    nodeResults.addContent(nodeResult)
                }
            }
        }
//BenchmarkUtils.elapsedSeconds(EBenchmark.QUERY_ITERATE_RESULTS)
        return nodeSparql
    }
}
