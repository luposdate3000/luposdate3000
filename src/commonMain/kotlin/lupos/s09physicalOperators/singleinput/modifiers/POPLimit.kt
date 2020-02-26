package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPLimit : POPBase {
    override val operatorID = EOperatorID.POPLimitID
    override val classname = "POPLimit"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val limit: Int
    private var count = 0

    override fun equals(other: Any?): Boolean {
        if (other !is POPLimit)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (limit != other.limit)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, limit: Int, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.limit = limit
        children[0] = child
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (v in children[0].getProvidedVariableNames())
            variables.add(Pair(resultSet.createVariable(v), children[0].resultSet.createVariable(v)))
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPLimit.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                var count = 0
                for (rsOld in children[0].channel) {
                    resultFlowConsume({ this@POPLimit }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    if (count >= limit)
                        break
                    for (v in variables)
                        rsNew[v.first] = rsOld[v.second]
                    count++
                    channel.send(resultFlowProduce({ this@POPLimit }, { rsNew }))
                }
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
