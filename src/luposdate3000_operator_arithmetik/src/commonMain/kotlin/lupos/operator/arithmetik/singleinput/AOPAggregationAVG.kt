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

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.iterator.ColumnIteratorAggregate
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.XMLElement
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class AOPAggregationAVG public constructor(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorIDExt.AOPAggregationAVGID, "AOPAggregationAVG", Array<IOPBase>(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "AVG(DISTINCT " + children[0].toSparql() + ")"
        }
        return "AVG(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean = other is AOPAggregationAVG && distinct == other.distinct && children.contentEquals(other.children)
    private class ColumnIteratorAggregateAVG(private val child: () -> Int, private val dictionary: IDictionary) : ColumnIteratorAggregate() {
        private val buffer = ByteArrayWrapper()
        private val bufferCurrent = ByteArrayWrapper()
        private var isError = false
        private var hasInit = false
        private var counter = 0

        init {
            DictionaryHelper.undefToByteArray(buffer)
        }

        override fun evaluate() {
            if (!isError) {
                dictionary.getValue(bufferCurrent, child())
                try {
                    if (!hasInit) {
                        ByteArrayWrapperExt.copyInto(bufferCurrent, buffer)
                        hasInit = true
                    } else {
                        AOPAggregationHelper.addition(buffer, bufferCurrent, buffer)
                    }
                } catch (e: Throwable) {
                    isError = true
                    DictionaryHelper.errorToByteArray(buffer)
                }
            }
        }

        override fun evaluateFinish(): Int {
            if (counter == 0) {
                return dictionary.createValue(buffer)
            } else {
                val type = DictionaryHelper.byteArrayToType(buffer)
                when (type) {
                    ETripleComponentTypeExt.DECIMAL -> {
                        val a = DictionaryHelper.byteArrayToDecimal_I(buffer)
                        val res = a / counter
                        DictionaryHelper.decimalToByteArray(buffer, res)
                    }
                    ETripleComponentTypeExt.DOUBLE -> {
                        val a = DictionaryHelper.byteArrayToDouble_I(buffer)
                        val res = a / counter
                        DictionaryHelper.doubleToByteArray(buffer, res)
                    }
                    ETripleComponentTypeExt.FLOAT -> {
                        val a = DictionaryHelper.byteArrayToFloat_I(buffer)
                        val res = a / counter
                        DictionaryHelper.floatToByteArray(buffer, res)
                    }
                    ETripleComponentTypeExt.INTEGER -> {
                        val a = DictionaryHelper.byteArrayToInteger_I(buffer)
                        val res = BigDecimal.fromBigInteger(a) / counter
                        DictionaryHelper.decimalToByteArray(buffer, res)
                    }
                }
                return dictionary.createValue(buffer)
            }
        }
    }

    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        return ColumnIteratorAggregateAVG((children[0] as AOPBase).evaluateID(row), query.getDictionary())
    }

    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            tmp.evaluateFinish()
        }
    }

    override fun cloneOP(): IOPBase = AOPAggregationAVG(query, distinct, Array<AOPBase>(children.size) { (children[it].cloneOP()) as AOPBase })
}
