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

    override fun getProvidedVariableNames(): List<String> {
        return childA.getProvidedVariableNames() + childB.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return childA.getRequiredVariableNames() + childB.getRequiredVariableNames()
    }

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

        println("JOIN variablesA ${variablesA}")
        println("JOIN variablesB ${variablesB}")
        println("JOIN variablesJ ${joinVariables}")

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
        while (true) {
            var resultRowB: ResultRow?
            if (!childB.hasNext()) {
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
                if (!childB.hasNext()) {
                    return null
                }
            }
            if (resultRowA == null) {
                if (!childA.hasNext())
                    return null
                else {
                    resultRowA = childA.next()
                    println("join a:" + resultRowA)
                    hadMatchForA = false
                }
            }
            require(resultRowA != null)
            resultRowB = childB.next()
            println("join b:" + resultRowB)
            hadMatchForA = true
            var joinVariableOk = true
            var rsNew = resultSetNew.createResultRow()
            for (p in variablesOldA) {
                // TODO reuse resultSet
                rsNew[p.second] = resultSetNew.createValue(resultSetOldA.getValue(resultRowA!![p.first]))
            }
            for (p in variablesOldB) {
                // TODO reuse resultSet
                rsNew[p.second] = resultSetNew.createValue(resultSetOldB.getValue(resultRowB[p.first]))
            }
            for (p in variablesOldJ) {
                // TODO reuse resultSet
                val a = resultSetOldA.getValue(resultRowA!![p.first.first])
                val b = resultSetOldB.getValue(resultRowB[p.first.second])
                if (a != b && a != resultSetOldA.getUndefValue() && b != resultSetOldB.getUndefValue()) {
                    println("no match " + a + " " + b + " ${a != b} ${a != resultSetOldA.getUndefValue()} ${b != resultSetOldB.getUndefValue()}")
                    joinVariableOk = false
                    break
                }
                if (a == resultSetOldA.getUndefValue())
                    rsNew[p.second] = resultSetNew.createValue(b)
                else
                    rsNew[p.second] = resultSetNew.createValue(a)
            }
            if (!joinVariableOk)
                continue
            println("join c:" + rsNew)
            return rsNew
        }
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${indentation}\tchilds:\n${childA.toString("${indentation}\t\t")}${childB.toString("${indentation}\t\t")}"
    }
}
