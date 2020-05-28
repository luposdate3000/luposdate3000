package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
class ColumnIteratorDebugVerbose(val uuid: Long, val name: String, val child: ColumnIterator) : ColumnIterator() {
    companion object {
        val counters = mutableMapOf<Long, MutableMap<String, MyListValue>>()
        fun debug() {
Coverage.funStart(3420)
            for ((k, v) in counters) {
Coverage.forLoopStart(3421)
                var count = 0
Coverage.statementStart(3422)
                for ((s, t) in v) {
Coverage.forLoopStart(3423)
                    count = t.size
Coverage.statementStart(3424)
                }
Coverage.statementStart(3425)
                for (key in v.keys) {
Coverage.forLoopStart(3426)
                    println("$k -> $key")
Coverage.statementStart(3427)
                }
Coverage.statementStart(3428)
                for (i in 0 until count) {
Coverage.forLoopStart(3429)
                    try {
Coverage.statementStart(3430)
                        println(v.keys.map { "$k -> ${v[it]!![i]}" })
Coverage.statementStart(3431)
                    } catch (e: Throwable) {
Coverage.statementStart(3432)
                        println(e.message)
Coverage.statementStart(3433)
                    }
Coverage.statementStart(3434)
                }
Coverage.statementStart(3435)
            }
Coverage.statementStart(3436)
            counters.clear()
Coverage.statementStart(3437)
        }
    }
    init {
Coverage.funStart(3438)
        if (counters[uuid] == null) {
Coverage.ifStart(3439)
            counters[uuid] = mutableMapOf(name to MyListValue())
Coverage.statementStart(3440)
        } else {
Coverage.ifStart(3441)
            counters[uuid]!![name] = MyListValue()
Coverage.statementStart(3442)
        }
Coverage.statementStart(3443)
        next = {
Coverage.statementStart(3444)
            val res = child.next()
Coverage.statementStart(3445)
            if (res != null) {
Coverage.ifStart(3446)
                counters[uuid]!![name]!!.add(res)
Coverage.statementStart(3447)
            } else {
Coverage.ifStart(3448)
                println("$uuid finished $name")
Coverage.statementStart(3449)
            }
Coverage.statementStart(3450)
            /*return*/res
        }
Coverage.statementStart(3451)
        close = {
Coverage.statementStart(3452)
            child.close()
Coverage.statementStart(3453)
            _close()
Coverage.statementStart(3454)
        }
Coverage.statementStart(3455)
    }
}
class ColumnIteratorDebugCount(val uuid: Long, name: String, val child: ColumnIterator) : ColumnIterator() {
    companion object {
        val counters = mutableMapOf<Long, MutableMap<String, Int>>()
        fun debug() {
Coverage.funStart(3456)
            for ((k, v) in counters) {
Coverage.forLoopStart(3457)
                println("$k -> $v")
Coverage.statementStart(3458)
            }
Coverage.statementStart(3459)
            counters.clear()
Coverage.statementStart(3460)
        }
    }
    init {
Coverage.funStart(3461)
        if (counters[uuid] == null) {
Coverage.ifStart(3462)
            counters[uuid] = mutableMapOf(name to 0)
Coverage.statementStart(3463)
        } else {
Coverage.ifStart(3464)
            counters[uuid]!![name] = 0
Coverage.statementStart(3465)
        }
Coverage.statementStart(3466)
        next = {
Coverage.statementStart(3467)
            val res = child.next()
Coverage.statementStart(3468)
            if (res != null) {
Coverage.ifStart(3469)
                counters[uuid]!![name] = counters[uuid]!![name]!! + 1
Coverage.statementStart(3470)
            }
Coverage.statementStart(3471)
            /*return*/res
        }
Coverage.statementStart(3472)
        close = {
Coverage.statementStart(3473)
            child.close()
Coverage.statementStart(3474)
            _close()
Coverage.statementStart(3475)
        }
Coverage.statementStart(3476)
    }
}
object ColumnIteratorDebugFast {
    fun debug() {
Coverage.funStart(3477)
    }
    operator fun invoke(uuid: Long, name: String, child: ColumnIterator) = child
}
