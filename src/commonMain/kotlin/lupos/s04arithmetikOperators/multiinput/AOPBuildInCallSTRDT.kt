package lupos.s04arithmetikOperators.multiinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.noinput.AOPTypedLiteral
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRDT(query:Query,child: AOPBase, childB: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallSTRDTID,"AOPBuildInCallSTRDT",arrayOf(child, childB)) {

    override fun toSparql() = "STRDT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRDT)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral && b is AOPIri)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPTypedLiteral.create(query,a.delimiter, a.content, b.iri)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall STRDT only works with simple string input and iri datatype")
        })
    }

    override fun cloneOP() = AOPBuildInCallSTRDT(query,children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
