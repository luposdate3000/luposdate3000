package lupos.s08logicalOptimisation
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerFilterUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterUpID) {
    override val classname = "LogicalOptimizerFilterUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPUnion) {
            for (idx in 0 until node.children.size) {
                val child = node.children[idx]
                if (child is LOPFilter && node.children[1 - idx].getProvidedVariableNames().containsAll(child.children[1].getRequiredVariableNamesRecoursive())) {
                    res = child
                    node.children[idx] = res.children[0]
                    res.children[0] = node
                    onChange()
                    break
                }
            }
        } else if (node !is LOPFilter) {
            for (idx in 0 until node.children.size) {
                val child = node.children[idx]
                if (child is LOPFilter && node.getProvidedVariableNames().containsAll(child.children[1].getRequiredVariableNamesRecoursive())) {
                    res = child
                    node.children[idx] = res.children[0]
                    res.children[0] = node
                    onChange()
                    break
                }
            }
        }
/*return*/ res
    })
}
