package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase


class AOPNotIn(childA: AOPBase, childB: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPNotInID
    override val classname = "AOPNotIn"
    override val children: Array<OPBase> = arrayOf(childA, childB)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPNotIn)
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
                    return AOPBoolean(false)
            }
            return AOPBoolean(true)
        } else
            return AOPBoolean(false)
    }

    override fun cloneOP() = AOPNotIn(children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
