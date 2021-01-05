package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
public class LogicalOptimizerFilterIntoTriple(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterIntoTripleID) {
    override val classname: String = "LogicalOptimizerFilterIntoTriple"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter) {
            val loptriple = node.getChildren()[0]
            val aopcompare = node.getChildren()[1]
            if (loptriple is LOPTriple && aopcompare is AOPEQ) {
                for (c in 0 until 2) {
                    val compareVar = aopcompare.getChildren()[1 - c]
                    val compareVal = aopcompare.getChildren()[c]
                    if (compareVal is AOPConstant && compareVar is AOPVariable) {
                        for (i in 0 until 3) {
                            val tmp = loptriple.getChildren()[i]
                            if (tmp is AOPVariable) {
                                if (tmp.name == compareVar.name) {
                                    onChange()
                                    loptriple.getChildren()[i] = compareVal
                                    res = LOPBind(query, compareVar, compareVal, loptriple)
                                }
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}
