package lupos.s09physicalOptimisation

import lupos.s00misc.classNameToString
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s03buildOperatorGraph.data.*
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.data.LOPTriple
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.multiinput.LOPJoin
import lupos.s03buildOperatorGraph.multiinput.LOPUnion
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.OPNothing
import lupos.s03buildOperatorGraph.singleinput.*
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPProjection
import lupos.s03buildOperatorGraph.singleinput.LOPRename
import lupos.s03buildOperatorGraph.singleinput.LOPSort
import lupos.s03buildOperatorGraph.singleinput.LOPSubGroup
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPLimit
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPOffset
import lupos.s07physicalOperators.*
import lupos.s07physicalOperators.multiinput.POPJoinHashMap
import lupos.s07physicalOperators.multiinput.POPUnion
import lupos.s07physicalOperators.POPEmptyRow
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.POPValues
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.singleinput.modifiers.POPLimit
import lupos.s07physicalOperators.singleinput.modifiers.POPOffset
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPFilter
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPGroup
import lupos.s07physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPSort
import lupos.s08tripleStore.*
import lupos.s09physicalOptimisation.OptimizerVisitorPOP


class PhysicalOptimizer() : OptimizerVisitorPOP() {

    override fun visit(node: LOPInsertData): OPBase {
	val s=store
	if(s==null)
	return POPInsertData(node.data,globalStore)
        return POPInsertData(node.data,s)
    }

    override fun visit(node: LOPProjection): OPBase {
        return POPProjection(node.variables, optimize(node.child))
    }

    override fun visit(node: LOPMakeBooleanResult): OPBase {
        return POPMakeBooleanResult(optimize(node.child))
    }

    override fun visit(node: LOPRename): OPBase {
        return POPRename(node.nameTo, node.nameFrom, optimize(node.child))
    }

    override fun visit(node: LOPValues): OPBase {
        return POPValues(node)
    }

    override fun visit(node: LOPLimit): OPBase {
        return POPLimit(node.limit, optimize(node.child))
    }

    override fun visit(node: LOPDistinct): OPBase {
        return POPDistinct(optimize(node.child))
    }

    override fun visit(node: LOPOffset): OPBase {
        return POPOffset(node.offset, optimize(node.child))
    }

    override fun visit(node: LOPGroup): OPBase {
        if (node.bindings != null)
            return POPGroup(node.by, optimize(node.bindings!!) as POPBind, optimize(node.child))
        return POPGroup(node.by, null, optimize(node.child))
    }

    override fun visit(node: LOPUnion): OPBase {
        return POPUnion(optimize(node.child), optimize(node.second))
    }

    override fun visit(node: LOPExpression): OPBase {
        return POPExpression(node.child)
    }

    override fun visit(node: LOPSort): OPBase {
        if (node.by is LOPVariable)
            return POPSort(node.by as LOPVariable, node.asc, optimize(node.child))
        else if (node.by is LOPExpression) {
            val v = LOPVariable("#" + node.uuid)
            return POPSort(v, node.asc, POPBind(v, optimize(node.by) as POPExpression, optimize(node.child)))
        } else
            throw UnsupportedOperationException("${classNameToString(this)} ${classNameToString(node)}, ${classNameToString(node.by)}")
    }

    override fun visit(node: LOPSubGroup): OPBase {
        return optimize(node.child)
    }

    override fun visit(node: LOPFilter): OPBase {
        return POPFilter(optimize(node.filter) as POPExpression, optimize(node.child))
    }

    override fun visit(node: LOPBind): OPBase {
        val variable = optimize(node.name) as LOPVariable
        val child = optimize(node.child)
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
//        return POPJoinNestedLoop(optimize(node.child), optimize(node.second), node.optional)
        return POPJoinHashMap(optimize(node.child), optimize(node.second), node.optional)
    }

    fun optimizeTriple(param: OPBase, name: String, child: POPBase, node: LOPTriple): POPBase {
        when (param) {
            is LOPVariable -> {
                if (param.name != name)
                    return POPRename(param, LOPVariable(name), child)
            }
            is LOPExpression -> {
                when (param.child) {
                    is ASTInteger -> return POPFilterExact(LOPVariable(name), "\"" + param.child.value + "\"^^<http://www.w3.org/2001/XMLSchema#integer>", child)
                    is ASTIri -> return POPFilterExact(LOPVariable(name), "<" + param.child.iri + ">", child)
                    is ASTLanguageTaggedLiteral -> return POPFilterExact(LOPVariable(name), param.child.delimiter + param.child.content + param.child.delimiter + "@" + param.child.language, child)
                    is ASTTypedLiteral -> return POPFilterExact(LOPVariable(name), param.child.delimiter + param.child.content + param.child.delimiter + "^^<" + param.child.type_iri + ">", child)
                    else -> throw UnsupportedOperationException("${classNameToString(this)} ${classNameToString(node)}, ${classNameToString(param.child)}")
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
        var result2 = if (store == null)
            globalStore.getNamedGraph(node.graph).getIterator()
        else
            store!!.getNamedGraph(node.graph).getIterator()
        var sname = result2.nameS
        var pname = result2.nameP
        var oname = result2.nameO
        var result: POPBase = result2
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
