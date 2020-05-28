package lupos.s00misc
import lupos.s00misc.Coverage
fun Throwable.kotlinStacktrace() {
Coverage.funStart(16119)
    printStackTrace()
Coverage.statementStart(16120)
    GlobalLogger.log(ELoggerType.DEBUG, { this })
Coverage.statementStart(16121)
}
