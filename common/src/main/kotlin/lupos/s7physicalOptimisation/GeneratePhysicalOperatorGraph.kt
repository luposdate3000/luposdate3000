package lupos.s7physicalOptimisation

import lupos.s2buildOperatorGraph.*
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.multiinput.*
import lupos.s2buildOperatorGraph.data.*
import lupos.s5physicalOperators.*
import lupos.s5physicalOperators.singleinput.*
import lupos.s6tripleStore.*

fun transformToPhysicalOperators(graph: OPBase): POPBase {
    when (graph) {
        is LOPSingleInputBase ->
            when (graph) {
                is LOPNOOP ->
                    return transformToPhysicalOperators(graph.child)
                is LOPProjection ->
                    return POPProjection(graph.variables, transformToPhysicalOperators(graph.child))
                else -> throw UnsupportedOperationException("UnsupportedOperationException physical optimisation ${graph::class.simpleName}")
            }
        is LOPTriple -> {
            var done = 0
            if (graph.s is LOPVariable) {
                if (graph.s.name == "s")
                    done++
                else
                    return POPRename(graph.s, LOPVariable("s"), transformToPhysicalOperators(LOPTriple(LOPVariable("s"), graph.p, graph.o)))
            } else if (graph.s is LOPExpression) {
                if (graph.s.child is ASTIRI) {
                    //TODO hier
                }
            }
            if (graph.p is LOPVariable) {
                if (graph.p.name == "p")
                    done++
                else
                    return POPRename(graph.p, LOPVariable("p"), transformToPhysicalOperators(LOPTriple(graph.s, LOPVariable("p"), graph.o)))
            }
            if (graph.o is LOPVariable) {
                if (graph.o.name != "o")
                    done++
                else
                    return POPRename(graph.o, LOPVariable("o"), transformToPhysicalOperators(LOPTriple(graph.s, graph.p, LOPVariable("o"))))
            }
            if (done == 3)
                return persistentTripleStore.getIterator()
            throw UnsupportedOperationException("UnsupportedOperationException physical optimisation ${graph::class.simpleName}, ${graph.s::class.simpleName},  ${graph.p::class.simpleName}, ${graph.o::class.simpleName}")

        }
        else -> throw UnsupportedOperationException("UnsupportedOperationException physical optimisation ${graph::class.simpleName}")
    }
}
