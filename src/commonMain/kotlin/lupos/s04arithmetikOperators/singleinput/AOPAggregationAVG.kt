package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


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

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        if (!collectMode.get()) {
            if (a.get() is ValueUndef)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    ValueUndef()
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
        if (a.get() is ValueUndef && b is ValueDouble)
            a.set(ValueDouble(b.toDouble() / (0.0 + count.get())))
        else if (a.get() is ValueUndef && b is ValueDecimal)
            a.set(ValueDecimal(b.toDouble() / (0.0 + count.get())))
        else if (a.get() is ValueUndef && b is ValueInteger)
            a.set(ValueDecimal(b.toDouble() / (0.0 + count.get())))
        else if (a.get() is ValueDouble || b is ValueDouble)
            a.set(ValueDouble(a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
        else if (a.get() is ValueDecimal || b is ValueDecimal)
            a.set(ValueDecimal(a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
        else if (a.get() is ValueInteger || b is ValueInteger)
            a.set(ValueDecimal(a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
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
