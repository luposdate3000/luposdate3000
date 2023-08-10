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

public class AOPBuildInCallSTRBEFORE public constructor(query: IQuery, child0: AOPBase, child1: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRBEFOREID, "AOPBuildInCallSTRBEFORE", arrayOf(child0, child1)) {
    override fun toSparql(): String = "STRBEFORE(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRBEFORE && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPBuildInCallSTRBEFORE(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_1: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_4: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        val child1: () -> DictionaryValueType = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: DictionaryValueType = 0
            val childIn0: DictionaryValueType = child0()
            val childIn1: DictionaryValueType = child1()
            query.getDictionary().getValue(tmp_0, childIn0)
            query.getDictionary().getValue(tmp_1, childIn1)
            val tmp_2: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            val tmp_3: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_1)
            when (tmp_2) {
                ETripleComponentTypeExt.BLANK_NODE -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.ERROR -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.FLOAT -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.INTEGER -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.IRI -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING -> {
                    val action1 = {
                        when (tmp_3) {
                            ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.UNDEF -> {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                            ETripleComponentTypeExt.STRING -> {
                                val tmp_140: String = DictionaryHelper.byteArrayToString(tmp_0)
                                val tmp_141: String = DictionaryHelper.byteArrayToString(tmp_1)
                                val tmp_143_idx: Int = tmp_140.indexOf(tmp_141)
                                res = if (tmp_143_idx >= 0) {
                                    val tmp_142: String = tmp_140.substring(0, tmp_143_idx)
                                    DictionaryHelper.stringToByteArray(tmp_4, tmp_142)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
                                    val tmp_142: String = ""
                                    DictionaryHelper.stringToByteArray(tmp_4, tmp_142)
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            ETripleComponentTypeExt.STRING_LANG -> {
                                val tmp_144: String = DictionaryHelper.byteArrayToString(tmp_0)
                                val tmp_145_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                                val tmp_145_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                                val tmp_147_idx: Int = tmp_144.indexOf(tmp_145_content)
                                res = if (tmp_147_idx >= 0) {
                                    val tmp_146: String = tmp_144.substring(0, tmp_147_idx)
                                    DictionaryHelper.stringToByteArray(tmp_4, tmp_146)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
                                    val tmp_146: String = ""
                                    DictionaryHelper.stringToByteArray(tmp_4, tmp_146)
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            ETripleComponentTypeExt.STRING_TYPED -> {
                                val tmp_148: String = DictionaryHelper.byteArrayToString(tmp_0)
                                val tmp_149_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                                val tmp_149_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                                val tmp_151_idx: Int = tmp_148.indexOf(tmp_149_content)
                                res = if (tmp_151_idx >= 0) {
                                    val tmp_150: String = tmp_148.substring(0, tmp_151_idx)
                                    DictionaryHelper.stringToByteArray(tmp_4, tmp_150)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
                                    val tmp_150: String = ""
                                    DictionaryHelper.stringToByteArray(tmp_4, tmp_150)
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            else -> {
                                res = DictionaryValueHelper.errorValue
                            }
                        }
                    }
                    action1()
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val action2 = {
                        when (tmp_3) {
                            ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.UNDEF -> {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                            ETripleComponentTypeExt.STRING -> {
                                val tmp_163_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                                val tmp_163_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                                val tmp_164: String = DictionaryHelper.byteArrayToString(tmp_1)
                                val tmp_166_idx: Int = tmp_163_content.indexOf(tmp_164)
                                val tmp_165_lang: String = tmp_163_lang
                                res = if (tmp_166_idx >= 0) {
                                    val tmp_165_content: String = tmp_163_content.substring(0, tmp_166_idx)
                                    DictionaryHelper.langToByteArray(tmp_4, tmp_165_content, tmp_165_lang)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
                                    DictionaryHelper.stringToByteArray(tmp_4, "")
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            ETripleComponentTypeExt.STRING_LANG -> {
                                val tmp_167_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                                val tmp_167_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                                val tmp_168_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                                val tmp_168_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                                val tmp_170_idx: Int = tmp_167_content.indexOf(tmp_168_content)
                                val tmp_169_lang: String = tmp_167_lang
                                res = if (tmp_170_idx >= 0) {
                                    val tmp_169_content: String = tmp_167_content.substring(0, tmp_170_idx)
                                    DictionaryHelper.langToByteArray(tmp_4, tmp_169_content, tmp_169_lang)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
 DictionaryHelper.stringToByteArray(tmp_4, "")
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            ETripleComponentTypeExt.STRING_TYPED -> {
                                val tmp_171_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                                val tmp_171_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                                val tmp_172_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                                val tmp_172_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                                val tmp_174_idx: Int = tmp_171_content.indexOf(tmp_172_content)
                                val tmp_173_lang: String = tmp_171_lang
                                res = if (tmp_174_idx >= 0) {
                                    val tmp_173_content: String = tmp_171_content.substring(0, tmp_174_idx)
                                    DictionaryHelper.langToByteArray(tmp_4, tmp_173_content, tmp_173_lang)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
 DictionaryHelper.stringToByteArray(tmp_4, "")
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            else -> {
                                res = DictionaryValueHelper.errorValue
                            }
                        }
                    }
                    action2()
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val action3 = {
                        when (tmp_3) {
                            ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.UNDEF -> {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                            ETripleComponentTypeExt.STRING -> {
                                val tmp_186_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                                val tmp_186_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                                val tmp_187: String = DictionaryHelper.byteArrayToString(tmp_1)
                                val tmp_189_idx: Int = tmp_186_content.indexOf(tmp_187)
                                val tmp_188_type: String = tmp_186_type
                                res = if (tmp_189_idx >= 0) {
                                    val tmp_188_content: String = tmp_186_content.substring(0, tmp_189_idx)
                                    DictionaryHelper.typedToByteArray(tmp_4, tmp_188_content, tmp_188_type)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
                                    val tmp_188_content: String = ""
                                    DictionaryHelper.typedToByteArray(tmp_4, tmp_188_content, tmp_188_type)
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            ETripleComponentTypeExt.STRING_LANG -> {
                                val tmp_190_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                                val tmp_190_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                                val tmp_191_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                                val tmp_191_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                                val tmp_193_idx: Int = tmp_190_content.indexOf(tmp_191_content)
                                val tmp_192_type: String = tmp_190_type
                                res = if (tmp_193_idx >= 0) {
                                    val tmp_192_content: String = tmp_190_content.substring(0, tmp_193_idx)
                                    DictionaryHelper.typedToByteArray(tmp_4, tmp_192_content, tmp_192_type)
                                    query.getDictionary().createValue(tmp_4)
                                } else {
                                    val tmp_192_content: String = ""
                                    DictionaryHelper.typedToByteArray(tmp_4, tmp_192_content, tmp_192_type)
                                    query.getDictionary().createValue(tmp_4)
                                }
                            }
                            ETripleComponentTypeExt.STRING_TYPED -> {
                                val action4 = {
                                    val tmp_194_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                                    val tmp_194_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                                    val tmp_195_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                                    val tmp_195_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                                    val tmp_197_idx: Int = tmp_194_content.indexOf(tmp_195_content)
                                    val tmp_196_type: String = tmp_194_type
                                    res = if (tmp_197_idx >= 0) {
                                        val tmp_196_content: String = tmp_194_content.substring(0, tmp_197_idx)
                                        DictionaryHelper.typedToByteArray(tmp_4, tmp_196_content, tmp_196_type)
                                        query.getDictionary().createValue(tmp_4)
                                    } else {
                                        val tmp_196_content: String = ""
                                        DictionaryHelper.typedToByteArray(tmp_4, tmp_196_content, tmp_196_type)
                                        query.getDictionary().createValue(tmp_4)
                                    }
                                }
                                action4()
                            }
                            else -> {
                                res = DictionaryValueHelper.errorValue
                            }
                        }
                    }
                    action3()
                }
                ETripleComponentTypeExt.UNDEF -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                else -> {
                    res = DictionaryValueHelper.errorValue
                }
            }
            res
        }
    }
}
