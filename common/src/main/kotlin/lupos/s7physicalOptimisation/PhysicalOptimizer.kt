package lupos.s7physicalOptimisation

import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s2buildOperatorGraph.*
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.multiinput.*
import lupos.s2buildOperatorGraph.data.*
import lupos.s5physicalOperators.*
import lupos.s5physicalOperators.singleinput.*
import lupos.s6tripleStore.*

class PhysicalOptimizer() {

    fun optimize(graph: OPBase, store: TripleStore): POPBase {
        when (graph) {
            is LOPSingleInputBase ->
                when (graph) {
                    is LOPNOOP ->
                        return optimize(graph.child, store)
                    is LOPProjection ->
                        return POPProjection(graph.variables, optimize(graph.child, store))
                    else -> throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} 0 ${graph::class.simpleName}")
                }
            is LOPTriple -> {
                var done = 0
                if (graph.s is LOPVariable) {
                    if (graph.s.name == "s")
                        done++
                    else
                        return POPRename(graph.s, LOPVariable("s"), optimize(LOPTriple(LOPVariable("s"), graph.p, graph.o), store))
                } else if (graph.s is LOPExpression) {
                    if (graph.s.child is ASTIri) {
                        return POPFilterExact(LOPVariable("s"), "<" + graph.s.child.iri + ">", optimize(LOPTriple(LOPVariable("s"), graph.p, graph.o), store))
                    }
                }
                if (graph.p is LOPVariable) {
                    if (graph.p.name == "p")
                        done++
                    else
                        return POPRename(graph.p, LOPVariable("p"), optimize(LOPTriple(graph.s, LOPVariable("p"), graph.o), store))
                } else if (graph.p is LOPExpression) {
                    if (graph.p.child is ASTIri) {
                        return POPFilterExact(LOPVariable("p"), "<" + graph.p.child.iri + ">", optimize(LOPTriple(graph.s, LOPVariable("p"), graph.o), store))
                    }
                }
                if (graph.o is LOPVariable) {
                    if (graph.o.name == "o") {
                        done++
                    } else {
                        return POPRename(graph.o, LOPVariable("o"), optimize(LOPTriple(graph.s, graph.p, LOPVariable("o")), store))
                    }
                } else if (graph.o is LOPExpression) {
                    if (graph.o.child is ASTIri) {
                        return POPFilterExact(LOPVariable("o"), "<" + graph.o.child.iri + ">", optimize(LOPTriple(graph.s, graph.p, LOPVariable("o")), store))
                    }
                }
                if (done == 3)
                    return store.getIterator()
                throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} 1 ${graph::class.simpleName}, ${graph.s::class.simpleName},  ${graph.p::class.simpleName}, ${graph.o::class.simpleName}")

            }
            else -> throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} 2 ${graph::class.simpleName}")
        }
    }
}
