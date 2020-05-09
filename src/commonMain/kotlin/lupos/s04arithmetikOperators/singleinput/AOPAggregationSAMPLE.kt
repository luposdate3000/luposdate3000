package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPAggregationSAMPLE(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationSAMPLEID, "AOPAggregationSAMPLE", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "SAMPLE(DISTINCT " + children[0].toSparql() + ")"
        }
        return "SAMPLE(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationSAMPLE) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        if (distinct != other.distinct) {
            return false
        }
        return true
    }

    override fun createIterator(row: ColumnIteratorRow): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        val child = (children[0] as AOPBase).evaluate(row)
        res.evaluate = {
            val value = child()
            res.value = value
            res.evaluate = {
            }
        }
        return res
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
        return {
            /*return*/tmp.value
        }
    }

    override fun cloneOP() = AOPAggregationSAMPLE(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
