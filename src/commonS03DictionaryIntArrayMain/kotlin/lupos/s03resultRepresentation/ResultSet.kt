package lupos.s03resultRepresentation
import lupos.s00misc.SanityCheck

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s04logicalOperators.Query


class ResultSet(@JvmField val dictionary: ResultSetDictionary) {
    @JvmField
    val variablesSTL = mutableMapOf<String, Variable>()
    @JvmField
    val variablesLTS = mutableListOf<String>()
    @JvmField
    val mutex = CoroutinesHelper.createLock()

     fun renameVariable(variableOld: String, variableNew: String): Variable {
        var res: Variable? = null
        CoroutinesHelper.runBlockWithLock(mutex, {
            val l = variablesSTL[variableOld]!!
            variablesSTL.remove(variableOld)
            variablesSTL[variableNew] = l
            variablesLTS[l.toInt()] = variableNew
            res = l
        })
        return res!!
    }

     fun createVariable(variable: String): Variable {
        var res: Variable? = null
        CoroutinesHelper.runBlockWithLock(mutex, {
            val o = variablesSTL[variable]
            if (o != null)
                res = o
            else {
                val l = 0L + variablesLTS.size
                variablesSTL[variable] = l
                variablesLTS.add(variable)
                res = l
            }
        })
        return res!!
    }

     fun getVariable(variable: Variable) = variablesLTS[variable.toInt()]

     fun hasVariable(name: String) = variablesLTS.contains(name)

     fun getVariableNames() = variablesLTS

     fun createValue(value: String?): Value {
        if (value == null)
            return dictionary.undefValue
        return dictionary.createValue(value)
    }

    val createdRows  = SanityCheck.helper<MutableSet<Long> >{mutableSetOf<Long>()}
     fun createResultRow(): ResultRow {
        val res = ResultRow(variablesLTS.size, dictionary.undefValue)
SanityCheck.helper<Unit>{createdRows!!.add(res.uuid)}
        return res
    }

     fun getValueString(value: Value) = dictionary.getValue(value)

     fun isUndefValue(r: ResultRow, v: Variable): Boolean {
SanityCheck.check({createdRows!!.contains(r.uuid)})
        return r.values[v.toInt()] == dictionary.undefValue
    }

     fun setUndefValue(r: ResultRow, v: Variable) {
        SanityCheck.check({createdRows!!.contains(r.uuid)})
        r.values[v.toInt()] = dictionary.undefValue
    }

     fun setValue(r: ResultRow, k: Variable, v: Value) {
        SanityCheck.check({createdRows!!.contains(r.uuid)})
        r.values[k.toInt()] = v
    }

     fun setValue(r: ResultRow, k: String, v: String) {
        SanityCheck.check({createdRows!!.contains(r.uuid)})
        r.values[createVariable(k).toInt()] = createValue(v)
    }

     fun setValue(r: ResultRow, k: Variable, v: String?) {
        SanityCheck.check({createdRows!!.contains(r.uuid)})
        r.values[k.toInt()] = createValue(v)
    }

     fun getValue(r: ResultRow, k: Variable): Value {
        SanityCheck.check({createdRows!!.contains(r.uuid)})
        return r.values[k.toInt()]
    }

     fun getValueString(r: ResultRow, k: Variable): String? {
        SanityCheck.check({createdRows!!.contains(r.uuid)})
        return getValueString(r.values[k.toInt()])
    }

     fun getValueString(r: ResultRow, k: String): String? {
        SanityCheck.check({createdRows!!.contains(r.uuid)})
        return getValueString(r.values[createVariable(k).toInt()])
    }

     fun copy(to: ResultRow, kTo: Variable, from: ResultRow, kFrom: Variable, fromR: ResultSet) {
SanityCheck.check({createdRows!!.contains(to.uuid)})
SanityCheck.check({fromR.createdRows!!.contains(from.uuid)})
SanityCheck.check({this.dictionary === fromR.dictionary})
        setValue(to, kTo, fromR.getValue(from, kFrom))
    }
}
