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

public class AOPBuildInCallSTRLANG public constructor(query: IQuery, child0: AOPBase, child1: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRLANGID, "AOPBuildInCallSTRLANG", arrayOf(child0, child1)) {
    override fun toSparql(): String = "STRLANG(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRLANG && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPBuildInCallSTRLANG(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_1: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_4: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        val child1: () -> DictionaryValueType = (children[1] as AOPBase).evaluateID(row)
        return {
            val res: DictionaryValueType
            val childIn0: DictionaryValueType = child0()
            val childIn1: DictionaryValueType = child1()
            query.getDictionary().getValue(tmp_0, childIn0)
            query.getDictionary().getValue(tmp_1, childIn1)
            val tmp_2: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            val tmp_3: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_1)
            when (tmp_2) {
                ETripleComponentTypeExt.BLANK_NODE -> {
                    res = DictionaryValueHelper.errorValue
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.ERROR -> {
                    res = DictionaryValueHelper.errorValue
                }
                ETripleComponentTypeExt.FLOAT -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.INTEGER -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.IRI -> {
                    res = DictionaryValueHelper.errorValue
                }
                ETripleComponentTypeExt.STRING -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            res = DictionaryValueHelper.errorValue
                        }
                        ETripleComponentTypeExt.STRING -> {
                            val tmp_140: String = DictionaryHelper.byteArrayToString(tmp_0)
                            val tmp_141: String = DictionaryHelper.byteArrayToString(tmp_1)
                            val tmp_142_content: String = tmp_140
                            val tmp_142_lang: String = tmp_141
                            DictionaryHelper.langToByteArray(tmp_4, tmp_142_content, tmp_142_lang)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    res = DictionaryValueHelper.undefValue
                }
                ETripleComponentTypeExt.UNDEF -> {
                    res = DictionaryValueHelper.errorValue
                }
                else -> {
                    res = DictionaryValueHelper.errorValue
                }
            }
            res
        }
    }
}
