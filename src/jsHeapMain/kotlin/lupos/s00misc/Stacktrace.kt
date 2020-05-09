package lupos.s00misc

fun Throwable.kotlinStacktrace() {
    printStackTrace()
    GlobalLogger.log(ELoggerType.DEBUG, { this })
}
