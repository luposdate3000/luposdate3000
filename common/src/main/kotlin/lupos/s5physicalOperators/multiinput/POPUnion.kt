package lupos.s5physicalOperators.multiinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.singleinput.POPTemporaryStore
import lupos.s5physicalOperators.POPBaseNullableIterator

class POPUnion : POPBaseNullableIterator {
    val childA: POPBase
    val childB: POPBase
    private val resultSetOldA: ResultSet
    private val variablesOldA = mutableListOf<Pair<Variable, Variable>>()
    private val variablesOldAMissing = mutableListOf<Variable>()
    private val resultSetOldB: ResultSet
    private val variablesOldB = mutableListOf<Pair<Variable, Variable>>()
    private val variablesOldBMissing = mutableListOf<Variable>()
    private val resultSetNew = ResultSet()

    override fun getProvidedVariableNames(): List<String> {
        return childA.getProvidedVariableNames() + childB.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return childA.getRequiredVariableNames() + childB.getRequiredVariableNames()
    }

    constructor(childA: POPBase, childB: POPBase) : super() {
        this.childA = childA
        this.childB = POPTemporaryStore(childB)
        resultSetOldA = this.childA.getResultSet()
        resultSetOldB = this.childB.getResultSet()
        var variablesA = resultSetOldA.getVariableNames()
        var variablesB = resultSetOldB.getVariableNames()

        for (name in variablesA) {
            variablesOldA.add(Pair(resultSetOldA.createVariable(name), resultSetNew.createVariable(name)))
            if (!variablesB.contains(name))
                variablesOldBMissing.add(resultSetNew.createVariable(name))
        }
        for (name in variablesB) {
            variablesOldB.add(Pair(resultSetOldB.createVariable(name), resultSetNew.createVariable(name)))
            if (!variablesA.contains(name))
                variablesOldAMissing.add(resultSetNew.createVariable(name))
        }

    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        if (childA.hasNext()) {
            val rsOld = childA.next()
            val rsNew = resultSetNew.createResultRow()
            for (p in variablesOldAMissing) {
                rsNew[p] = resultSetNew.createValue(resultSetNew.getUndefValue())
            }
            for (p in variablesOldA) {
                // TODO reuse resultSet
                rsNew[p.second] = resultSetNew.createValue(resultSetOldA.getValue(rsOld[p.first]))
            }
            return rsNew
        }
        if (childB.hasNext()) {
            val rsOld = childB.next()
            val rsNew = resultSetNew.createResultRow()
            for (p in variablesOldBMissing) {
                rsNew[p] = resultSetNew.createValue(resultSetNew.getUndefValue())
            }
            for (p in variablesOldB) {
                // TODO reuse resultSet
                rsNew[p.second] = resultSetNew.createValue(resultSetOldB.getValue(rsOld[p.first]))
            }
            return rsNew
        }
        return null
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPUnion")
        res.addContent(childA.toXMLElement())
        res.addContent(childB.toXMLElement())
        return res
    }
}
