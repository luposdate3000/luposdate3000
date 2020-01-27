package lupos.s00misc

import kotlin.time.*

class StackWithList<T> {
    val elements: MutableList<T> = mutableListOf()

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    fun push(item: T) = elements.add(item)

    fun pop(): T? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            elements.removeAt(elements.size - 1)
        }
        return item
    }

    fun peek(): T? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}

@UseExperimental(kotlin.time.ExperimentalTime::class)
object Trace {
    val map = mutableMapOf<String, Pair<Long, Double>>()
    val stack = StackWithList<Pair<String, ClockMark>>()

    fun start(obj: Any) {
        start(classNameToString(obj))
    }

    fun start(name: String) {
        stack.push(Pair(name, MonoClock.markNow()))
    }

    fun stop(obj: Any) {
        stop(classNameToString(obj))
    }

    fun stop(name: String) {
        val key = stack.toString()
        val tmp = stack.pop()
        require(tmp != null)
        require(name == tmp.first)
        val timediff = tmp.second.elapsedNow().toDouble(DurationUnit.MILLISECONDS)
        val old = map[key]
        if (old == null)
            map[key] = Pair(1L, timediff)
        else
            map[key] = Pair(old.first + 1L, old.second + timediff)
    }

    fun print() {
        println("stack")
        println("raw::")
        for ((k, v) in map)
            println("$k #${v.first} ${v.second} Seconds")
        val map2 = mutableMapOf<String, Pair<Long, Double>>()
        for ((k, v) in map) {
            val keys = k.replace("[", "").replace("]", "").replace(" ", "").replace("{", "").replace("}", "").split(",")
            val u = map2[keys.last()]
            if (u == null)
                map2[keys.last()] = Pair(v.first, v.second * 2)
            else
                map2[keys.last()] = Pair(u.first + v.first, u.second + v.second * 2)
            for (i in keys) {
                val t = map2[i]!!
                map2[i] = Pair(t.first, t.second - v.second)
            }
        }
        println("real::")
        for ((k, v) in map2)
            println("$k #${v.first} ${v.second} Seconds")
        map.clear()
        while (stack.pop() != null) {
        }
    }
}
