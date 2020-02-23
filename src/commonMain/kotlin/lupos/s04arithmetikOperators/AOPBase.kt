package lupos.s04arithmetikOperators
import lupos.s04logicalOperators.OPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s00misc.classNameToString



abstract class AOPBase : OPBase() {

    open fun toTestCaseInput(): String {
        var res = "${classNameToString(this)}("
        if (children.size > 0)
            res += (children[0] as AOPBase).toTestCaseInput()
        for (i in 1 until children.size)
            res += ", " + (children[i] as AOPBase).toTestCaseInput()
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
