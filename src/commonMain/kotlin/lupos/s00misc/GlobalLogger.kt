package lupos.s00misc

import kotlin.jvm.JvmField

object GlobalLogger {
    @JvmField
    var enabled = ELoggerType.TEST_DETAIL
    inline fun log(type: ELoggerType, crossinline action: () -> Any?) {
        if (enabled.ordinal >= type.ordinal) {
            println(action())
        }
    }

    inline suspend fun logSuspended(type: ELoggerType, crossinline action: suspend () -> Any?) {
        if (enabled.ordinal >= type.ordinal) {
            println(action())
        }
    }

    inline fun stacktrace(type: ELoggerType, e: Throwable) {
        if (enabled.ordinal >= type.ordinal) {
            e.printStackTrace()
        }
    }
}
