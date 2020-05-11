package lupos.s00misc

import lupos.s00misc.Coverage

fun Throwable.kotlinStacktrace() {
    printStackTrace()
    GlobalLogger.log(ELoggerType.DEBUG, { this })
}
