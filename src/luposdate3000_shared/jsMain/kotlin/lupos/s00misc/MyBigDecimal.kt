package lupos.s00misc

actual class MyBigDecimal {
    val s: String

    actual constructor(s1: String) {
        s = s1
    }

    actual constructor(s1: Double) {
        s = "" + s1
    }

    actual constructor(s1: Int) {
        s = "" + s1
    }

    actual fun toPlainString(): String {
        return s
    }

    actual fun compareTo(other: MyBigDecimal): Int {
        throw object : NotImplementedException("MyBigDecimal", "compareTo not implemented") {}
    }

    actual fun toDouble(): Double {
        throw object : NotImplementedException("MyBigDecimal", "toDouble not implemented") {}
    }

    actual fun toInt(): Int {
        throw object : NotImplementedException("MyBigDecimal", "toInt not implemented") {}
    }

    actual operator fun plus(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "plus not implemented") {}
    }

    actual operator fun minus(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "minus not implemented") {}
    }

    actual operator fun times(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "times not implemented") {}
    }

    actual operator fun div(other: MyBigDecimal): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "div not implemented") {}
    }

    actual fun ceil(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "ceil not implemented") {}
    }

    actual fun floor(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "floor not implemented") {}
    }

    actual fun round(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "round not implemented") {}
    }

    actual fun toMyBigInteger(): MyBigInteger {
        throw object : NotImplementedException("MyBigDecimal", "toMyBigInteger not implemented") {}
    }

    actual fun abs(): MyBigDecimal {
        throw object : NotImplementedException("MyBigDecimal", "abs not implemented") {}
    }
}
