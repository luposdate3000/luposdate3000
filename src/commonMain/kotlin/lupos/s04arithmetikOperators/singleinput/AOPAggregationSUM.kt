package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
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
class AOPAggregationSUM(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationSUMID, "AOPAggregationSUM", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
Coverage.funStart(2881)
        if (distinct) {
Coverage.ifStart(2882)
            return "SUM(DISTINCT " + children[0].toSparql() + ")"
        }
Coverage.statementStart(2883)
        return "SUM(" + children[0].toSparql() + ")"
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2884)
        if (other !is AOPAggregationSUM) {
Coverage.ifStart(2885)
            return false
        }
Coverage.statementStart(2886)
        for (i in children.indices) {
Coverage.forLoopStart(2887)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2888)
                return false
            }
Coverage.statementStart(2889)
        }
Coverage.statementStart(2890)
        if (distinct != other.distinct) {
Coverage.ifStart(2891)
            return false
        }
Coverage.statementStart(2892)
        return true
    }
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
Coverage.funStart(2893)
        val res = ColumnIteratorAggregate()
Coverage.statementStart(2894)
        val child = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2895)
        res.evaluate = {
Coverage.statementStart(2896)
            val value = child()
Coverage.statementStart(2897)
            res.count++
Coverage.statementStart(2898)
            if (value is ValueError) {
Coverage.ifStart(2899)
                res.value = value
Coverage.statementStart(2900)
                res.evaluate = res::_evaluate
Coverage.statementStart(2901)
            } else if (res.value is ValueUndef) {
Coverage.ifStart(2902)
                res.value = value
Coverage.statementStart(2903)
            } else if (res.value is ValueDouble || value is ValueDouble) {
Coverage.ifStart(2904)
                res.value = ValueDouble(res.value.toDouble() + value.toDouble())
Coverage.statementStart(2905)
            } else if (res.value is ValueDecimal || value is ValueDecimal) {
Coverage.ifStart(2906)
                res.value = ValueDecimal(res.value.toDouble() + value.toDouble())
Coverage.statementStart(2907)
            } else if (res.value is ValueInteger || value is ValueInteger) {
Coverage.ifStart(2908)
                res.value = ValueInteger(res.value.toInt() + value.toInt())
Coverage.statementStart(2909)
            } else {
Coverage.ifStart(2910)
                res.value = ValueError()
Coverage.statementStart(2911)
                res.evaluate = res::_evaluate
Coverage.statementStart(2912)
            }
Coverage.statementStart(2913)
        }
Coverage.statementStart(2914)
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2915)
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
Coverage.statementStart(2916)
        return {
Coverage.statementStart(2917)
            /*return*/tmp.value
        }
Coverage.statementStart(2918)
    }
    override fun cloneOP() = AOPAggregationSUM(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
