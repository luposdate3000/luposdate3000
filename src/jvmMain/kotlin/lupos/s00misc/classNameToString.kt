package lupos.s00misc
import lupos.s00misc.Coverage
fun classNameToString(c: Any): String {
Coverage.funStart(16122)
    val res = c::class.simpleName
Coverage.statementStart(16123)
    if (res == null) {
Coverage.ifStart(16124)
        return ""
    }
Coverage.statementStart(16125)
    return res
}
