package lupos.s3logicalOptimisation

import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s2buildOperatorGraph.data.LOPTriple
import lupos.s2buildOperatorGraph.data.LOPValues
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.multiinput.LOPJoin
import lupos.s2buildOperatorGraph.multiinput.LOPMinus
import lupos.s2buildOperatorGraph.multiinput.LOPUnion
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.singleinput.modifiers.*
import lupos.s2buildOperatorGraph.OPBase

abstract class OptimizerVisitorLOP {
	open fun visit(node:OPBase):OPBase{
		 throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} ${node::class.simpleName}")
	}
	open fun visit(node:LOPNOOP):OPBase{
		return LOPNOOP(visit(node.child))
	}
	open fun visit(node:LOPBind):OPBase{
		return LOPBind(visit(node.name),visit(node.expression),visit(node.child))
	}
	open fun visit(node:LOPFilter):OPBase{
		return LOPFilter(visit(node.filter) as LOPExpression,visit(node.child))
	}
	open fun visit(node:LOPGroup):OPBase{
		return LOPGroup(visit(node.by)as LOPVariable,visit(node.child))
	}
	open fun visit(node:LOPMakeBooleanResult):OPBase{
		return LOPMakeBooleanResult(visit(node.child))
	}
	open fun visit(node:LOPOptional):OPBase{
		return LOPOptional(visit(node.child))
	}
	open fun visit(node:LOPProjection):OPBase{		
		return LOPProjection(node.variables,visit(node.child))
	}
	open fun visit(node:LOPRename):OPBase{
		return LOPRename(visit(node.nameTo),visit(node.nameFrom),visit(node.child))
	}
	open fun visit(node:LOPSort):OPBase{
		return LOPSort(node.asc,visit(node.by),visit(node.child))
	}
	open fun visit(node:LOPDistinct):OPBase{
		return LOPDistinct(visit(node.child))
	}
	open fun visit(node:LOPLimit):OPBase{
		return LOPLimit(node.limit,visit(node.child))
	}
	open fun visit(node:LOPOffset):OPBase{
		return LOPOffset(node.offset,visit(node.child))
	}
	open fun visit(node:LOPPrefix):OPBase{
		return LOPPrefix(node.name,node.iri,visit(node.child))
	}
	open fun visit(node:LOPReduced):OPBase{
		return LOPReduced(visit(node.child))
	}
	open fun visit(node:LOPJoin):OPBase{
		return LOPJoin(visit(node.child),visit(node.second),node.optional)
	}
	open fun visit(node:LOPMinus):OPBase{
		return LOPMinus(visit(node.child),visit(node.second))
	}
	open fun visit(node:LOPUnion):OPBase{
		return LOPUnion(visit(node.child),visit(node.second))
	}
	open fun visit(node:LOPExpression):OPBase{
		return LOPExpression(node.child)
	}
	open fun visit(node:LOPTriple):OPBase{
		return LOPTriple(visit(node.s),visit(node.p),visit(node.o))
	}
	open fun visit(node:LOPValues):OPBase{
		val values = mutableListOf<LOPExpression>()
		node.values.forEach(){
			values.add(visit(it)as LOPExpression)
		}
		return LOPValues(node.variables,values)
	}
	open fun visit(node:LOPVariable):OPBase{
		return LOPVariable(node.name)
	}
}
