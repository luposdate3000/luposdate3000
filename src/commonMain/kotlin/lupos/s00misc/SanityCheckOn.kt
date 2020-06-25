package lupos.s00misc

import lupos.s00misc.Coverage

object SanityCheckOn {
    operator fun invoke(action: () -> Unit) = action()
    inline fun <T> helper(action: () -> T): T? = action()
    inline fun check(value: () -> Boolean, msg: () -> String) = require(value(), msg)
    inline fun check(value: () -> Boolean) = require(value())
    inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
