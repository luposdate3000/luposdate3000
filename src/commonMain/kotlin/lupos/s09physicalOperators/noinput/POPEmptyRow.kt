package lupos.s09physicalOperators.noinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPEmptyRow(override val dictionary: ResultSetDictionary) : POPBase() {
    override val children: Array<OPBase> = arrayOf()
    private val resultSetNew = ResultSet(dictionary)

    private var first = true

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPEmptyRow.hasNext")
            return first
        } finally {
            Trace.stop("POPEmptyRow.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPEmptyRow.next")
            first = false
            return resultSetNew.createResultRow()
        } finally {
            Trace.stop("POPEmptyRow.next")
        }
    }

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
