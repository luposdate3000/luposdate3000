package lupos.s09physicalOptimisation
import lupos.s04logicalOperators.data.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStore
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPSort
import lupos.s07physicalOperators.singleinput.POPTemporaryStore
import lupos.s08logicalOptimisation.OptimizerVisitorLOP
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPEmptyRow
import lupos.s09physicalOperators.POPExpression
import lupos.s09physicalOperators.POPGraphOperation
import lupos.s09physicalOperators.POPInsertData
import lupos.s09physicalOperators.POPValues
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



abstract class OptimizerVisitorPOP(transactionID: Long) : OptimizerVisitorLOP(transactionID) {
    var store: PersistentStore? = null


    open fun visit(node: POPGraphOperation): OPBase {
        require(node.transactionID == transactionID)
        return node
    }

    open fun visit(node: POPModify): OPBase {
        return POPModify(transactionID, node.iri, node.insert, node.delete, node.pstore, optimize(node.child))
    }

    open fun visit(node: POPInsertData): OPBase {
        require(node.transactionID == transactionID)
        return node
    }

    open fun visit(node: POPFilterExact): OPBase {
        return POPFilterExact(optimize(node.variable) as LOPVariable, node.value, optimize(node.child))
    }

    open fun visit(node: POPProjection): OPBase {
        return POPProjection(node.variables, optimize(node.child))
    }

    open fun visit(node: POPRename): OPBase {
        return POPRename(optimize(node.nameTo) as LOPVariable, optimize(node.nameFrom) as LOPVariable, optimize(node.child))
    }

    open fun visit(node: POPTripleStoreIteratorBase): OPBase {
        if (store == null)
            return node
        val res = store!!.getNamedGraph(node.getGraphName()).getIterator()
        res.setMNameS(node.nameS)
        res.setMNameP(node.nameP)
        res.setMNameO(node.nameO)
        return res
    }

    open fun visit(node: POPBind): OPBase {
        return POPBind(node.name, optimize(node.expression) as POPExpression, optimize(node.child))
    }

    open fun visit(node: POPBindUndefined): OPBase {
        return POPBindUndefined(node.name, optimize(node.child))
    }

    open fun visit(node: POPDistinct): OPBase {
        return POPDistinct(optimize(node.child))
    }

    open fun visit(node: POPEmptyRow): OPBase {
        return POPEmptyRow()
    }

    open fun visit(node: POPFilter): OPBase {
        return POPFilter(node.filter, optimize(node.child))
    }

    open fun visit(node: POPGroup): OPBase {
        var bindings: POPBase = POPEmptyRow()
        for ((v, e) in node.bindings) {
            bindings = POPBind(LOPVariable(node.getResultSet().getVariable(v)), e, bindings)
        }
        if (bindings is POPEmptyRow)
            return POPGroup(node.by, null, optimize(node.child))
        return POPGroup(node.by, optimize(bindings) as POPBind, optimize(node.child))
    }

    open fun visit(node: POPJoinHashMap): OPBase {
        return POPJoinHashMap(optimize(node.child[0]), optimize(node.child[1]), node.optional)
    }

    open fun visit(node: POPLimit): OPBase {
        return POPLimit(node.limit, optimize(node.child))
    }

    open fun visit(node: POPMakeBooleanResult): OPBase {
        return POPMakeBooleanResult(optimize(node.child))
    }

    open fun visit(node: POPSort): OPBase {
        return POPSort(LOPVariable(node.getResultSet().getVariable(node.sortBy)), node.sortOrder, optimize(node.child))
    }

    open fun visit(node: POPUnion): OPBase {
        return POPUnion(optimize(node.childA), optimize(node.childB))
    }

    open fun visit(node: POPExpression): OPBase {
        return POPExpression(node.child)
    }

    open fun visit(node: POPOffset): OPBase {
        return POPOffset(node.offset, optimize(node.child))
    }

    open fun visit(node: POPTemporaryStore): OPBase {
        return POPTemporaryStore(optimize(node.child))
    }

    open fun visit(node: POPValues): OPBase {
        return node
    }

    override open fun optimize(node: OPBase): OPBase {
        when (node) {
            is POPFilterExact -> return visit(node)
            is POPProjection -> return visit(node)
            is POPRename -> return visit(node)
            is POPTripleStoreIteratorBase -> return visit(node)
            is POPBind -> return visit(node)
            is POPBindUndefined -> return visit(node)
            is POPDistinct -> return visit(node)
            is POPEmptyRow -> return visit(node)
            is POPFilter -> return visit(node)
            is POPGroup -> return visit(node)
            is POPJoinHashMap -> return visit(node)
            is POPLimit -> return visit(node)
            is POPMakeBooleanResult -> return visit(node)
            is POPSort -> return visit(node)
            is POPUnion -> return visit(node)
            is POPExpression -> return visit(node)
            is POPOffset -> return visit(node)
            is POPTemporaryStore -> return visit(node)
            is POPValues -> return visit(node)
            is POPInsertData -> return visit(node)
            is POPModify -> return visit(node)
            is POPGraphOperation -> return visit(node)
        }
        return super.optimize(node)
    }
}
