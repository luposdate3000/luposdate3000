package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPBuildInCallDAY(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallDAYID, "AOPBuildInCallDAY", arrayOf(child)) {

    override fun toSparql() = "DAY(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallDAY)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is ValueDateTime)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueInteger(a.day)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall DAY only works with dateTime input")
        })
    }

    override fun cloneOP() = AOPBuildInCallDAY(query, children[0].cloneOP() as AOPBase)
}
