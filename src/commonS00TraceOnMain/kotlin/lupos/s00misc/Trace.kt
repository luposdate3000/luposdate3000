package lupos.s00misc
import kotlin.time.ClockMark
import kotlin.time.DurationUnit
import kotlin.time.MonoClock
import lupos.s00misc.EOperatorID



@UseExperimental(kotlin.time.ExperimentalTime::class)
object Trace {
    val map = ThreadSafeMutableMap<String, Pair<Long, Double>>()
    val stack = ThreadSafeMutableStack<Pair<String, ClockMark>>()

    inline fun <T> trace(name: () -> String, action: () -> T): T {
        try {
            start(name())
            return action()
        } finally {
            stop(name())
        }
    }

    fun start(obj: Any) {
        start(classNameToString(obj))
    }

    fun start(name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "trace-start $name" })
        stack.push(Pair(name, MonoClock.markNow()))
    }

    fun stop(obj: Any) {
        stop(classNameToString(obj))
    }

    fun stop(name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "trace-stop $name" })
        require(!stack.isEmpty())
        var key = ""
        stack.elements.forEach {
            key += it.first + "-"
        }
        key = key.substring(0, key.length - 1)
        val tmp = stack.pop()
        require(tmp != null)
        require(name == tmp.first)
        val timediff = tmp.second.elapsedNow().toDouble(DurationUnit.SECONDS)
        val old = map[key]
        if (old == null)
            map[key] = Pair(1L, timediff)
        else
            map[key] = Pair(old.first + 1L, old.second + timediff)
    }

    fun print() {
        GlobalLogger.log(ELoggerType.TRACE_RESULT, ::toString)
    }

    override fun toString(): String {
        var res = ""
        res += "stack\n"
        val map2 = mutableMapOf<String, Pair<Long, Double>>()
        map.forEach { k, v ->
            val keys = k.split("-")
            val u = map2[keys.last()]
            if (u == null)
                map2[keys.last()] = Pair(v.first, v.second)
            else
                map2[keys.last()] = Pair(u.first + v.first, u.second + v.second)
            if (keys.size > 1) {
                var i = keys[keys.size - 2]
                val t = map2[i]
                if (t != null)
                    map2[i] = Pair(t.first, t.second - v.second)
                else
                    map2[i] = Pair(0L, 0.0 - v.second)
            }
        }
        var total = 0.0
        var totalrelative = 0.0
        res += "real::\n"
        for (v in map2.values) {
            if (v.first > 0) {
                total += v.second
                totalrelative += v.second / v.first
            }
        }
        val scale = 1000000.0 / totalrelative
        for ((k, v) in map2) {
            val relativeTime = v.second / v.first
            val sortby = (relativeTime * scale).toInt().toString().padStart(7, '0')
            res += "$sortby $relativeTime #${v.first} ${v.second} Seconds $k\n"
        }
        map.clear()
        res += "total::${total}\n"
        return res
    }
}
