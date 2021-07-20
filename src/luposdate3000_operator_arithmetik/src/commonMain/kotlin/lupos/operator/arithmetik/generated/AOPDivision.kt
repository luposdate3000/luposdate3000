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
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
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

public class AOPDivision public constructor(query: IQuery, child0: AOPBase, child1: AOPBase) : AOPBase(query, EOperatorIDExt.AOPDivisionID, "AOPDivision", arrayOf(child0, child1)) {
    override fun toSparql(): String = "Division(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPDivision && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPDivision(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
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
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_50: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                            val tmp_51: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_1)
                            res = if (tmp_51 == BigDecimal.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_52: BigDecimal = tmp_50 / tmp_51
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_52)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_54: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                            val tmp_55: Double = DictionaryHelper.byteArrayToDouble_I(tmp_1)
                            res = if (tmp_55 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_56: Double = tmp_54.doubleValue() / tmp_55
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_56)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_59: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                            val tmp_60: Double = DictionaryHelper.byteArrayToFloat_I(tmp_1)
                            res = if (tmp_60 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_61: Double = tmp_59.doubleValue() / tmp_60
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_61)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_63: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_0)
                            val tmp_64: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_1)
                            res = if (tmp_64 == BigInteger.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_65: BigDecimal = tmp_63 / BigDecimal.fromBigInteger(tmp_64)
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_65)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_76: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                            val tmp_77: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_1)
                            res = if (tmp_77 == BigDecimal.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_78: Double = tmp_76 / tmp_77.doubleValue()
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_78)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_80: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                            val tmp_81: Double = DictionaryHelper.byteArrayToDouble_I(tmp_1)
                            res = if (tmp_81 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_82: Double = tmp_80 / tmp_81
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_82)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_85: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                            val tmp_86: Double = DictionaryHelper.byteArrayToFloat_I(tmp_1)
                            res = if (tmp_86 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_87: Double = tmp_85 / tmp_86
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_87)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_89: Double = DictionaryHelper.byteArrayToDouble_I(tmp_0)
                            val tmp_90: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_1)
                            res = if (tmp_90 == BigInteger.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_91: Double = tmp_89 / tmp_90.doubleValue()
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_91)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
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
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_116: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                            val tmp_117: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_1)
                            res = if (tmp_117 == BigDecimal.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_118: Double = tmp_116 / tmp_117.doubleValue()
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_118)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_120: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                            val tmp_121: Double = DictionaryHelper.byteArrayToDouble_I(tmp_1)
                            res = if (tmp_121 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_122: Double = tmp_120 / tmp_121
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_122)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_125: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                            val tmp_126: Double = DictionaryHelper.byteArrayToFloat_I(tmp_1)
                            res = if (tmp_126 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_127: Double = tmp_125 / tmp_126
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_127)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_129: Double = DictionaryHelper.byteArrayToFloat_I(tmp_0)
                            val tmp_130: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_1)
                            res = if (tmp_130 == BigInteger.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_131: Double = tmp_129 / tmp_130.doubleValue()
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_131)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.INTEGER -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.DECIMAL -> {
                            val tmp_142: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                            val tmp_143: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(tmp_1)
                            res = if (tmp_143 == BigDecimal.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_144: BigDecimal = BigDecimal.fromBigInteger(tmp_142) / tmp_143
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_144)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.DOUBLE -> {
                            val tmp_146: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                            val tmp_147: Double = DictionaryHelper.byteArrayToDouble_I(tmp_1)
                            res = if (tmp_147 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_148: Double = tmp_146.doubleValue() / tmp_147
                                DictionaryHelper.doubleToByteArray(tmp_4, tmp_148)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.FLOAT -> {
                            val tmp_151: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                            val tmp_152: Double = DictionaryHelper.byteArrayToFloat_I(tmp_1)
                            res = if (tmp_152 == 0.0) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_153: Double = tmp_151.doubleValue() / tmp_152
                                DictionaryHelper.floatToByteArray(tmp_4, tmp_153)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        ETripleComponentTypeExt.INTEGER -> {
                            val tmp_155: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_0)
                            val tmp_156: BigInteger = DictionaryHelper.byteArrayToInteger_I(tmp_1)
                            res = if (tmp_156 == BigInteger.ZERO) {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                val tmp_157: BigDecimal = BigDecimal.fromBigInteger(tmp_155) / BigDecimal.fromBigInteger(tmp_156)
                                DictionaryHelper.decimalToByteArray(tmp_4, tmp_157)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
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
                ETripleComponentTypeExt.STRING_LANG -> {
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
                ETripleComponentTypeExt.STRING_TYPED -> {
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
