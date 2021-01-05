package lupos.s00misc
public expect class MyBigDecimal {
    public constructor(s1: String)
    public constructor(s1: Double)
    public constructor(s1: Int)
    public fun compareTo(other: MyBigDecimal): Int
    public fun toPlainString(): String
    public fun toDouble(): Double
    public fun toMyBigInteger(): MyBigInteger
    public operator fun plus(other: MyBigDecimal): MyBigDecimal
    public operator fun minus(other: MyBigDecimal): MyBigDecimal
    public operator fun times(other: MyBigDecimal): MyBigDecimal
    public operator fun div(other: MyBigDecimal): MyBigDecimal
    public fun ceil(): MyBigDecimal
    public fun floor(): MyBigDecimal
    public fun round(): MyBigDecimal
    public fun abs(): MyBigDecimal
    public override fun toString(): String
    public override fun equals(other: Any?): Boolean
    public override fun hashCode(): Int
}
