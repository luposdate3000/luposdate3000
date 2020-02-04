package lupos.s10outputResult
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase



object QueryResultToCSV {
    fun toCSV(query: POPBase, separator: String = ","): String {
        var res = ""
        val resultSet = query.getResultSet()
        val variableNames = resultSet.getVariableNames().toTypedArray()
        val variables = mutableListOf<Pair<String, Variable>>()
        var i = 0
        for (variableName in variableNames) {
            variables.add(Pair(variableName, resultSet.createVariable(variableName)))
            if (i > 0)
                res += separator
            res += "?" + variableName
            i++
        }
        res += "\n"
        while (query.hasNext()) {
            val resultRow = query.next()
            i = 0
            for (variable in variables) {
                val value = resultSet.getValue(resultRow[variable.second])
                if (value != resultSet.getUndefValue())
                    res += value
                if (i > 0)
                    res += separator
                i++
            }
            res += "\n"
        }
        return res
    }
}
