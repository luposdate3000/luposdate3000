package lupos.s00misc
expect class MyBigInteger {
    constructor(s1: String)
    constructor(s1: Int)
    fun toDouble(): Double
    fun toMyBigDecimal(): MyBigDecimal
    fun compareTo(other: MyBigInteger): Int
    operator fun plus(other: MyBigInteger): MyBigInteger
    operator fun minus(other: MyBigInteger): MyBigInteger
    operator fun times(other: MyBigInteger): MyBigInteger
    operator fun div(other: MyBigInteger): MyBigInteger
    fun abs(): MyBigInteger
    override fun toString(): String
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}
