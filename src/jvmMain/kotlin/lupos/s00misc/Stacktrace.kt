package lupos.s00misc
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField


fun Throwable.kotlinStacktrace() {
    GlobalLogger.log(ELoggerType.DEBUG, { this.printStackTrace() })
}
