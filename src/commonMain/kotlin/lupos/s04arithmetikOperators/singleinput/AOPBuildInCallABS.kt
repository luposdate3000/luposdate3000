package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import kotlin.math.abs
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


class AOPBuildInCallABS(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child)) {

    override fun toSparql() = "ABS(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallABS)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is ValueDouble)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDouble(abs(a.value))
            })
        if (a is ValueDecimal)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDecimal(abs(a.value))
            })
        if (a is ValueInteger)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueInteger(abs(a.value))
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall ABS only works with numeric input")
        })
    }

    override fun cloneOP() = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
}
