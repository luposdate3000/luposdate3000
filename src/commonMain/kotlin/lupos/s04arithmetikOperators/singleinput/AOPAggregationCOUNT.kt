package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPAggregationCOUNT(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationCOUNTID, "AOPAggregationCOUNT", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        var res = "COUNT("
        if (distinct) {
            res += "DISTINCT "
        }
        if (children.size > 0) {
            res += children[0].toSparql()
        }
        res += ")"
        return res
    }

    override fun equals(other: Any?) = other is AOPAggregationCOUNT && distinct == other.distinct && children.contentEquals(other.children)
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        res.evaluate = {
            res.count++
        }
        return res
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
        return {
            /*return*/ValueInteger(tmp.count)
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPAggregationCOUNT(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
