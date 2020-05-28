package lupos.s11outputResult
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
object QueryResultToXMLElement {
    suspend fun toXML(rootNode: OPBase): XMLElement {
Coverage.funStart(12435)
        var res = mutableListOf<XMLElement>()
Coverage.statementStart(12436)
        val nodes: Array<OPBase>
Coverage.statementStart(12437)
        var columnProjectionOrder: List<List<String>>
Coverage.statementStart(12438)
        if (rootNode is OPBaseCompound) {
Coverage.ifStart(12439)
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
Coverage.statementStart(12440)
            columnProjectionOrder = rootNode.columnProjectionOrder
Coverage.statementStart(12441)
        } else {
Coverage.ifStart(12442)
            nodes = arrayOf<OPBase>(rootNode)
Coverage.statementStart(12443)
            columnProjectionOrder = listOf(listOf<String>())
Coverage.statementStart(12444)
        }
Coverage.statementStart(12445)
        for (i in 0 until nodes.size) {
Coverage.forLoopStart(12446)
            val node = nodes[i]
Coverage.statementStart(12447)
            val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
Coverage.statementStart(12448)
            val nodeHead = XMLElement("head")
Coverage.statementStart(12449)
            nodeSparql.addContent(nodeHead)
Coverage.statementStart(12450)
            if (node is OPNothing) {
Coverage.ifStart(12451)
                val nodeResults = XMLElement("results")
Coverage.statementStart(12452)
                nodeSparql.addContent(nodeResults)
Coverage.statementStart(12453)
                for (variable in node.getProvidedVariableNames()) {
Coverage.forLoopStart(12454)
                    nodeHead.addContent(XMLElement("variable").addAttribute("name", variable))
Coverage.statementStart(12455)
                }
Coverage.statementStart(12456)
            } else {
Coverage.ifStart(12457)
                val columnNames: List<String>
Coverage.statementStart(12458)
                if (columnProjectionOrder[i].size > 0) {
Coverage.ifStart(12459)
                    columnNames = columnProjectionOrder[i]
Coverage.statementStart(12460)
                    SanityCheck.check { columnNames.containsAll(node.getProvidedVariableNames()) }
Coverage.statementStart(12461)
                } else {
Coverage.ifStart(12462)
                    columnNames = node.getProvidedVariableNames()
Coverage.statementStart(12463)
                }
Coverage.statementStart(12464)
                val child = node.evaluate()
Coverage.statementStart(12465)
                val variables = columnNames.toTypedArray()
Coverage.statementStart(12466)
                if (variables.size == 1 && variables[0] == "?boolean") {
Coverage.ifStart(12467)
                    val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next()!!).valueToString()!!
Coverage.statementStart(12468)
                    val datatype = "http://www.w3.org/2001/XMLSchema#boolean"
Coverage.statementStart(12469)
                    SanityCheck.check({ value.endsWith("\"^^<" + datatype + ">") })
Coverage.statementStart(12470)
                    nodeSparql.addContent(XMLElement("boolean").addContent(value.substring(1, value.length - ("\"^^<" + datatype + ">").length)))
Coverage.statementStart(12471)
                    child.columns["?boolean"]!!.close()
Coverage.statementStart(12472)
                } else {
Coverage.ifStart(12473)
                    val bnodeMap = mutableMapOf<String, String>()
Coverage.statementStart(12474)
                    val nodeResults = XMLElement("results")
Coverage.statementStart(12475)
                    nodeSparql.addContent(nodeResults)
Coverage.statementStart(12476)
                    for (variable in variables) {
Coverage.forLoopStart(12477)
                        nodeHead.addContent(XMLElement("variable").addAttribute("name", variable))
Coverage.statementStart(12478)
                    }
Coverage.statementStart(12479)
                    if (variables.size == 0) {
Coverage.ifStart(12480)
                        for (j in 0 until child.count) {
Coverage.forLoopStart(12481)
                            val nodeResult = XMLElement("result")
Coverage.statementStart(12482)
                            nodeResults.addContent(nodeResult)
Coverage.statementStart(12483)
                        }
Coverage.statementStart(12484)
                    } else {
Coverage.ifStart(12485)
                        val columns = variables.map { child.columns[it] }.toTypedArray()
Coverage.statementStart(12486)
                        loop@ while (true) {
Coverage.whileLoopStart(12487)
                            val nodeResult = XMLElement("result")
Coverage.statementStart(12488)
                            for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(12489)
                                val valueID = columns[variableIndex]!!.next()
Coverage.statementStart(12490)
                                if (valueID == null) {
Coverage.ifStart(12491)
                                    break@loop
                                }
Coverage.statementStart(12492)
                                if (valueID != ResultSetDictionary.undefValue && valueID != ResultSetDictionary.errorValue) {
Coverage.ifStart(12493)
                                    val value = node.query.dictionary.getValue(valueID).valueToString()
Coverage.statementStart(12494)
                                    SanityCheck.check { value != null }
Coverage.statementStart(12495)
                                    val nodeBinding = XMLElement("binding").addAttribute("name", variables[variableIndex])
Coverage.statementStart(12496)
                                    if (value!!.length > 1) {
Coverage.ifStart(12497)
                                        if (value.startsWith("\"") && !value.endsWith("\"")) {
Coverage.ifStart(12498)
                                            val idx = value.lastIndexOf("\"^^<")
Coverage.statementStart(12499)
                                            if (idx >= 0) {
Coverage.ifStart(12500)
                                                val data = value.substring(1, idx)
Coverage.statementStart(12501)
                                                val type = value.substring(idx + 4, value.length - 1)
Coverage.statementStart(12502)
                                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
Coverage.statementStart(12503)
                                            } else {
Coverage.ifStart(12504)
                                                val idx2 = value.lastIndexOf("\"@")
Coverage.statementStart(12505)
                                                if (idx2 >= 0) {
Coverage.ifStart(12506)
                                                    val data = value.substring(1, idx2)
Coverage.statementStart(12507)
                                                    val lang = value.substring(idx2 + 2, value.length)
Coverage.statementStart(12508)
                                                    nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("xml:lang", lang))
Coverage.statementStart(12509)
                                                } else {
Coverage.ifStart(12510)
                                                    nodeBinding.addContent(XMLElement("literal").addContent(value))
Coverage.statementStart(12511)
                                                }
Coverage.statementStart(12512)
                                            }
Coverage.statementStart(12513)
                                        } else if (value.startsWith("<") && value.endsWith(">")) {
Coverage.ifStart(12514)
                                            nodeBinding.addContent(XMLElement("uri").addContent(value.substring(1, value.length - 1)))
Coverage.statementStart(12515)
                                        } else if (value.startsWith("_:")) {
Coverage.ifStart(12516)
                                            if (bnodeMap[value] == null) {
Coverage.ifStart(12517)
                                                bnodeMap[value] = "" + bnodeMap.keys.size
Coverage.statementStart(12518)
                                            }
Coverage.statementStart(12519)
                                            val name = bnodeMap[value]!!
Coverage.statementStart(12520)
                                            nodeBinding.addContent(XMLElement("bnode").addContent(name))
Coverage.statementStart(12521)
                                        } else {
Coverage.ifStart(12522)
                                            nodeBinding.addContent(XMLElement("literal").addContent(value.substring(1, value.length - 1)))
Coverage.statementStart(12523)
                                        }
Coverage.statementStart(12524)
                                    }
Coverage.statementStart(12525)
                                    nodeResult.addContent(nodeBinding)
Coverage.statementStart(12526)
                                }
Coverage.statementStart(12527)
                            }
Coverage.statementStart(12528)
                            nodeResults.addContent(nodeResult)
Coverage.statementStart(12529)
                        }
Coverage.statementStart(12530)
                    }
Coverage.statementStart(12531)
                }
Coverage.statementStart(12532)
            }
Coverage.statementStart(12533)
            res.add(nodeSparql)
Coverage.statementStart(12534)
        }
Coverage.statementStart(12535)
        if (res.size == 1) {
Coverage.ifStart(12536)
            return res[0]
        }
Coverage.statementStart(12537)
        val compountResult = XMLElement("")
Coverage.statementStart(12538)
        for (r in res) {
Coverage.forLoopStart(12539)
            compountResult.addContent(r)
Coverage.statementStart(12540)
        }
Coverage.statementStart(12541)
        return compountResult
    }
}
