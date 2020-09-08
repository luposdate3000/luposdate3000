package lupos.s11outputResult

import lupos.s00misc.DateHelper
import java.io.PrintWriter
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query

object QueryResultToXMLStream {
    suspend operator fun invoke(rootNode: OPBase, output: PrintWriter) {
        val nodes: Array<OPBase>
        var columnProjectionOrder = listOf<List<String>>()
        if (rootNode is OPBaseCompound) {
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf<OPBase>(rootNode)
        }
        for (i in 0 until nodes.size) {
            val node = nodes[i]
            output.print("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n")
            if (node is OPNothing) {
                val variables = node.getProvidedVariableNames()
                if (variables.size == 0) {
                    output.print(" <head/>\n")
                } else {
                    output.print(" <head>\n")
                    for (variable in variables) {
                        output.print("  <variable name=\"")
                        output.print(variable)
                        output.print("\">\n")
                    }
                    output.print(" </head>\n")
                }
                output.print(" <results/>\n")
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder[i].size > 0) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check { columnNames.containsAll(node.getProvidedVariableNames()) }
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
//var timer3 = DateHelper.markNow()
                val child = node.evaluate(Partition())
//println("timer #413 ${DateHelper.elapsedSeconds(timer3)}")
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    output.print(" <head/>\n")
                    val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next())
                    output.print(" <boolean>")
                    output.print(value.toBoolean())
                    output.print("</boolean>\n")
                    child.columns["?boolean"]!!.close()
                } else {
                    val bnodeMap = MyMapIntInt()
                    var bnodeMapSize = 0
                    val columns = variables.map { child.columns[it] }.toTypedArray()
                    if (variables.size == 0) {
                        output.print(" <head/>\n <results>\n")
                        for (j in 0 until child.count()) {
                            output.print("  <result/>\n")
                        }
                        output.print(" </results>\n")
                    } else {
                        output.print(" <head>\n")
                        for (variable in variables) {
                            output.print("  <variable name=\"")
                            output.print(variable)
                            output.print("\">\n")
                        }
                        output.print(" </head>\n <results>\n")
//                        var timer = DateHelper.markNow()
//                        var timer2 = DateHelper.markNow()
//                        var timerNext = DateHelper.helperElapsed(timer, timer)
//                        var timerPrint = timerNext
//                        var timerFlush = timerNext
                        loop@ while (true) {
                            for (variableIndex in 0 until variables.size) {
                                val valueID = columns[variableIndex]!!.next()
                                if (valueID == ResultSetDictionary.nullValue) {
                                    for (closeIndex in 0 until columns.size) {
                                        columns[closeIndex]!!.close()
                                    }
                                    break@loop
                                }
//                                timer2 = DateHelper.markNow()
//                                timerNext = DateHelper.helperAdd(timerNext, DateHelper.helperElapsed(timer, timer2))
//                                timer = timer2
                                if (variableIndex == 0) {
                                    output.print("  <result>\n")
                                }
                                val value = node.query.dictionary.getValue(valueID)
                                when (value) {
                                    is ValueBnode -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        if (PRETTY_BNODE_NAMES) {
                                            output.print("\">\n    <bnode>")
                                            output.print(bnodeMap.getOrCreate(valueID, { bnodeMapSize++ }))
                                            output.print("</bnode>\n   </binding>\n")
                                        } else {
                                            output.print("\">\n    <bnode>")
                                            output.print(valueID)
                                            output.print("</bnode>\n   </binding>\n")
                                        }
                                    }
                                    is ValueBoolean -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal>")
                                        output.print(value.value)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueLanguageTaggedLiteral -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal xml:lang=\"")
                                        output.print(value.language)
                                        output.print("\">")
                                        output.print(value.content)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueSimpleLiteral -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal>")
                                        output.print(value.content)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueTypedLiteral -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal datatype=\"")
                                        output.print(value.type_iri)
                                        output.print("\">")
                                        output.print(value.content)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueDecimal -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#decimal>")
                                        output.print(value.value)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueFloat -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#float>")
                                        output.print(value.value)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueDouble -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#double>")
                                        output.print(value.value)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueInteger -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#integer>")
                                        output.print(value.value)
                                        output.print("</literal>\n   </binding>\n")
                                    }
                                    is ValueIri -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n    <uri>")
                                        output.print(value.iri)
                                        output.print("</uri>\n   </binding>\n")
                                    }
                                    is ValueDateTime -> {
                                        output.print("   <binding name=\"")
                                        output.print(variables[variableIndex])
                                        output.print("\">\n")
                                        if (value.timezoneHours == -1 && value.timezoneMinutes == -1) {
                                            output.print("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}</literal>\n")
                                        } else if (value.timezoneHours == 0 && value.timezoneMinutes == 0) {
                                            output.print("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}Z</literal>\n")
                                        } else {
                                            output.print("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}-${value.timezoneHours.toString().padStart(2, '0')}:${value.timezoneMinutes.toString().padStart(2, '0')}</literal>\n")
                                        }
                                        output.print("   </binding>\n")
                                    }
                                    is ValueUndef, is ValueError -> {
                                    }
                                }
                            }
                            output.print("  </result>\n")
//                            timer2 = DateHelper.markNow()
//                            timerPrint = DateHelper.helperAdd(timerPrint, DateHelper.helperElapsed(timer, timer2))
//                            timer = timer2
/*
                            output.flush()
timer2=DateHelper.markNow()
timerFlush=DateHelper.helperAdd(timerFlush,DateHelper.helperElapsed(timer,timer2))
timer=timer2
*/
                        }
//                        println("timer #410 ${DateHelper.helperToSeconds(timerNext)}")
//                        println("timer #411 ${DateHelper.helperToSeconds(timerPrint)}")
//                        println("timer #412 ${DateHelper.helperToSeconds(timerFlush)}")
                        output.print(" </results>\n")
                    }
                }
                output.print("</sparql>\n")
            }
        }
    }
}
