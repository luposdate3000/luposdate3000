package lupos.s5physicalOperators.singleinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPBaseNullableIterator

class POPJOIN : POPBaseNullableIterator {
    val childA: POPBase
    val childB: POPBase
    val joinVariables: Set<String>
    private val resultSetOldA: ResultSet
    private val variablesOldA = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val resultSetOldB: ResultSet
    private val variablesOldB = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val variablesOldJ = mutableListOf<Pair<Pair<Variable, Variable>, Variable>>()//joined
    private val resultSetNew = ResultSet()
    private var resultRowA: ResultRow? = null

    constructor(childA: POPBase, childB: POPBase) : super() {
        this.childA = childA
        this.childB = childB
        resultSetOldA = childA.getResultSet()
        resultSetOldB = childB.getResultSet()
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
        while (true) {
            var resultRowB: ResultRow? = null
            if (!childB.hasNext()) {
                //TODO childB.reset()
                resultRowA = null
            } else {
                resultRowB = childB.next()
            }
            if (resultRowA == null) {
                if (!childA.hasNext())
                    return null
                else
                    resultRowA = childA.next()
            }

            var rsNew = resultSetNew.createResultRow()
            var joinVariableOk = true
            for (p in variablesOldJ) {
                // TODO reuse resultSet
                val a = resultSetOldA!!.getValue(resultRowA!![p.first.first])
                val b = resultSetOldB!!.getValue(resultRowB!![p.first.second])
                if (a != b) {
                    joinVariableOk = false
                    break
                }
                rsNew[p.second] = resultSetNew.createValue(a)
            }
            if (!joinVariableOk)
                continue
            for (p in variablesOldA) {
                // TODO reuse resultSet
                rsNew[p.second] = resultSetNew.createValue(resultSetOldA.getValue(resultRowA!![p.first]))
            }
            for (p in variablesOldB) {
                // TODO reuse resultSet
                rsNew[p.second] = resultSetNew.createValue(resultSetOldB.getValue(resultRowB!![p.first]))
            }
            return rsNew
        }
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${indentation}\tchilds:\n${childA.toString("${indentation}\t\t")}${childB.toString("${indentation}\t\t")}"
    }
}
