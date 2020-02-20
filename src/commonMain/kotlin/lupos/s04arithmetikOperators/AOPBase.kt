package lupos.s04arithmetikOperators

import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.*


abstract class AOPBase : OPBase() {
    override val resultSet = ResultSet(ResultSetDictionary())
    override fun evaluate() {
        throw Exception("this should not be called")
    }
}
