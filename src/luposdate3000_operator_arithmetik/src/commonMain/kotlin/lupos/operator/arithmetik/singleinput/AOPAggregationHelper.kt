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
package lupos.operator.arithmetik.singleinput

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared_inline.DictionaryHelper

internal object AOPAggregationHelper {
    internal fun addition(inputA: ByteArrayWrapper, inputB: ByteArrayWrapper, output: ByteArrayWrapper) {
        val tmp_2: ETripleComponentType = DictionaryHelper.byteArrayToType(inputA)
        val tmp_3: ETripleComponentType = DictionaryHelper.byteArrayToType(inputB)
        when (tmp_2) {
            ETripleComponentTypeExt.DECIMAL -> {
                when (tmp_3) {
                    ETripleComponentTypeExt.DECIMAL -> {
                        val tmp_50: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputA)
                        val tmp_51: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputB)
                        val tmp_52: BigDecimal = tmp_50 + tmp_51
                        DictionaryHelper.decimalToByteArray(output, tmp_52)
                    }
                    ETripleComponentTypeExt.DOUBLE -> {
                        val tmp_54: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputA)
                        val tmp_55: Double = DictionaryHelper.byteArrayToDouble_I(inputB)
                        val tmp_56: Double = tmp_54.doubleValue() + tmp_55
                        DictionaryHelper.doubleToByteArray(output, tmp_56)
                    }
                    ETripleComponentTypeExt.FLOAT -> {
                        val tmp_59: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputA)
                        val tmp_60: Double = DictionaryHelper.byteArrayToFloat_I(inputB)
                        val tmp_61: Double = tmp_59.doubleValue() + tmp_60
                        DictionaryHelper.floatToByteArray(output, tmp_61)
                    }
                    ETripleComponentTypeExt.INTEGER -> {
                        val tmp_63: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputA)
                        val tmp_64: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputB)
                        val tmp_65: BigDecimal = tmp_63 + BigDecimal.fromBigInteger(tmp_64)
                        DictionaryHelper.decimalToByteArray(output, tmp_65)
                    }
                    else -> {
                        DictionaryHelper.errorToByteArray(output)
                    }
                }
            }
            ETripleComponentTypeExt.DOUBLE -> {
                when (tmp_3) {
                    ETripleComponentTypeExt.DECIMAL -> {
                        val tmp_76: Double = DictionaryHelper.byteArrayToDouble_I(inputA)
                        val tmp_77: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputB)
                        val tmp_78: Double = tmp_76 + tmp_77.doubleValue()
                        DictionaryHelper.doubleToByteArray(output, tmp_78)
                    }
                    ETripleComponentTypeExt.DOUBLE -> {
                        val tmp_80: Double = DictionaryHelper.byteArrayToDouble_I(inputA)
                        val tmp_81: Double = DictionaryHelper.byteArrayToDouble_I(inputB)
                        val tmp_82: Double = tmp_80 + tmp_81
                        DictionaryHelper.doubleToByteArray(output, tmp_82)
                    }
                    ETripleComponentTypeExt.FLOAT -> {
                        val tmp_85: Double = DictionaryHelper.byteArrayToDouble_I(inputA)
                        val tmp_86: Double = DictionaryHelper.byteArrayToFloat_I(inputB)
                        val tmp_87: Double = tmp_85 + tmp_86
                        DictionaryHelper.doubleToByteArray(output, tmp_87)
                    }
                    ETripleComponentTypeExt.INTEGER -> {
                        val tmp_89: Double = DictionaryHelper.byteArrayToDouble_I(inputA)
                        val tmp_90: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputB)
                        val tmp_91: Double = tmp_89 + tmp_90.doubleValue()
                        DictionaryHelper.doubleToByteArray(output, tmp_91)
                    }
                    else -> {
                        DictionaryHelper.errorToByteArray(output)
                    }
                }
            }
            ETripleComponentTypeExt.FLOAT -> {
                when (tmp_3) {
                    ETripleComponentTypeExt.DECIMAL -> {
                        val inputB16: Double = DictionaryHelper.byteArrayToFloat_I(inputA)
                        val inputB17: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputB)
                        val inputB18: Double = inputB16 + inputB17.doubleValue()
                        DictionaryHelper.floatToByteArray(output, inputB18)
                    }
                    ETripleComponentTypeExt.DOUBLE -> {
                        val inputB20: Double = DictionaryHelper.byteArrayToFloat_I(inputA)
                        val inputB21: Double = DictionaryHelper.byteArrayToDouble_I(inputB)
                        val inputB22: Double = inputB20 + inputB21
                        DictionaryHelper.doubleToByteArray(output, inputB22)
                    }
                    ETripleComponentTypeExt.FLOAT -> {
                        val inputB25: Double = DictionaryHelper.byteArrayToFloat_I(inputA)
                        val inputB26: Double = DictionaryHelper.byteArrayToFloat_I(inputB)
                        val inputB27: Double = inputB25 + inputB26
                        DictionaryHelper.floatToByteArray(output, inputB27)
                    }
                    ETripleComponentTypeExt.INTEGER -> {
                        val inputB29: Double = DictionaryHelper.byteArrayToFloat_I(inputA)
                        val inputB30: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputB)
                        val inputB31: Double = inputB29 + inputB30.doubleValue()
                        DictionaryHelper.floatToByteArray(output, inputB31)
                    }
                    else -> {
                        DictionaryHelper.errorToByteArray(output)
                    }
                }
            }
            ETripleComponentTypeExt.INTEGER -> {
                when (tmp_3) {
                    ETripleComponentTypeExt.DECIMAL -> {
                        val inputB42: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputA)
                        val inputB43: BigDecimal = DictionaryHelper.byteArrayToDecimal_I(inputB)
                        val inputB44: BigDecimal = BigDecimal.fromBigInteger(inputB42) + inputB43
                        DictionaryHelper.decimalToByteArray(output, inputB44)
                    }
                    ETripleComponentTypeExt.DOUBLE -> {
                        val inputB46: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputA)
                        val inputB47: Double = DictionaryHelper.byteArrayToDouble_I(inputB)
                        val inputB48: Double = inputB46.doubleValue() + inputB47
                        DictionaryHelper.doubleToByteArray(output, inputB48)
                    }
                    ETripleComponentTypeExt.FLOAT -> {
                        val inputB51: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputA)
                        val inputB52: Double = DictionaryHelper.byteArrayToFloat_I(inputB)
                        val inputB53: Double = inputB51.doubleValue() + inputB52
                        DictionaryHelper.floatToByteArray(output, inputB53)
                    }
                    ETripleComponentTypeExt.INTEGER -> {
                        val inputB55: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputA)
                        val inputB56: BigInteger = DictionaryHelper.byteArrayToInteger_I(inputB)
                        val inputB57: BigInteger = inputB55 + inputB56
                        DictionaryHelper.integerToByteArray(output, inputB57)
                    }
                    else -> {
                        DictionaryHelper.errorToByteArray(output)
                    }
                }
            }
            else -> {
                DictionaryHelper.errorToByteArray(output)
            }
        }
    }
}
