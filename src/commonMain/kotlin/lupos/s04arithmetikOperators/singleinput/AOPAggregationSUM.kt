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
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPAggregationSUM(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationSUMID, "AOPAggregationSUM", Array(childs.size) { childs[it] }) {

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct)
            return "SUM(DISTINCT " + children[0].toSparql() + ")"
        return "SUM(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationSUM)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        if (distinct != other.distinct)
            return false
        return true
    }

    override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
        val value = a.get()!!
        val rVektor = ResultVektorRaw()
        for (i in 0 until resultChunk.availableRead())
            rVektor.data[i] = value
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow) {
        val child = children[0] as AOPVariable
        val variable = resultSet.createVariable(child.name)
        val b = resultSet.getValueObject(resultRow, variable)
        if (a.get() is ValueUndef)
            a.set(b)
        else if (a.get() is ValueDouble || b is ValueDouble)
            a.set(ValueDouble(a.get()!!.toDouble() + b.toDouble()))
        else if (a.get() is ValueDecimal || b is ValueDecimal)
            a.set(ValueDecimal(a.get()!!.toDouble() + b.toDouble()))
        else if (a.get() is ValueInteger || b is ValueInteger)
            a.set(ValueInteger(a.get()!!.toInt() + b.toInt()))
        else
            a.set(ValueError())
    }

    override fun cloneOP() = AOPAggregationSUM(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
