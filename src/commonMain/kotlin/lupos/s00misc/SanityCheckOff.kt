package lupos.s00misc

import lupos.s00misc.Coverage

object SanityCheckOff {
 /*inline*/ fun println(/*crossinline*/s:()->Any?){}
    /*inline*/   operator fun invoke(/*crossinline*/ action: () -> Unit) {}
    /*inline*/ fun <T> helper(/*crossinline*/ action: () -> Unit): T? = null
    /*inline*/ fun check(/*crossinline*/ value: () -> Boolean,/*crossinline*/ msg: () -> String) {}
    /*inline*/ fun check(/*crossinline*/ value: () -> Boolean) {}
    /*inline*/ fun checkUnreachable(): Nothing = throw UnreachableException()
}
