package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPAggregationAVG(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationAVGID, "AOPAggregationAVG", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
Coverage.funStart(2733)
        if (distinct) {
Coverage.ifStart(2734)
            return "AVG(DISTINCT " + children[0].toSparql() + ")"
        }
Coverage.statementStart(2735)
        return "AVG(" + children[0].toSparql() + ")"
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2736)
        if (other !is AOPAggregationAVG) {
Coverage.ifStart(2737)
            return false
        }
Coverage.statementStart(2738)
        for (i in children.indices) {
Coverage.forLoopStart(2739)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2740)
                return false
            }
Coverage.statementStart(2741)
        }
Coverage.statementStart(2742)
        if (distinct != other.distinct) {
Coverage.ifStart(2743)
            return false
        }
Coverage.statementStart(2744)
        return true
    }
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
Coverage.funStart(2745)
        val res = ColumnIteratorAggregate()
Coverage.statementStart(2746)
        val child = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2747)
        res.evaluate = {
Coverage.statementStart(2748)
            val value = child()
Coverage.statementStart(2749)
            res.count++
Coverage.statementStart(2750)
            if (value is ValueError) {
Coverage.ifStart(2751)
                res.value = value
Coverage.statementStart(2752)
                res.evaluate = res::_evaluate
Coverage.statementStart(2753)
            } else if (res.value is ValueUndef) {
Coverage.ifStart(2754)
                res.value = value
Coverage.statementStart(2755)
            } else if (res.value is ValueDouble || value is ValueDouble) {
Coverage.ifStart(2756)
                res.value = ValueDouble(res.value.toDouble() + value.toDouble())
Coverage.statementStart(2757)
            } else if (res.value is ValueDecimal || value is ValueDecimal) {
Coverage.ifStart(2758)
                res.value = ValueDecimal(res.value.toDouble() + value.toDouble())
Coverage.statementStart(2759)
            } else if (res.value is ValueInteger || value is ValueInteger) {
Coverage.ifStart(2760)
                res.value = ValueDecimal(0.0 + (res.value.toInt() + value.toInt()))
Coverage.statementStart(2761)
            } else {
Coverage.ifStart(2762)
                res.value = ValueError()
Coverage.statementStart(2763)
                res.evaluate = res::_evaluate
Coverage.statementStart(2764)
            }
Coverage.statementStart(2765)
        }
Coverage.statementStart(2766)
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2767)
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
Coverage.statementStart(2768)
        return {
Coverage.statementStart(2769)
            var res: ValueDefinition
Coverage.statementStart(2770)
            if (tmp.value is ValueDouble) {
Coverage.ifStart(2771)
                res = ValueDouble(tmp.value.toDouble() / tmp.count)
Coverage.statementStart(2772)
            } else if (tmp.value is ValueDecimal) {
Coverage.ifStart(2773)
                res = ValueDecimal(tmp.value.toDouble() / tmp.count)
Coverage.statementStart(2774)
            } else {
Coverage.ifStart(2775)
                res = ValueError()
Coverage.statementStart(2776)
            }
Coverage.statementStart(2777)
/*return*/res
        }
Coverage.statementStart(2778)
    }
    override fun cloneOP() = AOPAggregationAVG(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
