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


class POPUnion : POPBaseNullableIterator {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    private val variablesOldA = mutableListOf<Pair<Variable, Variable>>()
    private val variablesOldAMissing = mutableListOf<Variable>()
    private val variablesOldB = mutableListOf<Pair<Variable, Variable>>()
    private val variablesOldBMissing = mutableListOf<Variable>()
    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    constructor(dictionary: ResultSetDictionary, childA: OPBase, childB: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.children[0] = childA
        this.children[1] = childB
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        require(children[1].resultSet.dictionary == dictionary || (!(this.children[1] is POPBase)))
        var variablesA = children[0].resultSet.getVariableNames()
        var variablesB = children[1].resultSet.getVariableNames()

        for (name in variablesA) {
            variablesOldA.add(Pair(children[0].resultSet.createVariable(name), resultSet.createVariable(name)))
            if (!variablesB.contains(name))
                variablesOldBMissing.add(resultSet.createVariable(name))
        }
        for (name in variablesB) {
            variablesOldB.add(Pair(children[1].resultSet.createVariable(name), resultSet.createVariable(name)))
            if (!variablesA.contains(name))
                variablesOldAMissing.add(resultSet.createVariable(name))
        }

    }

    override fun nnext(): ResultRow? = Trace.trace({ "POPUnion.nnext" }, {
        if (children[0].hasNext()) {
            val rsOld = children[0].next()
            val rsNew = resultSet.createResultRow()
            for (p in variablesOldAMissing) {
                resultSet.setUndefValue(rsNew, p)
            }
            for (p in variablesOldA) {
                // TODO reuse resultSet
                rsNew[p.second] = rsOld[p.first]
            }
            return rsNew
        }
        if (children[1].hasNext()) {
            val rsOld = children[1].next()
            val rsNew = resultSet.createResultRow()
            for (p in variablesOldBMissing) {
                resultSet.setUndefValue(rsNew, p)
            }
            for (p in variablesOldB) {
                rsNew[p.second] = rsOld[p.first]
            }
            return rsNew
        }
        return null
    }) as ResultRow?

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPUnion")
        res.addContent(childrenToXML())
        return res
    }
}
