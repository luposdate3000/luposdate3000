package lupos.s09physicalOperators.multiinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.POPTemporaryStore


class POPJoinNestedLoop : POPBaseNullableIterator {
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    //    val children[0]: OPBase
//    val children[1]: POPTemporaryStore
    val optional: Boolean
    val joinVariables: Set<String>
    private val resultSetOldA: ResultSet
    private val variablesOldA = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val resultSetOldB: ResultSet
    private val variablesOldB = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val variablesOldJ = mutableListOf<Pair<Pair<Variable, Variable>, Variable>>()//joined
    private val resultSetNew: ResultSet
    private var resultRowA: ResultRow? = null
    private var hadMatchForA = false
    override val dictionary: ResultSetDictionary
    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    constructor(dictionary: ResultSetDictionary, childA: OPBase, childB: OPBase, optional: Boolean) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        this.children[0] = childA
        this.children[1] = POPTemporaryStore(dictionary, childB)
        this.optional = optional
        resultSetOldA = this.children[0].getResultSet()
        resultSetOldB = this.children[1].getResultSet()
        require(resultSetOldA.dictionary == dictionary || (!(this.children[0] is POPBase)))
        require(resultSetOldB.dictionary == dictionary || (!(this.children[0] is POPBase)))
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
                if (!children[1].hasNext()) {
                    (children[1] as POPTemporaryStore).reset()
                    if (optional && !hadMatchForA && resultRowA != null) {
                        var rsNew = resultSetNew.createResultRow()
                        for (p in variablesOldB) {
                            // TODO reuse resultSet
                            resultSetNew.setUndefValue(rsNew, p.second)
                        }
                        for (p in variablesOldJ) {
                            // TODO reuse resultSet
                            rsNew[p.second] = resultRowA!![p.first.first]
                        }
                        for (p in variablesOldA) {
                            // TODO reuse resultSet
                            rsNew[p.second] = resultRowA!![p.first]
                        }
                        resultRowA = null
                        return rsNew
                    }
                    resultRowA = null
                    if (!children[1].hasNext()) {
                        return null
                    }
                }
                if (resultRowA == null) {
                    if (!children[0].hasNext())
                        return null
                    else {
                        resultRowA = children[0].next()
                        hadMatchForA = false
                    }
                }
                require(resultRowA != null)
                resultRowB = children[1].next()
                var joinVariableOk = true
                var rsNew = resultSetNew.createResultRow()
                for (p in variablesOldA) {
                    // TODO reuse resultSet
                    rsNew[p.second] = resultRowA!![p.first]
                }
                for (p in variablesOldB) {
                    // TODO reuse resultSet
                    rsNew[p.second] = resultRowB[p.first]
                }
                for (p in variablesOldJ) {
                    // TODO reuse resultSet
                    val a = resultSetOldA.getValue(resultRowA!![p.first.first])
                    val b = resultSetOldB.getValue(resultRowB[p.first.second])
                    if (a != b && (!resultSetOldA.isUndefValue(resultRowA!!, p.first.first)) && (!resultSetOldB.isUndefValue(resultRowB, p.first.second))) {
                        joinVariableOk = false
                        break
                    }
                    if (resultSetOldA.isUndefValue(resultRowA!!, p.first.first))
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
        res.addContent(childrenToXML())
        return res
    }
}
