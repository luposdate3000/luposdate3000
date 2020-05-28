package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPAggregationSAMPLE(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationSAMPLEID, "AOPAggregationSAMPLE", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
Coverage.funStart(2856)
        if (distinct) {
Coverage.ifStart(2857)
            return "SAMPLE(DISTINCT " + children[0].toSparql() + ")"
        }
Coverage.statementStart(2858)
        return "SAMPLE(" + children[0].toSparql() + ")"
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2859)
        if (other !is AOPAggregationSAMPLE) {
Coverage.ifStart(2860)
            return false
        }
Coverage.statementStart(2861)
        for (i in children.indices) {
Coverage.forLoopStart(2862)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2863)
                return false
            }
Coverage.statementStart(2864)
        }
Coverage.statementStart(2865)
        if (distinct != other.distinct) {
Coverage.ifStart(2866)
            return false
        }
Coverage.statementStart(2867)
        return true
    }
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
Coverage.funStart(2868)
        val res = ColumnIteratorAggregate()
Coverage.statementStart(2869)
        val child = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2870)
        res.evaluate = {
Coverage.statementStart(2871)
            val value = child()
Coverage.statementStart(2872)
            res.value = value
Coverage.statementStart(2873)
            res.evaluate = {
Coverage.statementStart(2874)
            }
Coverage.statementStart(2875)
        }
Coverage.statementStart(2876)
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2877)
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
Coverage.statementStart(2878)
        return {
Coverage.statementStart(2879)
            /*return*/tmp.value
        }
Coverage.statementStart(2880)
    }
    override fun cloneOP() = AOPAggregationSAMPLE(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
