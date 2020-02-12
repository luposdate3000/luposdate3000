package lupos.s09physicalOperators.noinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPEmptyRow(override val dictionary: ResultSetDictionary) : POPBase() {
    override val children: Array<OPBase> = arrayOf()
    private val resultSetNew = ResultSet(dictionary)

    private var first = true

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean = Trace.trace("POPEmptyRow.hasNext") {
        return first
    } as Boolean

    override fun next(): ResultRow = Trace.trace("POPEmptyRow.next") {
        first = false
        return resultSetNew.createResultRow()
    } as ResultRow

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
