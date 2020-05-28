package lupos.s13keyDistributionOptimizer
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s12p2p.POPServiceIRI
class KeyDistributionOptimizer(query: Query) : OptimizerBase(query, EOptimizerID.KeyDistributionOptimizerID) {
    override val classname = "KeyDistributionOptimizer"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(12715)
        var res = node
Coverage.statementStart(12716)
        if (node is LOPServiceIRI) {
Coverage.ifStart(12717)
            val projectedVariables: List<String>
Coverage.statementStart(12718)
            if (parent is LOPProjection) {
Coverage.ifStart(12719)
                projectedVariables = parent.getProvidedVariableNames()
Coverage.statementStart(12720)
            } else if (parent is POPProjection) {
Coverage.ifStart(12721)
                projectedVariables = parent.getProvidedVariableNamesInternal()
Coverage.statementStart(12722)
            } else if (node is POPBase) {
Coverage.ifStart(12723)
                projectedVariables = node.getProvidedVariableNamesInternal()
Coverage.statementStart(12724)
            } else {
Coverage.ifStart(12725)
                projectedVariables = node.getProvidedVariableNames()
Coverage.statementStart(12726)
            }
Coverage.statementStart(12727)
            onChange()
Coverage.statementStart(12728)
            res = POPServiceIRI(query, projectedVariables, node.name, node.silent, optimizeInternal(node.children[0], null, onChange))
Coverage.statementStart(12729)
        }
Coverage.statementStart(12730)
        return res
    }
}
