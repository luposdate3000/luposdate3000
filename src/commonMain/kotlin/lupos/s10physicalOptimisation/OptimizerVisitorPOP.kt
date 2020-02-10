package lupos.s10physicalOptimisation
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s08logicalOptimisation.OptimizerVisitorLOP
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
import lupos.s09physicalOperators.singleinput.POPTemporaryStore


abstract class OptimizerVisitorPOP(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerVisitorLOP(transactionID, dictionary) {
    var store: DistributedTripleStore? = null


    open fun visit(node: POPGraphOperation): OPBase {
        require(node.transactionID == transactionID)
        return node
    }

    open fun visit(node: POPModify): OPBase {
        return POPModify(dictionary, transactionID, node.iri, node.insert, node.delete, node.pstore, optimize(node.children[0]))
    }

    open fun visit(node: POPModifyData): OPBase {
        require(node.transactionID == transactionID)
        return node
    }

    open fun visit(node: POPFilterExact): OPBase {
        return POPFilterExact(dictionary, optimize(node.variable) as LOPVariable, node.value, optimize(node.children[0]))
    }

    open fun visit(node: POPProjection): OPBase {
        return POPProjection(dictionary, node.variables, optimize(node.children[0]))
    }

    open fun visit(node: POPRename): OPBase {
        return POPRename(dictionary, optimize(node.nameTo) as LOPVariable, optimize(node.nameFrom) as LOPVariable, optimize(node.children[0]))
    }

    open fun visit(node: POPTripleStoreIteratorBase): OPBase {
        if (store == null)
            return node
        val res = store!!.getNamedGraph(node.getGraphName()).getIterator(dictionary)
        res.setMNameS(node.nameS)
        res.setMNameP(node.nameP)
        res.setMNameO(node.nameO)
        return res
    }

    open fun visit(node: POPBind): OPBase {
        return POPBind(dictionary, node.name, optimize(node.expression) as POPExpression, optimize(node.children[0]))
    }

    open fun visit(node: POPBindUndefined): OPBase {
        return POPBindUndefined(dictionary, node.name, optimize(node.children[0]))
    }

    open fun visit(node: POPDistinct): OPBase {
        return POPDistinct(dictionary, optimize(node.children[0]))
    }

    open fun visit(node: POPEmptyRow): OPBase {
        return POPEmptyRow(dictionary)
    }

    open fun visit(node: POPFilter): OPBase {
        return POPFilter(dictionary, node.filter, optimize(node.children[0]))
    }

    open fun visit(node: POPGroup): OPBase {
        var bindings: POPBase = POPEmptyRow(dictionary)
        for ((v, e) in node.bindings) {
            bindings = POPBind(dictionary, LOPVariable(node.getResultSet().getVariable(v)), e, bindings)
        }
        if (bindings is POPEmptyRow)
            return POPGroup(dictionary, node.by, null, optimize(node.children[0]))
        return POPGroup(dictionary, node.by, optimize(bindings) as POPBind, optimize(node.children[0]))
    }

    open fun visit(node: POPJoinHashMap): OPBase {
        return POPJoinHashMap(dictionary, optimize(node.children[0]), optimize(node.children[1]), node.optional)
    }

    open fun visit(node: POPLimit): OPBase {
        return POPLimit(dictionary, node.limit, optimize(node.children[0]))
    }

    open fun visit(node: POPMakeBooleanResult): OPBase {
        return POPMakeBooleanResult(dictionary, optimize(node.children[0]))
    }

    open fun visit(node: POPSort): OPBase {
        return POPSort(dictionary, LOPVariable(node.getResultSet().getVariable(node.sortBy)), node.sortOrder, optimize(node.children[0]))
    }

    open fun visit(node: POPUnion): OPBase {
        return POPUnion(dictionary, optimize(node.children[0]), optimize(node.children[1]))
    }

    open fun visit(node: POPExpression): OPBase {
        return POPExpression(dictionary, node.child)
    }

    open fun visit(node: POPOffset): OPBase {
        return POPOffset(dictionary, node.offset, optimize(node.children[0]))
    }

    open fun visit(node: POPTemporaryStore): OPBase {
        return POPTemporaryStore(dictionary, optimize(node.children[0]))
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
            is POPModifyData -> return visit(node)
            is POPModify -> return visit(node)
            is POPGraphOperation -> return visit(node)
        }
        return super.optimize(node)
    }
}
