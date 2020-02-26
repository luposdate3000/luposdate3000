package lupos.s00misc
import lupos.s00misc.EOperatorID

fun Throwable.kotlinStacktrace() {
    GlobalLogger.log(ELoggerType.RELEASE, { this.message })
    GlobalLogger.log(ELoggerType.DEBUG, { this })
}
