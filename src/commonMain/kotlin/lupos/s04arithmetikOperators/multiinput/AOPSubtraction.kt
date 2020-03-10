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


class AOPSubtraction(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPSubtractionID, "AOPSubtraction", arrayOf(childA, childB)) {

    override fun toSparql() = "(" + children[0].toSparql() + " - " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPSubtraction)
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
        if (a is ValueDouble || b is ValueDouble)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDouble(a.toDouble() - b.toDouble())
            })
        if (a is ValueDecimal || b is ValueDecimal)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueDecimal(a.toDouble() - b.toDouble())
            })
        if (a is ValueInteger || b is ValueInteger)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueInteger(a.toInt() - b.toInt())
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPSubtraction only works with numeric input")
        })
    }

    override fun cloneOP() = AOPSubtraction(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
