package lupos.s5physicalOperators.singleinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s4resultRepresentation.Value
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPBaseNullableIterator

class POPSort : POPBaseNullableIterator {
    private var data: MutableList<ResultRow>? = null
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null
    private val sortBy: Variable
    private val child: POPBase
    private val sortOrder: Boolean

    constructor(sortBy: LOPVariable, sortOrder: Boolean, child: POPBase) : super() {
        this.child = child
        this.sortOrder = sortOrder
        this.sortBy = resultSetNew.createVariable(sortBy.name)
        resultSetOld = child.getResultSet()
        for (name in resultSetOld.getVariableNames()) {
            variables.add(Pair(resultSetOld.createVariable(name), resultSetNew.createVariable(name)))
        }
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        if (data == null) {
            val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
            while (child.hasNext()) {
                val rsOld = child.next()
                val rsNew = resultSetNew.createResultRow()
                var key: String = ""
                for (variable in variables) {
                    val tmp = resultSetOld.getValue(rsOld[variable.second])
                    rsNew[variable.first] = resultSetNew.createValue(tmp)
                    if (variable.first == sortBy)
                        key = tmp
                }
                var tmp = tmpMutableMap[key]
                if (tmp == null) {
                    tmp = mutableListOf<ResultRow>()
                    tmpMutableMap[key] = tmp
                }
                tmp!!.add(rsNew)
            }
            data = mutableListOf<ResultRow>()
            val allKeys = Array<String>(tmpMutableMap.keys.size) { "" }
            var i = 0
            for (k in tmpMutableMap.keys) {
                allKeys[i] = k
                i++
            }
            allKeys.sort()
            for (k in allKeys) {
                data!!.addAll(tmpMutableMap[k]!!)
            }
            reset()
        }
        if (iterator == null || !iterator!!.hasNext())
            return null
        return iterator!!.next()
    }

    fun reset() {
        iterator = data!!.listIterator()
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
    }
}
