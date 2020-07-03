package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.NotImplementedException
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

    override fun equals(other: Any?) = other is POPFilter && children[0] == other.children[0] && children[1] == other.children[1]
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
       SanityCheck.println("POPFilterXXX$uuid open A $classname")
        val child = children[0].evaluate()
        var res: IteratorBundle? = null
        try {
            val columnsIn = Array(variables.size) { child.columns[variables[it]] }
            val columnsLocal = Array(variables.size) { ColumnIteratorQueue() }
            for (variableIndex in 0 until variables.size) {
                if (projectedVariables.contains(variables[variableIndex])) {
                    outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, variables[variableIndex], columnsLocal[variableIndex])
                }
                localMap[variables[variableIndex]] = columnsLocal[variableIndex]
            }
            val resLocal: IteratorBundle
            if (localMap.size > 0) {
                resLocal = IteratorBundle(localMap)
            } else {
                resLocal = IteratorBundle(0)
            }
            val columnsOut = Array(variablesOut.size) { resLocal.columns[variablesOut[it]]!! as ColumnIteratorQueue }
            val expression = (children[1] as AOPBase).evaluateAsBoolean(resLocal)
            if (variablesOut.size == 0) {
                res = IteratorBundle(0)
                if (variables.size == 0) {
                    if (expression()) {
                        res.hasNext2 = child.hasNext2
                        res.hasNext2Close = {
                           SanityCheck.println("POPFilterXXX$uuid close A $classname")
                            child.hasNext2Close()
                        }
                    } else {
                        res.hasNext2 = {
                            /*return*/false
                        }
                    }
                } else {
                    res.hasNext2Close = {
                       SanityCheck.println("POPFilterXXX$uuid close B $classname")
                        for ((k, v) in child.columns) {
                            v.close()
                        }
                    }
                    res.hasNext2 = {
                        var res2 = false
                        try {
                            var done = false
                            while (!done) {
                                for (variableIndex2 in 0 until variables.size) {
                                    columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
                                    //point each iterator to the current value
                                    if (columnsLocal[variableIndex2].tmp == null) {
                                       SanityCheck.println("POPFilterXXX$uuid close C $classname")
                                        for ((k, v) in child.columns) {
                                            v.close()
                                        }
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
                                    if (expression()) {
                                        //accept/deny row in each iterator
                                        res2 = true
                                    }
                                }
                            }
                        } catch (e: NotImplementedException) {
                           SanityCheck.println("filter caught notimplemented and closed its childs")
                           SanityCheck.println("POPFilterXXX$uuid close D $classname")
                            for ((k, v) in child.columns) {
                                v.close()
                            }
                            throw e
                        }
/*return*/res2
                    }
                }
            } else {
                res = IteratorBundle(outMap)
                for (variableIndex in 0 until variables.size) {
columnsLocal[variableIndex].close={
columnsLocal[variableIndex]._close()
println("POPFilterXXX$uuid close E $classname")
                                        for ((k, v) in child.columns) {
                                            v.close()
                                        }
}
                    columnsLocal[variableIndex].onEmptyQueue = {
                        try {
                            var done = false
                            while (!done) {
                                for (variableIndex2 in 0 until variables.size) {
                                    columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
                                    //point each iterator to the current value
                                    if (columnsLocal[variableIndex2].tmp == null) {
                                        SanityCheck.check { variableIndex2 == 0 }
                                       SanityCheck.println("POPFilterXXX$uuid close F $classname")
                                        for ((k, v) in child.columns) {
                                            v.close()
                                        }
                                        for (variableIndex3 in 0 until variables.size) {
                                            columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
                                        }
                                        done = true
                                        break
                                    }
                                }
                                if (!done) {
                                    //evaluate
                                    if (expression()) {
                                        //accept/deny row in each iterator
                                        for (variableIndex2 in 0 until variablesOut.size) {
                                            columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp!!)
                                        }
                                        done = true
                                    }
                                }
                            }
                        } catch (e: NotImplementedException) {
                           SanityCheck.println("POPFilterXXX$uuid close G $classname")
                           SanityCheck.println("filter caught notimplemented and closed its childs")
                            for ((k, v) in child.columns) {
                                v.close()
                            }
                            throw e
                        }
                    }
                }
            }
        } catch (e: NotImplementedException) {
           SanityCheck.println("POPFilterXXX$uuid close H $classname")
           SanityCheck.println("filter caught notimplemented and closed its childs")
            for ((k, v) in child.columns) {
                v.close()
            }
            throw e
        }
        return res!!
    }
}
