package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.singleinput.LOPBind
public class LogicalOptimizerDetectMinusStep2(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDetectMinusStep2ID) {
    override val classname: String = "LogicalOptimizerDetectMinusStep2"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPMinus) {
            val tmp = node.tmpFakeVariables.toMutableSet()
            tmp.removeAll(node.getChildren()[0].getProvidedVariableNames())
            if (tmp.size > 0) {
                for (v in tmp) {
                    res = LOPBind(query, AOPVariable(query, v), AOPConstant(query, ValueUndef()), res)
                }
                onChange()
                node.tmpFakeVariables = listOf()
            }
        }
        return res
    }
}
