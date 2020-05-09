package lupos.s10physicalOptimisation
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s15tripleStoreDistributed.*
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class PhysicalOptimizerJoinType(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerJoinTypeID) {
    override val classname = "PhysicalOptimizerJoinType"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        val projectedVariables: List<String>
        if (parent is LOPProjection) {
            projectedVariables = parent.getProvidedVariableNames()
        } else if (parent is POPProjection) {
            projectedVariables = parent.getProvidedVariableNamesInternal()
        } else if (node is POPBase) {
            projectedVariables = node.getProvidedVariableNamesInternal()
        } else {
            projectedVariables = node.getProvidedVariableNames()
        }
        if (node is LOPJoin) {
            val childA = node.children[0]
            val childB = node.children[1]
            val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
            if (columns[0].size == 0) {
                /*cartesian product*/
                res = POPJoinHashMap(query, projectedVariables, childA, childB, false)
            } else {
                if (node.optional) {
                    if ((childA is TripleStoreIteratorGlobal || childA is LOPTriple) && childB.getProvidedVariableNames().containsAll(node.mySortPriority)) {
                        res = POPJoinHashMap(query, projectedVariables, childB, childA, true)
                    } else {
                        res = POPJoinHashMap(query, projectedVariables, childA, childB, true)
                    }
                } else {
                    if (node.mySortPriority.size >= columns[0].size) {
                        if (projectedVariables.size == 1 && childA.getProvidedVariableNames().size == 1 && childB.getProvidedVariableNames().size == 1 && childA.getProvidedVariableNames()[0] == projectedVariables[0] && childB.getProvidedVariableNames()[0] == projectedVariables[0]) {
                            res = POPJoinMergeSingleColumn(query, projectedVariables, childA, childB, false)
                        } else {
                            var flag = true
                            for (i in 0 until columns[0].size) {
                                if (childA.mySortPriority[i] != node.mySortPriority[i] || childB.mySortPriority[i] != node.mySortPriority[i]) {
                                    flag = false
                                    break
                                }
                            }
                            if (flag) {
                                if (childA.getProvidedVariableNames().containsAll(node.mySortPriority)) {
                                    res = POPJoinMerge(query, projectedVariables, childA, childB, false)
                                } else {
                                    res = POPJoinMerge(query, projectedVariables, childB, childA, false)
                                }
                            }
                        }
                    }
                    if (res is LOPJoin) {
                        if (childA is LOPTriple && columns[1].size > 0 && childB.getProvidedVariableNames().containsAll(node.mySortPriority)) {
                            res = POPJoinWithStore(query, projectedVariables, childB, childA, false)
                        } else if (childB is LOPTriple && columns[2].size > 0 && childA.getProvidedVariableNames().containsAll(node.mySortPriority)) {
                            res = POPJoinWithStore(query, projectedVariables, childA, childB, false)
                        } else if (childA is TripleStoreIteratorGlobal || childA is LOPTriple && childB.getProvidedVariableNames().containsAll(node.mySortPriority)) {
                            res = POPJoinHashMap(query, projectedVariables, childB, childA, false)
                        } else {
                            res = POPJoinHashMap(query, projectedVariables, childA, childB, false)
                        }
                    }
                }
            }
            res.mySortPriority = node.mySortPriority
            res.sortPriorities = node.sortPriorities
        }
/*return*/ res
    })
}
