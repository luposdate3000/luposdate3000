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
public    constructor(v1: BigDecimal) {
        v = v1
        s = v1.toString()
    }
public    actual constructor(s1: Double) {
        v = BigDecimal(s1)
        s = "" + s1
    }
public    actual constructor(s1: Int) {
        v = BigDecimal(s1)
        s = "" + s1
    }
    actual public fun toDouble(): Double {
        return v.toDouble()
    }
    actual public fun toPlainString(): String {
        return v.toPlainString()
    }
    actual public fun compareTo(other: MyBigDecimal): Int {
        return v.compareTo(other.v)
    }
    actual operator public fun plus(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.add(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.add(other.v, MathContext.DECIMAL128))
        }
    }
    actual operator public fun minus(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.subtract(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.subtract(other.v, MathContext.DECIMAL128))
        }
    }
    actual operator public fun times(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.multiply(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.multiply(other.v, MathContext.DECIMAL128))
        }
    }
    actual operator public fun div(other: MyBigDecimal): MyBigDecimal {
        return try {
            MyBigDecimal(v.divide(other.v, MathContext.UNLIMITED))
        } catch (e: ArithmeticException) {
            MyBigDecimal(v.divide(other.v, MathContext.DECIMAL128))
        }
    }
    actual public fun ceil(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.CEILING))
    }
    actual public fun floor(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.FLOOR))
    }
    actual public fun round(): MyBigDecimal {
        return MyBigDecimal(v.setScale(0, RoundingMode.HALF_UP))
    }
    actual public fun toMyBigInteger(): MyBigInteger {
        return MyBigInteger(v.toBigInteger())
    }
    actual public fun abs(): MyBigDecimal {
        return MyBigDecimal(v.abs())
    }
    actual override public fun toString(): String = s
    actual override public fun equals(other: Any?): Boolean = other is MyBigDecimal && s == other.s
    actual override public fun hashCode(): Int {
        return s.hashCode()
    }
}
