package lupos.s00misc
public actual class MyBigDecimal {
    internal val s: String
    public actual constructor(s1: String) {
        s = s1
    }
    public actual constructor(s1: Double) {
        s = "" + s1
    }
    public actual constructor(s1: Int) {
        s = "" + s1
    }
    public actual fun toPlainString(): String {
        return s
    }
    public actual fun toDouble(): Double {
        throw object : NotImplementedException("MyBigDecimal", "toDouble not implemented") {}
    }
    public actual fun compareTo(other: MyBigDecimal): Int {
        throw object : NotImplementedException("MyBigDecimal", "compareTo not implemented") {}
    }
    public actual operator fun plus(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "plus not implemented") {}
    }
    public actual operator fun minus(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "minus not implemented") {}
    }
    public actual operator fun times(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "times not implemented") {}
    }
    public actual operator fun div(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "div not implemented") {}
    }
    public actual fun ceil(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "ceil not implemented") {}
    }
    public actual fun floor(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "floor not implemented") {}
    }
    public actual fun round(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "round not implemented") {}
    }
    public actual fun toMyBigInteger(): MyBigInteger {
        throw object : NotImplementedException("MyBigDecimal", "toMyBigInteger not implemented") {}
    }
    public actual fun abs(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "abs not implemented") {}
    }
    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigDecimal && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
