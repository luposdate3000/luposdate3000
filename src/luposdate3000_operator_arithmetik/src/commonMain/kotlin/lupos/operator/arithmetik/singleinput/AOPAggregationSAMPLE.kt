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
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class AOPAggregationSAMPLE public constructor(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorIDExt.AOPAggregationSAMPLEID, "AOPAggregationSAMPLE", Array<IOPBase>(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "SAMPLE(DISTINCT " + children[0].toSparql() + ")"
        }
        return "SAMPLE(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean = other is AOPAggregationSAMPLE && distinct == other.distinct && children.contentEquals(other.children)
    private class ColumnIteratorAggregateSAMPLE(private val child: () -> Int, private val dictionary: IDictionary) : ColumnIteratorAggregate() {
        private var value = DictionaryExt.undefValue
        private var isError = false
        private var hasInit = false

        override fun evaluate() {
            if (!hasInit) {
                value = child()
                hasInit = true
            }
        }

        override fun evaluateFinish(): Int {
            return value
        }
    }

    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        return ColumnIteratorAggregateSAMPLE((children[0] as AOPBase).evaluateID(row), query.getDictionary())
    }

    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            tmp.evaluateFinish()
        }
    }

    override fun cloneOP(): IOPBase = AOPAggregationSAMPLE(query, distinct, Array<AOPBase>(children.size) { (children[it].cloneOP()) as AOPBase })
}
