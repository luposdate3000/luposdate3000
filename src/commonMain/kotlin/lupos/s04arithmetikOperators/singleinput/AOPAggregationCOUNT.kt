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
Coverage.funStart(2779)
        var res = "COUNT("
Coverage.statementStart(2780)
        if (distinct) {
Coverage.ifStart(2781)
            res += "DISTINCT "
Coverage.statementStart(2782)
        }
Coverage.statementStart(2783)
        if (children.size > 0) {
Coverage.ifStart(2784)
            res += children[0].toSparql()
Coverage.statementStart(2785)
        }
Coverage.statementStart(2786)
        res += ")"
Coverage.statementStart(2787)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2788)
        if (other !is AOPAggregationCOUNT) {
Coverage.ifStart(2789)
            return false
        }
Coverage.statementStart(2790)
        for (i in children.indices) {
Coverage.forLoopStart(2791)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2792)
                return false
            }
Coverage.statementStart(2793)
        }
Coverage.statementStart(2794)
        if (distinct != other.distinct) {
Coverage.ifStart(2795)
            return false
        }
Coverage.statementStart(2796)
        return true
    }
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
Coverage.funStart(2797)
        val res = ColumnIteratorAggregate()
Coverage.statementStart(2798)
        res.evaluate = {
Coverage.statementStart(2799)
            res.count++
Coverage.statementStart(2800)
        }
Coverage.statementStart(2801)
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2802)
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
Coverage.statementStart(2803)
        return {
Coverage.statementStart(2804)
            /*return*/ValueInteger(tmp.count)
        }
Coverage.statementStart(2805)
    }
    override fun cloneOP() = AOPAggregationCOUNT(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
