package lupos.s10outputResult

import lupos.s07physicalOperators.POPBase

import lupos.s06resultRepresentation.Variable


fun printResult(graph: POPBase) {
    val resultSet = graph.getResultSet()
    val variableNames = resultSet.getVariableNames().toTypedArray()
    val variables = arrayOfNulls<Variable>(variableNames.size)
    var i = 0
    println("result:::")
    for (variableName in variableNames) {
        if (i == 0)
            print(variableName)
        else
            print(", " + variableName)
        variables[i] = resultSet.createVariable(variableName)
        i++
    }
    println()
    println()
    while (graph.hasNext()) {
        val resultRow = graph.next()
        i = 0
        for (variable in variables) {
            if (i == 0)
                print(resultSet.getValue(resultRow[variable!!]))
            else
                print(", " + resultSet.getValue(resultRow[variable!!]))
            i++
        }
        println()
    }
    println()
}
