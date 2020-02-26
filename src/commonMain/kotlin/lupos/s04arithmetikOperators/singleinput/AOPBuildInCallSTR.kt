package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTR(child: AOPBase) : AOPBase() {
    override val operatorID=EOperatorID.AOPBuildInCallSTRID
    override val classname = "AOPBuildInCallSTR"
    override val children: Array<OPBase> = arrayOf(child)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTR)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPSimpleLiteral(a.delimiter, a.content)
            })
        if (a !is AOPBnode && a !is AOPUndef)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                val tmp = a.valueToString()!!
                AOPSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.lastIndexOf(tmp.get(0))))
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall STR only works with string input")
        })
    }
}
