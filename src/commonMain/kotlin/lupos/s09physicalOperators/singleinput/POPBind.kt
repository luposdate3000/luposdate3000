package lupos.s09physicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPBind(query: Query, projectedVariables: List<String>, @JvmField val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPBindID, "POPBind", arrayOf(child, value), ESortPriority.BIND) {
    override fun toSparql(): String {
Coverage.funStart(11503)
        if (children[1] is AOPConstant && (children[1] as AOPConstant).value == ResultSetDictionary.undefValue) {
Coverage.ifStart(11504)
            return children[0].toSparql()
        }
Coverage.statementStart(11505)
        var res = "{SELECT "
Coverage.statementStart(11506)
        for (v in children[0].getProvidedVariableNames()) {
Coverage.forLoopStart(11507)
            res += AOPVariable(query, v).toSparql() + " "
Coverage.statementStart(11508)
        }
Coverage.statementStart(11509)
        res += "(" + children[1].toSparql() + " as " + name.toSparql() + "){"
Coverage.statementStart(11510)
        res += children[0].toSparql()
Coverage.statementStart(11511)
        res += "}}"
Coverage.statementStart(11512)
        return res
    }
    override fun cloneOP() = POPBind(query, projectedVariables, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPBind && name == other.name && children[0] == other.children[0]
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNamesInternal(): List<String> = (children[0].getProvidedVariableNames() + name.name).distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11513)
        val variablesOut = getProvidedVariableNames()
Coverage.statementStart(11514)
        val variablesLocal = getProvidedVariableNamesInternal()
Coverage.statementStart(11515)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11516)
        val localMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11517)
        val child = children[0].evaluate()
Coverage.statementStart(11518)
        val columnsLocal = Array(variablesLocal.size) { ColumnIteratorQueue() }
Coverage.statementStart(11519)
        var boundIndex = -1
Coverage.statementStart(11520)
        for (variableIndex in 0 until variablesLocal.size) {
Coverage.forLoopStart(11521)
            localMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
Coverage.statementStart(11522)
            if (projectedVariables.contains(variablesLocal[variableIndex])) {
Coverage.ifStart(11523)
                outMap[variablesLocal[variableIndex]] = ColumnIteratorDebug(uuid, variablesLocal[variableIndex], columnsLocal[variableIndex])
Coverage.statementStart(11524)
            }
Coverage.statementStart(11525)
            if (variablesLocal[variableIndex] == name.name) {
Coverage.ifStart(11526)
                boundIndex = variableIndex
Coverage.statementStart(11527)
            }
Coverage.statementStart(11528)
        }
Coverage.statementStart(11529)
        val columnsOut = Array(variablesOut.size) {
Coverage.statementStart(11530)
            /*return*/            localMap[variablesOut[it]] as ColumnIteratorQueue
        }
Coverage.statementStart(11531)
        val res = IteratorBundle(outMap)
Coverage.statementStart(11532)
        val expression = (children[1] as AOPBase).evaluate(IteratorBundle(localMap))
Coverage.statementStart(11533)
        SanityCheck.check { variablesLocal.size != 0 }
Coverage.statementStart(11534)
        if (variablesLocal.size == 1 && children[0].getProvidedVariableNames().size == 0) {
Coverage.ifStart(11535)
            val columnBound = ColumnIteratorRepeatValue(child.count, query.dictionary.createValue(expression()))
Coverage.statementStart(11536)
            outMap[name.name] = ColumnIteratorDebug(uuid, name.name, columnBound)
Coverage.statementStart(11537)
        } else {
Coverage.ifStart(11538)
            SanityCheck.check { boundIndex != -1 }
Coverage.statementStart(11539)
            val columnsIn = Array(variablesLocal.size) { child.columns[variablesLocal[it]] }
Coverage.statementStart(11540)
            for (variableIndex in 0 until variablesLocal.size) {
Coverage.forLoopStart(11541)
                columnsLocal[variableIndex].onEmptyQueue = {
Coverage.statementStart(11542)
                    var done = false
Coverage.statementStart(11543)
                    for (variableIndex2 in 0 until variablesLocal.size) {
Coverage.forLoopStart(11544)
                        if (boundIndex != variableIndex2) {
Coverage.ifStart(11545)
                            val value = columnsIn[variableIndex2]!!.next()
Coverage.statementStart(11546)
                            if (value == null) {
Coverage.ifStart(11547)
                                SanityCheck.check { variableIndex2 == 0 || (boundIndex == 0 && variableIndex2 == 1) }
Coverage.statementStart(11548)
                                for (variableIndex3 in 0 until variablesLocal.size) {
Coverage.forLoopStart(11549)
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
Coverage.statementStart(11550)
                                }
Coverage.statementStart(11551)
                                done = true
Coverage.statementStart(11552)
                                break
                            }
Coverage.statementStart(11553)
//point each iterator to the current value
Coverage.statementStart(11554)
                            columnsLocal[variableIndex2].tmp = value
Coverage.statementStart(11555)
                        }
Coverage.statementStart(11556)
                    }
Coverage.statementStart(11557)
                    if (!done) {
Coverage.ifStart(11558)
                        columnsLocal[boundIndex].tmp = query.dictionary.createValue(expression())
Coverage.statementStart(11559)
                        for (variableIndex2 in 0 until columnsOut.size) {
Coverage.forLoopStart(11560)
                            columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp!!)
Coverage.statementStart(11561)
                        }
Coverage.statementStart(11562)
                    }
Coverage.statementStart(11563)
                }
Coverage.statementStart(11564)
            }
Coverage.statementStart(11565)
        }
Coverage.statementStart(11566)
        return res
    }
}
