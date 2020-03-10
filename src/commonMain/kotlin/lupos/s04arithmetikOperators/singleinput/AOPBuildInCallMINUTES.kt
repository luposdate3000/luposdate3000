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


class AOPBuildInCallMINUTES(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallMINUTESID, "AOPBuildInCallMINUTES", arrayOf(child)) {

    override fun toSparql() = "MINUTES(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallMINUTES)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is ValueDateTime)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueInteger(a.minutes)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall MINUTES only works with dateTime input")
        })
    }

    override fun cloneOP() = AOPBuildInCallMINUTES(query, children[0].cloneOP() as AOPBase)
}
