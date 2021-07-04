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
package lupos.operator.arithmetik.generated

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.operator.arithmetik.AOPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPFunctionCallFloat public constructor(query: IQuery, child0: AOPBase) : AOPBase(query, EOperatorIDExt.AOPFunctionCallFloatID, "AOPFunctionCallFloat", arrayOf(child0)) {
    override fun toSparql(): String = "<http://www.w3.org/2001/XMLSchema#float>(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPFunctionCallFloat && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPFunctionCallFloat(query, children[0].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_2: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        return {
            var res: Int
            val childIn0: Int = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            val tmp_1: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            when (tmp_1) {
                ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.UNDEF -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    val tmp_4: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                    val tmp_5: Double = if (tmp_4) {
                        1.0
                    } else {
                        0.0
                    }
                    DictionaryHelper.floatToByteArray(tmp_2, tmp_5)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp_8: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                    val tmp_9: Double = tmp_8.doubleValue()
                    DictionaryHelper.floatToByteArray(tmp_2, tmp_9)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp_11: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                    val tmp_12: Double = tmp_11
                    DictionaryHelper.floatToByteArray(tmp_2, tmp_12)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.FLOAT -> {
                    val tmp_15: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                    val tmp_16: Double = tmp_15
                    DictionaryHelper.floatToByteArray(tmp_2, tmp_16)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp_18: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                    val tmp_19: Double = tmp_18.doubleValue()
                    DictionaryHelper.floatToByteArray(tmp_2, tmp_19)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp_22: String = DictionaryHelper.byteArrayToString(tmp_0)
                    try {
                        val tmp_23: Double = tmp_22.toDouble()
                        DictionaryHelper.floatToByteArray(tmp_2, tmp_23)
                        res = query.getDictionary().createValue(tmp_2)
                    } catch (e: Throwable) {
                        DictionaryHelper.errorToByteArray(tmp_2)
                        res = query.getDictionary().createValue(tmp_2)
                    }
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val tmp_25_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                    val tmp_25_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                    try {
                        val tmp_26: Double = tmp_25_content.toDouble()
                        DictionaryHelper.floatToByteArray(tmp_2, tmp_26)
                        res = query.getDictionary().createValue(tmp_2)
                    } catch (e: Throwable) {
                        DictionaryHelper.errorToByteArray(tmp_2)
                        res = query.getDictionary().createValue(tmp_2)
                    }
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_28_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_28_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    try {
                        val tmp_29: Double = tmp_28_content.toDouble()
                        DictionaryHelper.floatToByteArray(tmp_2, tmp_29)
                        res = query.getDictionary().createValue(tmp_2)
                    } catch (e: Throwable) {
                        DictionaryHelper.errorToByteArray(tmp_2)
                        res = query.getDictionary().createValue(tmp_2)
                    }
                }
                else -> {
                    res = DictionaryValueHelper.errorValue
                }
            }
            res
        }
    }
}
