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

public class AOPBuildInCallCONTAINS public constructor(query: IQuery, child0: AOPBase, child1: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPBuildInCallCONTAINSID, "AOPBuildInCallCONTAINS", arrayOf(child0, child1,)) {
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
                ETripleComponentTypeExt.STRING -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_5: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_6: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_7: Boolean = tmp_5.contains(tmp_6)
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_7)
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
                            val tmp_10_content: String = DictionaryHelper.byteArrayToLang_Content(tmp_0)
                            val tmp_10_lang: String = DictionaryHelper.byteArrayToLang_Lang(tmp_0)
                            val tmp_11: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_12: Boolean = tmp_10_content.contains(tmp_11)
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_12)
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
                            val tmp_15_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                            val tmp_15_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                            val tmp_16: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_17: Boolean = tmp_15_content.contains(tmp_16)
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_17)
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
