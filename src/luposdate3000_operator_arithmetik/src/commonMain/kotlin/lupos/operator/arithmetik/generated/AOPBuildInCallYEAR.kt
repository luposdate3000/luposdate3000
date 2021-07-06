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
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPBuildInCallYEAR public constructor(query: IQuery, child0: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallYEARID, "AOPBuildInCallYEAR", arrayOf(child0)) {
    override fun toSparql(): String = "YEAR(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallYEAR && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallYEAR(query, children[0].cloneOP() as AOPBase)
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
                ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    val tmp_5_typed_content: String = DictionaryHelper.byteArrayToDateTimeAsTyped_Content(tmp_0)
                    val tmp_5_year: BigInteger = DictionaryHelper.byteArrayToDateTime_Year(tmp_0)
                    val tmp_5_month: BigInteger = DictionaryHelper.byteArrayToDateTime_Month(tmp_0)
                    val tmp_5_day: BigInteger = DictionaryHelper.byteArrayToDateTime_Day(tmp_0)
                    val tmp_5_hours: BigInteger = DictionaryHelper.byteArrayToDateTime_Hours(tmp_0)
                    val tmp_5_minutes: BigInteger = DictionaryHelper.byteArrayToDateTime_Minutes(tmp_0)
                    val tmp_5_seconds: BigDecimal = DictionaryHelper.byteArrayToDateTime_Seconds(tmp_0)
                    val tmp_5_tz: String = DictionaryHelper.byteArrayToDateTime_TZ(tmp_0)
                    val tmp_5_timezone: String = DictionaryHelper.byteArrayToDateTime_TimeZone(tmp_0)
                    val tmp_6: BigInteger = tmp_5_year
                    DictionaryHelper.integerToByteArray(tmp_2, tmp_6)
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
