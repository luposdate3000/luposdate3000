package lupos.s00misc

import kotlin.jvm.JvmField


fun Throwable.kotlinStacktrace() {
    GlobalLogger.log(ELoggerType.RELEASE, { this.message })
    GlobalLogger.log(ELoggerType.DEBUG, { this })
}
