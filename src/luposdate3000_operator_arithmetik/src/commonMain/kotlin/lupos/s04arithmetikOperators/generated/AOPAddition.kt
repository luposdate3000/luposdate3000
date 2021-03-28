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
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPAddition public constructor(query: IQuery, child0: AOPBase, child1: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPAdditionID, "AOPAddition", arrayOf(child0, child1,)) {
    override fun toSparql(): String = "Addition(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPAddition && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPAddition(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp_0 = ByteArrayWrapper()
        val tmp_1 = ByteArrayWrapper()
        val tmp_4 = ByteArrayWrapper()
        val child0 = (children[0] as AOPBase).evaluateID(row)
        val child1 = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: Int
            val childIn0 = child0()
            val childIn1 = child1()
            query.getDictionary().getValue(tmp_0, childIn0)
            query.getDictionary().getValue(tmp_1, childIn1)
            val tmp_2 = DictionaryHelper.byteArrayToType(tmp_0)
            val tmp_3 = DictionaryHelper.byteArrayToType(tmp_1)
            when (tmp_2) {
                ETripleComponentTypeExt.DECIMAL -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_5 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_0))
                            val tmp_6 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_1))
                            if (tmp_6 == MyBigDecimal("0.0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_7 = tmp_5 + tmp_6
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_7.toString())
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_9 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_0))
                            val tmp_10 = DictionaryHelper.byteArrayToDouble(tmp_1)
                            if (tmp_10 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_11 = tmp_9.toDouble() + tmp_10
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_11)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_13 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_0))
                            val tmp_14 = DictionaryHelper.byteArrayToFloat(tmp_1)
                            if (tmp_14 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_15 = tmp_13.toDouble() + tmp_14
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_15)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_17 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_0))
                            val tmp_18 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_1))
                            if (tmp_18 == MyBigInteger("0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_19 = tmp_17 + tmp_18.toMyBigDecimal()
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_19.toString())
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_22 = DictionaryHelper.byteArrayToDouble(tmp_0)
                            val tmp_23 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_1))
                            if (tmp_23 == MyBigDecimal("0.0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_24 = tmp_22 + tmp_23.toDouble()
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_24)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_26 = DictionaryHelper.byteArrayToDouble(tmp_0)
                            val tmp_27 = DictionaryHelper.byteArrayToDouble(tmp_1)
                            if (tmp_27 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_28 = tmp_26 + tmp_27
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_28)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_30 = DictionaryHelper.byteArrayToDouble(tmp_0)
                            val tmp_31 = DictionaryHelper.byteArrayToFloat(tmp_1)
                            if (tmp_31 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_32 = tmp_30 + tmp_31
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_32)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_34 = DictionaryHelper.byteArrayToDouble(tmp_0)
                            val tmp_35 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_1))
                            if (tmp_35 == MyBigInteger("0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_36 = tmp_34 + tmp_35.toDouble()
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_36)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.FLOAT -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_39 = DictionaryHelper.byteArrayToFloat(tmp_0)
                            val tmp_40 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_1))
                            if (tmp_40 == MyBigDecimal("0.0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_41 = tmp_39 + tmp_40.toDouble()
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_41)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_43 = DictionaryHelper.byteArrayToFloat(tmp_0)
                            val tmp_44 = DictionaryHelper.byteArrayToDouble(tmp_1)
                            if (tmp_44 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_45 = tmp_43 + tmp_44
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_45)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_47 = DictionaryHelper.byteArrayToFloat(tmp_0)
                            val tmp_48 = DictionaryHelper.byteArrayToFloat(tmp_1)
                            if (tmp_48 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_49 = tmp_47 + tmp_48
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_49)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_51 = DictionaryHelper.byteArrayToFloat(tmp_0)
                            val tmp_52 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_1))
                            if (tmp_52 == MyBigInteger("0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_53 = tmp_51 + tmp_52.toDouble()
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_53)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryExt.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.INTEGER -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_56 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_0))
                            val tmp_57 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_1))
                            if (tmp_57 == MyBigDecimal("0.0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_58 = tmp_56.toMyBigDecimal() + tmp_57
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_58.toString())
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_60 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_0))
                            val tmp_61 = DictionaryHelper.byteArrayToDouble(tmp_1)
                            if (tmp_61 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_62 = tmp_60.toDouble() + tmp_61
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_62)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_64 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_0))
                            val tmp_65 = DictionaryHelper.byteArrayToFloat(tmp_1)
                            if (tmp_65 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_66 = tmp_64.toDouble() + tmp_65
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_66)
                                res = query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_68 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_0))
                            val tmp_69 = MyBigInteger(DictionaryHelper.byteArrayToInteger(tmp_1))
                            if (tmp_69 == MyBigInteger("0")) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                res = query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_70 = tmp_68 + tmp_69
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_70.toString())
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
