package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
public class LogicalOptimizerFilterOptionalStep2(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterOptionalStep2ID) {
    override val classname: String = "LogicalOptimizerFilterOptionalStep2"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        query.filtersMovedUpFromOptionals = true
        node.syntaxVerifyAllVariableExists(listOf(), true)
        return node
    }
}
