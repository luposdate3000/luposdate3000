package lupos.s07physicalOperators.singleinput.modifiers

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Value
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s07physicalOperators.singleinput.POPSingleInputBaseNullableIterator


class POPDistinct : POPSingleInputBaseNullableIterator {
    private var data: MutableList<ResultRow>? = null
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null

    constructor(child: OPBase) : super(child) {
        resultSetOld = child.getResultSet()
        for (name in resultSetOld.getVariableNames()) {
            variables.add(Pair(resultSetNew.createVariable(name), resultSetOld.createVariable(name)))
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPDistinct.nnext")
            if (data == null) {
                val tmpMutableMap = mutableMapOf<String, ResultRow>()
                while (child.hasNext()) {
                    val rsOld = child.next()
                    val rsNew = resultSetNew.createResultRow()
                    var key: String = ""
                    for (variable in variables) {
                        val tmp = resultSetOld.getValue(rsOld[variable.second])
                        rsNew[variable.first] = resultSetNew.createValue(tmp)
                        key += "-" + tmp
                    }
                    tmpMutableMap[key] = rsNew
                }
                data = mutableListOf<ResultRow>()
                for (k in tmpMutableMap.keys) {
                    data!!.add(tmpMutableMap[k]!!)
                }
                iterator = data!!.listIterator()
            }
            if (iterator == null || !iterator!!.hasNext())
                return null
            return iterator!!.next()
        } finally {
            Trace.stop("POPDistinct.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPDistinct")
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
