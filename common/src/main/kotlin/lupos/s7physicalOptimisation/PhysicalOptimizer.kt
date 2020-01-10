package lupos.s7physicalOptimisation

import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s2buildOperatorGraph.*
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.multiinput.*
import lupos.s2buildOperatorGraph.data.*
import lupos.s5physicalOperators.*
import lupos.s5physicalOperators.singleinput.*
import lupos.s6tripleStore.*

class PhysicalOptimizer() : OptimizerVisitorPOP() {

    var store: TripleStore = TripleStore()

    override fun visit(node: LOPProjection): OPBase {
        return POPProjection(node.variables, visit(node.child) as POPBase)
    }

    override fun visit(node: LOPTriple): OPBase {
        var done = 0
        if (node.s is LOPVariable) {
            if (node.s.name == "s")
                done++
            else
                return POPRename(node.s, LOPVariable("s"), optimize(LOPTriple(LOPVariable("s"), node.p, node.o), store))
        } else if (node.s is LOPExpression) {
            if (node.s.child is ASTIri) {
                return POPFilterExact(LOPVariable("s"), "<" + node.s.child.iri + ">", optimize(LOPTriple(LOPVariable("s"), node.p, node.o), store))
            }
        }
        if (node.p is LOPVariable) {
            if (node.p.name == "p")
                done++
            else
                return POPRename(node.p, LOPVariable("p"), optimize(LOPTriple(node.s, LOPVariable("p"), node.o), store))
        } else if (node.p is LOPExpression) {
            if (node.p.child is ASTIri) {
                return POPFilterExact(LOPVariable("p"), "<" + node.p.child.iri + ">", optimize(LOPTriple(node.s, LOPVariable("p"), node.o), store))
            }
        }
        if (node.o is LOPVariable) {
            if (node.o.name == "o") {
                done++
            } else {
                return POPRename(node.o, LOPVariable("o"), optimize(LOPTriple(node.s, node.p, LOPVariable("o")), store))
            }
        } else if (node.o is LOPExpression) {
            if (node.o.child is ASTIri) {
                return POPFilterExact(LOPVariable("o"), "<" + node.o.child.iri + ">", optimize(LOPTriple(node.s, node.p, LOPVariable("o")), store))
            }
        }
        if (done == 3)
            return store.getIterator()
        throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} 1 ${node::class.simpleName}, ${node.s::class.simpleName},  ${node.p::class.simpleName}, ${node.o::class.simpleName}")
    }

    fun optimize(graph: OPBase, store: TripleStore): POPBase {
        this.store = store
        return visit(graph) as POPBase
    }
}
