package lupos.s04arithmetikOperators.multiinput

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


class AOPDivision(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPDivisionID, "AOPDivision", arrayOf(childA, childB)) {

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

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is ValueDouble || b is ValueDouble) {
            if (b.toDouble() == 0.0)
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPDivision by zero")
                })
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDouble(a.toDouble() / b.toDouble())
            })
        }
        if (a is ValueDecimal || b is ValueDecimal) {
            if (b.toDouble() == 0.0)
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPDivision by zero")
                })
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDecimal(a.toDouble() / b.toDouble())
            })
        }
        if (a is ValueInteger || b is ValueInteger) {
            if (b.toInt() == 0)
                throw resultFlow({ this }, { resultRow }, { resultSet }, {
                    Exception("AOPDivision by zero")
                })
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDecimal(a.toDouble() / b.toDouble())
            })
        }
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPDivision only works with numeric input")
        })
    }

    override fun cloneOP() = AOPDivision(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
