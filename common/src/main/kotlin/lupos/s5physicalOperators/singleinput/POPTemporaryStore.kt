package lupos.s5physicalOperators.singleinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

class POPTemporaryStore : POPBase {
    private val data = mutableListOf<ResultRow>()
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>
    private val child: POPBase

    constructor(child: POPBase) : super() {
        this.child = child
        iterator = child
        resultSetOld = child.getResultSet()
        for (name in resultSetOld.getVariableNames()) {
            variables.add(Pair(resultSetOld.createVariable(name), resultSetNew.createVariable(name)))
        }
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        val res = iterator.hasNext()
        return res
    }

    override fun next(): ResultRow {
        if (iterator == child) {
            val rsOld = child.next()
            var rsNew = resultSetNew.createResultRow()
            for (variable in variables) {
                rsNew[variable.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variable.second]))
            }
            data.add(rsNew)
            return rsNew
        }
        val rsOld = iterator.next()
        var rsNew = resultSetNew.createResultRow()
        for (variable in variables) {
            rsNew[variable.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variable.second]))
        }
        return rsNew
    }

    fun reset() {
        iterator = data.listIterator()
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
    }
}
