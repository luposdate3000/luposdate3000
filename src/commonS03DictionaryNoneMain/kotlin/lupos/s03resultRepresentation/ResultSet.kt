package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.Query


class ResultSet(@JvmField val dictionary: ResultSetDictionary) {
    @JvmField
    val variables = mutableListOf<String>()

    fun renameVariable(variableOld: String, variableNew: String): String {
        val i = variables.indexOf(variableOld)
        variables[i] = variableNew
        return variableNew
    }

    fun createVariable(variable: String): Variable {
        if (!variables.contains(variable))
            variables.add(variable)
        return variable
    }

    fun getVariable(variable: Variable): String {
        return variable
    }

    fun getVariableNames(): List<String> {
        return variables
    }

    fun hasVariable(name: String): Boolean {
        return variables.contains(name)
    }

    fun createValue(value: String?): Value {
        if (value == null)
            return null
        return value
    }

    val createdRows = SanityCheck.helper<MutableSet<Long>> { mutableSetOf<Long>() }
    fun createResultRow(): ResultRow {
        val res = ResultRow()
        SanityCheck.helper { createdRows!!.add(res.uuid) }
        return res
    }

    fun getValueString(value: Value) = value

    fun isUndefValue(r: ResultRow, v: Variable): Boolean {
        SanityCheck.check({ createdRows!!.contains(r.uuid) })
        return r.values[v] == null
    }

    fun setUndefValue(r: ResultRow, v: Variable) {
        SanityCheck.check({ createdRows!!.contains(r.uuid) })
        r.values[v] = null
    }


    fun setValue(r: ResultRow, k: Variable, v: String?) {
        SanityCheck.check({ createdRows!!.contains(r.uuid) })
        r.values[k] = createValue(v)
    }

    fun getValue(r: ResultRow, k: Variable): Value {
        SanityCheck.check({ createdRows!!.contains(r.uuid) })
        return r.values[k]
    }

    fun getValueString(r: ResultRow, k: String): String? {
        SanityCheck.check({ createdRows!!.contains(r.uuid) })
        return getValueString(r.values[createVariable(k)])
    }

    fun copy(to: ResultRow, kTo: Variable, from: ResultRow, kFrom: Variable, fromR: ResultSet) {
        SanityCheck.check({ createdRows!!.contains(to.uuid) })
        SanityCheck.check({ fromR.createdRows!!.contains(from.uuid) })
        SanityCheck.check({ this.dictionary === fromR.dictionary })
        setValue(to, kTo, fromR.getValue(from, kFrom))
    }
}
