package lupos.s09physicalOperators.noinput

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


    override fun hasNext(): Boolean = Trace.trace({ "POPEmptyRow.hasNext" }, {
        return first
    }) as Boolean

    override fun next(): ResultRow = Trace.trace({ "POPEmptyRow.next" }, {
        first = false
        return resultSet.createResultRow()
    }) as ResultRow

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
