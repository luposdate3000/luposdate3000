package lupos.s10physicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
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
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class PhysicalOptimizerNaive(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerNaiveID) {
    override val classname = "PhysicalOptimizerNaive"

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        var change = true
        try {
            when (node) {
                is LOPSubGroup -> res = node.children[0]
                is LOPGraphOperation -> res = POPGraphOperation(query, node.silent, node.graph1type, node.graph1iri, node.graph2type, node.graph2iri, node.action)
                is LOPModify -> res = POPModify(query, node.insert, node.delete, node.children[0])
                is LOPModifyData -> res = POPModifyData(query, node.type, node.data)
                is LOPProjection -> res = POPProjection(query, node.variables, node.children[0])
                is LOPMakeBooleanResult -> res = POPMakeBooleanResult(query, node.children[0])
                is LOPRename -> res = POPRename(query, node.nameTo, node.nameFrom, node.children[0])
                is LOPValues -> res = POPValues(query, node)
                is LOPLimit -> res = POPLimit(query, node.limit, node.children[0])
                is LOPDistinct -> res = POPDistinct(query, node.children[0])
                is LOPOffset -> res = POPOffset(query, node.offset, node.children[0])
                is LOPGroup -> {
                    if (node.children[1] is POPBind)
                        res = POPGroup(query, node.by, node.children[1] as POPBind, node.children[0])
                    else
                        res = POPGroup(query, node.by, null, node.children[0])
                }
                is LOPUnion -> res = POPUnion(query, node.children[0], node.children[1])
                is LOPSort -> res = POPSort(query, node.by, node.asc, node.children[0])
                is LOPFilter -> res = POPFilter(query, node.children[1] as AOPBase, node.children[0])
                is LOPBind -> {
                    val variable = node.name
                    val child = node.children[0]
                    when (node.children[1]) {
                        is AOPVariable ->
                            if (child.getProvidedVariableNames().contains(variable.name))
                                res = POPRename(query, variable, node.children[1] as AOPVariable, child)
                            else
                                res = POPBind(query, variable, AOPConstant(query, ValueUndef()), child)
                        else -> res = POPBind(query, variable, node.children[1] as AOPBase, child)
                    }
                }
                is LOPJoin -> res = POPJoinHashMap(query, node.children[0], node.children[1], node.optional)
                is LOPTriple -> {
                    res = DistributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.children[it] as AOPBase }, EIndexPattern.SPO)
                }
                is OPNothing -> res = POPEmptyRow(query)
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

}
