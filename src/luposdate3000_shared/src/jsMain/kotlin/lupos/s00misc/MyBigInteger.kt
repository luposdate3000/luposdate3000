package lupos.s00misc
public actual class MyBigInteger {
   internal  val s: String
    actual public constructor(s1: String) {
        s = s1
    }
    actual public constructor(s1: Int) {
        s = "" + s1
    }
    actual public fun compareTo(other: MyBigInteger): Int {
        throw object : NotImplementedException("MyBigInteger", "compareTo not implemented") {}
    }
    actual public fun toDouble(): Double {
        throw object : NotImplementedException("MyBigDecimal", "toDouble not implemented") {}
    }
    actual operator public fun plus(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "plus not implemented") {}
    }
    actual operator public fun minus(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "minus not implemented") {}
    }
    actual operator public fun times(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "times not implemented") {}
    }
    actual operator public fun div(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "div not implemented") {}
    }
    actual public fun toMyBigDecimal(): MyBigDecimal {
        throw object : NotImplementedException("MyBigInteger", "toMyBigDecimal not implemented") {}
    }
    actual public fun abs(): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "abs not implemented") {}
    }
    actual override public fun toString(): String = s
    actual override public fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    actual override public fun hashCode(): Int {
        return s.hashCode()
    }
}
