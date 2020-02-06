package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPGraphOperation
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
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s04logicalOperators.singleinput.LOPServiceVAR
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s08logicalOptimisation.LogicalOptimizer


abstract class OptimizerVisitorLOP(val transactionID: Long,val dictionary:ResultSetDictionary) {

    open fun visit(node: LOPGraphOperation): OPBase {
        return node
    }

    open fun visit(node: LOPModify): OPBase {
        val tmp = LOPModify(optimize(node.children[0]))
        tmp.insert.addAll(node.insert)
        tmp.iri = node.iri
        tmp.delete.addAll(node.delete)
        return tmp
    }

    open fun visit(node: LOPServiceIRI): OPBase {
        return LOPServiceIRI(node.name, node.silent, optimize(node.children[0]))
    }

    open fun visit(node: LOPServiceVAR): OPBase {
        return LOPServiceVAR(node.name, node.silent, optimize(node.children[1]), optimize(node.children[0]))
    }

    open fun visit(node: OPBase): OPBase {
        throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} a ${node::class.simpleName}")
    }

    open fun visit(node: OPNothing): OPBase {
        return OPNothing()
    }

    open fun visit(node: LOPNOOP): OPBase {
        return LOPNOOP(optimize(node.children[0]))
    }

    open fun visit(node: LOPBind): OPBase {
        return LOPBind(optimize(node.name) as LOPVariable, optimize(node.expression), optimize(node.children[0]))
    }

    open fun visit(node: LOPFilter): OPBase {
        return LOPFilter(optimize(node.filter) as LOPExpression, optimize(node.children[0]))
    }

    open fun visit(node: LOPGroup): OPBase {
        if (node.bindings != null)
            return LOPGroup(node.by, optimize(node.bindings!!), optimize(node.children[0]))
        return LOPGroup(node.by, null, optimize(node.children[0]))
    }

    open fun visit(node: LOPMakeBooleanResult): OPBase {
        return LOPMakeBooleanResult(optimize(node.children[0]))
    }

    open fun visit(node: LOPOptional): OPBase {
        return LOPOptional(optimize(node.children[0]))
    }

    open fun visit(node: LOPSubGroup): OPBase {
        return LOPSubGroup(optimize(node.children[0]))
    }

    open fun visit(node: LOPProjection): OPBase {
        return LOPProjection(node.variables, optimize(node.children[0]))
    }

    open fun visit(node: LOPRename): OPBase {
        return LOPRename(optimize(node.nameTo) as LOPVariable, optimize(node.nameFrom) as LOPVariable, optimize(node.children[0]))
    }

    open fun visit(node: LOPSort): OPBase {
        return LOPSort(node.asc, optimize(node.by), optimize(node.children[0]))
    }

    open fun visit(node: LOPDistinct): OPBase {
        return LOPDistinct(optimize(node.children[0]))
    }

    open fun visit(node: LOPLimit): OPBase {
        return LOPLimit(node.limit, optimize(node.children[0]))
    }

    open fun visit(node: LOPOffset): OPBase {
        return LOPOffset(node.offset, optimize(node.children[0]))
    }

    open fun visit(node: LOPPrefix): OPBase {
        return LOPPrefix(node.name, node.iri, optimize(node.children[0]))
    }

    open fun visit(node: LOPReduced): OPBase {
        return LOPReduced(optimize(node.children[0]))
    }

    open fun visit(node: LOPJoin): OPBase {
        return LOPJoin(optimize(node.children[0]), optimize(node.children[1]), node.optional)
    }

    open fun visit(node: LOPMinus): OPBase {
        return LOPMinus(optimize(node.children[0]), optimize(node.children[1]))
    }

    open fun visit(node: LOPUnion): OPBase {
        return LOPUnion(optimize(node.children[0]), optimize(node.children[1]))
    }

    open fun visit(node: LOPExpression): OPBase {
        return LOPExpression(node.child)
    }

    open fun visit(node: LOPTriple): OPBase {
        return LOPTriple(optimize(node.s), optimize(node.p), optimize(node.o), node.graph)
    }

    open fun visit(node: LOPValues): OPBase {
        val values = mutableListOf<LOPExpression>()
        node.values.forEach() {
            values.add(optimize(it) as LOPExpression)
        }
        return LOPValues(node.variables, values)
    }

    open fun visit(node: LOPVariable): OPBase {
        return LOPVariable(node.name)
    }

    open fun visit(node: LOPModifyData): OPBase {
        return node
    }

    protected open fun optimize(node: OPBase): OPBase {
        when (node) {
            is LOPExpression -> return visit(node)
            is LOPVariable -> return visit(node)
            is OPNothing -> return visit(node)
            is LOPMakeBooleanResult -> return visit(node)
            is LOPNOOP -> return visit(node)
            is LOPPrefix -> return visit(node)
            is LOPFilter -> return visit(node)
            is LOPJoin -> return visit(node)
            is LOPProjection -> return visit(node)
            is LOPSort -> return visit(node)
            is LOPBind -> return visit(node)
            is LOPDistinct -> return visit(node)
            is LOPGroup -> return visit(node)
            is LOPMinus -> return visit(node)
            is LOPTriple -> return visit(node)
            is LOPValues -> return visit(node)
            is LOPOptional -> return visit(node)
            is LOPUnion -> return visit(node)
            is LOPSubGroup -> return visit(node)
            is LOPLimit -> return visit(node)
            is LOPOffset -> return visit(node)
            is LOPRename -> return visit(node)
            is LOPServiceIRI -> return visit(node)
            is LOPServiceVAR -> return visit(node)
            is LOPModifyData -> return visit(node)
            is LOPModify -> return visit(node)
            is LOPGraphOperation -> return visit(node)
        }
        throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} c ${node::class.simpleName}")
    }

    fun optimizeCall(node: OPBase): OPBase {
        node.syntaxVerifyAllVariableExists(listOf<String>(),true)
        val res = optimize(node)
        res.syntaxVerifyAllVariableExists(listOf<String>(),false)
        return res
    }
}
