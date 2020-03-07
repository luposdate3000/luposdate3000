package lupos.s04arithmetikOperators.singleinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBnode
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallBNODE1(query:Query,child: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallBNODE1ID, "AOPBuildInCallBNODE1",arrayOf(child)) {

    override fun toSparql() = "BNODE(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallBNODE1)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPBnode(query,"" + uuid + a.valueToString())
        })
    }

    override fun cloneOP() = AOPBuildInCallBNODE1(query,children[0].cloneOP() as AOPBase)
}
