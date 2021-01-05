package lupos.s00misc
import kotlin.jvm.JvmName
internal object SanityCheckOff {
    @JvmName("println") internal inline fun println(crossinline s: () -> Any?) {}
    @JvmName("invoke") internal inline operator fun invoke(crossinline action: () -> Unit) {}
    /*suspend*/ @JvmName("suspended") internal inline fun suspended(crossinline action: /*suspend*/ () -> Unit) {}
    @JvmName("helper") internal inline fun <T> helper(crossinline action: () -> Unit): T? = null
    @JvmName("check") internal inline fun check(crossinline value: () -> Boolean, crossinline msg: () -> String) {}
    @JvmName("check") internal inline fun check(crossinline value: () -> Boolean) {}
    @JvmName("checkUnreachable") internal inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
