package lupos.s04arithmetikOperators.singleinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPNumeric
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallIsNUMERIC(query:Query,child: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallIsNUMERICID,"AOPBuildInCallIsNUMERIC",arrayOf(child)) {
    override fun toSparql() = "isNumeric(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIsNUMERIC)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPUndef)
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("typeError")
            })
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPBoolean(a is AOPNumeric)
        })
    }

    override fun cloneOP() = AOPBuildInCallIsNUMERIC(children[0].cloneOP() as AOPBase)
}
