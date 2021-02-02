/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorIDExt
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
public class AOPAggregationAVG public constructor(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorIDExt.AOPAggregationAVGID, "AOPAggregationAVG", Array(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("distinct", "" + distinct)
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
