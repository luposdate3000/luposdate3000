package lupos.s08logicalOptimisation

import lupos.s00misc.EOperatorID
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerFilterIntoTriple(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerFilterIntoTripleID) {
    override val classname = "LogicalOptimizerFilterIntoTriple"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        val lopfilter = node
        if (lopfilter is LOPFilter) {
            val loptriple = lopfilter.children[0]
            val aopcompare = lopfilter.children[1]
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
                                    require(false)/*TODO check this*/
                                    loptriple.children[i] = compareVal
                                    res = loptriple
                                }
                            }
                        }
                    }
                }
            }
        }
        res
    })
}
