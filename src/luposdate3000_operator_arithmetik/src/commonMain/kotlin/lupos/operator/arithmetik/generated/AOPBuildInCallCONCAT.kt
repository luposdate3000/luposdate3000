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

public class AOPBuildInCallCONCAT public constructor(query: IQuery, child0: AOPBase, child1: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallCONCATID, "AOPBuildInCallCONCAT", arrayOf(child0, child1)) {
    override fun toSparql(): String = "CONCAT(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallCONCAT && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPBuildInCallCONCAT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_1: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_4: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        val child1: () -> DictionaryValueType = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: DictionaryValueType
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
                            val tmp_142: String = tmp_140 + tmp_141
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_142)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_144: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_145_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_145_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_146: String = tmp_144 + tmp_145_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_146)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_148: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_149_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_149_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_150: String = tmp_148 + tmp_149_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_150)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
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
                            val tmp_165: String = tmp_163_content + tmp_164
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_165)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_167_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_167_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_168_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_168_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            if (tmp_167_lang == tmp_168_lang) {
                                val tmp_169_content: String = tmp_167_content + tmp_168_content
                                val tmp_169_lang: String = tmp_167_lang
                                DictionaryHelper.langToByteArray(tmp_4, tmp_169_content, tmp_169_lang)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_169: String = tmp_167_content + tmp_168_content
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_169)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_171_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_171_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_172_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_172_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            val tmp_173: String = tmp_171_content + tmp_172_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_173)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
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
                            val tmp_188: String = tmp_186_content + tmp_187
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_188)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_LANG -> {
                            val tmp_190_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_190_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_191_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_1)
                            val tmp_191_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_1)
                            val tmp_192: String = tmp_190_content + tmp_191_content
                            DictionaryHelper.stringToByteArray(tmp_4, tmp_192)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING_TYPED -> {
                            val tmp_194_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_194_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_195_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_1)
                            val tmp_195_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_1)
                            if (tmp_194_type == "http://www.w3.org/2001/XMLSchema#string" && tmp_195_type == "http://www.w3.org/2001/XMLSchema#string") {
                                val tmp_196_content: String = tmp_194_content + tmp_195_content
                                val tmp_196_type: String = "http://www.w3.org/2001/XMLSchema#string"
                                DictionaryHelper.typedToByteArray(tmp_4, tmp_196_content, tmp_196_type)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_196: String = tmp_194_content + tmp_195_content
                                DictionaryHelper.stringToByteArray(tmp_4, tmp_196)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
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
                            res = DictionaryValueHelper.errorValue
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
