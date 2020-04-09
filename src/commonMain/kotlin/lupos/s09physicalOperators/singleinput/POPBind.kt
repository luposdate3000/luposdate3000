package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPBind(query: Query, projectedVariables: List<String>, @JvmField val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPBindID, "POPBind", arrayOf(child, value), ESortPriority.BIND) {
    override fun toSparql(): String {
        if (children[1] is AOPConstant && (children[1] as AOPConstant).value is ValueUndef) {
            return children[0].toSparql()
        }
        var res = "{SELECT "
        for (v in children[0].getProvidedVariableNames()) {
            res += AOPVariable(query, v).toSparql() + " "
        }
        res += "(" + children[1].toSparql() + " as " + name.toSparql() + "){"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP() = POPBind(query, projectedVariables, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPBind && name == other.name && children[0] == other.children[0]
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNamesInternal(): List<String> = (children[0].getProvidedVariableNames() + name.name).distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
    override suspend fun evaluate(): ColumnIteratorRow {
        val variablesOut = getProvidedVariableNames()
        val variablesLocal = getProvidedVariableNamesInternal()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val columnsIn = Array(variablesLocal.size) { child.columns[variablesLocal[it]] }
        val columnsLocal = Array(variablesLocal.size) { ColumnIteratorQueue() }
        var boundIndex = -1
        for (variableIndex in 0 until variablesLocal.size) {
            localMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            outMap[variablesLocal[variableIndex]] = ColumnIteratorDebug(uuid, variablesLocal[variableIndex], columnsLocal[variableIndex])
            if (variablesLocal[variableIndex] == name.name) {
                boundIndex = variableIndex
            }
        }
        val columnsOut = Array(variablesOut.size) {
            /*return*/            localMap[variablesOut[it]] as ColumnIteratorQueue
        }
        val res = ColumnIteratorRow(outMap)
        val expression = (children[1] as AOPBase).evaluate(ColumnIteratorRow(localMap))
        require(variablesLocal.size != 0)
        if (variablesLocal.size == 1 && children[0].getProvidedVariableNames().size == 0) {
            val columnBound = ColumnIteratorRepeatValue(child.count, query.dictionary.createValue(expression()))
            outMap[name.name] = ColumnIteratorDebug(uuid, name.name, columnBound)
        } else {
            require(boundIndex != -1)
            for (variableIndex in 0 until variablesLocal.size) {
                columnsLocal[variableIndex].onEmptyQueue = {
                    var done = false
                    for (variableIndex2 in 0 until variablesLocal.size) {
                        if (boundIndex != variableIndex2) {
                            val value = columnsIn[variableIndex2]!!.next()
                            if (value == null) {
                                require(variableIndex2 == 0 || (boundIndex == 0 && variableIndex2 == 1), { "${variablesLocal[variableIndex2]} $variableIndex2 $boundIndex ${name.name} $uuid ${variablesLocal.map { it }}" })
                                for (variableIndex3 in 0 until variablesLocal.size) {
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
                                }
                                done = true
                                break
                            }
//point each iterator to the current value
                            columnsLocal[variableIndex2].tmp = value
                        }
                    }
                    if (!done) {
                        columnsLocal[boundIndex].tmp = query.dictionary.createValue(expression())
                        for (variableIndex2 in 0 until columnsOut.size) {
                            columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp!!)
                        }
                    }
                }
            }
        }
        return res
    }
}
