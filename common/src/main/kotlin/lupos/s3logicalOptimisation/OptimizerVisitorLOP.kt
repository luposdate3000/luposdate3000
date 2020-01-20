package lupos.s3logicalOptimisation

import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s2buildOperatorGraph.data.LOPTriple
import lupos.s2buildOperatorGraph.data.LOPValues
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.multiinput.LOPJoin
import lupos.s2buildOperatorGraph.multiinput.LOPMinus
import lupos.s2buildOperatorGraph.multiinput.LOPUnion
import lupos.s2buildOperatorGraph.singleinput.LOPBind
import lupos.s2buildOperatorGraph.singleinput.LOPFilter
import lupos.s2buildOperatorGraph.singleinput.LOPGroup
import lupos.s2buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s2buildOperatorGraph.singleinput.LOPNOOP
import lupos.s2buildOperatorGraph.singleinput.LOPOptional
import lupos.s2buildOperatorGraph.singleinput.LOPProjection
import lupos.s2buildOperatorGraph.singleinput.LOPRename
import lupos.s2buildOperatorGraph.singleinput.LOPSort
import lupos.s2buildOperatorGraph.singleinput.LOPSubGroup
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPLimit
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPOffset
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPPrefix
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPReduced
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.OPNothing

abstract class OptimizerVisitorLOP() {
    open fun visit(node: OPBase): OPBase {
        throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} a ${node::class.simpleName}")
    }

    open fun visit(node: OPNothing): OPBase {
        return OPNothing()
    }

    open fun visit(node: LOPNOOP): OPBase {
        return LOPNOOP(optimize(node.child))
    }

    open fun visit(node: LOPBind): OPBase {
        return LOPBind(optimize(node.name) as LOPVariable, optimize(node.expression), optimize(node.child))
    }

    open fun visit(node: LOPFilter): OPBase {
        return LOPFilter(optimize(node.filter) as LOPExpression, optimize(node.child))
    }

    open fun visit(node: LOPGroup): OPBase {
        if (node.bindings != null)
            return LOPGroup(node.by, optimize(node.bindings!!), optimize(node.child))
        return LOPGroup(node.by, null, optimize(node.child))
    }

    open fun visit(node: LOPMakeBooleanResult): OPBase {
        return LOPMakeBooleanResult(optimize(node.child))
    }

    open fun visit(node: LOPOptional): OPBase {
        return LOPOptional(optimize(node.child))
    }

    open fun visit(node: LOPSubGroup): OPBase {
        return LOPSubGroup(optimize(node.child))
    }

    open fun visit(node: LOPProjection): OPBase {
        return LOPProjection(node.variables, optimize(node.child))
    }

    open fun visit(node: LOPRename): OPBase {
        return LOPRename(optimize(node.nameTo) as LOPVariable, optimize(node.nameFrom) as LOPVariable, optimize(node.child))
    }

    open fun visit(node: LOPSort): OPBase {
        return LOPSort(node.asc, optimize(node.by), optimize(node.child))
    }

    open fun visit(node: LOPDistinct): OPBase {
        return LOPDistinct(optimize(node.child))
    }

    open fun visit(node: LOPLimit): OPBase {
        return LOPLimit(node.limit, optimize(node.child))
    }

    open fun visit(node: LOPOffset): OPBase {
        return LOPOffset(node.offset, optimize(node.child))
    }

    open fun visit(node: LOPPrefix): OPBase {
        return LOPPrefix(node.name, node.iri, optimize(node.child))
    }

    open fun visit(node: LOPReduced): OPBase {
        return LOPReduced(optimize(node.child))
    }

    open fun visit(node: LOPJoin): OPBase {
        return LOPJoin(optimize(node.child), optimize(node.second), node.optional)
    }

    open fun visit(node: LOPMinus): OPBase {
        return LOPMinus(optimize(node.child), optimize(node.second))
    }

    open fun visit(node: LOPUnion): OPBase {
        return LOPUnion(optimize(node.child), optimize(node.second))
    }

    open fun visit(node: LOPExpression): OPBase {
        return LOPExpression(node.child)
    }

    open fun visit(node: LOPTriple): OPBase {
        return LOPTriple(optimize(node.s), optimize(node.p), optimize(node.o))
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

    open fun optimize(node: OPBase): OPBase {
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
        }
        throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} c ${node::class.simpleName}")
    }
}
