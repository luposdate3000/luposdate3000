package lupos.s10physicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPDebug
public class PhysicalOptimizerDebug(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerDebugID) {
    override val classname: String = "PhysicalOptimizerDebug"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            !is POPDebug -> {
                SanityCheck {
                    // this code is intended to be debugging only - even if it changes the resulting operator-graph
                    if (node is POPBase && (parent == null || (parent !is POPDebug && parent !is OPBaseCompound))) {
                        res = POPDebug(query, node.projectedVariables, node)
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
