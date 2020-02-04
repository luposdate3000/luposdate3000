package lupos.s07physicalOperators.singleinput.modifiers

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.singleinput.modifiers.POPLimit
import lupos.s07physicalOperators.singleinput.POPSingleInputBaseNullableIterator


class POPOffset : POPSingleInputBaseNullableIterator {
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val offset: Int
    private var count = 0

    constructor(offset: Int, child: OPBase) : super(child) {
        this.offset = offset
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

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPOffset.nnext")
            while (count < offset) {
                if (child.hasNext()) {
                    child.next()
                    count++
                } else
                    count = offset
            }
            if (!child.hasNext())
                return null
            var rsNew = resultSetNew.createResultRow()
            val rsOld = child.next()
            for (v in variables) {
                // TODO reuse resultSet
                rsNew[v.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[v.second]))
            }
            return rsNew
        } finally {
            Trace.stop("POPOffset.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPOffset")
        res.addAttribute("offset", "" + offset)
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
