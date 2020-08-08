package lupos.s00misc

inline fun helperSanityPrintln(s: Any?) = println(s)

object SanityCheckOn {
    /*inline*/ fun println(/*crossinline*/s: () -> Any?) {
        helperSanityPrintln(s())
    }

    suspend    /*inline*/ fun printlnSuspended(/*crossinline*/s: suspend () -> Any?) {
        helperSanityPrintln(s())
    }

    inline operator fun invoke(crossinline action: () -> Unit) = action()
    inline suspend fun suspended(crossinline action: suspend () -> Unit) = action()
    /*inline*/ fun <T> helper(/*crossinline*/action: () -> T): T? = action()
    /*inline*/ fun check(/*crossinline*/value: () -> Boolean,/*crossinline*/ msg: () -> String) = require(value(), msg)
    /*inline*/ fun check(/*crossinline*/value: () -> Boolean) = require(value())
    /*inline*/ fun checkUnreachable(): Nothing = throw UnreachableException()
}
