package lupos.s10physicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortType
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class PhysicalOptimizerTripleIndex(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerTripleIndexID) {
    override val classname = "PhysicalOptimizerTripleIndex"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(12337)
        var res = node
Coverage.statementStart(12338)
        if (node is LOPTriple) {
Coverage.ifStart(12339)
            val projectedVariables: List<String>
Coverage.statementStart(12340)
            if (parent is LOPProjection) {
Coverage.ifStart(12341)
                projectedVariables = parent.getProvidedVariableNames()
Coverage.statementStart(12342)
            } else if (parent is POPProjection) {
Coverage.ifStart(12343)
                projectedVariables = parent.getProvidedVariableNamesInternal()
Coverage.statementStart(12344)
            } else if (node is POPBase) {
Coverage.ifStart(12345)
                projectedVariables = node.getProvidedVariableNamesInternal()
Coverage.statementStart(12346)
            } else {
Coverage.ifStart(12347)
                projectedVariables = node.getProvidedVariableNames()
Coverage.statementStart(12348)
            }
Coverage.statementStart(12349)
            onChange()
Coverage.statementStart(12350)
            val store = DistributedTripleStore.getNamedGraph(query, node.graph)
Coverage.statementStart(12351)
            val params = Array<AOPBase>(3) {
Coverage.statementStart(12352)
                var res2 = node.children[it] as AOPBase
Coverage.statementStart(12353)
                SanityCheck {
Coverage.statementStart(12354)
                    if (res2 is AOPVariable) {
Coverage.ifStart(12355)
                        SanityCheck.check { projectedVariables.contains(res2.name) || res2.name == "_" }
Coverage.statementStart(12356)
                    }
Coverage.statementStart(12357)
                }
Coverage.statementStart(12358)
/*return*/res2
            }
Coverage.statementStart(12359)
            SanityCheck {
Coverage.statementStart(12360)
                for (i in 0 until node.mySortPriority.size) {
Coverage.forLoopStart(12361)
                    SanityCheck.check { node.mySortPriority[i].sortType == ESortType.FAST }
Coverage.statementStart(12362)
                }
Coverage.statementStart(12363)
            }
Coverage.statementStart(12364)
            res = store.getIterator(params, LOPTriple.getIndex(node.children, node.mySortPriority.map { it.variableName }))
Coverage.statementStart(12365)
            SanityCheck { res.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName }) }
Coverage.statementStart(12366)
            res.mySortPriority = node.mySortPriority
Coverage.statementStart(12367)
            res.sortPriorities = node.sortPriorities
Coverage.statementStart(12368)
        }
Coverage.statementStart(12369)
        return res
    }
}
