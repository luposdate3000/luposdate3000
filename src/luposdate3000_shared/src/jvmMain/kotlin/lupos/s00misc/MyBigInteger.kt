package lupos.s00misc
import java.math.BigInteger
public actual class MyBigInteger {
    internal val s: String
    private val v: BigInteger
    public actual constructor(s1: String) {
        v = BigInteger(s1)
        s = s1
    }
public    constructor(v1: BigInteger) {
        v = v1
        s = v1.toString()
    }
public    actual constructor(s1: Int) {
        v = BigInteger("" + s1)
        s = "" + s1
    }
    actual public fun toDouble(): Double {
        return v.toDouble()
    }
    actual public fun compareTo(other: MyBigInteger): Int {
        return v.compareTo(other.v)
    }
    actual operator public fun plus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.add(other.v))
    }
    actual operator public fun minus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.subtract(other.v))
    }
    actual operator public fun times(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.multiply(other.v))
    }
    actual operator public fun div(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.divide(other.v))
    }
    actual public fun toMyBigDecimal(): MyBigDecimal {
        return MyBigDecimal(v.toBigDecimal())
    }
    actual public fun abs(): MyBigInteger {
        return MyBigInteger(v.abs())
    }
    actual override public fun toString(): String = s
    actual override public fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    actual override public fun hashCode(): Int {
        return s.hashCode()
    }
}
