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
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPBuildInCallBNODE1 public constructor(query: IQuery, child0: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallBNODE1ID, "AOPBuildInCallBNODE1", arrayOf(child0)) {
    override fun toSparql(): String = "BNODE1(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallBNODE1 && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallBNODE1(query, children[0].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_2: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        return {
            var res: DictionaryValueType
            val childIn0: Int = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            val tmp_1: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            when (tmp_1) {
                ETripleComponentTypeExt.BLANK_NODE -> {
                    val tmp_3: Int = DictionaryHelper.byteArrayToBnode_I(tmp_0)
                    val tmp_4: Int = query.getDictionary().createNewBNode("BLANK_NODE_$tmp_3")
                    res = tmp_4
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    val tmp_6: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                    val tmp_7: Int = query.getDictionary().createNewBNode("BOOLEAN_$tmp_6")
                    res = tmp_7
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp_10: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                    val tmp_11: Int = query.getDictionary().createNewBNode("DECIMAL_$tmp_10")
                    res = tmp_11
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp_13: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                    val tmp_14: Int = query.getDictionary().createNewBNode("DOUBLE_$tmp_13")
                    res = tmp_14
                }
                ETripleComponentTypeExt.ERROR -> {
                    val tmp_17: Int = query.getDictionary().createNewBNode("ERROR_ERROR")
                    res = tmp_17
                }
                ETripleComponentTypeExt.FLOAT -> {
                    val tmp_19: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                    val tmp_20: Int = query.getDictionary().createNewBNode("FLOAT_$tmp_19")
                    res = tmp_20
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp_22: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                    val tmp_23: Int = query.getDictionary().createNewBNode("INTEGER_$tmp_22")
                    res = tmp_23
                }
                ETripleComponentTypeExt.IRI -> {
                    val tmp_25: String = DictionaryHelper.byteArrayToIri(tmp_0)
                    val tmp_26: Int = query.getDictionary().createNewBNode("IRI_$tmp_25")
                    res = tmp_26
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp_28: String = DictionaryHelper.byteArrayToString(tmp_0)
                    val tmp_29: Int = query.getDictionary().createNewBNode("STRING_$tmp_28")
                    res = tmp_29
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val tmp_31_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                    val tmp_31_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                    val tmp_32: Int = query.getDictionary().createNewBNode("STRING_LANG_${tmp_31_content}_$tmp_31_lang")
                    res = tmp_32
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_34_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_34_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    val tmp_35: Int = query.getDictionary().createNewBNode("STRING_TYPED_${tmp_34_content}_$tmp_34_type")
                    res = tmp_35
                }
                ETripleComponentTypeExt.UNDEF -> {
                    val tmp_38: Int = query.getDictionary().createNewBNode("UNDEF_UNDEF")
                    res = tmp_38
                }
                else -> {
                    res = DictionaryValueHelper.errorValue
                }
            }
            res
        }
    }
}
