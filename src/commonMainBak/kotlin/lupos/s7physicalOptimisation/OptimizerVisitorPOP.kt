package lupos.s7physicalOptimisation

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s3logicalOptimisation.OptimizerVisitorLOP
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.singleinput.POPBind
import lupos.s5physicalOperators.singleinput.POPFilterExact
import lupos.s5physicalOperators.singleinput.POPProjection
import lupos.s5physicalOperators.singleinput.POPRename
import lupos.s6tripleStore.POPTripleStoreIteratorBase

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
