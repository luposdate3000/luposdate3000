package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.*
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPLanguageTaggedLiteral
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral

import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase



class AOPBuildInCallSTRLANG(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallSTRLANGID
    override val classname = "AOPBuildInCallSTRLANG"
    override val children: Array<OPBase> = arrayOf(child, childB)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRLANG)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral && b is AOPSimpleLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPLanguageTaggedLiteral(a.delimiter, a.content, b.content)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall STRLANG only works with simple string input")
        })
    }
}
