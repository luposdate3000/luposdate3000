package lupos.s10physicalOptimisation

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.classNameToString
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class PhysicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.PhysicalOptimizerID) {
    override val classname = "PhysicalOptimizer"

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        var change = true
        try {
            when (node) {
                is LOPGraphOperation -> res = POPGraphOperation(dictionary, transactionID, node.silent, node.graphref1!!, node.graphref2, node.action)
                is LOPModify -> res = POPModify(dictionary, transactionID, node.iri, node.insert, node.delete, node.children[0])
                is LOPModifyData -> res = POPModifyData(dictionary, transactionID, node.type, node.data)
                is LOPProjection -> res = POPProjection(dictionary, node.variables, node.children[0])
                is LOPMakeBooleanResult -> res = POPMakeBooleanResult(dictionary, node.children[0])
                is LOPRename -> res = POPRename(dictionary, node.nameTo, node.nameFrom, node.children[0])
                is LOPValues -> res = POPValues(dictionary, node)
                is LOPLimit -> res = POPLimit(dictionary, node.limit, node.children[0])
                is LOPDistinct -> res = POPDistinct(dictionary, node.children[0])
                is LOPOffset -> res = POPOffset(dictionary, node.offset, node.children[0])
                is LOPGroup -> {
                    if (node.children[1] is POPBind)
                        res = POPGroup(dictionary, node.by, node.children[1] as POPBind, node.children[0])
                    else
                        res = POPGroup(dictionary, node.by, null, node.children[0])
                }
                is LOPUnion -> res = POPUnion(dictionary, node.children[0], node.children[1])
                is LOPSort -> res = POPSort(dictionary, node.by, node.asc, node.children[0])
                is LOPSubGroup -> res = node.children[0]
                is LOPFilter -> res = POPFilter(dictionary, node.children[1] as AOPBase, node.children[0])
                is LOPBind -> {
                    val variable = node.name
                    val child = node.children[0]
                    when (node.children[1]) {
                        is AOPVariable ->
                            if (child.getProvidedVariableNames().contains(variable.name))
                                res = POPRename(dictionary, variable, node.children[1] as AOPVariable, child)
                            else
                                res = POPBindUndefined(dictionary, variable, child)
                        else -> res = POPBind(dictionary, variable, node.children[1] as AOPBase, child)
                    }
                }
                is LOPJoin -> res = POPJoinHashMap(dictionary, node.children[0], node.children[1], node.optional)
                is LOPTriple -> {
                    val ts = optimizeTriple(node.s)
                    val tp = optimizeTriple(node.p)
                    val to = optimizeTriple(node.o)
                    if (node.graph == null)
                        res = DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, ts.first, tp.first, to.first, ts.second, tp.second, to.second, EIndexPattern.SPO)
                    else
                        res = DistributedTripleStore.getNamedGraph(node.graph).getIterator(transactionID, dictionary, ts.first, tp.first, to.first, ts.second, tp.second, to.second, EIndexPattern.SPO)
                }
                is OPNothing -> res = POPEmptyRow(dictionary)
                else -> {
                    change = false
                }
            }
        } finally {
            if (change)
                onChange()
        }
        res
    })

    fun optimizeTriple(param: OPBase): Pair<String, Boolean> {
        when (param) {
            is AOPVariable -> return Pair(param.name, false)
            is AOPConstant -> return Pair(param.valueToString()!!, true)
            else -> throw UnsupportedOperationException("${classNameToString(this)} , 2 ${classNameToString(param)}")
        }
    }
}
