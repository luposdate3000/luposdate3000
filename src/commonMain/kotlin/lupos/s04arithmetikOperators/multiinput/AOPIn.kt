package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPIn(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPInID, "AOPIn", arrayOf(childA, childB)) {

    override fun toSparql() = "( " + children[0].toSparql() + " IN " + children[1].toSparql() + " )"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPIn)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase)
        if (b is AOPSet) {
            for (c in b.children) {
                if ((c as AOPBase).calculate(resultSet, resultRow) == a)
                    return ValueBoolean(true)
            }
            return ValueBoolean(false)
        } else
            return ValueBoolean(false)
    }

    override fun cloneOP() = AOPIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
