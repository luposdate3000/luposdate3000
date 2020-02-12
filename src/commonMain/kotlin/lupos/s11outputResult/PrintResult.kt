package lupos.s11outputResult
import lupos.s00misc.*

import lupos.s03resultRepresentation.Variable
import lupos.s09physicalOperators.POPBase


inline fun printResult(graph: POPBase) {
    val resultSet = graph.getResultSet()
    val variableNames = resultSet.getVariableNames().toTypedArray()
    val variables = arrayOfNulls<Variable>(variableNames.size)
    var i = 0
    GlobalLogger.log(ELoggerType.RELEASE,{"result:::"})
    for (variableName in variableNames) {
        if (i == 0)
            GlobalLogger.log(ELoggerType.DEBUG,{variableName})
        else
            GlobalLogger.log(ELoggerType.DEBUG,{", " + variableName})
        variables[i] = resultSet.createVariable(variableName)
        i++
    }
    GlobalLogger.log(ELoggerType.RELEASE,{""})
    GlobalLogger.log(ELoggerType.RELEASE,{""})
    while (graph.hasNext()) {
        val resultRow = graph.next()
        i = 0
        for (variable in variables) {
            if (i == 0)
                GlobalLogger.log(ELoggerType.DEBUG,{resultSet.getValue(resultRow[variable!!])})
            else
                GlobalLogger.log(ELoggerType.DEBUG,{", " + resultSet.getValue(resultRow[variable!!])})
            i++
        }
        GlobalLogger.log(ELoggerType.RELEASE,{""})
    }
    GlobalLogger.log(ELoggerType.RELEASE,{""})
}
