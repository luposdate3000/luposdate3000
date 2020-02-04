package lupos.s09physicalOperators.multiinput
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinNestedLoop
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.POPTemporaryStore



class POPUnion : POPBaseNullableIterator {
    val childA: OPBase
    val childB: OPBase
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

    constructor(childA: OPBase, childB: OPBase) : super() {
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
        try {
            Trace.start("POPUnion.nnext")
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
        } finally {
            Trace.stop("POPUnion.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPUnion")
        res.addContent(XMLElement("childA").addContent(childA.toXMLElement()))
        res.addContent(XMLElement("childB").addContent(childB.toXMLElement()))
        return res
    }
}
