package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPBuildInCallIF(query: Query, child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIFID, "AOPBuildInCallIF", arrayOf(child, childA, childB)) {

    override fun toSparql() = "IF(" + children[0].toSparql() + ", " + children[1].toSparql() + ", " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIF)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow) as AOPBoolean
        if (a.value)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                (children[1] as AOPBase).calculate(resultSet, resultRow)
            })
        else
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                (children[2] as AOPBase).calculate(resultSet, resultRow)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall IF only works with boolean condition")
        })
    }

    override fun cloneOP() = AOPBuildInCallIF(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase)
}
