package lupos.s11outputResult

import lupos.s03resultRepresentation.Variable
import lupos.s09physicalOperators.POPBase


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
                if (! resultSet.isUndefValue(resultRow,variable.second))
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
