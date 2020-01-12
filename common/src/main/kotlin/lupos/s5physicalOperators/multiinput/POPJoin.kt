package lupos.s5physicalOperators.multiinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.singleinput.POPTemporaryStore
import lupos.s5physicalOperators.POPBaseNullableIterator

class POPJoin : POPBaseNullableIterator {
    val childA: POPBase
    val childB: POPTemporaryStore
    val optional: Boolean
    val joinVariables: Set<String>
    private val resultSetOldA: ResultSet
    private val variablesOldA = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val resultSetOldB: ResultSet
    private val variablesOldB = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val variablesOldJ = mutableListOf<Pair<Pair<Variable, Variable>, Variable>>()//joined
    private val resultSetNew = ResultSet()
    private var resultRowA: ResultRow? = null
    private var hadMatchForA = false

    constructor(childA: POPBase, childB: POPBase, optional: Boolean) : super() {
        this.childA = childA
        this.childB = POPTemporaryStore(childB)
        this.optional = optional
        resultSetOldA = this.childA.getResultSet()
        resultSetOldB = this.childB.getResultSet()
        var variablesA = resultSetOldA.getVariableNames()
        var variablesB = resultSetOldB.getVariableNames()
        joinVariables = variablesA.intersect(variablesB)
        variablesA = variablesA.subtract(joinVariables)
        variablesB = variablesB.subtract(joinVariables)

        for (name in variablesA) {
            variablesOldA.add(Pair(resultSetOldA.createVariable(name), resultSetNew.createVariable(name)))
        }
        for (name in variablesB) {
            variablesOldB.add(Pair(resultSetOldB.createVariable(name), resultSetNew.createVariable(name)))
        }
        for (name in joinVariables) {
            variablesOldJ.add(Pair(Pair(resultSetOldA.createVariable(name), resultSetOldB.createVariable(name)), resultSetNew.createVariable(name)))
        }
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        try {
            while (true) {
                var resultRowB: ResultRow? = null
                try {
                    if (!childB.hasNext()) {
                        try {
                            childB.reset()
                            if (optional && !hadMatchForA && resultRowA != null) {
                                var rsNew = resultSetNew.createResultRow()
                                for (p in variablesOldA) {
                                    // TODO reuse resultSet
                                    rsNew[p.second] = resultSetNew.createValue(resultSetOldA.getValue(resultRowA!![p.first]))
                                }
                                resultRowA = null
                                return rsNew
                            }
                            resultRowA = null
                        } catch (e: Throwable) {
                            println("POPJoin f:: " + e)
                            throw e
                        }
                        if (!childB.hasNext()) {
                            return null
                        }
                    }
                    try {
                        if (resultRowA == null) {
                            if (!childA.hasNext())
                                return null
                            else {
                                resultRowA = childA.next()
                                println("join a:" + resultRowA)
                                hadMatchForA = false
                            }
                        }
                    } catch (e: Throwable) {
                        println("POPJoin h:: " + e)
                        throw e
                    }
                    require(resultRowA != null)
                    try {
                        resultRowB = childB.next()
                        println("join b:" + resultRowB)
                        hadMatchForA = true
                    } catch (e: Throwable) {
                        println("POPJoin g:: " + e)
                        throw e
                    }
                    require(resultRowB != null)
                } catch (e: Throwable) {
                    println("POPJoin a:: " + e)
                    throw e
                }
                var joinVariableOk = true
                var rsNew = resultSetNew.createResultRow()
                try {
                    for (p in variablesOldJ) {
                        // TODO reuse resultSet
                        val a = resultSetOldA.getValue(resultRowA!![p.first.first])
                        val b = resultSetOldB.getValue(resultRowB[p.first.second])
                        if (a != b) {
                            println("no match " + a + " " + b)
                            joinVariableOk = false
                            break
                        }
                        rsNew[p.second] = resultSetNew.createValue(a)
                    }
                } catch (e: Throwable) {
                    println("POPJoin b ${resultSetOldA} ${resultSetOldB} ${resultRowA} ${resultRowB}:: " + e)
                    throw e
                }
                try {
                    if (!joinVariableOk)
                        continue
                    for (p in variablesOldA) {
                        // TODO reuse resultSet
                        rsNew[p.second] = resultSetNew.createValue(resultSetOldA.getValue(resultRowA!![p.first]))
                    }
                } catch (e: Throwable) {
                    println("POPJoin c:: " + e)
                    throw e
                }
                try {
                    for (p in variablesOldB) {
                        // TODO reuse resultSet
                        rsNew[p.second] = resultSetNew.createValue(resultSetOldB.getValue(resultRowB[p.first]))
                    }
                } catch (e: Throwable) {
                    println("POPJoin d:: " + e)
                    throw e
                }
                return rsNew
            }
        } catch (e: Throwable) {
            println("POPJoin:: " + e)
            throw e
        }
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${indentation}\tchilds:\n${childA.toString("${indentation}\t\t")}${childB.toString("${indentation}\t\t")}"
    }
}
