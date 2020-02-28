package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s04arithmetikOperators.AOPBase


class LogicalOptimizerFilterDown(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerFilterDownID) {
    override val classname = "LogicalOptimizerFilterDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val c = node.children[0]
            if (c.children.size == 1) {
                val cc = c.children[0]
                if (cc.getProvidedVariableNames().containsAll(node.getRequiredVariableNames())) {
                    c.children[0] = node
                    node.children[0] = cc
                    onChange()
                    res = c
                }
            }else {
		var moved=false
		for(ci in c.children.indices){
val cc=c.children[ci]
			if (cc.getProvidedVariableNames().containsAll(node.getRequiredVariableNames())) {
      	                    c.children[ci] = LOPFilter(node.children[1]as AOPBase,c.children[ci])
				moved=true
	                }
		}
		if(moved)
			res=c
	    }
        }
        res
    })
}
