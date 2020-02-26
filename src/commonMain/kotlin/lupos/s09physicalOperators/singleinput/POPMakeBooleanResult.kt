package lupos.s09physicalOperators.singleinput

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


class POPMakeBooleanResult : POPBase {
    override val operatorID = EOperatorID.POPMakeBooleanResultID
    override val classname = "POPMakeBooleanResult"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val variableNew: Variable
    private var count = 0
    override fun equals(other: Any?): Boolean {
        if (other !is POPMakeBooleanResult)
            return false
        if (dictionary !== other.dictionary)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.variableNew = resultSet.createVariable("?boolean")
    }

    override fun getProvidedVariableNames() = mutableListOf("?boolean")

    override fun getRequiredVariableNames(): List<String> {
        return listOf()
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPMakeBooleanResult.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                var first = true
                for (c in children[0].channel) {
                    resultFlowConsume({ this@POPMakeBooleanResult }, { children[0] }, { c })
                    first = false
                    children[0].channel.close()
                    break
                }
                var rsNew = resultSet.createResultRow()
                rsNew[variableNew] = resultSet.createValue("\"" + (!first) + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>")
                channel.send(resultFlowProduce({ this@POPMakeBooleanResult }, { rsNew }))
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

}
