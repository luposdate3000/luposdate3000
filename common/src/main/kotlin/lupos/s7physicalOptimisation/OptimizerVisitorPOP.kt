package lupos.s7physicalOptimisation

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s3logicalOptimisation.OptimizerVisitorLOP
import lupos.s5physicalOperators.*
import lupos.s5physicalOperators.singleinput.*
import lupos.s6tripleStore.POPTripleStoreIteratorBase

open abstract class OptimizerVisitorPOP():OptimizerVisitorLOP(){
	open fun visit(node:POPFilterExact):OPBase{
		return POPFilterExact(visit(node.variable)as LOPVariable,node.value,visit(node.child)as POPBase)
	}
	open fun visit(node:POPProjection):OPBase{
		return POPProjection(node.variables,visit(node.child)as POPBase)
	}
	open fun visit(node:POPRename):OPBase{
		return POPRename(visit(node.nameTo)as LOPVariable,visit(node.nameFrom)as LOPVariable,visit(node.child)as POPBase)
	}
	open fun visit(node:POPTripleStoreIteratorBase):OPBase{
		return node
	}
}
