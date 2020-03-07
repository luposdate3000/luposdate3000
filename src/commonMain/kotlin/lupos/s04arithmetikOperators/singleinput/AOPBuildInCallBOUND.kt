package lupos.s04arithmetikOperators.singleinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallBOUND(query:Query,child: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallBOUNDID,"AOPBuildInCallBOUND",arrayOf(child)) {

    override fun toSparql() = "BOUND(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallBOUND)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPBoolean(a !is AOPUndef)
        })
    }

    override fun cloneOP() = AOPBuildInCallBOUND(children[0].cloneOP() as AOPBase)
}
