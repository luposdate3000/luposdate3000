package lupos.s09physicalOperators.multiinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.POPTemporaryStore


class POPJoinNestedLoop : POPBaseNullableIterator {
    override val dictionary: ResultSetDictionary
    override val resultSet: ResultSet
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    val optional: Boolean
    val joinVariables: Set<String>
    private val variablesOldA = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val variablesOldB = mutableListOf<Pair<Variable, Variable>>()//not joined
    private val variablesOldJ = mutableListOf<Pair<Pair<Variable, Variable>, Variable>>()//joined
    private var resultRowA: ResultRow? = null
    private var hadMatchForA = false
    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    constructor(dictionary: ResultSetDictionary, childA: OPBase, childB: OPBase, optional: Boolean) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.children[0] = childA
        this.children[1] = POPTemporaryStore(dictionary, childB)
        this.optional = optional
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        require(children[1].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        var variablesA = children[0].resultSet.getVariableNames()
        var variablesB = children[1].resultSet.getVariableNames()
        joinVariables = variablesA.intersect(variablesB)
        variablesA = variablesA.subtract(joinVariables)
        variablesB = variablesB.subtract(joinVariables)

        for (name in variablesA) {
            variablesOldA.add(Pair(children[0].resultSet.createVariable(name), resultSet.createVariable(name)))
        }
        for (name in variablesB) {
            variablesOldB.add(Pair(children[1].resultSet.createVariable(name), resultSet.createVariable(name)))
        }
        for (name in joinVariables) {
            variablesOldJ.add(Pair(Pair(children[0].resultSet.createVariable(name), children[1].resultSet.createVariable(name)), resultSet.createVariable(name)))
        }
    }

    override fun nnext(): ResultRow? = Trace.trace({ "POPJoinNestedLoop.nnext" }, {
        while (true) {
            var resultRowB: ResultRow?
            if (!children[1].hasNext()) {
                (children[1] as POPTemporaryStore).reset()
                if (optional && !hadMatchForA && resultRowA != null) {
                    var rsNew = resultSet.createResultRow()
                    for (p in variablesOldB) {
                        // TODO reuse resultSet
                        resultSet.setUndefValue(rsNew, p.second)
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
            var rsNew = resultSet.createResultRow()
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
                val a = children[0].resultSet.getValue(resultRowA!![p.first.first])
                val b = children[1].resultSet.getValue(resultRowB[p.first.second])
                if (a != b && (!children[0].resultSet.isUndefValue(resultRowA!!, p.first.first)) && (!children[1].resultSet.isUndefValue(resultRowB, p.first.second))) {
                    joinVariableOk = false
                    break
                }
                if (children[0].resultSet.isUndefValue(resultRowA!!, p.first.first))
                    rsNew[p.second] = resultSet.createValue(b)
                else
                    rsNew[p.second] = resultSet.createValue(a)
            }
            if (!joinVariableOk)
                continue
            hadMatchForA = true
            return rsNew
        }
    }) as ResultRow?

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPJoinNestedLoop")
        res.addAttribute("optional", "" + optional)
        res.addContent(childrenToXML())
        return res
    }
}
