package lupos.s00misc

import lupos.s00misc.Coverage

fun Throwable.kotlinStacktrace() {
    GlobalLogger.log(ELoggerType.DEBUG, { this.printStackTrace() })
}
