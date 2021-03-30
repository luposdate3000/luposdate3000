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

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.dictionary.DictionaryExt
import lupos.dictionary.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPBuildInCallSECONDS public constructor(query: IQuery, child0: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSECONDSID, "AOPBuildInCallSECONDS", arrayOf(child0,)) {
    override fun toSparql(): String = "SECONDS(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSECONDS && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallSECONDS(query, children[0].cloneOP() as AOPBase)
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
                ETripleComponentTypeExt.DATE_TIME -> {
                    val tmp_3_typed_content: String = DictionaryHelper.byteArrayToDateTimeAsTyped_Content(tmp_0)
                    val tmp_3_year: BigInteger = DictionaryHelper.byteArrayToDateTime_Year(tmp_0)
                    val tmp_3_month: BigInteger = DictionaryHelper.byteArrayToDateTime_Month(tmp_0)
                    val tmp_3_day: BigInteger = DictionaryHelper.byteArrayToDateTime_Day(tmp_0)
                    val tmp_3_hours: BigInteger = DictionaryHelper.byteArrayToDateTime_Hours(tmp_0)
                    val tmp_3_minutes: BigInteger = DictionaryHelper.byteArrayToDateTime_Minutes(tmp_0)
                    val tmp_3_seconds: BigDecimal = DictionaryHelper.byteArrayToDateTime_Seconds(tmp_0)
                    val tmp_3_tz: String = DictionaryHelper.byteArrayToDateTime_TZ(tmp_0)
                    val tmp_3_timezone: String = DictionaryHelper.byteArrayToDateTime_TimeZone(tmp_0)
                    val tmp_4: BigDecimal = tmp_3_seconds
                    DictionaryHelper.decimalToByteArray(tmp_2, tmp_4)
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
