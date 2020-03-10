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


class AOPSet(query: Query, childs: List<AOPBase>) : AOPBase(query, EOperatorID.AOPSetID, "AOPSet", Array(childs.size) { childs[it] }) {

    override fun toSparql(): String {
        var res = ""
        res += "("
        if (children.size > 0)
            res += children[0].toSparql()
        for (i in 1 until children.size)
            res += "," + children[i].toSparql()
        res += ")"
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPSet)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        TODO("not implemented")
    }

    override fun cloneOP() = AOPSet(query, List(children.size) { children[it].cloneOP() as AOPBase })
}
