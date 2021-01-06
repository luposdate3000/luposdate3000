package lupos.s00misc
public actual class MyBigDecimal {
internal    val s: String
    actual public constructor(s1: String) {
        s = s1
    }
    actual public constructor(s1: Double) {
        s = "" + s1
    }
    actual public constructor(s1: Int) {
        s = "" + s1
    }
    actual public fun toPlainString(): String {
        return s
    }
    actual public fun compareTo(other: MyBigDecimal): Int {
        throw object : NotImplementedException("MyBigDecimal", "compareTo not implemented") {}
    }
    actual public fun toDouble(): Double {
        throw object : NotImplementedException("MyBigDecimal", "toDouble not implemented") {}
    }
    actual operator public fun plus(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "plus not implemented") {}
    }
    actual operator public fun minus(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "minus not implemented") {}
    }
    actual operator public fun times(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "times not implemented") {}
    }
    actual operator public fun div(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "div not implemented") {}
    }
    actual public fun ceil(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "ceil not implemented") {}
    }
    actual public fun floor(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "floor not implemented") {}
    }
    actual public fun round(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "round not implemented") {}
    }
    actual public fun toMyBigInteger(): MyBigInteger {
        throw object : NotImplementedException("MyBigDecimal", "toMyBigInteger not implemented") {}
    }
    actual public fun abs(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "abs not implemented") {}
    }
    actual override public fun toString(): String = s
    actual override public fun equals(other: Any?): Boolean = other is MyBigDecimal && s == other.s
    actual override public fun hashCode(): Int {
        return s.hashCode()
    }
}
