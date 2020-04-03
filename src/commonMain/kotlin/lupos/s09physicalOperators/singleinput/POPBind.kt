package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
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

class POPBind(query: Query, @JvmField val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase(query, EOperatorID.POPBindID, "POPBind", arrayOf(child, value)) {
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

    override fun cloneOP() = POPBind(query, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPBind && name == other.name && children[0] == other.children[0]
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): List<String> = (children[0].getProvidedVariableNames() + name.name).distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNames()
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val columnsIn = Array(variables.size) { child.columns[variables[it]] }
        val columnsOut = Array(variables.size) { ColumnIteratorQueue() }
        var boundIndex = -1
        for (variableIndex in 0 until variables.size) {
            outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, columnsOut[variableIndex])
            if (variables[variableIndex] == name.name) {
                boundIndex = variableIndex
            }
        }
        val res = ColumnIteratorRow(outMap)
        val expression = (children[1] as AOPBase).evaluate(res)
        if (variables.size == 0) {
            require(boundIndex == -1)
            val columnBound = ColumnIteratorRepeatValue(child.count, query.dictionary.createValue(expression()))
            outMap[name.name] = ColumnIteratorDebug(uuid, columnBound)
        } else {
            require(boundIndex != -1)
            val columnBound = ColumnIteratorQueue()
            outMap[name.name] = ColumnIteratorDebug(uuid, columnBound)
            for (variableIndex in 0 until variables.size) {
                columnsOut[variableIndex].onEmptyQueue = {
                    var done = false
                    for (variableIndex2 in 0 until variables.size) {
                        if (boundIndex != variableIndex2) {
                            val value = columnsIn[variableIndex2]!!.next()
                            if (value == null) {
                                require(variableIndex2 == 0)
                                for (variableIndex3 in 0 until variables.size) {
                                    columnsOut[variableIndex3].onEmptyQueue = columnsOut[variableIndex3]::_onEmptyQueue
                                }
                                done = true
                                break
                            }
//point each iterator to the current value
                            columnsOut[variableIndex2].tmp = value
                            columnsOut[variableIndex2].queue.add(value)
                        }
                    }
                    if (!done) {
                        columnBound.queue.add(query.dictionary.createValue(expression()))
                    }
                }
            }
        }
        return res
    }
}
