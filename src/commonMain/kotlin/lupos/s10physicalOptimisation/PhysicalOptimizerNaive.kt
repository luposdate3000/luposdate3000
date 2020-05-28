package lupos.s10physicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPMinus
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class PhysicalOptimizerNaive(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerNaiveID) {
    override val classname = "PhysicalOptimizerNaive"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(12235)
        var res = node
Coverage.statementStart(12236)
        var change = true
Coverage.statementStart(12237)
        try {
Coverage.statementStart(12238)
            val projectedVariables: List<String>
Coverage.statementStart(12239)
            if (parent is LOPProjection) {
Coverage.ifStart(12240)
                projectedVariables = parent.getProvidedVariableNames()
Coverage.statementStart(12241)
            } else if (parent is POPProjection) {
Coverage.ifStart(12242)
                projectedVariables = parent.getProvidedVariableNamesInternal()
Coverage.statementStart(12243)
            } else if (node is POPBase) {
Coverage.ifStart(12244)
                projectedVariables = node.getProvidedVariableNamesInternal()
Coverage.statementStart(12245)
            } else {
Coverage.ifStart(12246)
                projectedVariables = node.getProvidedVariableNames()
Coverage.statementStart(12247)
            }
Coverage.statementStart(12248)
            when (node) {
                is LOPSortAny -> {
Coverage.whenCaseStart(12250)
                    val child = node.children[0]
Coverage.statementStart(12251)
                    val v1 = node.possibleSortOrder
Coverage.statementStart(12252)
                    val v2 = child.mySortPriority
Coverage.statementStart(12253)
                    var flag = v1.size == v2.size
Coverage.statementStart(12254)
                    var i = 0
Coverage.statementStart(12255)
                    while (flag && i < v1.size) {
Coverage.whileLoopStart(12256)
                        if (v1[i].variableName != v2[i].variableName || v1[i].sortType != v2[i].sortType) {
Coverage.ifStart(12257)
                            flag = false
Coverage.statementStart(12258)
                        }
Coverage.statementStart(12259)
                        i++
Coverage.statementStart(12260)
                    }
Coverage.statementStart(12261)
                    if (flag) {
Coverage.ifStart(12262)
                        res = child
Coverage.statementStart(12263)
                    } else {
Coverage.ifStart(12264)
                        res = POPSort(query, projectedVariables, arrayOf<AOPVariable>(), true, child)
Coverage.statementStart(12265)
                    }
Coverage.statementStart(12266)
                }
                is LOPGraphOperation -> {
Coverage.whenCaseStart(12267)
                    res = POPGraphOperation(query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph2type, node.graph2iri, node.action)
Coverage.statementStart(12268)
                }
                is LOPModify -> {
Coverage.whenCaseStart(12269)
                    res = POPModify(query, projectedVariables, node.insert, node.delete, node.children[0])
Coverage.statementStart(12270)
                }
                is LOPModifyData -> {
Coverage.whenCaseStart(12271)
                    res = POPModifyData(query, projectedVariables, node.type, node.data)
Coverage.statementStart(12272)
                }
                is LOPProjection -> {
Coverage.whenCaseStart(12273)
                    res = POPProjection(query, projectedVariables, node.children[0])
Coverage.statementStart(12274)
                }
                is LOPMakeBooleanResult -> {
Coverage.whenCaseStart(12275)
                    if (node.children[0] is LOPProjection || node.children[0] is POPProjection) {
Coverage.ifStart(12276)
                        res = POPMakeBooleanResult(query, projectedVariables, node.children[0].children[0])
Coverage.statementStart(12277)
                    } else {
Coverage.ifStart(12278)
                        res = POPMakeBooleanResult(query, projectedVariables, node.children[0])
Coverage.statementStart(12279)
                    }
Coverage.statementStart(12280)
                }
                is LOPValues -> {
Coverage.whenCaseStart(12281)
                    res = POPValues(query, projectedVariables, node)
Coverage.statementStart(12282)
                }
                is LOPLimit -> {
Coverage.whenCaseStart(12283)
                    res = POPLimit(query, projectedVariables, node.limit, node.children[0])
Coverage.statementStart(12284)
                }
                is LOPDistinct -> {
Coverage.whenCaseStart(12285)
                    res = POPDistinct(query, projectedVariables, node.children[0])
Coverage.statementStart(12286)
                }
                is LOPReduced -> {
Coverage.whenCaseStart(12287)
                    res = POPReduced(query, projectedVariables, node.children[0])
Coverage.statementStart(12288)
                }
                is LOPOffset -> {
Coverage.whenCaseStart(12289)
                    res = POPOffset(query, projectedVariables, node.offset, node.children[0])
Coverage.statementStart(12290)
                }
                is LOPGroup -> {
Coverage.whenCaseStart(12291)
                    res = POPGroup(query, projectedVariables, node.by, node.bindings, node.children[0])
Coverage.statementStart(12292)
                }
                is LOPUnion -> {
Coverage.whenCaseStart(12293)
                    var countA = node.children[0].getChildrenCountRecoursive()
Coverage.statementStart(12294)
                    var countB = node.children[1].getChildrenCountRecoursive()
Coverage.statementStart(12295)
                    if (countA < countB) {
Coverage.ifStart(12296)
                        res = POPUnion(query, projectedVariables, node.children[0], node.children[1])
Coverage.statementStart(12297)
                    } else {
Coverage.ifStart(12298)
                        res = POPUnion(query, projectedVariables, node.children[1], node.children[0])
Coverage.statementStart(12299)
                    }
Coverage.statementStart(12300)
                }
                is LOPMinus -> {
Coverage.whenCaseStart(12301)
                    res = POPMinus(query, projectedVariables, node.children[0], node.children[1])
Coverage.statementStart(12302)
                }
                is LOPSort -> {
Coverage.whenCaseStart(12303)
                    if (parent !is LOPSort) {
Coverage.ifStart(12304)
                        val sortBy = mutableListOf<AOPVariable>()
Coverage.statementStart(12305)
                        sortBy.add(node.by)
Coverage.statementStart(12306)
                        var child = node.children[0]
Coverage.statementStart(12307)
                        while (child is LOPSort) {
Coverage.whileLoopStart(12308)
                            sortBy.add(child.by)
Coverage.statementStart(12309)
                            child = child.children[0]
Coverage.statementStart(12310)
                        }
Coverage.statementStart(12311)
                        res = POPSort(query, projectedVariables, sortBy.toTypedArray(), node.asc, child)
Coverage.statementStart(12312)
                    } else {
Coverage.ifStart(12313)
                        change = false
Coverage.statementStart(12314)
                    }
Coverage.statementStart(12315)
                }
                is LOPFilter -> {
Coverage.whenCaseStart(12316)
                    res = POPFilter(query, projectedVariables, node.children[1] as AOPBase, node.children[0])
Coverage.statementStart(12317)
                }
                is LOPBind -> {
Coverage.whenCaseStart(12318)
                    res = POPBind(query, projectedVariables, node.name, node.children[1] as AOPBase, node.children[0])
Coverage.statementStart(12319)
                }
                is LOPJoin -> {
Coverage.whenCaseStart(12320)
                    res = POPJoinHashMap(query, projectedVariables, node.children[0], node.children[1], node.optional)
Coverage.statementStart(12321)
                }
                is LOPTriple -> {
Coverage.whenCaseStart(12322)
                    res = DistributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.children[it] as AOPBase }, EIndexPattern.SPO)
Coverage.statementStart(12323)
                }
                is OPEmptyRow -> {
Coverage.whenCaseStart(12324)
                    res = POPEmptyRow(query, projectedVariables)
Coverage.statementStart(12325)
                }
                else -> {
Coverage.whenCaseStart(12326)
                    change = false
Coverage.statementStart(12327)
                }
            }
Coverage.statementStart(12328)
        } finally {
Coverage.statementStart(12329)
            if (change) {
Coverage.ifStart(12330)
                SanityCheck { res.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName }) }
Coverage.statementStart(12331)
                res.mySortPriority = node.mySortPriority
Coverage.statementStart(12332)
                res.sortPriorities = node.sortPriorities
Coverage.statementStart(12333)
                onChange()
Coverage.statementStart(12334)
            }
Coverage.statementStart(12335)
        }
Coverage.statementStart(12336)
        return res
    }
}
