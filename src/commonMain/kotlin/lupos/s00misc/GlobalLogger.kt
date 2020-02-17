package lupos.s00misc

object GlobalLogger {
    var enabled = ELoggerType.DEBUG
    fun log(type: ELoggerType, action: () -> Any?) {
        if (enabled.ordinal >= type.ordinal)
            println(action())
    }

    fun stacktrace(type: ELoggerType, e: Throwable) {
        if (enabled.ordinal >= type.ordinal)
            e.printStackTrace()
    }
}
