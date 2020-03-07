package lupos.s04arithmetikOperators.singleinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.noinput.AOPLanguageTaggedLiteral
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallLANG(query:Query,child: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallLANGID,"AOPBuildInCallLANG",arrayOf(child)) {

    override fun toSparql() = "LANG(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallLANG)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPLanguageTaggedLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPSimpleLiteral(a.delimiter, a.language)
            })
        if (a is AOPConstantString || a is AOPNumeric || a is AOPBoolean || a is AOPDateTime)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPSimpleLiteral("\"", "")
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("Type error $classname ${a.classname}")
        })
    }

    override fun cloneOP() = AOPBuildInCallLANG(children[0].cloneOP() as AOPBase)
}
