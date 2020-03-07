package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPBuildInCallIsLITERAL(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsLITERALID, "AOPBuildInCallIsLITERAL", arrayOf(child)) {

    override fun toSparql() = "isLiteral(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIsLITERAL)
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
            AOPBoolean(query, a is AOPConstantString || a is AOPDouble || a is AOPBoolean || a is AOPInteger || a is AOPDecimal || a is AOPDateTime)
        })
    }

    override fun cloneOP() = AOPBuildInCallIsLITERAL(query, children[0].cloneOP() as AOPBase)
}
