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
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared_inline.DictionaryHelper

public class AOPBuildInCallSTRAFTER public constructor(query: IQuery, child0: AOPBase, child1: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRAFTERID, "AOPBuildInCallSTRAFTER", arrayOf(child0, child1)) {
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
                ETripleComponentTypeExt.BLANK_NODE -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.ERROR -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.FLOAT -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.INTEGER -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.IRI -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_140: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_141: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_143_idx: Int = tmp_140.indexOf(tmp_141)
                            if (tmp_143_idx >= 0) {
                                val tmp_142: String = tmp_140.substring(tmp_143_idx + tmp_141.length, tmp_140.length)
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_142)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_142: String = ""
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_142)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_144: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_145_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_145_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_147_idx: Int = tmp_144.indexOf(tmp_145_content)
                            if (tmp_147_idx >= 0) {
                                val tmp_146: String = tmp_144.substring(tmp_147_idx + tmp_145_content.length, tmp_144.length)
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_146)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_146: String = ""
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_146)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_148: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_149_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_149_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_151_idx: Int = tmp_148.indexOf(tmp_149_content)
                            if (tmp_151_idx >= 0) {
                                val tmp_150: String = tmp_148.substring(tmp_151_idx + tmp_149_content.length, tmp_148.length)
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_150)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_150: String = ""
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_150)
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
                            if (tmp_166_idx >= 0) {
                                val tmp_165_content: String = tmp_163_content.substring(tmp_166_idx + tmp_164.length, tmp_163_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_165_content, tmp_165_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_165_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_165_content, tmp_165_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_167_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_167_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_168_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_168_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_170_idx: Int = tmp_167_content.indexOf(tmp_168_content)
                            val tmp_169_lang: String = tmp_167_lang
                            if (tmp_170_idx >= 0) {
                                val tmp_169_content: String = tmp_167_content.substring(tmp_170_idx + tmp_168_content.length, tmp_167_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_169_content, tmp_169_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_169_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_169_content, tmp_169_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_171_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_171_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_172_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_172_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_174_idx: Int = tmp_171_content.indexOf(tmp_172_content)
                            val tmp_173_lang: String = tmp_171_lang
                            if (tmp_174_idx >= 0) {
                                val tmp_173_content: String = tmp_171_content.substring(tmp_174_idx + tmp_172_content.length, tmp_171_content.length)
                                DictionaryHelper.langToByteArray(tmp_4, tmp_173_content, tmp_173_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_173_content: String = ""
                                DictionaryHelper.langToByteArray(tmp_4, tmp_173_content, tmp_173_lang)
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
                            if (tmp_189_idx >= 0) {
                                val tmp_188_content: String = tmp_186_content.substring(tmp_189_idx + tmp_187.length, tmp_186_content.length)
                                DictionaryHelper.typedToByteArray(tmp_4, tmp_188_content, tmp_188_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_188_content: String = ""
                                DictionaryHelper.typedToByteArray(tmp_4, tmp_188_content, tmp_188_type)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_190_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_190_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_191_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_191_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_193_idx: Int = tmp_190_content.indexOf(tmp_191_content)
                            val tmp_192_type: String = tmp_190_type
                            if (tmp_193_idx >= 0) {
                                val tmp_192_content: String = tmp_190_content.substring(tmp_193_idx + tmp_191_content.length, tmp_190_content.length)
                                DictionaryHelper.typedToByteArray(tmp_4, tmp_192_content, tmp_192_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_192_content: String = ""
                                DictionaryHelper.typedToByteArray(tmp_4, tmp_192_content, tmp_192_type)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_194_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_194_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_195_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_195_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_197_idx: Int = tmp_194_content.indexOf(tmp_195_content)
                            val tmp_196_type: String = tmp_194_type
                            if (tmp_197_idx >= 0) {
                                val tmp_196_content: String = tmp_194_content.substring(tmp_197_idx + tmp_195_content.length, tmp_194_content.length)
                                DictionaryHelper.typedToByteArray(tmp_4, tmp_196_content, tmp_196_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_196_content: String = ""
                                DictionaryHelper.typedToByteArray(tmp_4, tmp_196_content, tmp_196_type)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.UNDEF -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
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
