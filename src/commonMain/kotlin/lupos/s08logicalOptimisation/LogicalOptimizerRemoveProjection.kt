package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPProjection
class LogicalOptimizerRemoveProjection(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveProjectionID) {
    override val classname = "LogicalOptimizerRemoveProjection"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9848)
        var res: OPBase = node
Coverage.statementStart(9849)
        if (node is LOPProjection) {
Coverage.ifStart(9850)
            val child = node.children[0]
Coverage.statementStart(9851)
            val projection = node.getProvidedVariableNames()
Coverage.statementStart(9852)
            if (projection.containsAll(child.getProvidedVariableNames())) {
Coverage.ifStart(9853)
                onChange()
Coverage.statementStart(9854)
                res = child
Coverage.statementStart(9855)
            } else if (child is LOPTriple) {
Coverage.ifStart(9856)
                for (i in 0 until 3) {
Coverage.forLoopStart(9857)
                    val cc = child.children[i]
Coverage.statementStart(9858)
                    if (cc is AOPVariable && !projection.contains(cc.name)) {
Coverage.ifStart(9859)
                        child.children[i] = AOPVariable(query, "_")
Coverage.statementStart(9860)
                        onChange()
Coverage.statementStart(9861)
                    }
Coverage.statementStart(9862)
                }
Coverage.statementStart(9863)
            } else if (child is LOPBind) {
Coverage.ifStart(9864)
                if (!projection.contains(child.name.name)) {
Coverage.ifStart(9865)
                    res.children[0] = child.children[0]
Coverage.statementStart(9866)
                    onChange()
Coverage.statementStart(9867)
                }
Coverage.statementStart(9868)
            }
Coverage.statementStart(9869)
        }
Coverage.statementStart(9870)
        return res
    }
}
