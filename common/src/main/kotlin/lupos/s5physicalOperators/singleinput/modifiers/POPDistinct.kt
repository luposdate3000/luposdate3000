package lupos.s5physicalOperators.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s4resultRepresentation.Value
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPBaseNullableIterator

class POPDistinct : POPSingleInputBaseNullableIterator {
    private var data: MutableList<ResultRow>? = null
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null

    constructor(child: POPBase) : super(child) {
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
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPDistinct")
        res.addContent(child.toXMLElement())
        return res
    }
}
