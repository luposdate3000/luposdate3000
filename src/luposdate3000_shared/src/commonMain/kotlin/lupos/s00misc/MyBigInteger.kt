package lupos.s00misc
public expect class MyBigInteger {
    public constructor(s1: String)
    public constructor(s1: Int)
    public fun toDouble(): Double
    public fun toMyBigDecimal(): MyBigDecimal
    public fun compareTo(other: MyBigInteger): Int
    public operator fun plus(other: MyBigInteger): MyBigInteger
    public operator fun minus(other: MyBigInteger): MyBigInteger
    public operator fun times(other: MyBigInteger): MyBigInteger
    public operator fun div(other: MyBigInteger): MyBigInteger
    public fun abs(): MyBigInteger
    public override fun toString(): String
    public override fun equals(other: Any?): Boolean
    public override fun hashCode(): Int
}
