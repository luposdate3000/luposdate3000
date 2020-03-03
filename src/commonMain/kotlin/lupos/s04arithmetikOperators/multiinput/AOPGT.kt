package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04logicalOperators.OPBase


class AOPGT(childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName() {
    override val operatorID = EOperatorID.AOPGTID
    override val classname = "AOPGT"
    override val children: Array<OPBase> = arrayOf(childA, childB)

    override fun toSparql() = "(" + children[0].toSparql() + " > " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPGT)
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
if(a is AOPXPathCompareable && b is AOPXPathCompareable)
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPBoolean(a.valueToString()!! > b.valueToString()!!)
        })
throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("Type error $classname ${a.classname} ${b.classname}")
        })
    }

    override fun cloneOP() = AOPGT(children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
