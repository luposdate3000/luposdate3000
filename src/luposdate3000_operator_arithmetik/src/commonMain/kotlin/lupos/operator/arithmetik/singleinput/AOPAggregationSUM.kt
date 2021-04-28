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
package lupos.operator.arithmetik.singleinput

import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.iterator.ColumnIteratorAggregate
import lupos.shared.EOperatorIDExt
import lupos.shared.IQuery
import lupos.shared.ValueDecimal
import lupos.shared.ValueDefinition
import lupos.shared.ValueDouble
import lupos.shared.ValueError
import lupos.shared.ValueFloat
import lupos.shared.ValueInteger
import lupos.shared.ValueUndef
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class AOPAggregationSUM public constructor(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorIDExt.AOPAggregationSUMID, "AOPAggregationSUM", Array<IOPBase>(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "SUM(DISTINCT " + children[0].toSparql() + ")"
        }
        return "SUM(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean = other is AOPAggregationSUM && distinct == other.distinct && children.contentEquals(other.children)
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        val child = (children[0] as AOPBase).evaluate(row)
        res.evaluate = {
            val value = child()
            res.count++
            if (value is ValueError) {
                res.value = value
                res.evaluate = res::aggregateEvaluate
            } else if (res.value is ValueUndef) {
                res.value = value
            } else if (res.value is ValueDouble || value is ValueDouble) {
                res.value = ValueDouble(res.value.toDouble() + value.toDouble())
            } else if (res.value is ValueFloat || value is ValueFloat) {
                res.value = ValueFloat(res.value.toDouble() + value.toDouble())
            } else if (res.value is ValueDecimal || value is ValueDecimal) {
                res.value = ValueDecimal(res.value.toDecimal() + value.toDecimal())
            } else if (res.value is ValueInteger || value is ValueInteger) {
                res.value = ValueInteger(res.value.toInt() + value.toInt())
            } else {
                res.value = ValueError()
                res.evaluate = res::aggregateEvaluate
            }
        }
        return res
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            tmp.value
        }
    }

    override fun cloneOP(): IOPBase = AOPAggregationSUM(query, distinct, Array<AOPBase>(children.size) { (children[it].cloneOP()) as AOPBase })
}
