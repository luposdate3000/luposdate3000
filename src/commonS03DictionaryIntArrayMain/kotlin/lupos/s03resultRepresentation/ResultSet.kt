package lupos.s03resultRepresentation

import java.io.PrintWriter
import java.io.StringWriter
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable


class ResultSet {
    val dictionary: ResultSetDictionary
    val variablesSTL = mutableMapOf<String, Variable>()
    val variablesLTS = mutableListOf<String>()

    constructor(dictionary: ResultSetDictionary) {
        this.dictionary = dictionary
    }


    fun renameVariable(variableOld: String, variableNew: String): Variable {
        val l = variablesSTL[variableOld]!!
        variablesSTL.remove(variableOld)
        variablesSTL[variableNew] = l
        variablesLTS[l.toInt()] = variableNew
        return l
    }

    fun createVariable(variable: String): Variable {
        val o = variablesSTL[variable]
        if (o != null)
            return o
        val l = 0L + variablesLTS.size
        variablesSTL[variable] = l
        variablesLTS.add(variable)
        return l
    }

    fun getVariable(variable: Variable): String {
        return variablesLTS[variable.toInt()]
    }

    fun hasVariable(name: String): Boolean {
        return variablesLTS.contains(name)
    }

    fun getVariableNames(): List<String> {
        return variablesLTS
    }

    fun createValue(value: String?): Value {
        if (value == null)
            return dictionary.undefValue
        return dictionary.createValue(value)
    }

    fun createResultRow(): ResultRow {
        val res = ResultRow(variablesLTS.size, dictionary.undefValue)
        res.resultSet = this
        return res
    }

    fun getValue(value: Value): String? {
        return dictionary.getValue(value)
    }

    fun isUndefValue(r: ResultRow, v: Variable): Boolean {
        return r[v] == dictionary.undefValue
    }

    fun setUndefValue(r: ResultRow, v: Variable) {
        r[v] = dictionary.undefValue
    }
}
