package lupos.s10physicalOptimisation

import lupos.s00misc.classNameToString
import lupos.s00misc.EIndexPattern
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTSimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
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
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s10physicalOptimisation.*
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class PhysicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary) {
    override val classname="PhysicalOptimizer"

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var change = true
        try {
            when (node) {
                is LOPGraphOperation -> return POPGraphOperation(dictionary, transactionID, node.silent, node.graphref1!!, node.graphref2, node.action)
                is LOPModify -> return POPModify(dictionary, transactionID, node.iri, node.insert, node.delete, node.children[0])
                is LOPModifyData -> return POPModifyData(dictionary, transactionID, node.type, node.data)
                is LOPProjection -> return POPProjection(dictionary, node.variables, node.children[0])
                is LOPMakeBooleanResult -> return POPMakeBooleanResult(dictionary, node.children[0])
                is LOPRename -> return POPRename(dictionary, node.nameTo, node.nameFrom, node.children[0])
                is LOPValues -> return POPValues(dictionary, node)
                is LOPLimit -> return POPLimit(dictionary, node.limit, node.children[0])
                is LOPDistinct -> return POPDistinct(dictionary, node.children[0])
                is LOPOffset -> return POPOffset(dictionary, node.offset, node.children[0])
                is LOPGroup -> {
                    if (node.children[1] is POPBind)
                        return POPGroup(dictionary, node.by, node.children[1] as POPBind, node.children[0])
                    return POPGroup(dictionary, node.by, null, node.children[0])
                }
                is LOPUnion -> return POPUnion(dictionary, node.children[0], node.children[1])
                is LOPExpression -> return POPExpression(dictionary, node.child)
                is LOPSort -> return POPSort(dictionary, node.by, node.asc, node.children[0])
                is LOPSubGroup -> return node.children[0]
                is LOPFilter -> return POPFilter(dictionary, node.children[1] as POPExpression, node.children[0])
                is LOPBind -> {
                    val variable = node.name
                    val child = node.children[0]
                    when (node.children[1]) {
                        is AOPVariable ->
                            if (child.getProvidedVariableNames().contains(variable.name))
                                return POPRename(dictionary, variable, node.children[1] as AOPVariable, child)
                            else
                                return POPBindUndefined(dictionary, variable, child)
                        else -> return POPBind(dictionary, variable, node.children[1] as POPExpression, child)
                    }
                }
                is LOPJoin -> return POPJoinHashMap(dictionary, node.children[0], node.children[1], node.optional)
                is LOPTriple -> {
                    val ts = optimizeTriple(node.s)
                    val tp = optimizeTriple(node.p)
                    val to = optimizeTriple(node.o)
                    if (node.graph == null)
                        return DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, ts.first, tp.first, to.first, ts.second, tp.second, to.second, EIndexPattern.SPO)
                    else
                        return DistributedTripleStore.getNamedGraph(node.graph).getIterator(transactionID, dictionary, ts.first, tp.first, to.first, ts.second, tp.second, to.second, EIndexPattern.SPO)
                }
                is OPNothing -> return POPEmptyRow(dictionary)
                else -> {
                    change = false
                    return node
                }
            }
        } finally {
            if (change)
                onChange()
        }
    }

    fun optimizeTriple(param: OPBase): Pair<String, Boolean> {
        when (param) {
            is AOPVariable -> return Pair(param.name, false)
            is AOPConstant -> return Pair(param.valueToString()!!, true)
            else -> throw UnsupportedOperationException("${classNameToString(this)} , 2 ${classNameToString(param)}")
        }
    }
}
