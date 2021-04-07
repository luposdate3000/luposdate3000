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

public class AOPBuildInCallCONCAT public constructor(query: IQuery, child0: AOPBase, child1: AOPBase, ) : AOPBase(query, EOperatorIDExt.AOPBuildInCallCONCATID, "AOPBuildInCallCONCAT", arrayOf(child0, child1, )) {
    override fun toSparql(): String = "CONCAT(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallCONCAT && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPBuildInCallCONCAT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
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
                            val tmp_7: String = tmp_5 + tmp_6
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_7)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_9: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_10_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_10_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_11: String = tmp_9 + tmp_10_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_11)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_13: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_14_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_14_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_15: String = tmp_13 + tmp_14_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_15)
                            res = query.getDictionary().createValue(tmp_4)
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
                            val tmp_20: String = tmp_18_content + tmp_19
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_20)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_22_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_22_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_23_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_23_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            if (tmp_22_lang == tmp_23_lang) {
                                val tmp_24_content: String = tmp_22_content + tmp_23_content
                                val tmp_24_lang: String = tmp_22_lang
                                DictionaryHelper.langToByteArray(tmp_4, tmp_24_content, tmp_24_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_24: String = tmp_22_content + tmp_23_content
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_24)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_26_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_26_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_27_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_27_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_28: String = tmp_26_content + tmp_27_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_28)
                            res = query.getDictionary().createValue(tmp_4)
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
                            val tmp_33: String = tmp_31_content + tmp_32
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_33)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_35_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_35_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_36_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_36_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_37: String = tmp_35_content + tmp_36_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_37)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_39_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_39_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_40_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_40_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            if (tmp_39_type == "http://www.w3.org/2001/XMLSchema#string" && tmp_40_type == "http://www.w3.org/2001/XMLSchema#string") {
                                val tmp_41_content: String = tmp_39_content + tmp_40_content
                                val tmp_41_type: String = "http://www.w3.org/2001/XMLSchema#string"
                                DictionaryHelper.langToByteArray(tmp_4, tmp_41_content, tmp_41_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_41: String = tmp_39_content + tmp_40_content
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_41)
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
