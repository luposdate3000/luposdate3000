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
import lupos.s04arithmetikOperators.ArrayAllocatorAOPBase
import lupos.s04logicalOperators.ArrayAllocatorIOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class AOPAggregationSUM public constructor(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorIDExt.AOPAggregationSUMID, "AOPAggregationSUM", ArrayAllocatorIOPBase(childs.size) { childs[it] }) {
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

    override fun cloneOP(): IOPBase = AOPAggregationSUM(query, distinct, ArrayAllocatorAOPBase(children.size) { (children[it].cloneOP()) as AOPBase })
}
