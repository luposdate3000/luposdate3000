package lupos.s09physicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPFilter(query: Query, projectedVariables: List<String>, filter: AOPBase, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPFilterID, "POPFilter", arrayOf(child, filter), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return "{SELECT * {" + sparql + ". FILTER (" + children[1].toSparql() + ")}}"
        }
        return "{SELECT * {" + sparql + " FILTER (" + children[1].toSparql() + ")}}"
    }

    override fun equals(other: Any?): Boolean = other is POPFilter && children[0] == other.children[0] && children[1] == other.children[1]
    override fun childrenToVerifyCount() = 1
    override fun cloneOP() = POPFilter(query, projectedVariables, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames() = children[1].getRequiredVariableNamesRecoursive()
    override suspend fun evaluate(): IteratorBundle {
//TODO not-equal shortcut during evaluation based on integer-ids
        val variables = children[0].getProvidedVariableNames()
        val variablesOut = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val columnsIn = Array(variables.size) { child.columns[variables[it]] }
        val columnsLocal = Array(variables.size) { ColumnIteratorQueue() }
        for (variableIndex in 0 until variables.size) {
            outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, variables[variableIndex], columnsLocal[variableIndex])
            localMap[variables[variableIndex]] = columnsLocal[variableIndex]
        }
        val res: IteratorBundle
        val resLocal: IteratorBundle
        if (localMap.size > 0) {
            resLocal = IteratorBundle(localMap)
        } else {
            resLocal = IteratorBundle(0)
        }
        val columnsOut = Array(variablesOut.size) { resLocal.columns[variablesOut[it]]!! as ColumnIteratorQueue }
        val expression = (children[1] as AOPBase).evaluate(resLocal)
        if (variablesOut.size == 0) {
            res = IteratorBundle(0)
            if (variables.size == 0) {
                val value = expression()
                res.hasNext = {
                    /*return*/false
                }
                try {
                    if (value.toBoolean()) {
                        res.hasNext = child.hasNext
                    }
                } catch (e: Throwable) {
                }
            } else {
                res.hasNext = {
                    var res2 = false
                    var done = false
                    while (!done) {
                        for (variableIndex2 in 0 until variables.size) {
                            columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
//point each iterator to the current value
                            if (columnsLocal[variableIndex2].tmp == null) {
                                SanityCheck.check { variableIndex2 == 0 }
                                for (variableIndex3 in 0 until variables.size) {
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
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
                                    res2 = true
                                }
                            } catch (e: Throwable) {
                            }
                        }
                    }
/*return*/res2
                }
            }
        } else {
            res = IteratorBundle(outMap)
            for (variableIndex in 0 until variables.size) {
                columnsLocal[variableIndex].onEmptyQueue = {
                    var done = false
                    while (!done) {
                        for (variableIndex2 in 0 until variables.size) {
                            columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
//point each iterator to the current value
                            if (columnsLocal[variableIndex2].tmp == null) {
                                SanityCheck.check { variableIndex2 == 0 }
                                for (variableIndex3 in 0 until variables.size) {
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
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
                                    for (variableIndex2 in 0 until variablesOut.size) {
                                        columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp!!)
                                    }
                                    done = true
                                }
                            } catch (e: Throwable) {
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}
