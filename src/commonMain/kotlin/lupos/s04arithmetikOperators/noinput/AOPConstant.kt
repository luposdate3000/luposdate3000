package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.Query


class AOPConstant(query: Query, val value: ValueDefinition) : AOPBase(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
    override fun calculate(resultSet: ResultSet, resultRow: ResultRow) = value
    override fun cloneOP() = this
}
