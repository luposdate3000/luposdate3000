package lupos.s00misc

internal object SanityCheckOn {
    inline fun println(crossinlines: () -> Any?) {
        println(s())
    }

    inline operator fun invoke(crossinline action: () -> Unit) {
        try {
            action()
        } catch (e: Throwable) {
            println("Exception during SanityCheck.invoke")
            e.printStackTrace()
            throw e
        }
    }

    /*suspend*/ inline fun suspended(crossinline action: /*suspend*/ () -> Unit) {
        try {
            action()
        } catch (e: Throwable) {
            println("Exception during SanityCheck.suspended")
            e.printStackTrace()
            throw e
        }
    }

    inline fun <T> helper(crossinlineaction: () -> T): T? = action()
    inline fun check(crossinlinevalue: () -> Boolean, crossinline msg: () -> String) {
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

    inline fun check(crossinlinevalue: () -> Boolean) {
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

    inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
