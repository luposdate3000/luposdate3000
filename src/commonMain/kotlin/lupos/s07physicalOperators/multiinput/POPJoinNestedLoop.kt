package lupos.s07physicalOperators.multiinput

import lupos.s00misc.*
import lupos.s07physicalOperators.singleinput.POPTemporaryStore
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.multiinput.POPJoinHashMap
import lupos.s03buildOperatorGraph.data.LOPVariable

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


class POPJoinNestedLoop : POPBaseNullableIterator {
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
            Trace.start("POPJoinNestedLoop.nnext")
            while (true) {
                var resultRowB: ResultRow?
                if (!childB.hasNext()) {
                    childB.reset()
                    if (optional && !hadMatchForA && resultRowA != null) {
                        var rsNew = resultSetNew.createResultRow()
                        for (p in variablesOldB) {
                            // TODO reuse resultSet
                            rsNew[p.second] = resultSetNew.createValue(resultSetNew.getUndefValue())
                        }
                        for (p in variablesOldJ) {
                            // TODO reuse resultSet
                            rsNew[p.second] = resultSetNew.createValue(resultSetOldA.getValue(resultRowA!![p.first.first]))
                        }
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
                        hadMatchForA = false
                    }
                }
                require(resultRowA != null)
                resultRowB = childB.next()
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
                hadMatchForA = true
                return rsNew
            }
        } finally {
            Trace.stop("POPJoinNestedLoop.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPJoinNestedLoop")
        res.addAttribute("optional", "" + optional)
        res.addContent(XMLElement("childA").addContent(childA.toXMLElement()))
        res.addContent(XMLElement("childB").addContent(childB.toXMLElement()))
        return res
    }

    companion object {
        fun fromXMLElement(xml: XMLElement): POPJoinNestedLoop {
            return POPJoinNestedLoop(XMLElement.convertToPOPBase(xml["childA"]!!), XMLElement.convertToPOPBase(xml["childB"]!!), xml.attributes["optional"]!!.toBoolean())
        }
    }
}
