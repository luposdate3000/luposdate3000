package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPConstantString
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.resultFlow
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallCONTAINS(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallCONTAINSID
    override val classname = "AOPBuildInCallCONTAINS"
    override val children: Array<OPBase> = arrayOf(child, childB)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCONTAINS)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString) {
            val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
            if (b is AOPSimpleLiteral)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    AOPBoolean(a.content.contains(b.content))
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
}
