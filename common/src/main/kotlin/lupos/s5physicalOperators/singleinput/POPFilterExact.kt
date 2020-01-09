package lupos.s5physicalOperators.singleinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

class POPFilterExact : POPSingleInputBase {
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val filterVariable: Variable
    private val filterValue: String
    private var nextRow: ResultRow? = null
    private var hasNextRow: Boolean = false
    private var hasInit: Boolean

    constructor(variable: LOPVariable, value: String, child: POPBase) : super(child) {
        resultSetOld = child.getResultSet()
        val variableNames = resultSetOld.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(it: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size, init = fun(it: Int) = (null as Variable?))
        var i = 0
        for (name in variableNames) {
            variablesOld[i] = resultSetOld.createVariable(name)
            variablesNew[i] = resultSetNew.createVariable(name)
            i++
        }
        filterVariable = resultSetOld.createVariable(variable.name)
        filterValue = value
        hasInit = false;
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    fun calculateNext() {
        while (true) {
            val res = child.hasNext()
            if (!res) {
                hasNextRow = false
                return
            }
            nextRow = child.next()
            if (resultSetOld.getValue(nextRow!![filterVariable]) == filterValue) {
                hasNextRow = true
                return
            }
        }
    }

    override fun hasNext(): Boolean {
        if (!hasInit) {
            calculateNext()
            hasInit = true
        }
        return hasNextRow
    }

    override fun next(): ResultRow {
        var rsNew = resultSetNew.createResultRow()
        val rsOld = nextRow!!
        for (i in variablesNew.indices) {
            // TODO reuse resultSet
            rsNew[variablesNew[i]!!] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]!!]))
        }
        calculateNext()
        return rsNew
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tvariable:\n${indentation}\t\t${resultSetOld.getVariable(filterVariable)}\n${indentation}\tvalue:\n${indentation}\t\t${filterValue}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}
