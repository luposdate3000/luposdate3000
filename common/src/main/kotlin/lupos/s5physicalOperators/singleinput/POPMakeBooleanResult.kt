package lupos.s5physicalOperators.singleinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

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
        var rsNew = resultSetNew.createResultRow()
        rsNew[variableNew] = resultSetNew.createValue("\"" + child.hasNext() + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>")
        count++
        return rsNew
    }

    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName}\n"
        res += "${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
        return res
    }
}
