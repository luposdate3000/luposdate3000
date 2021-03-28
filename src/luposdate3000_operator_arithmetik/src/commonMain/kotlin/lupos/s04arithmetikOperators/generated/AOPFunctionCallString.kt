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
package lupos.s04arithmetikOperators.generated

import lupos.dictionary.DictionaryExt
import lupos.dictionary.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPFunctionCallString public constructor(query: IQuery, child0: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPFunctionCallStringID, "AOPFunctionCallString", arrayOf(child0,)) {
    override fun toSparql(): String = "<http://www.w3.org/2001/XMLSchema#string>(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPFunctionCallString && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPFunctionCallString(query, children[0].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_2: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> Int = (children[0] as AOPBase).evaluateID(row)
        return {
            var res: Int
            val childIn0: Int = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            val tmp_1: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            when (tmp_1) {
                ETripleComponentTypeExt.BOOLEAN -> {
                    val tmp_3: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                    val tmp_4_content: String = tmp_3.toString()
                    val tmp_4_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_4_content, tmp_4_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    val tmp_6_str: String = DictionaryHelper.byteArrayToDateTime(tmp_0)
                    val tmp_6_year: MyBigInteger = DictionaryHelper.byteArrayToDateTime_Year(tmp_0)
                    val tmp_6_month: MyBigInteger = DictionaryHelper.byteArrayToDateTime_Month(tmp_0)
                    val tmp_6_day: MyBigInteger = DictionaryHelper.byteArrayToDateTime_Day(tmp_0)
                    val tmp_6_hours: MyBigInteger = DictionaryHelper.byteArrayToDateTime_Hours(tmp_0)
                    val tmp_6_minutes: MyBigInteger = DictionaryHelper.byteArrayToDateTime_Minutes(tmp_0)
                    val tmp_6_seconds: MyBigDecimal = DictionaryHelper.byteArrayToDateTime_Seconds(tmp_0)
                    val tmp_6_tz: String = DictionaryHelper.byteArrayToDateTime_TZ(tmp_0)
                    val tmp_6_timezone: String = DictionaryHelper.byteArrayToDateTime_TimeZone(tmp_0)
                    val tmp_7_content: String = tmp_6_str
                    val tmp_7_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_7_content, tmp_7_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp_9: MyBigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                    val tmp_10_content: String = tmp_9.toString()
                    val tmp_10_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_10_content, tmp_10_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp_12: Double = DictionaryHelper.byteArrayToDouble(tmp_0)
                    val tmp_13_content: String = tmp_12.toString()
                    val tmp_13_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_13_content, tmp_13_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.FLOAT -> {
                    val tmp_15: Double = DictionaryHelper.byteArrayToFloat(tmp_0)
                    val tmp_16_content: String = tmp_15.toString()
                    val tmp_16_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_16_content, tmp_16_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp_18: MyBigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                    val tmp_19_content: String = tmp_18.toString()
                    val tmp_19_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_19_content, tmp_19_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp_21: String = DictionaryHelper.byteArrayToString(tmp_0)
                    val tmp_22_content: String = tmp_21
                    val tmp_22_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_22_content, tmp_22_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val tmp_24_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                    val tmp_24_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                    val tmp_25_content: String = tmp_24_content
                    val tmp_25_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_25_content, tmp_25_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_27_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_27_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    val tmp_28_content: String = tmp_27_content
                    val tmp_28_type: String = "http://www.w3.org/2001/XMLSchema#string"
                    DictionaryHelper.langToByteArray(tmp_2, tmp_28_content, tmp_28_type)
                    res = query.getDictionary().createValue(tmp_2)
                }
                else -> {
                    res = DictionaryExt.errorValue
                }
            }
            res
        }
    }
}
