package lupos.s09physicalOperators.singleinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*

import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPFilter(query: Query, filter: AOPBase, child: OPBase) : POPBase(query, EOperatorID.POPFilterID, "POPFilter", child.resultSet, arrayOf(child, filter)) {
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return "{SELECT * {" + sparql + ". FILTER (" + children[1].toSparql() + ")}}"
        return "{SELECT * {" + sparql + " FILTER (" + children[1].toSparql() + ")}}"
    }

    override fun equals(other: Any?): Boolean = other is POPFilter && children[0] == other.children[0] && children[1] == other.children[1]
    override fun childrenToVerifyCount() = 1
    override fun cloneOP() = POPFilter(query, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun getProvidedVariableNames() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames() = children[1].getRequiredVariableNamesRecoursive()
    override suspend fun evaluate(): ColumnIteratorRow {
//TODO not-equal shortcut during evaluation based on integer-ids
        val variables = getProvidedVariableNames()
        var count = 0
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val columnsIn = Array(variables.size) { child.columns[variables[it]] }
        val columnsOut = Array(variables.size) { ColumnIteratorQueue() }
        for (variableIndex in 0 until variables) {
            outMap[variables[variableIndex]] = columnsOut[variableIndex]
        }
        val res = ColumnIteratorRow(outMap)
        val expression = (children[1] as AOPBase).evaluate(res)
        for (variableIndex in 0 until variables) {
            columnsOut[variableIndex].onEmptyQueue = {
                var done = false
                while (!done) {
                    for (variableIndex2 in 0 until variables.size) {
                        columnsOut[variableIndex2].tmp = columnsIn[variableIndex2].next()
//point each iterator to the current value
                        if (columnsOut[variableIndex2].tmp == null) {
                            require(variableIndex2 == 0)
                            for (variableIndex3 in 0 until variables.size) {
                                columnsOut[variableIndex3].onEmptyQueue = columnsOut[variableIndex3]._onEmptyQueue
                            }
                            done = true
                            break
                        }
                    }
                    if (!done) {
//evaluate
                        val value = expression()
                        try {
                            if (value.toBoolean()) {
//accept/deny row in each iterator
                                for (variableIndex2 in 0 until variables.size) {
                                    columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp)
                                }
                                done = true
                            }
                        } catch (e: Throwable) {
                        }
                    }
                }
            }
        }
        return res
    }
}
