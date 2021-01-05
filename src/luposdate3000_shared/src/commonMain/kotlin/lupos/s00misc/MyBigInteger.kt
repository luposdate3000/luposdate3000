package lupos.s00misc
public expect class MyBigInteger {
   public constructor(s1: String)
  public  constructor(s1: Int)
    public fun toDouble(): Double
    public fun toMyBigDecimal(): MyBigDecimal
    public fun compareTo(other: MyBigInteger): Int
    operator public fun plus(other: MyBigInteger): MyBigInteger
    operator public fun minus(other: MyBigInteger): MyBigInteger
    operator public fun times(other: MyBigInteger): MyBigInteger
    operator public fun div(other: MyBigInteger): MyBigInteger
    public fun abs(): MyBigInteger
    override public fun toString(): String
    override public fun equals(other: Any?): Boolean
    override public fun hashCode(): Int
}
