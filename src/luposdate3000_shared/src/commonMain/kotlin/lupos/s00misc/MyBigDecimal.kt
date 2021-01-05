package lupos.s00misc
public expect class MyBigDecimal {
  public   constructor(s1: String)
  public  constructor(s1: Double)
   public constructor(s1: Int)
    public fun compareTo(other: MyBigDecimal): Int
    public fun toPlainString(): String
    public fun toDouble(): Double
    public fun toMyBigInteger(): MyBigInteger
    operator public fun plus(other: MyBigDecimal): MyBigDecimal
    operator public fun minus(other: MyBigDecimal): MyBigDecimal
    operator public fun times(other: MyBigDecimal): MyBigDecimal
    operator public fun div(other: MyBigDecimal): MyBigDecimal
    public fun ceil(): MyBigDecimal
    public fun floor(): MyBigDecimal
    public fun round(): MyBigDecimal
    public fun abs(): MyBigDecimal
    override public fun toString(): String
    override public fun equals(other: Any?): Boolean
    override public fun hashCode(): Int
}
