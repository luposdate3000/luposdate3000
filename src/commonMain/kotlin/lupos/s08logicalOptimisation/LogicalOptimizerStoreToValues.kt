package lupos.s08logicalOptimisation
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class LogicalOptimizerStoreToValues(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerStoreToValuesID) {
    override val classname = "LogicalOptimizerStoreToValues"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9887)
        var res: OPBase = node
Coverage.statementStart(9888)
        if (node is LOPTriple && REPLACE_STORE_WITH_VALUES) {
Coverage.ifStart(9889)
            var hashCode = 0L
Coverage.statementStart(9890)
            for (c in node.children) {
Coverage.forLoopStart(9891)
                hashCode += c.uuid + c.toString().hashCode()
Coverage.statementStart(9892)
            }
Coverage.statementStart(9893)
            if (hashCode == -1L) {
Coverage.ifStart(9894)
                //just avoid this flag ...
Coverage.statementStart(9895)
                hashCode = 0L
Coverage.statementStart(9896)
            }
Coverage.statementStart(9897)
            if (node.alreadyCheckedStore != hashCode) {
Coverage.ifStart(9898)
                node.alreadyCheckedStore = hashCode
Coverage.statementStart(9899)
                //dont query the same statements twice ... 
Coverage.statementStart(9900)
                runBlocking {
Coverage.statementStart(9901)
                    var variables = mutableListOf<String>()
Coverage.statementStart(9902)
                    for (c in node.children) {
Coverage.forLoopStart(9903)
                        if (c is AOPVariable) {
Coverage.ifStart(9904)
                            variables.add(c.name)
Coverage.statementStart(9905)
                        }
Coverage.statementStart(9906)
                    }
Coverage.statementStart(9907)
                    if (variables.size == 0) {
Coverage.ifStart(9908)
                        val idx = LOPTriple.getIndex(node.children, listOf<String>())
Coverage.statementStart(9909)
                        val tmp = DistributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.children[it] as AOPBase }, idx)
Coverage.statementStart(9910)
                        val tmp2 = tmp.evaluate()
Coverage.statementStart(9911)
                        SanityCheck.check { tmp2.hasCountMode() }
Coverage.statementStart(9912)
                        if (tmp2.count > 0) {
Coverage.ifStart(9913)
                            res = OPEmptyRow(query)
Coverage.statementStart(9914)
                        } else {
Coverage.ifStart(9915)
                            res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9916)
                        }
Coverage.statementStart(9917)
                        onChange()
Coverage.statementStart(9918)
                    } else if (variables.size == 1) {
Coverage.ifStart(9919)
                        val idx = LOPTriple.getIndex(node.children, listOf<String>())
Coverage.statementStart(9920)
                        val tmp = DistributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.children[it] as AOPBase }, idx)
Coverage.statementStart(9921)
                        val tmp2 = tmp.evaluate()
Coverage.statementStart(9922)
                        val columns = tmp2.columns
Coverage.statementStart(9923)
                        SanityCheck.check { columns.size == 1 }
Coverage.statementStart(9924)
                        val data = IntArray(5)
Coverage.statementStart(9925)
                        var i = 0
Coverage.statementStart(9926)
                        val iterator = columns[variables[0]]!!
Coverage.statementStart(9927)
                        while (i < data.size) {
Coverage.whileLoopStart(9928)
                            val t = iterator.next()
Coverage.statementStart(9929)
                            if (t != null) {
Coverage.ifStart(9930)
                                data[i] = t
Coverage.statementStart(9931)
                                i++
Coverage.statementStart(9932)
                            } else {
Coverage.ifStart(9933)
                                break
                            }
Coverage.statementStart(9934)
                        }
Coverage.statementStart(9935)
                        if (i == 0) {
Coverage.ifStart(9936)
                            res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9937)
                            onChange()
Coverage.statementStart(9938)
                        } else if (i == 1) {
Coverage.ifStart(9939)
                            res = LOPBind(query, AOPVariable(query, variables[0]), AOPConstant(query, data[0]))
Coverage.statementStart(9940)
                            onChange()
Coverage.statementStart(9941)
                        } else if (i < 5) {
Coverage.ifStart(9942)
                            var constants = mutableListOf<AOPValue>()
Coverage.statementStart(9943)
                            for (j in 0 until i) {
Coverage.forLoopStart(9944)
                                constants.add(AOPValue(query, listOf(AOPConstant(query, data[j]))))
Coverage.statementStart(9945)
                            }
Coverage.statementStart(9946)
                            res = LOPValues(query, listOf(AOPVariable(query, variables[0])), constants)
Coverage.statementStart(9947)
                            onChange()
Coverage.statementStart(9948)
                        }
Coverage.statementStart(9949)
                    }
Coverage.statementStart(9950)
                }
Coverage.statementStart(9951)
            }
Coverage.statementStart(9952)
        }
Coverage.statementStart(9953)
        return res
    }
}
