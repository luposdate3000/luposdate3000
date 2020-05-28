package lupos.s09physicalOperators.singleinput
import lupos.s00misc.Coverage
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
Coverage.funStart(11567)
        val sparql = children[0].toSparql()
Coverage.statementStart(11568)
        if (sparql.startsWith("{SELECT ")) {
Coverage.ifStart(11569)
            return "{SELECT * {" + sparql + ". FILTER (" + children[1].toSparql() + ")}}"
        }
Coverage.statementStart(11570)
        return "{SELECT * {" + sparql + " FILTER (" + children[1].toSparql() + ")}}"
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(11571)
        if (other !is POPFilter) {
Coverage.ifStart(11572)
            return false
        }
Coverage.statementStart(11573)
        if (children[0] != other.children[0]) {
Coverage.ifStart(11574)
            return false
        }
Coverage.statementStart(11575)
        if (children[1] != other.children[1]) {
Coverage.ifStart(11576)
            return false
        }
Coverage.statementStart(11577)
        return true
    }
    override fun childrenToVerifyCount() = 1
    override fun cloneOP() = POPFilter(query, projectedVariables, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames() = children[1].getRequiredVariableNamesRecoursive()
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11578)
//TODO not-equal shortcut during evaluation based on integer-ids
Coverage.statementStart(11579)
        val variables = children[0].getProvidedVariableNames()
Coverage.statementStart(11580)
        val variablesOut = getProvidedVariableNames()
Coverage.statementStart(11581)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11582)
        val localMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11583)
        val child = children[0].evaluate()
Coverage.statementStart(11584)
        val columnsIn = Array(variables.size) { child.columns[variables[it]] }
Coverage.statementStart(11585)
        val columnsLocal = Array(variables.size) { ColumnIteratorQueue() }
Coverage.statementStart(11586)
        for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(11587)
            if (projectedVariables.contains(variables[variableIndex])) {
Coverage.ifStart(11588)
                outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, variables[variableIndex], columnsLocal[variableIndex])
Coverage.statementStart(11589)
            }
Coverage.statementStart(11590)
            localMap[variables[variableIndex]] = columnsLocal[variableIndex]
Coverage.statementStart(11591)
        }
Coverage.statementStart(11592)
        val res: IteratorBundle
Coverage.statementStart(11593)
        val resLocal: IteratorBundle
Coverage.statementStart(11594)
        if (localMap.size > 0) {
Coverage.ifStart(11595)
            resLocal = IteratorBundle(localMap)
Coverage.statementStart(11596)
        } else {
Coverage.ifStart(11597)
            resLocal = IteratorBundle(0)
Coverage.statementStart(11598)
        }
Coverage.statementStart(11599)
        val columnsOut = Array(variablesOut.size) { resLocal.columns[variablesOut[it]]!! as ColumnIteratorQueue }
Coverage.statementStart(11600)
        val expression = (children[1] as AOPBase).evaluate(resLocal)
Coverage.statementStart(11601)
        if (variablesOut.size == 0) {
Coverage.ifStart(11602)
            res = IteratorBundle(0)
Coverage.statementStart(11603)
            if (variables.size == 0) {
Coverage.ifStart(11604)
                val value = expression()
Coverage.statementStart(11605)
                res.hasNext = {
Coverage.statementStart(11606)
                    /*return*/false
                }
Coverage.statementStart(11607)
                try {
Coverage.statementStart(11608)
                    if (value.toBoolean()) {
Coverage.ifStart(11609)
                        res.hasNext = child.hasNext
Coverage.statementStart(11610)
                    }
Coverage.statementStart(11611)
                } catch (e: Throwable) {
Coverage.statementStart(11612)
                }
Coverage.statementStart(11613)
            } else {
Coverage.ifStart(11614)
                res.hasNext = {
Coverage.statementStart(11615)
                    var res2 = false
Coverage.statementStart(11616)
                    var done = false
Coverage.statementStart(11617)
                    while (!done) {
Coverage.whileLoopStart(11618)
                        for (variableIndex2 in 0 until variables.size) {
Coverage.forLoopStart(11619)
                            columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
Coverage.statementStart(11620)
//point each iterator to the current value
Coverage.statementStart(11621)
                            if (columnsLocal[variableIndex2].tmp == null) {
Coverage.ifStart(11622)
                                SanityCheck.check { variableIndex2 == 0 }
Coverage.statementStart(11623)
                                for (variableIndex3 in 0 until variables.size) {
Coverage.forLoopStart(11624)
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
Coverage.statementStart(11625)
                                }
Coverage.statementStart(11626)
                                done = true
Coverage.statementStart(11627)
                                break
                            }
Coverage.statementStart(11628)
                        }
Coverage.statementStart(11629)
                        if (!done) {
Coverage.ifStart(11630)
//evaluate
Coverage.statementStart(11631)
                            val value = expression()
Coverage.statementStart(11632)
                            try {
Coverage.statementStart(11633)
                                if (value.toBoolean()) {
Coverage.ifStart(11634)
//accept/deny row in each iterator
Coverage.statementStart(11635)
                                    res2 = true
Coverage.statementStart(11636)
                                }
Coverage.statementStart(11637)
                            } catch (e: Throwable) {
Coverage.statementStart(11638)
                            }
Coverage.statementStart(11639)
                        }
Coverage.statementStart(11640)
                    }
Coverage.statementStart(11641)
/*return*/res2
                }
Coverage.statementStart(11642)
            }
Coverage.statementStart(11643)
        } else {
Coverage.ifStart(11644)
            res = IteratorBundle(outMap)
Coverage.statementStart(11645)
            for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(11646)
                columnsLocal[variableIndex].onEmptyQueue = {
Coverage.statementStart(11647)
                    var done = false
Coverage.statementStart(11648)
                    while (!done) {
Coverage.whileLoopStart(11649)
                        for (variableIndex2 in 0 until variables.size) {
Coverage.forLoopStart(11650)
                            columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
Coverage.statementStart(11651)
//point each iterator to the current value
Coverage.statementStart(11652)
                            if (columnsLocal[variableIndex2].tmp == null) {
Coverage.ifStart(11653)
                                SanityCheck.check { variableIndex2 == 0 }
Coverage.statementStart(11654)
                                for (variableIndex3 in 0 until variables.size) {
Coverage.forLoopStart(11655)
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
Coverage.statementStart(11656)
                                }
Coverage.statementStart(11657)
                                done = true
Coverage.statementStart(11658)
                                break
                            }
Coverage.statementStart(11659)
                        }
Coverage.statementStart(11660)
                        if (!done) {
Coverage.ifStart(11661)
//evaluate
Coverage.statementStart(11662)
                            val value = expression()
Coverage.statementStart(11663)
                            try {
Coverage.statementStart(11664)
                                if (value.toBoolean()) {
Coverage.ifStart(11665)
//accept/deny row in each iterator
Coverage.statementStart(11666)
                                    for (variableIndex2 in 0 until variablesOut.size) {
Coverage.forLoopStart(11667)
                                        columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp!!)
Coverage.statementStart(11668)
                                    }
Coverage.statementStart(11669)
                                    done = true
Coverage.statementStart(11670)
                                }
Coverage.statementStart(11671)
                            } catch (e: Throwable) {
Coverage.statementStart(11672)
                            }
Coverage.statementStart(11673)
                        }
Coverage.statementStart(11674)
                    }
Coverage.statementStart(11675)
                }
Coverage.statementStart(11676)
            }
Coverage.statementStart(11677)
        }
Coverage.statementStart(11678)
        return res
    }
}
