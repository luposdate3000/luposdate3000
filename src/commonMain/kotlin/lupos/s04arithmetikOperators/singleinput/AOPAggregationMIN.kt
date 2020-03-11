package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.resultFlow
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPAggregationMIN(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationMINID, "AOPAggregationMIN", Array(childs.size) { childs[it] }) {

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct)
            return "MIN(DISTINCT " + children[0].toSparql() + ")"
        return "MIN(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationMIN)
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
        val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a.get() is ValueUndef || a.get()!!.compareTo(b) > 0)
            a.set(b)
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            a.get()!!
        })
    }

    override fun cloneOP() = AOPAggregationMIN(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
