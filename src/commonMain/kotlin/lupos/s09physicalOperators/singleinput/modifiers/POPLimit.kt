package lupos.s09physicalOperators.singleinput.modifiers

import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import lupos.s00misc.*
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPLimit : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val limit: Int
    private var count = 0

    constructor(dictionary: ResultSetDictionary, limit: Int, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.limit = limit
        children[0] = child
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (v in children[0].resultSet.getVariableNames())
            variables.add(Pair(resultSet.createVariable(v), children[0].resultSet.createVariable(v)))
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPLimit.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            var count = 0
            for (rsOld in children[0].channel) {
                var rsNew = resultSet.createResultRow()
                if (count >= limit)
                    break
                for (v in variables)
                    rsNew[v.first] = rsOld[v.second]
                count++
                channel.send(rsNew)
            }
            channel.close()
            children[0].channel.close()
        }
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPLimit")
        res.addAttribute("limit", "" + limit)
        res.addContent(childrenToXML())
        return res
    }
}
