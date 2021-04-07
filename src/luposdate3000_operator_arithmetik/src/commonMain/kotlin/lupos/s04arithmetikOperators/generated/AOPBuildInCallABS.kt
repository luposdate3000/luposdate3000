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
import kotlin.math.abs

public class AOPBuildInCallABS public constructor(query: IQuery, child0: AOPBase, ) : AOPBase(query, EOperatorIDExt.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child0, )) {
    override fun toSparql(): String = "ABS(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallABS && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
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
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp_3: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                    val tmp_4: BigDecimal = tmp_3.abs()
                    DictionaryHelper.decimalToByteArray(tmp_2, tmp_4)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp_6: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                    val tmp_7: Double = abs(tmp_6)
                    DictionaryHelper.doubleToByteArray(tmp_2, tmp_7)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.FLOAT -> {
                    val tmp_9: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                    val tmp_10: Double = abs(tmp_9)
                    DictionaryHelper.floatToByteArray(tmp_2, tmp_10)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp_12: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                    val tmp_13: BigInteger = tmp_12.abs()
                    DictionaryHelper.integerToByteArray(tmp_2, tmp_13)
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
