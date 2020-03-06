package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPNumeric
import lupos.s04arithmetikOperators.noinput.AOPXPathCompareable
import lupos.s04logicalOperators.OPBase


class AOPLEQ(childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName() {
    override val operatorID = EOperatorID.AOPLEQID
    override val classname = "AOPLEQ"
    override val children: Array<OPBase> = arrayOf(childA, childB)

    override fun toSparql() = "(" + children[0].toSparql() + " <= " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPLEQ)
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
        if (a is AOPNumeric && b is AOPNumeric) {
            if (a is AOPDouble || b is AOPDouble)
                return AOPBoolean(a.toDouble() <= b.toDouble())
            if (a is AOPDecimal || b is AOPDecimal)
                return AOPBoolean(a.toDouble() <= b.toDouble())
            if (a is AOPInteger || b is AOPInteger)
                return AOPBoolean(a.toInt() <= b.toInt())
        }
        if (a is AOPXPathCompareable && b is AOPXPathCompareable)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.valueToString()!! <= b.valueToString()!!)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("Type error $classname ${a.classname} ${b.classname}")
        })
    }

    override fun cloneOP() = AOPLEQ(children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
