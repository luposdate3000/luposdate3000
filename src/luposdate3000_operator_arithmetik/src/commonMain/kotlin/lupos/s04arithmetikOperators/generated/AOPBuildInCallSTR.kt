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

public class AOPBuildInCallSTR public constructor(query: IQuery, child0: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRID, "AOPBuildInCallSTR", arrayOf(child0,)) {
    override fun toSparql(): String = "STR(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTR && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallSTR(query, children[0].cloneOP() as AOPBase)
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
                    val tmp_4: String = tmp_3.toString()
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_4)
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
                    val tmp_7: String = tmp_6_str
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_7)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp_9: MyBigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                    val tmp_10: String = tmp_9.toString()
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_10)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp_12: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                    val tmp_13: String = tmp_12.toString()
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_13)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.FLOAT -> {
                    val tmp_15: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                    val tmp_16: String = tmp_15.toString()
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_16)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp_18: MyBigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                    val tmp_19: String = tmp_18.toString()
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_19)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.IRI -> {
                    val tmp_21: String = DictionaryHelper.byteArrayToIri(tmp_0)
                    val tmp_22: String = tmp_21
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_22)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp_24: String = DictionaryHelper.byteArrayToString(tmp_0)
                    val tmp_25: String = tmp_24
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_25)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val tmp_27_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                    val tmp_27_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                    val tmp_28: String = tmp_27_content
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_28)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_30_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_30_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    val tmp_31: String = tmp_30_content
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_31)
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
