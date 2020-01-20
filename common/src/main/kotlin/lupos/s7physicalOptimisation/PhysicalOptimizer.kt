package lupos.s7physicalOptimisation

import lupos.s1buildSyntaxTree.sparql1_1.ASTIri
import lupos.s1buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.OPNothing
import lupos.s2buildOperatorGraph.singleinput.LOPBind
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.singleinput.modifiers.*
import lupos.s2buildOperatorGraph.singleinput.LOPGroup
import lupos.s2buildOperatorGraph.singleinput.LOPProjection
import lupos.s2buildOperatorGraph.singleinput.LOPFilter
import lupos.s2buildOperatorGraph.singleinput.LOPSubGroup
import lupos.s2buildOperatorGraph.multiinput.LOPJoin
import lupos.s2buildOperatorGraph.multiinput.LOPUnion
import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s2buildOperatorGraph.data.LOPTriple
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.data.LOPValues
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPValues
import lupos.s5physicalOperators.POPEmptyRow
import lupos.s5physicalOperators.POPExpression
import lupos.s5physicalOperators.multiinput.POPJoin
import lupos.s5physicalOperators.multiinput.POPUnion
import lupos.s5physicalOperators.singleinput.POPBind
import lupos.s5physicalOperators.singleinput.modifiers.*
import lupos.s5physicalOperators.singleinput.*
import lupos.s5physicalOperators.singleinput.POPGroup
import lupos.s5physicalOperators.singleinput.POPFilter
import lupos.s5physicalOperators.singleinput.POPBindUndefined
import lupos.s5physicalOperators.singleinput.POPFilterExact
import lupos.s5physicalOperators.singleinput.POPProjection
import lupos.s5physicalOperators.singleinput.POPRename
import lupos.s6tripleStore.TripleStore
import lupos.s6tripleStore.POPTripleStoreIteratorBase

class PhysicalOptimizer() : OptimizerVisitorPOP() {

    companion object {
        val _store: TripleStore = TripleStore()
    }

    var store = _store
    override fun visit(node: LOPProjection): OPBase {
        return POPProjection(node.variables, optimize(node.child) as POPBase)
    }

    override fun visit(node: LOPValues): OPBase {
        return POPValues(node)
    }

    override fun visit(node: LOPLimit): OPBase {
        return POPLimit(node.limit, optimize(node.child) as POPBase)
    }

    override fun visit(node: LOPOffset): OPBase {
        return POPOffset(node.offset, optimize(node.child) as POPBase)
    }

    override fun visit(node: LOPGroup): OPBase {
        if (node.bindings != null)
            return POPGroup(node.by, optimize(node.bindings!!) as POPBind, optimize(node.child) as POPBase)
        return POPGroup(node.by, null, optimize(node.child) as POPBase)
    }

    override fun visit(node: LOPUnion): OPBase {
        return POPUnion(optimize(node.child) as POPBase, optimize(node.second) as POPBase)
    }

    override fun visit(node: LOPExpression): OPBase {
        return POPExpression(node.child)
    }

    override fun visit(node: LOPSort): OPBase {
        return POPSort(node.by as LOPVariable, node.asc, optimize(node.child) as POPBase)
    }

    override fun visit(node: LOPSubGroup): OPBase {
        return optimize(node.child) as POPBase
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
                    else -> throw UnsupportedOperationException("${this::class.simpleName} ${node::class.simpleName}, ${param.child::class.simpleName}")
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
