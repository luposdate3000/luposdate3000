package lupos.s00misc
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmName
@OptIn(kotlin.contracts.ExperimentalContracts::class)
internal object SanityCheckOn {
    @JvmName("println") internal inline fun println(crossinline s: () -> Any?) {
        contract { callsInPlace(s, EXACTLY_ONCE) }
        println(s())
    }
    @JvmName("invoke") internal inline operator fun invoke(crossinline action: () -> Unit) {
        try {
            action()
        } catch (e: Throwable) {
            println("Exception during SanityCheck.invoke")
            e.printStackTrace()
            throw e
        }
    }
    /*suspend*/ @JvmName("suspended") internal inline fun suspended(crossinline action: /*suspend*/ () -> Unit) {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        try {
            action()
        } catch (e: Throwable) {
            println("Exception during SanityCheck.suspended")
            e.printStackTrace()
            throw e
        }
    }
    @JvmName("helper") internal inline fun <T> helper(crossinline action: () -> T): T? {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        return action()
    }
    @JvmName("check") internal inline fun check(crossinline value: () -> Boolean, crossinline msg: () -> String) {
        contract { callsInPlace(value, EXACTLY_ONCE) }
        try {
            if (!value()) {
                throw Exception("SanityCheck failed :: " + msg())
            }
        } catch (e: Throwable) {
            println("Exception during SanityCheck.check")
            e.printStackTrace()
            throw e
        }
    }
    @JvmName("check") internal inline fun check(crossinline value: () -> Boolean) {
        contract { callsInPlace(value, EXACTLY_ONCE) }
        try {
            if (!value()) {
                throw Exception("SanityCheck failed")
            }
        } catch (e: Throwable) {
            println("Exception during SanityCheck.check")
            e.printStackTrace()
            throw e
        }
    }
    @JvmName("checkUnreachable") internal inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
