package lupos.s07physicalOperators.singleinput
import lupos.s00misc.*

import lupos.s07physicalOperators.singleinput.POPSingleInputBase
import lupos.s07physicalOperators.singleinput.POPGroup
import lupos.s07physicalOperators.singleinput.POPFilter
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.POPBase
import lupos.s03buildOperatorGraph.data.LOPVariable

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


class POPMakeBooleanResult : POPSingleInputBase {
    private val resultSetNew = ResultSet()
    private val variableNew: Variable
    private var count = 0

    constructor(child: POPBase) : super(child) {
        this.variableNew = resultSetNew.createVariable("?boolean")
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        res.add("?boolean")
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun hasNext(): Boolean {
        return count == 0
    }

    override fun next(): ResultRow {
Trace.start(this)
        var rsNew = resultSetNew.createResultRow()
        rsNew[variableNew] = resultSetNew.createValue("\"" + child.hasNext() + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>")
        count++
Trace.stop(this)
        return rsNew
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPMakeBooleanResult")
        res.addContent(child.toXMLElement())
        return res
    }
}
