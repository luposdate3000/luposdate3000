package lupos.s00misc

inline fun helperSanityPrintln(s: Any?) = println(s)

object SanityCheckOn {
    /*inline*/ fun println(/*crossinline*/s: () -> Any?) {
        helperSanityPrintln(s())
    }

    inline operator fun invoke(crossinline action: () -> Unit) {
        try {
            action()
        } catch (e: Throwable) {
            println("Exception during SanityCheck.invoke")
            e.printStackTrace()
        }
    }

    inline suspend fun suspended(crossinline action: suspend () -> Unit) {
        try {
            action()
        } catch (e: Throwable) {
            println("Exception during SanityCheck.suspended")
            e.printStackTrace()
        }
    }

    /*inline*/ fun <T> helper(/*crossinline*/action: () -> T): T? = action()
    /*inline*/ fun check(/*crossinline*/value: () -> Boolean,/*crossinline*/ msg: () -> String) {
        try {
            if (!value()) {
                throw Exception("SanityCheck failed :: " + msg())
            }
        } catch (e: Throwable) {
            println("Exception during SanityCheck.check")
            e.printStackTrace()
        }
    }

    /*inline*/ fun check(/*crossinline*/value: () -> Boolean) {
        try {
            if (!value()) {
                throw Exception("SanityCheck failed")
            }
        } catch (e: Throwable) {
            println("Exception during SanityCheck.check")
            e.printStackTrace()
        }
    }

    /*inline*/ fun checkUnreachable(): Nothing = throw UnreachableException()
}
