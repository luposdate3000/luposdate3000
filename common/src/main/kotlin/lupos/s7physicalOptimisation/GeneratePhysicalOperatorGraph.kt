package lupos.s7physicalOptimisation

import lupos.s2buildOperatorGraph.*
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.multiinput.*
import lupos.s2buildOperatorGraph.data.*
import lupos.s5physicalOperators.*
import lupos.s5physicalOperators.singleinput.*
import lupos.s6tripleStore.*

fun transformToPhysicalOperators(graph:OPBase):POPBase{
	when(graph){
		is LOPSingleInputBase ->
			when(graph){
			is LOPNOOP ->
				return transformToPhysicalOperators(graph.child)
			is LOPProjection ->
				return POPProjection(graph.variables,transformToPhysicalOperators(graph.child))
			else -> throw UnsupportedOperationException("UnsupportedOperationException physical optimisation ${graph::class.simpleName}")
		}
		else -> throw UnsupportedOperationException("UnsupportedOperationException physical optimisation ${graph::class.simpleName}")
	}
}
