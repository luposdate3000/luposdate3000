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

import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.iterator.ColumnIteratorAggregate
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.IQuery
import lupos.shared.PartitionHelper
import lupos.shared.XMLElement
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class AOPAggregationCOUNT public constructor(
    query: IQuery,
    @JvmField public val distinct: Boolean,
    child: AOPBase?,
) : AOPAggregationBase(
    query,
    EOperatorIDExt.AOPAggregationCOUNTID,
    "AOPAggregationCOUNT",
    if (child == null) {
        arrayOf()
    } else {
        arrayOf(child)
    },
) {
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = super.toXMLElement(partial, partition).addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "COUNT(DISTINCT " + children[0].toSparql() + ")"
        }
        return "COUNT(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean = other is AOPAggregationCOUNT && distinct == other.distinct && children.contentEquals(other.children)
    private class ColumnIteratorAggregateCOUNT(private val child: () -> DictionaryValueType, private val dictionary: IDictionary) : ColumnIteratorAggregate() {
        val myList = mutableSetOf<DictionaryValueType>()
        private var counter = 0L

        override fun evaluate() {
            val i = child()
            if (!myList.contains(i)) {
                myList.add(i)
                counter++
            }
        }

        override fun evaluateFinish(): DictionaryValueType {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.integerToByteArray(buffer, BigInteger.parseString(counter.toString(), 10))
            return dictionary.createValue(buffer)
        }
    }

    private class ColumnIteratorAggregateCOUNTNoChild(private val dictionary: IDictionary) : ColumnIteratorAggregate() {
        private var counter = 0L

        override fun evaluate() {
            counter++
        }

        override fun evaluateFinish(): DictionaryValueType {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.integerToByteArray(buffer, BigInteger.parseString(counter.toString(), 10))
            return dictionary.createValue(buffer)
        }
    }

    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        return if (children.isEmpty() || !distinct) {
            ColumnIteratorAggregateCOUNTNoChild(query.getDictionary())
        } else {
            ColumnIteratorAggregateCOUNT((children[0] as AOPBase).evaluateID(row), query.getDictionary())
        }
    }

    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            tmp.evaluateFinish()
        }
    }

    override fun cloneOP(): IOPBase = AOPAggregationCOUNT(
        query,
        distinct,
        if (children.size == 0) {
            null
        } else {
            children[0] as AOPBase
        },
    )
}
