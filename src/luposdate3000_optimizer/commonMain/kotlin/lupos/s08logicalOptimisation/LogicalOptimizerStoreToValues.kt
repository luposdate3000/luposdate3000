package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
import lupos.s00misc.REPLACE_STORE_WITH_VALUES
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s15tripleStoreDistributed.distributedTripleStore

class LogicalOptimizerStoreToValues(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerStoreToValuesID) {
    override val classname: String = "LogicalOptimizerStoreToValues"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPTriple && REPLACE_STORE_WITH_VALUES) {
            var hashCode = 0L
            for (c in node.getChildren()) {
                hashCode += c.getUUID() + c.toString().hashCode()
            }
            if (hashCode == -1L) {
                //just avoid this flag ...
                hashCode = 0L
            }
            if (node.alreadyCheckedStore != hashCode) {
                node.alreadyCheckedStore = hashCode
                //dont query the same statements twice ... 
                val variables = mutableListOf<String>()
                for (c in node.getChildren()) {
                    if (c is AOPVariable) {
                        variables.add(c.name)
                    }
                }
                if (variables.size == 0) {
                    val idx = LOPTriple.getIndex(node.getChildren(), listOf())
                    val tmp = distributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.getChildren()[it] as IAOPBase }, idx, Partition())
                    val tmp2 = tmp.evaluate(Partition())
                    SanityCheck.check { tmp2.hasCountMode() }
                    res = if (tmp2.count() > 0) {//closed childs due to reading from count
                        OPEmptyRow(query)
                    } else {
                        OPNothing(query, node.getProvidedVariableNames())
                    }
                    onChange()
                } else if (variables.size == 1) {
                    val idx = LOPTriple.getIndex(node.getChildren(), listOf())
                    val tmp = distributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.getChildren()[it] as IAOPBase }, idx, Partition())
                    val tmp2 = tmp.evaluate(Partition())
                    val columns = tmp2.columns
                    SanityCheck.check { columns.size == 1 }
                    val data = IntArray(5)
                    var i = 0
                    val iterator = columns[variables[0]]!!
                    while (i < data.size) {
                        val t = iterator.next()
                        if (t != ResultSetDictionaryExt.nullValue) {
                            data[i] = t
                            i++
                        } else {
                            break
                        }
                    }
                    when {
                        i == 0 -> {
                            res = OPNothing(query, node.getProvidedVariableNames())
                            onChange()
                        }
                        i == 1 -> {
                            res = LOPBind(query, AOPVariable(query, variables[0]), AOPConstant(query, data[0]))
                            onChange()
                        }
                        i < 5 -> {
                            val constants = mutableListOf<AOPValue>()
                            for (j in 0 until i) {
                                constants.add(AOPValue(query, listOf(AOPConstant(query, data[j]))))
                            }
                            res = LOPValues(query, listOf(AOPVariable(query, variables[0])), constants)
                            onChange()
                        }
                    }
                    for ((k, v) in columns) {
                        v.close()
                    }
                }
            }
        }
        return res
    }
}
