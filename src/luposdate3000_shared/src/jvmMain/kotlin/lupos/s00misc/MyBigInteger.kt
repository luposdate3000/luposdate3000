package lupos.s00misc
import java.math.BigInteger
public actual class MyBigInteger {
    internal val s: String
    private val v: BigInteger
    public actual constructor(s1: String) {
        v = BigInteger(s1)
        s = s1
    }
    public constructor(v1: BigInteger) {
        v = v1
        s = v1.toString()
    }
    public actual constructor(s1: Int) {
        v = BigInteger("" + s1)
        s = "" + s1
    }
    public actual fun toDouble(): Double {
        return v.toDouble()
    }
    public actual fun compareTo(other: MyBigInteger): Int {
        return v.compareTo(other.v)
    }
    public actual operator fun plus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.add(other.v))
    }
    public actual operator fun minus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.subtract(other.v))
    }
    public actual operator fun times(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.multiply(other.v))
    }
    public actual operator fun div(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.divide(other.v))
    }
    public actual fun toMyBigDecimal(): MyBigDecimal {
        return MyBigDecimal(v.toBigDecimal())
    }
    public actual fun abs(): MyBigInteger {
        return MyBigInteger(v.abs())
    }
    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
