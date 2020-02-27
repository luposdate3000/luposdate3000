package lupos.s04arithmetikOperators
import lupos.s00misc.classNameToString
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase



abstract class AOPBase : OPBase() {
    override val operatorID = EOperatorID.AOPBaseID
    override val classname = "AOPBase"

    override val resultSet = ResultSet(ResultSetDictionary())
    override fun evaluate() = throw Exception("this should not be called")
    abstract fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant
}
