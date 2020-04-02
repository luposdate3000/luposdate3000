package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter

class LogicalOptimizerFilterIntoTriple(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterIntoTripleID) {
    override val classname = "LogicalOptimizerFilterIntoTriple"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val loptriple = node.children[0]
            val aopcompare = node.children[1]
            if (loptriple is LOPTriple && aopcompare is AOPEQ) {
                for (c in 0 until 2) {
                    val compareVar = aopcompare.children[1 - c]
                    val compareVal = aopcompare.children[c]
                    if (compareVal is AOPConstant && compareVar is AOPVariable) {
                        for (i in 0 until 3) {
                            val tmp = loptriple.children[i]
                            if (tmp is AOPVariable) {
                                if (tmp.name == compareVar.name) {
                                    onChange()
                                    loptriple.children[i] = compareVal
                                    res = LOPBind(query, compareVar, compareVal, loptriple)
                                }
                            }
                        }
                    }
                }
            }
        }
/*return*/res
    })
}
