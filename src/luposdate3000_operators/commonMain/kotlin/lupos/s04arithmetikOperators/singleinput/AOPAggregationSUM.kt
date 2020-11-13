package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPAggregationSUM(query: IQuery, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationSUMID, "AOPAggregationSUM", Array(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "SUM(DISTINCT " + children[0].toSparql() + ")"
        }
        return "SUM(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean = other is AOPAggregationSUM && distinct == other.distinct && children.contentEquals(other.children)
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        val child = (children[0] as AOPBase).evaluate(row)
        res.evaluate = {
            val value = child()
            res.count++
            if (value is ValueError) {
                res.value = value
                res.evaluate = res::aggregateEvaluate
            } else if (res.value is ValueUndef) {
                res.value = value
            } else if (res.value is ValueDouble || value is ValueDouble) {
                res.value = ValueDouble(res.value.toDouble() + value.toDouble())
            } else if (res.value is ValueFloat || value is ValueFloat) {
                res.value = ValueFloat(res.value.toDouble() + value.toDouble())
            } else if (res.value is ValueDecimal || value is ValueDecimal) {
                res.value = ValueDecimal(res.value.toDecimal() + value.toDecimal())
            } else if (res.value is ValueInteger || value is ValueInteger) {
                res.value = ValueInteger(res.value.toInt() + value.toInt())
            } else {
                res.value = ValueError()
                res.evaluate = res::aggregateEvaluate
            }
        }
        return res
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            tmp.value
        }
    }

    override fun cloneOP(): IOPBase = AOPAggregationSUM(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
