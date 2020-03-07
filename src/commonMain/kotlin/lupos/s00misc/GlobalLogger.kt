package lupos.s00misc
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField


object GlobalLogger {
    @JvmField
    var enabled = ELoggerType.RELEASE

    fun log(type: ELoggerType, action: () -> Any?) {
        if (enabled.ordinal >= type.ordinal)
            println(action())
    }

    fun stacktrace(type: ELoggerType, e: Throwable) {
        if (enabled.ordinal >= type.ordinal)
            e.printStackTrace()
    }
}
