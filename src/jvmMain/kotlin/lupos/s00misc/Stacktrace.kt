package lupos.s00misc
import lupos.s00misc.Coverage
fun Throwable.kotlinStacktrace() {
Coverage.funStart(16270)
    GlobalLogger.log(ELoggerType.DEBUG, { this.printStackTrace() })
Coverage.statementStart(16271)
}
