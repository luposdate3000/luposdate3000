package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LogicalOptimizerStoreToValues(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerStoreToValuesID) {
    override val classname = "LogicalOptimizerStoreToValues"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node

if(node is LOPTriple){
var constants=0
for(c in node.children){
if(c is AOPConstant){
constants++
}
}
if(constants==3){
val idx=LOPTriple.getIndex(node.children,listOf<String>())
val tmp= DistributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.children[it] as AOPBase }, idx)
val tmp2=tmp.evaluate()
require(tmp2.hasCountMode())
if(tmp2.count>0){
res=OPNothing(query)
}else{
}
}
}


/*return*/res
    })
}
