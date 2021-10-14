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
import lupos.operator.arithmetik.AOPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.math.ceil

public class AOPBuildInCallCEIL public constructor(query: IQuery, child0: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallCEILID, "AOPBuildInCallCEIL", arrayOf(child0)) {
    override fun toSparql(): String = "CEIL(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallCEIL && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallCEIL(query, children[0].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_2: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        return {
            val res: DictionaryValueType
            val childIn0: DictionaryValueType = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            when (DictionaryHelper.byteArrayToType(tmp_0)) {
                ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp_6: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                    val tmp_7: BigDecimal = tmp_6.ceil()
                    DictionaryHelper.decimalToByteArray(tmp_2, tmp_7)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp_9: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                    val tmp_10: Double = ceil(tmp_9)
                    DictionaryHelper.doubleToByteArray(tmp_2, tmp_10)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.FLOAT -> {
                    val tmp_13: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                    val tmp_14: Double = ceil(tmp_13)
                    DictionaryHelper.floatToByteArray(tmp_2, tmp_14)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.INTEGER -> {
                    ByteArrayWrapperExt.copyInto(tmp_0, tmp_2, false)
                    res = query.getDictionary().createValue(tmp_2)
                }
                else -> {
                    res = DictionaryValueHelper.errorValue
                }
            }
            res
        }
    }
}
