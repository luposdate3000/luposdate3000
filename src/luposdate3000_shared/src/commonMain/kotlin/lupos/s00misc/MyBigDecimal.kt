package lupos.s00misc
expect class MyBigDecimal {
    constructor(s1: String)
    constructor(s1: Double)
    constructor(s1: Int)
    fun compareTo(other: MyBigDecimal): Int
    fun toPlainString(): String
    fun toDouble(): Double
    fun toMyBigInteger(): MyBigInteger
    operator fun plus(other: MyBigDecimal): MyBigDecimal
    operator fun minus(other: MyBigDecimal): MyBigDecimal
    operator fun times(other: MyBigDecimal): MyBigDecimal
    operator fun div(other: MyBigDecimal): MyBigDecimal
    fun ceil(): MyBigDecimal
    fun floor(): MyBigDecimal
    fun round(): MyBigDecimal
    fun abs(): MyBigDecimal
    override fun toString(): String
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}
