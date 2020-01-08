package lupos.s5physicalOperators.singleinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

class POPRename : POPSingleInputBase {
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>

    constructor(nameTo:LOPVariable, nameFrom:LOPVariable, child: POPBase) : super(child) {
        resultSetOld = child.getResultSet()
	val variableNames = resultSetOld.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(it: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size, init = fun(it: Int) = (null as Variable?))
	var i=0
	for (name in variableNames){
		variablesOld[i]=resultSetOld.createVariable(name)
		if(name==nameFrom.name)
			variablesNew[i]=resultSetNew.createVariable(nameTo.name)
		else
			variablesNew[i]=resultSetNew.createVariable(name)
		i++
	}
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        return child.hasNext()
    }

    override fun next(): ResultRow {
        var rsNew = resultSetNew.createResultRow()
        val rsOld = child.next()
        for (i in variablesNew.indices) {
        // TODO reuse resultSet
            rsNew[variablesNew[i]!!] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]!!]))
        }
        return rsNew
    }
}
