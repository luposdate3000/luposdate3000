package lupos.s10physicalOptimisation

import lupos.s00misc.classNameToString
import lupos.s00misc.EIndexPattern
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTSimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s10physicalOptimisation.OptimizerVisitorPOP
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class PhysicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerVisitorPOP(transactionID, dictionary) {


    override fun visit(node: LOPGraphOperation): OPBase {
        return POPGraphOperation(dictionary, transactionID, node.silent, node.graphref1!!, node.graphref2, node.action)
    }

    override fun visit(node: LOPModify): OPBase {
        return POPModify(dictionary, transactionID, node.iri, node.insert, node.delete, optimize(node.children[0]))
    }

    override fun visit(node: LOPModifyData): OPBase {
        return POPModifyData(dictionary, transactionID, node.type, node.data)
    }

    override fun visit(node: LOPProjection): OPBase {
        return POPProjection(dictionary, node.variables, optimize(node.children[0]))
    }

    override fun visit(node: LOPMakeBooleanResult): OPBase {
        return POPMakeBooleanResult(dictionary, optimize(node.children[0]))
    }

    override fun visit(node: LOPRename): OPBase {
        return POPRename(dictionary, node.nameTo, node.nameFrom, optimize(node.children[0]))
    }

    override fun visit(node: LOPValues): OPBase {
        return POPValues(dictionary, node)
    }

    override fun visit(node: LOPLimit): OPBase {
        return POPLimit(dictionary, node.limit, optimize(node.children[0]))
    }

    override fun visit(node: LOPDistinct): OPBase {
        return POPDistinct(dictionary, optimize(node.children[0]))
    }

    override fun visit(node: LOPOffset): OPBase {
        return POPOffset(dictionary, node.offset, optimize(node.children[0]))
    }

    override fun visit(node: LOPGroup): OPBase {
        if (node.bindings != null)
            return POPGroup(dictionary, node.by, optimize(node.bindings!!) as POPBind, optimize(node.children[0]))
        return POPGroup(dictionary, node.by, null, optimize(node.children[0]))
    }

    override fun visit(node: LOPUnion): OPBase {
        return POPUnion(dictionary, optimize(node.children[0]), optimize(node.children[1]))
    }

    override fun visit(node: LOPExpression): OPBase {
        return POPExpression(dictionary, node.child)
    }

    override fun visit(node: LOPSort): OPBase {
        if (node.by is LOPVariable)
            return POPSort(dictionary, node.by as LOPVariable, node.asc, optimize(node.children[0]))
        else if (node.by is LOPExpression) {
            val v = LOPVariable("#" + node.uuid)
            return POPSort(dictionary, v, node.asc, POPBind(dictionary, v, optimize(node.by) as POPExpression, optimize(node.children[0])))
        } else
            throw UnsupportedOperationException("${classNameToString(this)} ${classNameToString(node)}, ${classNameToString(node.by)}")
    }

    override fun visit(node: LOPSubGroup): OPBase {
        return optimize(node.children[0])
    }

    override fun visit(node: LOPFilter): OPBase {
        return POPFilter(dictionary, optimize(node.filter) as POPExpression, optimize(node.children[0]))
    }

    override fun visit(node: LOPBind): OPBase {
        val variable = optimize(node.name) as LOPVariable
        val child = optimize(node.children[0])
        when (node.expression) {
            is LOPVariable ->
                if (child.resultSet.getVariableNames().contains(variable.name))
                    return POPRename(dictionary, variable, node.expression, child)
                else
                    return POPBindUndefined(dictionary, variable, child)
            else ->
                return POPBind(dictionary, variable, optimize(node.expression) as POPExpression, child)
        }
    }

    override fun visit(node: LOPJoin): OPBase {
        return POPJoinHashMap(dictionary, optimize(node.children[0]), optimize(node.children[1]), node.optional)
    }

    fun optimizeTriple(param: OPBase): Pair<String, Boolean> {
        when (param) {
            is LOPVariable -> {
                return Pair(param.name, false)
            }
            is LOPExpression -> {
                when (param.child) {
                    is ASTInteger -> return Pair("\"" + param.child.value + "\"^^<http://www.w3.org/2001/XMLSchema#integer>", true)
                    is ASTIri -> return Pair("<" + param.child.iri + ">", true)
                    is ASTLanguageTaggedLiteral -> return Pair(param.child.delimiter + param.child.content + param.child.delimiter + "@" + param.child.language, true)
                    is ASTTypedLiteral -> return Pair(param.child.delimiter + param.child.content + param.child.delimiter + "^^<" + param.child.type_iri + ">", true)
                    is ASTSimpleLiteral -> return Pair(param.child.delimiter + param.child.content + param.child.delimiter, true)
                    else -> throw UnsupportedOperationException("${classNameToString(this)}, 1 ${classNameToString(param.child)}")
                }
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} , 2 ${classNameToString(param)}")
        }

    }

    override fun visit(node: LOPTriple): OPBase {
        val ts = optimizeTriple(node.s)
        val tp = optimizeTriple(node.p)
        val to = optimizeTriple(node.o)
        return DistributedTripleStore.getNamedGraph(node.graph).getIterator(transactionID, dictionary, ts.first, tp.first, to.first, ts.second, tp.second, to.second, EIndexPattern.SPO)
    }

    override fun visit(node: OPNothing): OPBase {
        return POPEmptyRow(dictionary)
    }
}
