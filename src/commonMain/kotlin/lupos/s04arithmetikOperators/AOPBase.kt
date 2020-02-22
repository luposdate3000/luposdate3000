package lupos.s04arithmetikOperators

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*


abstract class AOPBase : OPBase() {

    open fun toTestCaseInput(): String {
        var res = "${classNameToString(this)}("
        if (children.size > 0)
            res += (children[0] as AOPBase).toTestCaseInput()
        for (i in 1 until children.size)
            res += "," + (children[i] as AOPBase).toTestCaseInput()
        return res + ")"
    }

    override val resultSet = ResultSet(ResultSetDictionary())
    override fun evaluate() {
        throw Exception("this should not be called")
    }

    abstract fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant
    override fun getProvidedVariableNames() = listOf<String>()
    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children)
            res += c.getRequiredVariableNames()
        return res
    }
}
