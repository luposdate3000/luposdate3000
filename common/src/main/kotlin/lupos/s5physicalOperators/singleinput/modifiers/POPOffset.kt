package lupos.s5physicalOperators.singleinput.modifiers

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.singleinput.POPSingleInputBaseNullableIterator

class POPOffset : POPSingleInputBaseNullableIterator {
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private val offset: Int
    private var count = 0

    constructor(offset: Int, child: POPBase) : super(child) {
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
    }

    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName}\n${indentation}\toffset: ${offset}\n"
        res += "${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
        return res
    }
}
