package lupos.s09physicalOptimisation
import lupos.s08tripleStore.POPTripleStoreIteratorBase

import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.POPBase
import lupos.s05logicalOptimisation.OptimizerVisitorLOP
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.data.LOPVariable


abstract class OptimizerVisitorPOP() : OptimizerVisitorLOP() {
    open fun visit(node: POPFilterExact): OPBase {
        return POPFilterExact(optimize(node.variable) as LOPVariable, node.value, optimize(node.child) as POPBase)
    }

    open fun visit(node: POPProjection): OPBase {
        return POPProjection(node.variables, optimize(node.child) as POPBase)
    }

    open fun visit(node: POPRename): OPBase {
        return POPRename(optimize(node.nameTo) as LOPVariable, optimize(node.nameFrom) as LOPVariable, optimize(node.child) as POPBase)
    }

    open fun visit(node: POPTripleStoreIteratorBase): OPBase {
        return node
    }

    open fun visit(node: POPBind): OPBase {
        return POPBind(node.name, node.expression, optimize(node.child) as POPBase)
    }

    override open fun optimize(node: OPBase): OPBase {
        when (node) {
            is POPFilterExact -> return visit(node)
            is POPProjection -> return visit(node)
            is POPRename -> return visit(node)
            is POPTripleStoreIteratorBase -> return visit(node)
            is POPBind -> return visit(node)
        }
        return super.optimize(node)
    }
}
