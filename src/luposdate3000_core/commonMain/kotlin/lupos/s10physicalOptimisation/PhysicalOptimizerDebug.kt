package lupos.s10physicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPDebug
import lupos.s09physicalOperators.singleinput.POPProjection

class PhysicalOptimizerDebug(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerDebugID) {
    override val classname = "PhysicalOptimizerDebug"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        var change = true
        var projectedVariables = listOf<String>()
        try {
            if (parent is LOPProjection) {
                projectedVariables = parent.getProvidedVariableNames()
            } else if (parent is POPProjection) {
                projectedVariables = parent.getProvidedVariableNamesInternal()
            } else if (node is POPBase) {
                projectedVariables = node.getProvidedVariableNamesInternal()
            } else {
                projectedVariables = node.getProvidedVariableNames()
            }
            when (node) {
                is POPDebug -> {
                    change = false
                }
                else -> {
                    change = false
                    SanityCheck {
//this code is intended to be debuggin only - even if it changes the resulting operator-graph
                        if (node is POPBase && (parent == null || (parent !is POPDebug && parent !is OPBaseCompound))) {
                            res = POPDebug(query, node.projectedVariables, node)
                            change = true
                        }
                    }
                }
            }
        } finally {
            if (change) {
                SanityCheck {
                    val tmp = node.getMySortPriority().map { it.variableName }
                    SanityCheck.check { (!projectedVariables.containsAll(tmp)) || (projectedVariables.containsAll(tmp) && res.getProvidedVariableNames().containsAll(tmp)) }
                }
                res.setMySortPriority ( node.getMySortPriority())
                res.setSortPriorities ( node.getSortPriorities())
                onChange()
            }
        }
        return res
    }
}
