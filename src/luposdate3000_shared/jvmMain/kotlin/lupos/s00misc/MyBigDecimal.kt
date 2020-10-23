package lupos.s00misc

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

actual class MyBigDecimal {
    val s: String
    val v: BigDecimal

    actual constructor(s1: String) {
        v = BigDecimal(s1)
        s = s1
    }

    constructor(v1: BigDecimal) {
        v = v1
        s = v1.toString()
    }

    actual constructor(s1: Double) {
        v = BigDecimal(s1)
        s = "" + s1
    }

    actual constructor(s1: Int) {
        v = BigDecimal(s1)
        s = "" + s1
    }

    actual fun toDouble(): Double {
        return v.toDouble()
    }

    actual fun toInt(): Int {
        return v.toInt()
    }

    actual fun toPlainString(): String {
        return v.toPlainString()
    }

    actual fun compareTo(other: MyBigDecimal): Int {
        return v.compareTo(other.v)
    }

    actual operator fun plus(other: MyBigDecimal): MyBigDecimal {
        try {
            return MyBigDecimal(v.add(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            return MyBigDecimal(v.add(other.v, MathContext.DECIMAL128))
        }
    }

    actual operator fun minus(other: MyBigDecimal): MyBigDecimal {
        try {
            return MyBigDecimal(v.subtract(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            return MyBigDecimal(v.subtract(other.v, MathContext.DECIMAL128))
        }
    }

    actual operator fun times(other: MyBigDecimal): MyBigDecimal {
        try {
            return MyBigDecimal(v.multiply(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            return MyBigDecimal(v.multiply(other.v, MathContext.DECIMAL128))
        }
    }

    actual operator fun div(other: MyBigDecimal): MyBigDecimal {
        try {
            return MyBigDecimal(v.divide(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            return MyBigDecimal(v.divide(other.v, MathContext.DECIMAL128))
        }
    }

    actual fun ceil(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.CEILING))
    }

    actual fun floor(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.FLOOR))
    }

    actual fun round(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.HALF_UP))
    }

    actual fun toMyBigInteger(): MyBigInteger {
        return MyBigInteger(v.toBigInteger())
    }

    actual fun abs(): MyBigDecimal {
        return MyBigDecimal(v.abs())
    }

    actual override fun toString(): String = s
    actual override fun equals(other: Any?): Boolean = other is MyBigDecimal && s == other.s
}
