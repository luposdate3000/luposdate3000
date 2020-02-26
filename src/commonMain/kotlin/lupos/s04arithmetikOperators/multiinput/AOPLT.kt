package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.resultFlow
import lupos.s04logicalOperators.OPBase


class AOPLT(childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName() {
    override val operatorID = EOperatorID.AOPLTID
    override val classname = "AOPLT"
    override val children: Array<OPBase> = arrayOf(childA, childB)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPLT)
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
        if (a is AOPDouble || b is AOPDouble)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.toDouble() < b.toDouble())
            })
        if (a is AOPDecimal || b is AOPDecimal)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.toDouble() < b.toDouble())
            })
        if (a is AOPInteger || b is AOPInteger)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.toInt() < b.toInt())
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPLT only works with numeric input")
        })
    }
}
