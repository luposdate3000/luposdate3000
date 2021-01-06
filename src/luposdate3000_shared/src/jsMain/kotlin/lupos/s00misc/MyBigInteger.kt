package lupos.s00misc
public actual class MyBigInteger {
    internal val s: String
    public actual constructor(s1: String) {
        s = s1
    }
    public actual constructor(s1: Int) {
        s = "" + s1
    }
    public actual fun compareTo(other: MyBigInteger): Int {
        throw object : NotImplementedException("MyBigInteger", "compareTo not implemented") {}
    }
    public actual fun toDouble(): Double {
        throw object : NotImplementedException("MyBigDecimal", "toDouble not implemented") {}
    }
    public actual operator fun plus(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "plus not implemented") {}
    }
    public actual operator fun minus(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "minus not implemented") {}
    }
    public actual operator fun times(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "times not implemented") {}
    }
    public actual operator fun div(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "div not implemented") {}
    }
    public actual fun toMyBigDecimal(): MyBigDecimal {
        throw object : NotImplementedException("MyBigInteger", "toMyBigDecimal not implemented") {}
    }
    public actual fun abs(): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "abs not implemented") {}
    }
    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
