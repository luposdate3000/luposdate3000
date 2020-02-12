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
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    private val resultSetOldA: ResultSet
    private val variablesOldA = mutableListOf<Pair<Variable, Variable>>()
    private val variablesOldAMissing = mutableListOf<Variable>()
    private val resultSetOldB: ResultSet
    private val variablesOldB = mutableListOf<Pair<Variable, Variable>>()
    private val variablesOldBMissing = mutableListOf<Variable>()
    private val resultSetNew: ResultSet
    override val dictionary: ResultSetDictionary
    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    constructor(dictionary: ResultSetDictionary, childA: OPBase, childB: OPBase) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        this.children[0] = childA
        this.children[1] = childB
        resultSetOldA = this.children[0].getResultSet()
        resultSetOldB = this.children[1].getResultSet()
        require(resultSetOldA.dictionary == dictionary || (!(this.children[0] is POPBase)))
        require(resultSetOldB.dictionary == dictionary || (!(this.children[1] is POPBase)))
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

    override fun nnext(): ResultRow? = Trace.trace({ "POPUnion.nnext" }, {
        if (children[0].hasNext()) {
            val rsOld = children[0].next()
            val rsNew = resultSetNew.createResultRow()
            for (p in variablesOldAMissing) {
                resultSetNew.setUndefValue(rsNew, p)
            }
            for (p in variablesOldA) {
                // TODO reuse resultSet
                rsNew[p.second] = rsOld[p.first]
            }
            return rsNew
        }
        if (children[1].hasNext()) {
            val rsOld = children[1].next()
            val rsNew = resultSetNew.createResultRow()
            for (p in variablesOldBMissing) {
                resultSetNew.setUndefValue(rsNew, p)
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
