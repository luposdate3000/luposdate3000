package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.noinput.AOPTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRDT(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val operatorID=EOperatorID.AOPBuildInCallSTRDTID
    override val classname = "AOPBuildInCallSTRDT"
    override val children: Array<OPBase> = arrayOf(child, childB)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRDT)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral && b is AOPIri)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPTypedLiteral(a.delimiter, a.content, b.iri)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall STRDT only works with simple string input and iri datatype")
        })
    }
}
