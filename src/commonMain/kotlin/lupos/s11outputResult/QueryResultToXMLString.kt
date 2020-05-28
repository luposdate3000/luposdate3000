package lupos.s11outputResult
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
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
import lupos.s09physicalOperators.POPBase
object QueryResultToXMLString {
    operator fun invoke(rootNode: OPBase): String {
Coverage.funStart(12542)
        var res = StringBuilder()
Coverage.statementStart(12543)
        val nodes: Array<OPBase>
Coverage.statementStart(12544)
        var columnProjectionOrder = listOf<List<String>>()
Coverage.statementStart(12545)
        if (rootNode is OPBaseCompound) {
Coverage.ifStart(12546)
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
Coverage.statementStart(12547)
            columnProjectionOrder = rootNode.columnProjectionOrder
Coverage.statementStart(12548)
        } else {
Coverage.ifStart(12549)
            nodes = arrayOf<OPBase>(rootNode)
Coverage.statementStart(12550)
        }
Coverage.statementStart(12551)
        for (i in 0 until nodes.size) {
Coverage.forLoopStart(12552)
            val node = nodes[i]
Coverage.statementStart(12553)
            res.append("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n")
Coverage.statementStart(12554)
            if (node is OPNothing) {
Coverage.ifStart(12555)
                val variables = node.getProvidedVariableNames()
Coverage.statementStart(12556)
                if (variables.size == 0) {
Coverage.ifStart(12557)
                    res.append(" <head/>\n")
Coverage.statementStart(12558)
                } else {
Coverage.ifStart(12559)
                    res.append(" <head>\n")
Coverage.statementStart(12560)
                    for (variable in variables) {
Coverage.forLoopStart(12561)
                        res.append("  <variable name=\"")
Coverage.statementStart(12562)
                        res.append(variable)
Coverage.statementStart(12563)
                        res.append("\">\n")
Coverage.statementStart(12564)
                    }
Coverage.statementStart(12565)
                    res.append(" </head>\n")
Coverage.statementStart(12566)
                }
Coverage.statementStart(12567)
                res.append(" <results/>\n")
Coverage.statementStart(12568)
            } else {
Coverage.ifStart(12569)
                val columnNames: List<String>
Coverage.statementStart(12570)
                if (columnProjectionOrder[i].size > 0) {
Coverage.ifStart(12571)
                    columnNames = columnProjectionOrder[i]
Coverage.statementStart(12572)
                    SanityCheck.check { columnNames.containsAll(node.getProvidedVariableNames()) }
Coverage.statementStart(12573)
                } else {
Coverage.ifStart(12574)
                    columnNames = node.getProvidedVariableNames()
Coverage.statementStart(12575)
                }
Coverage.statementStart(12576)
                CoroutinesHelper.runBlock {
Coverage.statementStart(12577)
                    val child = node.evaluate()
Coverage.statementStart(12578)
                    val variables = columnNames.toTypedArray()
Coverage.statementStart(12579)
                    if (variables.size == 1 && variables[0] == "?boolean") {
Coverage.ifStart(12580)
                        res.append(" <head/>\n")
Coverage.statementStart(12581)
                        val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next()!!)
Coverage.statementStart(12582)
                        res.append(" <boolean>\n  ")
Coverage.statementStart(12583)
                        res.append(value.toBoolean())
Coverage.statementStart(12584)
                        res.append(" </boolean>\n")
Coverage.statementStart(12585)
                        child.columns["?boolean"]!!.close()
Coverage.statementStart(12586)
                    } else {
Coverage.ifStart(12587)
                        val bnodeMap = MyMapIntInt()
Coverage.statementStart(12588)
                        var bnodeMapSize = 0
Coverage.statementStart(12589)
                        val columns = variables.map { child.columns[it] }.toTypedArray()
Coverage.statementStart(12590)
                        if (variables.size == 0) {
Coverage.ifStart(12591)
                            res.append(" <head/>\n <results>\n")
Coverage.statementStart(12592)
                            for (j in 0 until child.count) {
Coverage.forLoopStart(12593)
                                res.append("  <result/>\n")
Coverage.statementStart(12594)
                            }
Coverage.statementStart(12595)
                            res.append(" </results>\n")
Coverage.statementStart(12596)
                        } else {
Coverage.ifStart(12597)
                            res.append(" <head>\n")
Coverage.statementStart(12598)
                            for (variable in variables) {
Coverage.forLoopStart(12599)
                                res.append("  <variable name=\"")
Coverage.statementStart(12600)
                                res.append(variable)
Coverage.statementStart(12601)
                                res.append("\">\n")
Coverage.statementStart(12602)
                            }
Coverage.statementStart(12603)
                            res.append(" </head>\n <results>\n")
Coverage.statementStart(12604)
                            loop@ while (true) {
Coverage.whileLoopStart(12605)
                                for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(12606)
                                    val valueID = columns[variableIndex]!!.next()
Coverage.statementStart(12607)
                                    if (valueID == null) {
Coverage.ifStart(12608)
                                        break@loop
                                    }
Coverage.statementStart(12609)
                                    if (variableIndex == 0) {
Coverage.ifStart(12610)
                                        res.append("  <result>\n")
Coverage.statementStart(12611)
                                    }
Coverage.statementStart(12612)
                                    val value = node.query.dictionary.getValue(valueID)
Coverage.statementStart(12613)
                                    when (value) {
                                        is ValueBnode -> {
Coverage.whenCaseStart(12615)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12616)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12617)
                                            res.append("\">\n    <bnode>")
Coverage.statementStart(12618)
                                            res.append(bnodeMap.getOrCreate(valueID, { bnodeMapSize++ }))
Coverage.statementStart(12619)
                                            res.append("</bnode>\n   </binding>\n")
Coverage.statementStart(12620)
                                        }
                                        is ValueBoolean -> {
Coverage.whenCaseStart(12621)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12622)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12623)
                                            res.append("\">\n    <literal>")
Coverage.statementStart(12624)
                                            res.append(value.value)
Coverage.statementStart(12625)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12626)
                                        }
                                        is ValueLanguageTaggedLiteral -> {
Coverage.whenCaseStart(12627)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12628)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12629)
                                            res.append("\">\n    <literal xml:lang=\"")
Coverage.statementStart(12630)
                                            res.append(value.language)
Coverage.statementStart(12631)
                                            res.append("\">")
Coverage.statementStart(12632)
                                            res.append(value.content)
Coverage.statementStart(12633)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12634)
                                        }
                                        is ValueSimpleLiteral -> {
Coverage.whenCaseStart(12635)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12636)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12637)
                                            res.append("\">\n    <literal>")
Coverage.statementStart(12638)
                                            res.append(value.content)
Coverage.statementStart(12639)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12640)
                                        }
                                        is ValueTypedLiteral -> {
Coverage.whenCaseStart(12641)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12642)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12643)
                                            res.append("\">\n    <literal datatype=\"")
Coverage.statementStart(12644)
                                            res.append(value.type_iri)
Coverage.statementStart(12645)
                                            res.append("\">")
Coverage.statementStart(12646)
                                            res.append(value.content)
Coverage.statementStart(12647)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12648)
                                        }
                                        is ValueDecimal -> {
Coverage.whenCaseStart(12649)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12650)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12651)
                                            res.append("\">\n    <literal>")
Coverage.statementStart(12652)
                                            res.append(value.value)
Coverage.statementStart(12653)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12654)
                                        }
                                        is ValueDouble -> {
Coverage.whenCaseStart(12655)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12656)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12657)
                                            res.append("\">\n    <literal>")
Coverage.statementStart(12658)
                                            res.append(value.value)
Coverage.statementStart(12659)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12660)
                                        }
                                        is ValueInteger -> {
Coverage.whenCaseStart(12661)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12662)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12663)
                                            res.append("\">\n    <literal>")
Coverage.statementStart(12664)
                                            res.append(value.value)
Coverage.statementStart(12665)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12666)
                                        }
                                        is ValueIri -> {
Coverage.whenCaseStart(12667)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12668)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12669)
                                            res.append("\">\n    <uri>")
Coverage.statementStart(12670)
                                            res.append(value.iri)
Coverage.statementStart(12671)
                                            res.append("</uri>\n   </binding>\n")
Coverage.statementStart(12672)
                                        }
                                        is ValueDateTime -> {
Coverage.whenCaseStart(12673)
                                            res.append("   <binding name=\"")
Coverage.statementStart(12674)
                                            res.append(variables[variableIndex])
Coverage.statementStart(12675)
                                            res.append("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">")
Coverage.statementStart(12676)
                                            if (value.timezoneHours == -1 && value.timezoneMinutes == -1) {
Coverage.ifStart(12677)
                                                res.append("${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}")
Coverage.statementStart(12678)
                                            } else if (value.timezoneHours == 0 && value.timezoneMinutes == 0) {
Coverage.ifStart(12679)
                                                res.append("${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}Z")
Coverage.statementStart(12680)
                                            } else {
Coverage.ifStart(12681)
                                                res.append("${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}-${value.timezoneHours.toString().padStart(2, '0')}:${value.timezoneMinutes.toString().padStart(2, '0')}")
Coverage.statementStart(12682)
                                            }
Coverage.statementStart(12683)
                                            res.append("</literal>\n   </binding>\n")
Coverage.statementStart(12684)
                                        }
                                        is ValueUndef, is ValueError -> {
Coverage.whenCaseStart(12685)
                                        }
                                    }
Coverage.statementStart(12686)
                                }
Coverage.statementStart(12687)
                                res.append("  <result/>\n")
Coverage.statementStart(12688)
                            }
Coverage.statementStart(12689)
                            res.append(" <results/>\n")
Coverage.statementStart(12690)
                        }
Coverage.statementStart(12691)
                    }
Coverage.statementStart(12692)
                }
Coverage.statementStart(12693)
                res.append("</sparql>\n")
Coverage.statementStart(12694)
            }
Coverage.statementStart(12695)
        }
Coverage.statementStart(12696)
        return res.toString()
    }
}
