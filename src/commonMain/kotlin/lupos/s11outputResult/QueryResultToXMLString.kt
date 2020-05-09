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
                val bnodeMap = MyMapIntInt()
                var bnodeMapSize = 0
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
                            val value = node.query.dictionary.getValue(valueID)
                            when (value) {
                                is ValueBnode -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <bnode>")
                                    res.append(bnodeMap.getOrCreate(valueID, { bnodeMapSize++ }))
                                    res.append("</bnode>\n   </binding>\n")
                                }
                                is ValueBoolean -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal>")
                                    res.append(value.value)
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueLanguageTaggedLiteral -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal xml:lang=\"")
                                    res.append(value.language)
                                    res.append("\">")
                                    res.append(value.content)
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueSimpleLiteral -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal>")
                                    res.append(value.content)
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueTypedLiteral -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal datatype=\"")
                                    res.append(value.type_iri)
                                    res.append("\">")
                                    res.append(value.content)
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueDecimal -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal>")
                                    res.append(value.value)
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueDouble -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal>")
                                    res.append(value.value)
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueInteger -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal>")
                                    res.append(value.value)
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueIri -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <uri>")
                                    res.append(value.iri)
                                    res.append("</uri>\n   </binding>\n")
                                }
                                is ValueDateTime -> {
                                    res.append("   <binding name=\"")
                                    res.append(variables[variableIndex])
                                    res.append("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">")
                                    if (value.timezoneHours == -1 && value.timezoneMinutes == -1) {
                                        res.append("${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}")
                                    } else if (value.timezoneHours == 0 && value.timezoneMinutes == 0) {
                                        res.append("${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}Z")
                                    } else {
                                        res.append("${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}-${value.timezoneHours.toString().padStart(2, '0')}:${value.timezoneMinutes.toString().padStart(2, '0')}")
                                    }
                                    res.append("</literal>\n   </binding>\n")
                                }
                                is ValueUndef, is ValueError -> {
                                }
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
