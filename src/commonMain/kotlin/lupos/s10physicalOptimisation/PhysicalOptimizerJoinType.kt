package lupos.s10physicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPJoinCartesianProduct
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPJoinMergeOptional
import lupos.s09physicalOperators.multiinput.POPJoinMergeSingleColumn
import lupos.s09physicalOperators.multiinput.POPJoinWithStore
import lupos.s09physicalOperators.multiinput.POPJoinWithStoreExists
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
class PhysicalOptimizerJoinType(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerJoinTypeID) {
    override val classname = "PhysicalOptimizerJoinType"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(12161)
        var res = node
Coverage.statementStart(12162)
        val projectedVariables: List<String>
Coverage.statementStart(12163)
        if (parent is LOPProjection) {
Coverage.ifStart(12164)
            projectedVariables = parent.getProvidedVariableNames()
Coverage.statementStart(12165)
        } else if (parent is POPProjection) {
Coverage.ifStart(12166)
            projectedVariables = parent.getProvidedVariableNamesInternal()
Coverage.statementStart(12167)
        } else if (node is POPBase) {
Coverage.ifStart(12168)
            projectedVariables = node.getProvidedVariableNamesInternal()
Coverage.statementStart(12169)
        } else {
Coverage.ifStart(12170)
            projectedVariables = node.getProvidedVariableNames()
Coverage.statementStart(12171)
        }
Coverage.statementStart(12172)
        if (node is LOPJoin) {
Coverage.ifStart(12173)
            val childA = node.children[0]
Coverage.statementStart(12174)
            val childB = node.children[1]
Coverage.statementStart(12175)
            val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
Coverage.statementStart(12176)
            if (columns[0].size == 0) {
Coverage.ifStart(12177)
                /*cartesian product*/
Coverage.statementStart(12178)
                res = POPJoinCartesianProduct(query, projectedVariables, childA, childB, false)
Coverage.statementStart(12179)
            } else {
Coverage.ifStart(12180)
                if (node.mySortPriority.size >= columns[0].size) {
Coverage.ifStart(12181)
                    if (projectedVariables.size == 1 && childA.getProvidedVariableNames().size == 1 && childB.getProvidedVariableNames().size == 1 && childA.getProvidedVariableNames()[0] == projectedVariables[0] && childB.getProvidedVariableNames()[0] == projectedVariables[0]) {
Coverage.ifStart(12182)
                        if (node.optional) {
Coverage.ifStart(12183)
                            res = POPJoinMergeOptional(query, projectedVariables, childA, childB, true)
Coverage.statementStart(12184)
                        } else {
Coverage.ifStart(12185)
                            res = POPJoinMergeSingleColumn(query, projectedVariables, childA, childB, false)
Coverage.statementStart(12186)
                        }
Coverage.statementStart(12187)
                    } else {
Coverage.ifStart(12188)
                        var flag = true
Coverage.statementStart(12189)
                        for (i in 0 until columns[0].size) {
Coverage.forLoopStart(12190)
                            if ((childA.mySortPriority.size > i && childA.mySortPriority[i] != node.mySortPriority[i]) || (childB.mySortPriority.size > i && childB.mySortPriority[i] != node.mySortPriority[i])) {
Coverage.ifStart(12191)
                                flag = false
Coverage.statementStart(12192)
                                break
                            }
Coverage.statementStart(12193)
                        }
Coverage.statementStart(12194)
                        if (flag) {
Coverage.ifStart(12195)
                            if (childA.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
Coverage.ifStart(12196)
                                if (node.optional) {
Coverage.ifStart(12197)
                                    res = POPJoinMergeOptional(query, projectedVariables, childA, childB, true)
Coverage.statementStart(12198)
                                } else {
Coverage.ifStart(12199)
                                    res = POPJoinMerge(query, projectedVariables, childA, childB, false)
Coverage.statementStart(12200)
                                }
Coverage.statementStart(12201)
                            } else {
Coverage.ifStart(12202)
                                if (node.optional) {
Coverage.ifStart(12203)
                                    res = POPJoinMergeOptional(query, projectedVariables, childA, childB, true)
Coverage.statementStart(12204)
                                } else {
Coverage.ifStart(12205)
                                    res = POPJoinMerge(query, projectedVariables, childB, childA, false)
Coverage.statementStart(12206)
                                }
Coverage.statementStart(12207)
                            }
Coverage.statementStart(12208)
                        }
Coverage.statementStart(12209)
                    }
Coverage.statementStart(12210)
                }
Coverage.statementStart(12211)
                if (res is LOPJoin) {
Coverage.ifStart(12212)
                    if (node.optional) {
Coverage.ifStart(12213)
                        res = POPJoinHashMap(query, projectedVariables, childA, childB, true)
Coverage.statementStart(12214)
                    } else if (node.partOfAskQuery && projectedVariables.size == 0 && childA is LOPTriple) {
Coverage.ifStart(12215)
                        res = POPJoinWithStoreExists(query, projectedVariables, childB, childA, false)
Coverage.statementStart(12216)
                    } else if (node.partOfAskQuery && projectedVariables.size == 0 && childB is LOPTriple) {
Coverage.ifStart(12217)
                        res = POPJoinWithStoreExists(query, projectedVariables, childA, childB, false)
Coverage.statementStart(12218)
                    } else if (node.partOfAskQuery && childA is LOPTriple && columns[1].size > 0 && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
Coverage.ifStart(12219)
                        res = POPJoinWithStore(query, projectedVariables, childB, childA, false)
Coverage.statementStart(12220)
                    } else if (node.partOfAskQuery && childB is LOPTriple && columns[2].size > 0 && childA.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
Coverage.ifStart(12221)
                        res = POPJoinWithStore(query, projectedVariables, childA, childB, false)
Coverage.statementStart(12222)
                    } else if (childA is TripleStoreIteratorGlobal || childA is LOPTriple && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
Coverage.ifStart(12223)
                        res = POPJoinHashMap(query, projectedVariables, childB, childA, false)
Coverage.statementStart(12224)
                    } else {
Coverage.ifStart(12225)
                        res = POPJoinHashMap(query, projectedVariables, childA, childB, false)
Coverage.statementStart(12226)
                    }
Coverage.statementStart(12227)
                }
Coverage.statementStart(12228)
            }
Coverage.statementStart(12229)
            SanityCheck { res.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName }) }
Coverage.statementStart(12230)
            res.mySortPriority = node.mySortPriority
Coverage.statementStart(12231)
            res.sortPriorities = node.sortPriorities
Coverage.statementStart(12232)
            onChange()
Coverage.statementStart(12233)
        }
Coverage.statementStart(12234)
        return res
    }
}
