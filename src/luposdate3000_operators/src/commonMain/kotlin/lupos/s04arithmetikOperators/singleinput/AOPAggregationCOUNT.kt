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
import lupos.s00misc.MyBigInteger
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public class AOPAggregationCOUNT public constructor(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorIDExt.AOPAggregationCOUNTID, "AOPAggregationCOUNT", Array(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        var res = "COUNT("
        if (distinct) {
            res += "DISTINCT "
        }
        if (children.isNotEmpty()) {
            res += children[0].toSparql()
        }
        res += ")"
        return res
    }
    override fun equals(other: Any?): Boolean = other is AOPAggregationCOUNT && distinct == other.distinct && children.contentEquals(other.children)
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        res.evaluate = {
            res.count++
        }
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            ValueInteger(MyBigInteger(tmp.count))
        }
    }
    override fun cloneOP(): IOPBase = AOPAggregationCOUNT(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
