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


class AOPBuildInCallLCASE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallLCASEID, "AOPBuildInCallLCASE", arrayOf(child)) {

    override fun toSparql() = "LCASE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallLCASE)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is ValueLanguageTaggedLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueLanguageTaggedLiteral(a.delimiter, a.content.toLowerCase(), a.language)
            })
        if (a is ValueTypedLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueTypedLiteral.create(a.delimiter, a.content.toLowerCase(), a.type_iri)
            })
        if (a is ValueSimpleLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueSimpleLiteral(a.delimiter, a.content.toLowerCase())
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall LCASE only works with string input")
        })
    }

    override fun cloneOP() = AOPBuildInCallLCASE(query, children[0].cloneOP() as AOPBase)
}
