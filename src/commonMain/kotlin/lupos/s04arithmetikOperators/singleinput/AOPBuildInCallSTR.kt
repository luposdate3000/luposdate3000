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


class AOPBuildInCallSTR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRID, "AOPBuildInCallSTR", arrayOf(child)) {

    override fun toSparql() = "STR(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTR)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is ValueStringBase)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueSimpleLiteral(a.delimiter, a.content)
            })
        if (a is ValueIri)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueSimpleLiteral("\"", a.iri)
            })
        if (a !is ValueBnode && a !is ValueUndef)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                val tmp = a.valueToString()!!
                ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.lastIndexOf(tmp.get(0))))
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall STR only works with string input")
        })
    }

    override fun cloneOP() = AOPBuildInCallSTR(query, children[0].cloneOP() as AOPBase)
}
