package lupos.s7physicalOptimisation

import lupos.s1buildSyntaxTree.sparql1_1.ASTIri
import lupos.s1buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.OPNothing
import lupos.s2buildOperatorGraph.singleinput.LOPBind
import lupos.s2buildOperatorGraph.singleinput.LOPProjection
import lupos.s2buildOperatorGraph.singleinput.LOPFilter
import lupos.s2buildOperatorGraph.multiinput.LOPJoin
import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s2buildOperatorGraph.data.LOPTriple
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPEmptyRow
import lupos.s5physicalOperators.POPExpression
import lupos.s5physicalOperators.multiinput.POPJoin
import lupos.s5physicalOperators.singleinput.POPBind
import lupos.s5physicalOperators.singleinput.POPFilter
import lupos.s5physicalOperators.singleinput.POPBindUndefined
import lupos.s5physicalOperators.singleinput.POPFilterExact
import lupos.s5physicalOperators.singleinput.POPProjection
import lupos.s5physicalOperators.singleinput.POPRename
import lupos.s6tripleStore.TripleStore
import lupos.s6tripleStore.POPTripleStoreIteratorBase

class PhysicalOptimizer() : OptimizerVisitorPOP() {

    var store: TripleStore = TripleStore()

    override fun visit(node: LOPProjection): OPBase {
        return POPProjection(node.variables, optimize(node.child) as POPBase)
    }

    override fun visit(node: LOPExpression): OPBase {
        return POPExpression(node.child)
    }

    override fun visit(node: LOPFilter): OPBase {
        return POPFilter(optimize(node.filter) as POPExpression, optimize(node.child) as POPBase)
    }

    override fun visit(node: LOPBind): OPBase {
        val variable = optimize(node.name) as LOPVariable
        val child = optimize(node.child) as POPBase
        when (node.expression) {
            is LOPVariable ->
                if (child.getResultSet().getVariableNames().contains(variable.name))
                    return POPRename(variable, node.expression, child)
                else
                    return POPBindUndefined(variable, child)
            else ->
                return POPBind(variable, optimize(node.expression) as POPExpression, child)
        }
    }

    override fun visit(node: LOPJoin): OPBase {
        return POPJoin(optimize(node.child) as POPBase, optimize(node.second) as POPBase, node.optional)
    }

    fun optimizeTriple(param: OPBase, name: String, child: POPBase, node: LOPTriple): POPBase {
        when (param) {
            is LOPVariable -> {
                if (param.name != name)
                    return POPRename(param, LOPVariable(name), child)
            }
            is LOPExpression -> {
                when (param.child) {
                    is ASTIri -> return POPFilterExact(LOPVariable(name), "<" + param.child.iri + ">", child)
                    is ASTLanguageTaggedLiteral -> return POPFilterExact(LOPVariable(name), param.child.delimiter + param.child.content + param.child.delimiter + "@" + param.child.language, child)
                    else -> throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} 2 ${node::class.simpleName}, ${param.child::class.simpleName}")
                }
            }
        }
        return child
    }

    override fun visit(node: LOPTriple): OPBase {
        val variables = mutableListOf<LOPVariable>()
        if (node.s is LOPVariable)
            variables.add(node.s)
        if (node.p is LOPVariable)
            variables.add(node.p)
        if (node.o is LOPVariable)
            variables.add(node.o)
        var result: POPBase = store.getIterator()
        var sname = (result as POPTripleStoreIteratorBase).getNameForS()
        var pname = (result as POPTripleStoreIteratorBase).getNameForP()
        var oname = (result as POPTripleStoreIteratorBase).getNameForO()
        result = optimizeTriple(node.s, sname, result, node)
        result = optimizeTriple(node.p, pname, result, node)
        result = optimizeTriple(node.o, oname, result, node)
        if (variables.size < 3) {
            result = POPProjection(variables, result)
        }
        return result
    }

    override fun visit(node: OPNothing): OPBase {
        return POPEmptyRow()
    }
}
