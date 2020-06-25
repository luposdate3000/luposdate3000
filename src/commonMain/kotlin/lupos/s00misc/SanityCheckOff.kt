package lupos.s00misc

import lupos.s00misc.Coverage

object SanityCheckOff {
    inline fun <T> helper(action: () -> Unit): T? = null
    operator fun invoke(action: () -> Unit) {}
    inline fun check(value: () -> Boolean, msg: () -> String) {}
    inline fun check(value: () -> Boolean) {}
    inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
