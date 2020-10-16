package lupos.s00misc

actual class MyBigInteger {
    val s: String

    actual constructor(s1: String) {
        s = s1
    }

    actual constructor(s1: Int) {
        s = "" + s1
    }

    actual constructor(s1: Long) {
        s = "" + s1
    }

    actual fun compareTo(other: MyBigInteger): Int {
        throw object : NotImplementedException("MyBigInteger", "compareTo not implemented") {}
    }

    actual fun toDouble(): Double {
        throw object : NotImplementedException("MyBigDecimal", "toDouble not implemented") {}
    }

    actual fun toInt(): Int {
        throw object : NotImplementedException("MyBigDecimal", "toInt not implemented") {}
    }

actual operator fun plus(other:MyBigInteger):MyBigInteger{
throw object : NotImplementedException("MyBigInteger", "plus not implemented") {}
}
actual operator fun minus(other:MyBigInteger):MyBigInteger{
throw object : NotImplementedException("MyBigInteger", "minus not implemented") {}
}
actual operator fun times(other:MyBigInteger):MyBigInteger{
throw object : NotImplementedException("MyBigInteger", "times not implemented") {}
}
actual operator fun div(other:MyBigInteger):MyBigInteger{
throw object : NotImplementedException("MyBigInteger", "div not implemented") {}
}


    actual fun ceil(): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "ceil not implemented") {}
    }

    actual fun floor(): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "floor not implemented") {}
    }

    actual fun round(): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "round not implemented") {}
    }

    actual fun toMyBigDecimal(): MyBigDecimal {
        throw object : NotImplementedException("MyBigInteger", "toMyBigDecimal not implemented") {}
    }
actual fun abs():MyBigInteger{ 
throw object : NotImplementedException("MyBigInteger", "abs not implemented") {}
}
}
