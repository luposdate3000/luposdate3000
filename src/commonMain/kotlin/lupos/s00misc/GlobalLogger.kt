package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
object GlobalLogger {
    @JvmField
    var enabled = ELoggerType.TEST_DETAIL
    fun log(type: ELoggerType, action: () -> Any?) {
Coverage.funStart(150)
        if (enabled.ordinal >= type.ordinal) {
Coverage.ifStart(151)
            println(action())
Coverage.statementStart(152)
        }
Coverage.statementStart(153)
    }
    fun stacktrace(type: ELoggerType, e: Throwable) {
Coverage.funStart(154)
        if (enabled.ordinal >= type.ordinal) {
Coverage.ifStart(155)
            e.printStackTrace()
Coverage.statementStart(156)
        }
Coverage.statementStart(157)
    }
}
