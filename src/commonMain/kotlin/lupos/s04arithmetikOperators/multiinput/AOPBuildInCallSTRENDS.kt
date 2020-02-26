package lupos.s04arithmetikOperators.singleinput
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPConstantString
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet

import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRENDS(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val classname = "AOPBuildInCallSTRENDS"
    override val children: Array<OPBase> = arrayOf(child, childB)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRENDS)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString) {
            if (b is AOPSimpleLiteral)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    AOPBoolean(a.content.endsWith(b.content))
                })
            else
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPBuiltInCall STRENDS only works with simple compare string input")
                })
        }
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall STRENDS only works with string input")
        })
    }
}
