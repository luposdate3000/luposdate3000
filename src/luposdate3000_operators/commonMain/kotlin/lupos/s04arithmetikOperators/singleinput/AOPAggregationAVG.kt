package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField

import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.SanityCheck


import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
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
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "AVG(DISTINCT " + children[0].toSparql() + ")"
        }
        return "AVG(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?) = other is AOPAggregationAVG && distinct == other.distinct && children.contentEquals(other.children)
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
                    res.evaluate = res::_evaluate
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
                    res.evaluate = res::_evaluate
                }
            } catch (e: EvaluationException) {
                tmp1 = ValueError()
                res.evaluate = res::_evaluate
            } catch (e: Throwable) {
                SanityCheck.println({ "TODO exception 34" })
                e.printStackTrace()
                tmp1 = ValueError()
                res.evaluate = res::_evaluate
            }
            res.value = tmp1
        }
        return res
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#" + uuid]!! as ColumnIteratorAggregate
        return {
            var res: ValueDefinition
            var tmp1 = tmp.value
            if (tmp1 is ValueDouble) {
                res = ValueDouble(tmp1.toDouble() / tmp.count)
            } else if (tmp1 is ValueFloat) {
                res = ValueFloat(tmp1.toDouble() / tmp.count)
            } else if (tmp1 is ValueDecimal) {
                res = ValueDecimal(tmp1.value / MyBigDecimal(tmp.count))
            } else {
                res = ValueError()
            }
/*return*/res
        }

    }

    override fun cloneOP() :IOPBase= AOPAggregationAVG(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
