package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase


class AOPIn(childA: AOPBase, childB: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPInID
    override val classname = "AOPIn"
    override val children: Array<OPBase> = arrayOf(childA, childB)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPIn)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase)
        if (b is AOPSet) {
            for (c in b.children) {
                if ((c as AOPBase).calculate(resultSet, resultRow) == a)
                    return AOPBoolean(true)
            }
            return AOPBoolean(false)
        } else
            return AOPBoolean(false)
    }

    override fun cloneOP() = AOPIn(children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
