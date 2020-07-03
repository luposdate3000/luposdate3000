package lupos.s00misc

import lupos.s00misc.Coverage

inline fun helperSanityPrintln(s: Any?) = println(s)

object SanityCheckOn {
    /*inline*/ fun println(/*crossinline*/s: () -> Any?) {
        helperSanityPrintln(s())
    }

    operator fun invoke(/*crossinline*/action: () -> Unit) = action()
    /*inline*/ fun <T> helper(/*crossinline*/action: () -> T): T? = action()
    /*inline*/ fun check(/*crossinline*/value: () -> Boolean,/*crossinline*/ msg: () -> String) = require(value(), msg)
    /*inline*/ fun check(/*crossinline*/value: () -> Boolean) = require(value())
    /*inline*/ fun checkUnreachable(): Nothing = throw UnreachableException()
}
