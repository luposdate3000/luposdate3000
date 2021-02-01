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
package lupos.s00misc
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
public actual class MyBigDecimal {
    internal val s: String
    private val v: BigDecimal
    public actual constructor(s1: String) {
        v = BigDecimal(s1)
        s = s1
    }
    public constructor(v1: BigDecimal) {
        v = v1
        s = v1.toString()
    }
    public actual constructor(s1: Double) {
        v = BigDecimal(s1)
        s = "" + s1
    }
    public actual constructor(s1: Int) {
        v = BigDecimal(s1)
        s = "" + s1
    }
    public actual fun toDouble(): Double {
        return v.toDouble()
    }
    public actual fun toPlainString(): String {
        return v.toPlainString()
    }
    public actual operator fun compareTo(other: MyBigDecimal): Int {
        return v.compareTo(other.v)
    }
    public actual operator fun plus(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.add(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.add(other.v, MathContext.DECIMAL128))
        }
    }
    public actual operator fun minus(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.subtract(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.subtract(other.v, MathContext.DECIMAL128))
        }
    }
    public actual operator fun times(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.multiply(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.multiply(other.v, MathContext.DECIMAL128))
        }
    }
    public actual operator fun div(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.divide(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.divide(other.v, MathContext.DECIMAL128))
        }
    }
    public actual fun ceil(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.CEILING))
    }
    public actual fun floor(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.FLOOR))
    }
    public actual fun round(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.HALF_UP))
    }
    public actual fun toMyBigInteger(): MyBigInteger {
        return MyBigInteger(v.toBigInteger())
    }
    public actual fun abs(): MyBigDecimal {
        return MyBigDecimal(v.abs())
    }
    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigDecimal && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
