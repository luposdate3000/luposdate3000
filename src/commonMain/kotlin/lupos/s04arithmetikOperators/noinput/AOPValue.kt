package lupos.s04arithmetikOperators.noinput
import lupos.s04arithmetikOperators.AOPBase
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet

import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPValue(childs: List<AOPConstant>) : AOPBase() {
    override val operatorID = EOperatorID.AOPValueID
    override val classname = "AOPValue"
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }


    override fun equals(other: Any?): Boolean {
        if (other !is AOPValue)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        TODO("not implemented")
    }
}
