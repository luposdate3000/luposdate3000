package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortType
import lupos.s00misc.SortHelper
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
public class LogicalOptimizerMinusAddSort(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerMinusAddSortID) {
    override val classname: String = "LogicalOptimizerMinusAddSort"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        val res: IOPBase = node
        if (node is LOPMinus) {
            if (!node.hadSortPushDown) {
                node.hadSortPushDown = true
                val provided = node.getChildren()[0].getProvidedVariableNames().intersect(node.getChildren()[1].getProvidedVariableNames())
                node.getChildren()[1] = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[1])))
                node.getChildren()[0] = LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[0]))
                onChange()
            }
        }
        return res
    }
}
