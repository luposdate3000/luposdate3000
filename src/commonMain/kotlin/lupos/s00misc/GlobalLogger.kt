package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query


object GlobalLogger {
    @JvmField
    var enabled = ELoggerType.TEST_DETAIL

    fun log(type: ELoggerType, action: () -> Any?) {
        if (enabled.ordinal >= type.ordinal)
            println(action())
    }

    fun stacktrace(type: ELoggerType, e: Throwable) {
        if (enabled.ordinal >= type.ordinal)
            e.printStackTrace()
    }
}
