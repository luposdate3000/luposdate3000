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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPBuildInCallSTRAFTER public constructor(query: IQuery, child0: AOPBase, child1: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRAFTERID, "AOPBuildInCallSTRAFTER", arrayOf(child0, child1,)) {
    override fun toSparql(): String = "STRAFTER(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRAFTER && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPBuildInCallSTRAFTER(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_1: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_4: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> Int = (children[0] as AOPBase).evaluateID(row)
        val child1: () -> Int = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: Int
            val childIn0: Int = child0()
            val childIn1: Int = child1()
            query.getDictionary().getValue(tmp_0, childIn0)
            query.getDictionary().getValue(tmp_1, childIn1)
            val tmp_2: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            val tmp_3: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_1)
            when (tmp_2) {
                ETripleComponentTypeExt.STRING -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_5: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_6: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_8_idx: Int = tmp_5.indexOf(tmp_6)
                            if (tmp_8_idx >= 0) {
                                val tmp_7: String = tmp_5.substring(tmp_8_idx + tmp_6.length, tmp_5.length)
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_7)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_7: String = ""
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_7)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_9: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_10_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_10_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_12_idx: Int = tmp_9.indexOf(tmp_10_content)
                            if (tmp_12_idx >= 0) {
                                val tmp_11: String = tmp_9.substring(tmp_12_idx + tmp_10_content.length, tmp_9.length)
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_11)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_11: String = ""
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_11)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_13: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_14_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_14_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_16_idx: Int = tmp_13.indexOf(tmp_14_content)
                            if (tmp_16_idx >= 0) {
                                val tmp_15: String = tmp_13.substring(tmp_16_idx + tmp_14_content.length, tmp_13.length)
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_15)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_15: String = ""
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_15)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_18_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_18_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_19: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_21_idx: Int = tmp_18_content.indexOf(tmp_19)
                            val tmp_20_lang: String = tmp_18_lang
                            if (tmp_21_idx >= 0) {
                                val tmp_20_content: String = tmp_18_content.substring(tmp_21_idx + tmp_19.length, tmp_18_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_20_content, tmp_20_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_20_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_20_content, tmp_20_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_22_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_22_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_23_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_23_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_25_idx: Int = tmp_22_content.indexOf(tmp_23_content)
                            val tmp_24_lang: String = tmp_22_lang
                            if (tmp_25_idx >= 0) {
                                val tmp_24_content: String = tmp_22_content.substring(tmp_25_idx + tmp_23_content.length, tmp_22_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_24_content, tmp_24_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_24_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_24_content, tmp_24_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_26_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_26_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_27_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_27_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_29_idx: Int = tmp_26_content.indexOf(tmp_27_content)
                            val tmp_28_lang: String = tmp_26_lang
                            if (tmp_29_idx >= 0) {
                                val tmp_28_content: String = tmp_26_content.substring(tmp_29_idx + tmp_27_content.length, tmp_26_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_28_content, tmp_28_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_28_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_28_content, tmp_28_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_31_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_31_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_32: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_34_idx: Int = tmp_31_content.indexOf(tmp_32)
                            val tmp_33_type: String = tmp_31_type
                            if (tmp_34_idx >= 0) {
                                val tmp_33_content: String = tmp_31_content.substring(tmp_34_idx + tmp_32.length, tmp_31_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_33_content, tmp_33_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_33_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_33_content, tmp_33_type)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_35_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_35_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_36_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_36_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_38_idx: Int = tmp_35_content.indexOf(tmp_36_content)
                            val tmp_37_type: String = tmp_35_type
                            if (tmp_38_idx >= 0) {
                                val tmp_37_content: String = tmp_35_content.substring(tmp_38_idx + tmp_36_content.length, tmp_35_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_37_content, tmp_37_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_37_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_37_content, tmp_37_type)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_39_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_39_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_40_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_40_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_42_idx: Int = tmp_39_content.indexOf(tmp_40_content)
                            val tmp_41_type: String = tmp_39_type
                            if (tmp_42_idx >= 0) {
                                val tmp_41_content: String = tmp_39_content.substring(tmp_42_idx + tmp_40_content.length, tmp_39_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_41_content, tmp_41_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_41_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_41_content, tmp_41_type)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                else -> {
                    res = DictionaryExt.errorValue
                }
            }
            res
        }
    }
}
