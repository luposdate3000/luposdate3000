package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPMakeBooleanResult : POPBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val resultSetNew: ResultSet
    private val variableNew: Variable
    private var count = 0

    constructor(dictionary: ResultSetDictionary, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
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
        return listOf<String>()
    }

    override fun hasNext(): Boolean = Trace.trace({ "POPMakeBooleanResult.hasNext" }, {
        return count == 0
    }) as Boolean

    override fun next(): ResultRow = Trace.trace({ "POPMakeBooleanResult.next" }, {
        var rsNew = resultSetNew.createResultRow()
        rsNew[variableNew] = resultSetNew.createValue("\"" + children[0].hasNext() + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>")
        count++
        return rsNew
    }) as ResultRow

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPMakeBooleanResult")
        res.addContent(childrenToXML())
        return res
    }
}
