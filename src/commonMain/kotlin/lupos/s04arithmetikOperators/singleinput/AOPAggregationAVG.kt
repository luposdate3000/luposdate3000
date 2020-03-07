package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPAggregationAVG(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationAVGID, "AOPAggregationAVG", Array(childs.size) { childs[it] }) {

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct)
            return "AVG(DISTINCT " + children[0].toSparql() + ")"
        return "AVG(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationAVG)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        if (distinct != other.distinct)
            return false
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        if (!collectMode.get()) {
            if (a.get() == null)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    AOPUndef(query)
                })
            else
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    a.get()!!
                })
        }
        if (distinct)
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("AOPAggregationAVG does not support distinct")
            })
        val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a.get() == null && b is AOPDouble)
            a.set(AOPDouble(query, b.toDouble() / (0.0 + count.get())))
        else if (a.get() == null && b is AOPDecimal)
            a.set(AOPDecimal(query, b.toDouble() / (0.0 + count.get())))
        else if (a.get() == null && b is AOPInteger)
            a.set(AOPDecimal(query, b.toDouble() / (0.0 + count.get())))
        else if (a.get() is AOPDouble || b is AOPDouble)
            a.set(AOPDouble(query, a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
        else if (a.get() is AOPDecimal || b is AOPDecimal)
            a.set(AOPDecimal(query, a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
        else if (a.get() is AOPInteger || b is AOPInteger)
            a.set(AOPDecimal(query, a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
        else
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("AOPAggregationAVG avg only defined on numberic input")
            })
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            a.get()!!
        })
    }

    override fun cloneOP() = AOPAggregationAVG(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
