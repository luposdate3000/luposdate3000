package lupos.s04logicalOperators

import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary


abstract class LOPBase : OPBase() {
    override val resultSet = ResultSet(ResultSetDictionary())
    override fun evaluate() {
        throw Exception("this should not be called")
    }
}
