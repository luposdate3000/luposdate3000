package lupos.s11outputResult

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
            output.println("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">")
            if (node is OPNothing) {
                val variables = node.getProvidedVariableNames()
                if (variables.size == 0) {
                    output.println(" <head/>")
                } else {
                    output.println(" <head>")
                    for (variable in variables) {
                        output.println("  <variable name=\"${variable}\">")
                    }
                    output.println(" </head>")
                }
                output.println(" <results/>")
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder[i].size > 0) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check { columnNames.containsAll(node.getProvidedVariableNames()) }
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val child = node.evaluate(Partition())
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    output.println(" <head/>")
                    val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next())
                    output.println(" <boolean>${value.toBoolean()}</boolean>")
                    child.columns["?boolean"]!!.close()
                } else {
                    val bnodeMap = MyMapIntInt()
                    var bnodeMapSize = 0
                    val columns = variables.map { child.columns[it] }.toTypedArray()
                    if (variables.size == 0) {
                        output.println(" <head/>")
                        output.println(" <results>")
                        for (j in 0 until child.count()) {
                            output.println("  <result/>")
                        }
                        output.println(" </results>")
                    } else {
                        output.println(" <head>")
                        for (variable in variables) {
                            output.println("  <variable name=\"${variable}\">")
                        }
                        output.println(" </head>")
                        output.println(" <results>")
                        loop@ while (true) {
                            for (variableIndex in 0 until variables.size) {
                                val valueID = columns[variableIndex]!!.next()
                                if (valueID == ResultSetDictionary.nullValue) {
                                    for (closeIndex in 0 until columns.size) {
                                        columns[closeIndex]!!.close()
                                    }
                                    break@loop
                                }
                                if (variableIndex == 0) {
                                    output.println("  <result>")
                                }
                                val value = node.query.dictionary.getValue(valueID)
                                when (value) {
                                    is ValueBnode -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        if (PRETTY_BNODE_NAMES) {
                                            output.println("    <bnode>${bnodeMap.getOrCreate(valueID, { bnodeMapSize++ })}</bnode>")
                                        } else {
                                            output.println("    <bnode>${valueID}</bnode>")
                                        }
                                        output.println("   </binding>")
                                    }
                                    is ValueBoolean -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal>${value.value}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueLanguageTaggedLiteral -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal xml:lang=\"${value.language}\">${value.content}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueSimpleLiteral -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal>${value.content}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueTypedLiteral -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal datatype=\"${value.type_iri}\">${value.content}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueDecimal -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal datatype=\"http://www.w3.org/2001/XMLSchema#decimal>${value.value}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueFloat -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal datatype=\"http://www.w3.org/2001/XMLSchema#float>${value.value}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueDouble -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal datatype=\"http://www.w3.org/2001/XMLSchema#double>${value.value}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueInteger -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <literal datatype=\"http://www.w3.org/2001/XMLSchema#integer>${value.value}</literal>")
                                        output.println("   </binding>")
                                    }
                                    is ValueIri -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        output.println("    <uri>${value.iri}</uri>")
                                        output.println("   </binding>")
                                    }
                                    is ValueDateTime -> {
                                        output.println("   <binding name=\"${variables[variableIndex]}\">")
                                        if (value.timezoneHours == -1 && value.timezoneMinutes == -1) {
                                            output.println("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}</literal>")
                                        } else if (value.timezoneHours == 0 && value.timezoneMinutes == 0) {
                                            output.println("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}Z</literal>")
                                        } else {
                                            output.println("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}-${value.timezoneHours.toString().padStart(2, '0')}:${value.timezoneMinutes.toString().padStart(2, '0')}</literal>")
                                        }
                                        output.println("   </binding>")
                                    }
                                    is ValueUndef, is ValueError -> {
                                    }
                                }
                            }
                            output.println("  </result>")
                        }
                        output.println(" </results>")
                    }
                }
                output.println("</sparql>")
            }
        }
    }
}
