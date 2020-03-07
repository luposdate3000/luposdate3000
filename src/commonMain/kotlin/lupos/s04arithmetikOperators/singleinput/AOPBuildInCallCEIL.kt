package lupos.s04arithmetikOperators.singleinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import kotlin.math.ceil
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallCEIL(query:Query,child: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallCEILID,"AOPBuildInCallCEIL",arrayOf(child)) {

    override fun toSparql() = "CEIL(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCEIL)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPDouble)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPDouble(query,ceil(a.toDouble()))
            })
        if (a is AOPDecimal)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPDecimal(query,ceil(a.toDouble()))
            })
        if (a is AOPInteger)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                a
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall CEIL only works with numeric input")
        })
    }

    override fun cloneOP() = AOPBuildInCallCEIL(query,children[0].cloneOP() as AOPBase)
}
