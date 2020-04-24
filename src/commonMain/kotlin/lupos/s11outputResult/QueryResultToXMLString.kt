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

object QueryResultToXMLString {
    operator fun invoke(node: POPBase): String {
        var res = StringBuilder()
        CoroutinesHelper.runBlock {
            val child = node.evaluate()
            res.append("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n")
            val variables = node.getProvidedVariableNames().toTypedArray()
            if (variables.size == 1 && variables[0] == "?boolean") {
                res.append(" <head/>\n")
                val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next()!!)
                res.append(" <boolean>\n  ")
                res.append(value.toBoolean())
                res.append(" </boolean>\n")
                child.columns["?boolean"]!!.close()
            } else {
                val bnodeMap = mutableMapOf<String, String>()
                val columns = variables.map { child.columns[it] }.toTypedArray()
                if (variables.size == 0) {
                    res.append(" <head/>\n  <results>\n")
                    for (i in 0 until child.count) {
                        res.append("   <result/>\n")
                    }
                    res.append("  </results>\n")
                } else {
                    res.append(" <head>\n")
                    for (variable in variables) {
                        res.append("  <variable name=\"")
                        res.append(variable)
                        res.append("\">\n")
                    }
                    res.append(" </head>\n <results>\n")
                    loop@ while (true) {
                        for (variableIndex in 0 until variables.size) {
                            val valueID = columns[variableIndex]!!.next()
                            if (valueID == null) {
                                break@loop
                            }
                            if (variableIndex == 0) {
                                res.append("  <result>\n")
                            }
                            if (valueID != ResultSetDictionary.undefValue && valueID != ResultSetDictionary.errorValue) {
                                val value = node.query.dictionary.getValue(valueID).valueToString()
                                require(value != null, { "QueryResultToXML unexpected null" })
                                res.append("   <binding name=\"")
                                res.append(variables[variableIndex])
                                res.append("\">\n")
                                if (value.length > 1) {
                                    if (value.startsWith("\"") && !value.endsWith("\"")) {
                                        val idx = value.lastIndexOf("\"^^<")
                                        if (idx >= 0) {
                                            res.append("    <literal datatype=\"")
                                            res.append(value.substring(idx + 4, value.length - 1))
                                            res.append("\">")
                                            res.append(value.substring(1, idx))
                                            res.append("</literal>\n")
                                        } else {
                                            val idx2 = value.lastIndexOf("\"@")
                                            if (idx2 >= 0) {
                                                res.append("    <literal xml:lang=\"")
                                                res.append(value.substring(idx2 + 2, value.length))
                                                res.append("\">")
                                                res.append(value.substring(1, idx2))
                                                res.append("</literal>\n")
                                            } else {
                                                res.append("    <literal>")
                                                res.append(value)
                                                res.append("</literal>\n")
                                            }
                                        }
                                    } else if (value.startsWith("<") && value.endsWith(">")) {
                                        res.append("    <uri>")
                                        res.append(value.substring(1, value.length - 1))
                                        res.append("</uri>\n")
                                    } else if (value.startsWith("_:")) {
                                        if (bnodeMap[value] == null) {
                                            bnodeMap[value] = "" + bnodeMap.keys.size
                                        }
                                        res.append("    <bnode>")
                                        res.append(bnodeMap[value]!!)
                                        res.append("</bnode>\n")
                                    } else {
                                        res.append("    <literal>")
                                        res.append(value.substring(1, value.length - 1))
                                        res.append("</literal>\n")
                                    }
                                }
                                res.append("   </binding>\n")
                            }
                        }
                        res.append("  <result/>\n")
                    }
                    res.append(" <results/>\n")
                }
            }
            res.append("</sparql>")
        }
        return res.toString()
    }
}
