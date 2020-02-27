package lupos.s00misc

import lupos.s00misc.EOperatorID


object GlobalLogger {
    var enabled = ELoggerType.TEST_RESULT
    fun log(type: ELoggerType, action: () -> Any?) {
        if (enabled.ordinal >= type.ordinal)
            println(action())
    }

    fun stacktrace(type: ELoggerType, e: Throwable) {
        if (enabled.ordinal >= type.ordinal)
            e.printStackTrace()
    }
}
