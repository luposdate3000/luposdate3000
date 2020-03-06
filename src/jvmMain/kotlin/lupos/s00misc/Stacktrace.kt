package lupos.s00misc

import kotlin.jvm.JvmField


fun Throwable.kotlinStacktrace() {
    GlobalLogger.log(ELoggerType.DEBUG, { this.printStackTrace() })
}
