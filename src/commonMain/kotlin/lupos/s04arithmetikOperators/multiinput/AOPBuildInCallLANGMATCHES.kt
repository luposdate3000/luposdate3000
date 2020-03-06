package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallLANGMATCHES(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallLANGMATCHESID
    override val classname = "AOPBuildInCallLANGMATCHES"
    override val children: Array<OPBase> = arrayOf(child, childB)
    override fun toSparql() = "LANGMATCHES(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallLANGMATCHES)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral && b is AOPSimpleLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.content == b.content)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall LANGMATCHES only works with simple language string input")
        })
    }

    override fun cloneOP() = AOPBuildInCallLANGMATCHES(children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
