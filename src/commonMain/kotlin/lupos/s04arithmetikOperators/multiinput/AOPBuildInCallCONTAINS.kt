package lupos.s04arithmetikOperators.multiinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPConstantString
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallCONTAINS(query:Query,child: AOPBase, childB: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallCONTAINSID,"AOPBuildInCallCONTAINS",arrayOf(child, childB)) {

    override fun toSparql() = "CONTAINS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCONTAINS)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString) {
            val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
            if (b is AOPSimpleLiteral)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    AOPBoolean(query,a.content.contains(b.content))
                })
            else
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPBuiltInCall CONTAINS only works with simple compare string input")
                })
        }
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall CONTAINS only works with string input")
        })
    }

    override fun cloneOP() = AOPBuildInCallCONTAINS(query,children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
