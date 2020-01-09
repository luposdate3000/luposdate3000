package lupos.s3logicalOptimisation

import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s2buildOperatorGraph.*
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.singleinput.modifiers.*
import lupos.s2buildOperatorGraph.multiinput.*
import lupos.s2buildOperatorGraph.data.*

class LogicalOptimizer() {
    fun optimize(graph: OPBase): OPBase {
        when (graph) {
            is LOPNOOP -> {
                return optimize(graph.child)
            }
            is LOPJoin -> {
                return LOPJoin(optimize(graph.child), optimize(graph.second), graph.optional)
            }
            is LOPMinus -> {
                return LOPMinus(optimize(graph.child), optimize(graph.second))
            }
            is LOPUnion -> {
                return LOPUnion(optimize(graph.child), optimize(graph.second))
            }
            is LOPTriple -> {
                return graph
            }
            is LOPPrefix -> {
                return optimize(graph.child)
            }
            is LOPSingleInputBase -> {
                graph.child = optimize(graph.child)
                return graph
            }
            else -> {
                throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} ${graph::class.simpleName}")
            }
        }
    }
}
