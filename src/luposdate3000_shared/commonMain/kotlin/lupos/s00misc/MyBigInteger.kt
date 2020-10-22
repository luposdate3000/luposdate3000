package lupos.s00misc

expect class MyBigInteger {
    constructor(s1: String)
    constructor(s1: Int)
    constructor(s1: Long)

    fun toDouble(): Double
    fun toInt(): Int
    fun toMyBigDecimal(): MyBigDecimal
    fun compareTo(other: MyBigInteger): Int
    operator fun plus(other: MyBigInteger): MyBigInteger
    operator fun minus(other: MyBigInteger): MyBigInteger
    operator fun times(other: MyBigInteger): MyBigInteger
    operator fun div(other: MyBigInteger): MyBigInteger
    fun ceil(): MyBigInteger
    fun floor(): MyBigInteger
    fun round(): MyBigInteger
    fun abs(): MyBigInteger
override fun toString():String
override fun equals(other: Any?) : Boolean 
}
