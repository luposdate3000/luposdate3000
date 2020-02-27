package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase


abstract class AOPConstant : AOPBase() {
    override val operatorID = EOperatorID.AOPConstantID
    override val classname = "AOPConstant"
    abstract fun valueToString(): String?
    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant = this
    abstract fun toDouble(): Double
    abstract fun toInt(): Int
    abstract fun toBoolean(): Boolean
}
