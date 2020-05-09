package lupos.s11outputResult
import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
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
