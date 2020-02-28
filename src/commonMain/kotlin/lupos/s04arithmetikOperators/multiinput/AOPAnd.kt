package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase


class AOPAnd(childA: AOPBase, childB: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPAndID
    override val classname = "AOPAnd"
    override val children: Array<OPBase> = arrayOf(childA, childB)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAnd)
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
        if (a is AOPBoolean && b is AOPBoolean)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.value && b.value)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPAnd only works with boolean input")
        })
    }
override fun cloneOP()=AOPAnd(children[0].cloneOP()as AOPBase,children[1].cloneOP()as AOPBase)
}
