package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import kotlin.math.floor
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPBuildInCallFLOOR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallFLOORID, "AOPBuildInCallFLOOR", arrayOf(child)) {

    override fun toSparql() = "FLOOR(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallFLOOR)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is ValueDouble)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDouble(floor(a.toDouble()))
            })
        if (a is ValueDecimal)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDecimal(floor(a.toDouble()))
            })
        if (a is ValueInteger)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                a
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall FLOOR only works with numeric input")
        })
    }

    override fun cloneOP() = AOPBuildInCallFLOOR(query, children[0].cloneOP() as AOPBase)
}
