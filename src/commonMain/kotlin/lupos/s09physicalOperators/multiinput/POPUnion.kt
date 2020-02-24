package lupos.s09physicalOperators.multiinput
import lupos.s04arithmetikOperators.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPUnion : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    private val variablesOld = arrayOf(mutableListOf<Pair<Variable, Variable>>(), mutableListOf<Pair<Variable, Variable>>())
    private val variablesOldMissing = arrayOf(mutableListOf<Variable>(), mutableListOf<Variable>())

    override fun equals(other: Any?): Boolean {
        if (other !is POPUnion)
            return false
        if (dictionary !== other.dictionary)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

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
            variablesOld[0].add(Pair(children[0].resultSet.createVariable(name), resultSet.createVariable(name)))
            if (!variablesB.contains(name))
                variablesOldMissing[1].add(resultSet.createVariable(name))
        }
        for (name in variablesB) {
            variablesOld[1].add(Pair(children[1].resultSet.createVariable(name), resultSet.createVariable(name)))
            if (!variablesA.contains(name))
                variablesOldMissing[0].add(resultSet.createVariable(name))
        }

    }

    override fun evaluate() = Trace.trace<Unit>({ "POPUnion.evaluate" }, {
        for (c in children)
            c.evaluate()
        CoroutinesHelper.run {
            try {
                for (idx in children.indices) {
                    val c = children[idx]
                    for (rsOld in c.channel) {
resultFlowConsume({this@POPUnion},{c},{rsOld})
                        val rsNew = resultSet.createResultRow()
                        for (p in variablesOldMissing[idx])
                            resultSet.setUndefValue(rsNew, p)
                        for (p in variablesOld[idx])
                            rsNew[p.second] = rsOld[p.first]
                        channel.send(resultFlowProduce({this@POPUnion},{rsNew}))
                    }
                }
                channel.close()
                for (c in children)
                    c.channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                for (c in children)
                    c.channel.close(e)
            }
        }
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPUnion")
        res.addContent(childrenToXML())
        return res
    }
}
