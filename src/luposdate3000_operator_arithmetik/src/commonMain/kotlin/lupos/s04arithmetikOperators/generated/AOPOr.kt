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

public class AOPOr public constructor(query: IQuery, child0: AOPBase, child1: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPOrID, "AOPOr", arrayOf(child0, child1,)) {
    override fun toSparql(): String = "Or(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPOr && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
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
                ETripleComponentTypeExt.BOOLEAN -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BOOLEAN -> {
                            val tmp_5: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                            val tmp_6: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_1)
                            val tmp_7: Boolean = tmp_5 || tmp_6
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_7)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.ERROR -> {
                            val tmp_9: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                            if (tmp_9) {
                                val tmp_11: Boolean = true
                                DictionaryHelper.booleanToByteArray(tmp_4, tmp_11)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.ERROR -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BOOLEAN -> {
                            val tmp_15: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_1)
                            if (tmp_15) {
                                val tmp_16: Boolean = true
                                DictionaryHelper.booleanToByteArray(tmp_4, tmp_16)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                DictionaryHelper.errorToByteArray(tmp_4)
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
