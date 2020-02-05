package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup


class POPMakeBooleanResult : POPBase {
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val resultSetNew = ResultSet()
    private val variableNew: Variable
    private var count = 0

    constructor(child: OPBase) : super() {
        children[0] = child
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
        return children[0].getRequiredVariableNames()
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPMakeBooleanResult.hasNext")
            return count == 0
        } finally {
            Trace.stop("POPMakeBooleanResult.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPMakeBooleanResult.next")
            var rsNew = resultSetNew.createResultRow()
            rsNew[variableNew] = resultSetNew.createValue("\"" + children[0].hasNext() + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>")
            count++
            return rsNew
        } finally {
            Trace.stop("POPMakeBooleanResult.next")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPMakeBooleanResult")
        res.addContent(childrenToXML())
        return res
    }
}
