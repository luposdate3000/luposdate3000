package lupos.s00misc

import java.math.BigInteger

actual class MyBigInteger {
    val s: String
    private val v: BigInteger

    actual constructor(s1: String) {
        v = BigInteger(s1)
        s = s1
    }

    constructor(v1: BigInteger) {
        v = v1
        s = v1.toString()
    }

    actual constructor(s1: Int) {
        v = BigInteger("" + s1)
        s = "" + s1
    }

    actual fun toDouble(): Double {
        return v.toDouble()
    }

    actual fun compareTo(other: MyBigInteger): Int {
        return v.compareTo(other.v)
    }

    actual operator fun plus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.add(other.v))
    }

    actual operator fun minus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.subtract(other.v))
    }

    actual operator fun times(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.multiply(other.v))
    }

    actual operator fun div(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.divide(other.v))
    }

    actual fun toMyBigDecimal(): MyBigDecimal {
        return MyBigDecimal(v.toBigDecimal())
    }

    actual fun abs(): MyBigInteger {
        return MyBigInteger(v.abs())
    }

    actual override fun toString(): String = s
    actual override fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
