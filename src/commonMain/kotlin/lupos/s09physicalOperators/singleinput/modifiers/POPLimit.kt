package lupos.s09physicalOperators.singleinput.modifiers
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.data.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPSingleInputBase



class POPLimit : POPSingleInputBase {
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val limit: Int
    private var count = 0

    constructor(limit: Int, child: OPBase) : super(child) {
        this.limit = limit
        resultSetOld = child.getResultSet()
        for (v in resultSetOld.getVariableNames())
            variables.add(Pair(resultSetNew.createVariable(v), resultSetOld.createVariable(v)))
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPLimit.hasNext")
            return count < limit && child.hasNext()
        } finally {
            Trace.stop("POPLimit.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPLimit.next")
            var rsNew = resultSetNew.createResultRow()
            val rsOld = child.next()
            for (v in variables) {
                // TODO reuse resultSet
                rsNew[v.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[v.second]))
            }
            count++
            return rsNew
        } finally {
            Trace.stop("POPLimit.next")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPLimit")
        res.addAttribute("limit", "" + limit)
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
