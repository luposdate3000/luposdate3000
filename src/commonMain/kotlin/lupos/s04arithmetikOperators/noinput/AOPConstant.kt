package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase


abstract class AOPConstant(query:Query,operatorID: EOperatorID, classname: String) : AOPBase(query,operatorID,classname,arrayOf()), Comparable<AOPConstant> {
    abstract fun valueToString(): String?
    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant = this
    abstract fun toDouble(): Double
    abstract fun toInt(): Int
    abstract fun toBoolean(): Boolean
    override fun cloneOP() = this
    override fun toSparql(): String {
        val res = valueToString()
        if (res == null)
            return "UNDEF"
        return res
    }

    override operator fun compareTo(other: AOPConstant): Int = throw Exception("type error")
}
