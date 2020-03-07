package lupos.s04arithmetikOperators.multiinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04logicalOperators.OPBase


class AOPDivision(query:Query,childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query,EOperatorID.AOPDivisionID, "AOPDivision",arrayOf(childA, childB)) {

    override fun toSparql() = "(" + children[0].toSparql() + " / " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPDivision)
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
        if (a is AOPDouble || b is AOPDouble) {
            if (b.toDouble() == 0.0)
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPDivision by zero")
                })
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPDouble(query,a.toDouble() / b.toDouble())
            })
        }
        if (a is AOPDecimal || b is AOPDecimal) {
            if (b.toDouble() == 0.0)
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPDivision by zero")
                })
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPDecimal(query,a.toDouble() / b.toDouble())
            })
        }
        if (a is AOPInteger || b is AOPInteger) {
            if (b.toInt() == 0)
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPDivision by zero")
                })
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPInteger(query,a.toInt() / b.toInt())
            })
        }
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPDivision only works with numeric input")
        })
    }

    override fun cloneOP() = AOPDivision(query,children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
