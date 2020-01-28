package lupos.s00misc

import kotlin.time.*


@UseExperimental(kotlin.time.ExperimentalTime::class)
object Trace {
    val map = ThreadSafeMutableMap<String, Pair<Long, Double>>()
    val stack = ThreadSafeMutableStack<Pair<String, ClockMark>>()

    fun start(obj: Any) {
        start(classNameToString(obj))
    }

    fun start(name: String) {
println("Trace.start(\"$name\")")
        stack.push(Pair(name, MonoClock.markNow()))
    }

    fun stop(obj: Any) {
        stop(classNameToString(obj))
    }

    fun stop(name: String) {
        require(!stack.isEmpty())
        var key = ""
        stack.elements.forEach {
            key += it.first + "-"
        }
        key = key.substring(0, key.length - 1)
        val tmp = stack.pop()
println("Trace.stop(\"$name\") vs $tmp")
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
        require(stack.isEmpty())
        println("stack")
        println("raw::")
        map.forEach { k, v ->
            println("$k #${v.first} ${v.second} Seconds")
        }
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
            }
        }
        var total = 0.0
        println("real::")
        for ((k, v) in map2) {
            total += v.second
            println("$k #${v.first} ${v.second} Seconds")
        }
        map.clear()
        println("total::${total}")
    }
}
