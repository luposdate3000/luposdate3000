package lupos.s05logicalOptimisation
import lupos.s03buildOperatorGraph.singleinput.LOPModify
import lupos.s03buildOperatorGraph.singleinput.LOPServiceIRI
import lupos.s03buildOperatorGraph.singleinput.LOPServiceVAR
import lupos.s03buildOperatorGraph.singleinput.LOPSubGroup
import lupos.s03buildOperatorGraph.data.LOPGraphOperation
import lupos.s03buildOperatorGraph.data.LOPInsertData
import lupos.s03buildOperatorGraph.data.LOPValues

import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.data.LOPTriple
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.multiinput.LOPJoin
import lupos.s03buildOperatorGraph.multiinput.LOPMinus
import lupos.s03buildOperatorGraph.multiinput.LOPUnion
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.OPNothing
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPNOOP
import lupos.s03buildOperatorGraph.singleinput.LOPOptional
import lupos.s03buildOperatorGraph.singleinput.LOPProjection
import lupos.s03buildOperatorGraph.singleinput.LOPRename
import lupos.s03buildOperatorGraph.singleinput.LOPSort
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPLimit
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPOffset
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPPrefix
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPReduced
import lupos.s05logicalOptimisation.LogicalOptimizer


abstract class OptimizerVisitorLOP(val transactionID: Long) {
    open fun visit(node: LOPGraphOperation): OPBase {
        return node
    }

    open fun visit(node: LOPModify): OPBase {
        val tmp = LOPModify(optimize(node.child))
        tmp.insert.addAll(node.insert)
        tmp.iri = node.iri
        tmp.delete.addAll(node.delete)
        return tmp
    }

    open fun visit(node: LOPServiceIRI): OPBase {
        return LOPServiceIRI(node.name, node.silent, optimize(node.constraint))
    }

    open fun visit(node: LOPServiceVAR): OPBase {
        return LOPServiceVAR(node.name, node.silent, optimize(node.constraint), optimize(node.child))
    }

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

    open fun visit(node: LOPInsertData): OPBase {
        return node
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
            is LOPRename -> return visit(node)
            is LOPServiceIRI -> return visit(node)
            is LOPServiceVAR -> return visit(node)
            is LOPInsertData -> return visit(node)
            is LOPModify -> return visit(node)
            is LOPGraphOperation -> return visit(node)
        }
        throw UnsupportedOperationException("UnsupportedOperationException ${this::class.simpleName} c ${node::class.simpleName}")
    }
}
