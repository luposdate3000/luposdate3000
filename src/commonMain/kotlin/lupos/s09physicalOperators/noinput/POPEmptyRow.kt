package lupos.s09physicalOperators.noinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPEmptyRow : POPBase {
    override val operatorID = EOperatorID.POPEmptyRowID
    override val classname = "POPEmptyRow"
    override val dictionary: ResultSetDictionary
    override val resultSet: ResultSet
    override val children: Array<OPBase> = arrayOf()
    private var first = true

    override fun equals(other: Any?): Boolean {
        if (other !is POPEmptyRow)
            return false
        if (dictionary !== other.dictionary)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPEmptyRow.evaluate" }, {
        CoroutinesHelper.run {
            try {
                channel.send(resultFlowProduce({ this@POPEmptyRow }, { resultSet.createResultRow() }))
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

}
