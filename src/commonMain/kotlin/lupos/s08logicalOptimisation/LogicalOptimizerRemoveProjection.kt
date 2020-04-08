package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.noinput.*

class LogicalOptimizerRemoveProjection(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveProjectionID) {
    override val classname = "LogicalOptimizerRemoveProjection"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPProjection) {
            val child = node.children[0]
val projection=node.getProvidedVariableNames()
            if (projection.containsAll(child.getProvidedVariableNames())) {
                onChange()
                res = child
            }else if(child is LOPTriple){
		for(i in 0 until 3){
			val cc=child.children[i]
			if(cc is AOPVariable && !projection.contains(cc.name)){
				child.children[i]=AOPVariable(query,"_")
				onChange()
			}
		}
	    }else if (child is LOPBind){
		if(!projection.contains(child.name.name)){
			res.children[0]=child.children[0]
			onChange()
		}
	    }
        }
/*return*/res
    })
}
