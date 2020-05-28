package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPAggregationMIN(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationMINID, "AOPAggregationMIN", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
Coverage.funStart(2831)
        if (distinct) {
Coverage.ifStart(2832)
            return "MIN(DISTINCT " + children[0].toSparql() + ")"
        }
Coverage.statementStart(2833)
        return "MIN(" + children[0].toSparql() + ")"
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2834)
        if (other !is AOPAggregationMIN) {
Coverage.ifStart(2835)
            return false
        }
Coverage.statementStart(2836)
        for (i in children.indices) {
Coverage.forLoopStart(2837)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2838)
                return false
            }
Coverage.statementStart(2839)
        }
Coverage.statementStart(2840)
        if (distinct != other.distinct) {
Coverage.ifStart(2841)
            return false
        }
Coverage.statementStart(2842)
        return true
    }
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
Coverage.funStart(2843)
        val res = ColumnIteratorAggregate()
Coverage.statementStart(2844)
        val child = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2845)
        res.evaluate = {
Coverage.statementStart(2846)
            val value = child()
Coverage.statementStart(2847)
            if (res.value is ValueUndef || res.value.compareTo(value) > 0) {
Coverage.ifStart(2848)
                res.value = value
Coverage.statementStart(2849)
            }
Coverage.statementStart(2850)
        }
Coverage.statementStart(2851)
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2852)
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
Coverage.statementStart(2853)
        return {
Coverage.statementStart(2854)
            /*return*/tmp.value
        }
Coverage.statementStart(2855)
    }
    override fun cloneOP() = AOPAggregationMIN(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
