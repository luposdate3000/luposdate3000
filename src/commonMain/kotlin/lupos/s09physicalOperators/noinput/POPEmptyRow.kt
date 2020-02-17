package lupos.s09physicalOperators.noinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPEmptyRow : POPBase {
    override val dictionary: ResultSetDictionary
    override val resultSet: ResultSet
    override val children: Array<OPBase> = arrayOf()
    private var first = true

    constructor(dictionary: ResultSetDictionary) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPEmptyRow.evaluate" }, {
        CoroutinesHelper.run {
            try {
                channel.send(resultSet.createResultRow())
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPEmptyRow")
        return res
    }
}
