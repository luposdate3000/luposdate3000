package lupos.s11outputResult

import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query

object QueryResultToXMLElement {
    suspend fun toXML(rootNode: IOPBase): XMLElement {
        var res = mutableListOf<XMLElement>()
        val nodes: Array<IOPBase>
        var columnProjectionOrder: List<List<String>>
        if (rootNode is OPBaseCompound) {
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf<OPBase>(rootNode)
            columnProjectionOrder = listOf(listOf<String>())
        }
        for (i in 0 until nodes.size) {
            val node = nodes[i]
            val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
            val nodeHead = XMLElement("head")
            nodeSparql.addContent(nodeHead)
            if (node is OPNothing) {
                val nodeResults = XMLElement("results")
                nodeSparql.addContent(nodeResults)
                for (variable in node.getProvidedVariableNames()) {
                    nodeHead.addContent(XMLElement("variable").addAttribute("name", variable))
                }
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder[i].size > 0) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check { node.getProvidedVariableNames().containsAll(columnNames) }
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val child = node.evaluate(Partition())
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next()).valueToString()!!
                    val datatype = "http://www.w3.org/2001/XMLSchema#boolean"
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
                    if (variables.size == 0) {
                        for (j in 0 until child.count()) {
                            val nodeResult = XMLElement("result")
                            nodeResults.addContent(nodeResult)
                        }
                    } else {
                        val columns = variables.map { child.columns[it]!! }.toTypedArray()
                        loop@ while (true) {
                            val nodeResult = XMLElement("result")
                            for (variableIndex in 0 until variables.size) {
                                val valueID = columns[variableIndex].next()
                                if (valueID == ResultSetDictionaryExt.nullValue) {
                                    for (closeIndex in 0 until columns.size) {
                                        columns[closeIndex].close()
                                    }
                                    break@loop
                                }
                                if (valueID != ResultSetDictionaryExt.undefValue && valueID != ResultSetDictionaryExt.errorValue) {
                                    val value = node.query.dictionary.getValue(valueID).valueToString()
                                    SanityCheck.check { value != null }
                                    val nodeBinding = XMLElement("binding").addAttribute("name", variables[variableIndex])
                                    if (value!!.length > 1) {
                                        if (value.startsWith("\"") && !value.endsWith("\"")) {
                                            val idx = value.lastIndexOf("\"^^<")
                                            if (idx >= 0) {
                                                val data = value.substring(1, idx)
                                                val type = value.substring(idx + 4, value.length - 1)
                                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                                            } else {
                                                val idx2 = value.lastIndexOf("\"@")
                                                SanityCheck.check { idx2 >= 0 }
                                                val data = value.substring(1, idx2)
                                                val lang = value.substring(idx2 + 2, value.length)
                                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("xml:lang", lang))
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
            }
            res.add(nodeSparql)
        }
        if (res.size == 1) {
            return res[0]
        }
        val compountResult = XMLElement("")
        for (r in res) {
            compountResult.addContent(r)
        }
        return compountResult
    }
}
