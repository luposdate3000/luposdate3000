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

public class AOPBuildInCallCONTAINS public constructor(query: IQuery, child0: AOPBase, child1: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallCONTAINSID, "AOPBuildInCallCONTAINS", arrayOf(child0, child1)) {
    override fun toSparql(): String = "CONTAINS(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallCONTAINS && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPBuildInCallCONTAINS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
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
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_140: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_141: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_142: Boolean = tmp_140.contains(tmp_141)
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_142)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_157_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_157_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_158: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_159: Boolean = tmp_157_content.contains(tmp_158)
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_159)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_174_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_174_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_175: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_176: Boolean = tmp_174_content.contains(tmp_175)
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_176)
                            res = query.getDictionary().createValue(tmp_4)
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
                }
                else -> {
                    res = DictionaryExt.errorValue
                }
            }
            res
        }
    }
}
