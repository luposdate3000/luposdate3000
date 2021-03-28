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

public class AOPBuildInCallBNODE1 public constructor(query: IQuery, child0: AOPBase, ) : AOPBase(query, EOperatorIDExt.AOPBuildInCallBNODE1ID, "AOPBuildInCallBNODE1", arrayOf(child0, )) {
    override fun toSparql(): String = "BNODE1(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallBNODE1 && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallBNODE1(query, children[0].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> Int = (children[0] as AOPBase).evaluateID(row)
        return {
            var res: Int
            val childIn0: Int = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            val tmp_1: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            when (tmp_1) {
                ETripleComponentTypeExt.BLANK_NODE -> {
                    val tmp_3: Int = DictionaryHelper.byteArrayToBnode_I(tmp_0)
                    val tmp_4: Int = query.getDictionary().createNewBNode("BLANK_NODE_${tmp_3}")
                    res = tmp_4
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    val tmp_6: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                    val tmp_7: Int = query.getDictionary().createNewBNode("BOOLEAN_${tmp_6}")
                    res = tmp_7
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp_9: MyBigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                    val tmp_10: Int = query.getDictionary().createNewBNode("DECIMAL_${tmp_9.toString()}")
                    res = tmp_10
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp_12: Double = DictionaryHelper.byteArrayToDouble(tmp_0)
                    val tmp_13: Int = query.getDictionary().createNewBNode("DOUBLE_${tmp_12.toString()}")
                    res = tmp_13
                }
                ETripleComponentTypeExt.ERROR -> {
                    val tmp_16: Int = query.getDictionary().createNewBNode("ERROR_ERROR")
                    res = tmp_16
                }
                ETripleComponentTypeExt.FLOAT -> {
                    val tmp_18: Double = DictionaryHelper.byteArrayToFloat(tmp_0)
                    val tmp_19: Int = query.getDictionary().createNewBNode("FLOAT_${tmp_18.toString()}")
                    res = tmp_19
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp_21: MyBigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                    val tmp_22: Int = query.getDictionary().createNewBNode("INTEGER_${tmp_21.toString()}")
                    res = tmp_22
                }
                ETripleComponentTypeExt.IRI -> {
                    val tmp_24: String = DictionaryHelper.byteArrayToIri(tmp_0)
                    val tmp_25: Int = query.getDictionary().createNewBNode("IRI_${tmp_24.toString()}")
                    res = tmp_25
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp_27: String = DictionaryHelper.byteArrayToString(tmp_0)
                    val tmp_28: Int = query.getDictionary().createNewBNode("STRING_${tmp_27.toString()}")
                    res = tmp_28
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val tmp_30_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                    val tmp_30_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                    val tmp_31: Int = query.getDictionary().createNewBNode("STRING_LANG_${tmp_30_content.toString()}_${tmp_30_lang.toString()}")
                    res = tmp_31
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_33_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_33_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    val tmp_34: Int = query.getDictionary().createNewBNode("STRING_TYPED_${tmp_33_content.toString()}_${tmp_33_type.toString()}")
                    res = tmp_34
                }
                ETripleComponentTypeExt.UNDEF -> {
                    val tmp_37: Int = query.getDictionary().createNewBNode("UNDEF_UNDEF")
                    res = tmp_37
                }
                else -> {
                    res = DictionaryExt.errorValue
                }
            }
            res
        }
    }
}
