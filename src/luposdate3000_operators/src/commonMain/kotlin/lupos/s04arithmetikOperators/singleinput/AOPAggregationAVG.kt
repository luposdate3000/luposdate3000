package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.SanityCheck
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
import kotlin.jvm.JvmField

class AOPAggregationAVG(query: IQuery, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationAVGID, "AOPAggregationAVG", Array(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "AVG(DISTINCT " + children[0].toSparql() + ")"
        }
        return "AVG(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean = other is AOPAggregationAVG && distinct == other.distinct && children.contentEquals(other.children)
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        val child = (children[0] as AOPBase).evaluate(row)
        res.evaluate = {
            var tmp1 = res.value
            res.count++
            try {
                val value = child()
                if (value is ValueError) {
                    tmp1 = value
                    res.evaluate = res::aggregateEvaluate
                } else if (tmp1 is ValueUndef) {
                    tmp1 = value
                } else if (tmp1 is ValueDouble || value is ValueDouble) {
                    tmp1 = ValueDouble(tmp1.toDouble() + value.toDouble())
                } else if (tmp1 is ValueFloat || value is ValueFloat) {
                    tmp1 = ValueFloat(tmp1.toDouble() + value.toDouble())
                } else if (tmp1 is ValueDecimal || value is ValueDecimal) {
                    tmp1 = ValueDecimal(tmp1.toDecimal() + value.toDecimal())
                } else if (tmp1 is ValueInteger || value is ValueInteger) {
                    tmp1 = ValueDecimal((tmp1.toInt() + value.toInt()).toMyBigDecimal())
                } else {
                    tmp1 = ValueError()
                    res.evaluate = res::aggregateEvaluate
                }
            } catch (e: EvaluationException) {
                tmp1 = ValueError()
                res.evaluate = res::aggregateEvaluate
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 34" }
                e.printStackTrace()
                tmp1 = ValueError()
                res.evaluate = res::aggregateEvaluate
            }
            res.value = tmp1
        }
        return res
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            val res: ValueDefinition
            val tmp1 = tmp.value
            res = when (tmp1) {
                is ValueDouble -> {
                    ValueDouble(tmp1.toDouble() / tmp.count)
                }
                is ValueFloat -> {
                    ValueFloat(tmp1.toDouble() / tmp.count)
                }
                is ValueDecimal -> {
                    ValueDecimal(tmp1.value / MyBigDecimal(tmp.count))
                }
                else -> {
                    ValueError()
                }
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPAggregationAVG(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
