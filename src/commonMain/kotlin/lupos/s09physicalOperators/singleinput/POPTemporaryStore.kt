package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.resultFlowConsume
import lupos.s04arithmetikOperators.resultFlowProduce
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPTemporaryStore : POPBase {
    override val operatorID = EOperatorID.POPTemporaryStoreID
    override val classname = "POPTemporaryStore"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val data = mutableListOf<ResultRow>()
    private val variables = mutableListOf<Pair<Variable, Variable>>()

    override fun equals(other: Any?): Boolean {
        if (other !is POPTemporaryStore)
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
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (name in children[0].getProvidedVariableNames()) {
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        }
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPTemporaryStore.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (rsOld in children[0].channel) {
                    resultFlowConsume({ this@POPTemporaryStore }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    for (variable in variables)
                        rsNew[variable.first] = rsOld[variable.second]
                    data.add(rsNew)
                    channel.send(resultFlowProduce({ this@POPTemporaryStore }, { rsNew }))
                }
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

    suspend fun reset() {
        for (c in data) {
            channel.send(resultFlowProduce({ this@POPTemporaryStore }, { c }))
        }
    }

}
