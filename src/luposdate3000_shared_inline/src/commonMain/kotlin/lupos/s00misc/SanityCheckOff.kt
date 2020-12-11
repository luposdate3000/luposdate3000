package lupos.s00misc
internal object SanityCheckOff {
    inline fun println(crossinline s: () -> Any?) {}
    inline operator fun invoke(crossinline action: () -> Unit) {}
    /*suspend*/ inline fun suspended(crossinline action: /*suspend*/ () -> Unit) {}
    inline fun <T> helper(crossinline action: () -> Unit): T? = null
    inline fun check(crossinline value: () -> Boolean, crossinline msg: () -> String) {}
    inline fun check(crossinline value: () -> Boolean) {}
    inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
