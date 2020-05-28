package lupos.s00misc
import lupos.s00misc.Coverage
class ThreadSafeUuid {
    companion object {
        var global_uuid = 0L
    }
    fun next(): Long {
Coverage.funStart(16359)
        synchronized(global_uuid) {
Coverage.statementStart(16360)
            return global_uuid++
        }
Coverage.statementStart(16361)
    }
}
