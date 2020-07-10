package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value

class ColumnIteratorDebugVerbose(val uuid: Long, val name: String, val child: ColumnIterator) : ColumnIterator() {
    companion object {
        val counters = mutableMapOf<Long, MutableMap<String, MyListValue>>()
        fun debug() {
            for ((k, v) in counters) {
                var count = 0
                for ((s, t) in v) {
                    count = t.size
                }
                for (key in v.keys) {
                    SanityCheck.println({ "$k -> $key" })
                }
                for (i in 0 until count) {
                    try {
                        SanityCheck.println({ v.keys.map { "$k -> ${v[it]!![i]}" } })
                    } catch (e: Throwable) {
                        SanityCheck.println({ "TODO exception 11" })
                        e.printStackTrace()
                    }
                }
            }
            counters.clear()
        }
    }

    init {
        if (counters[uuid] == null) {
            counters[uuid] = mutableMapOf(name to MyListValue())
        } else {
            counters[uuid]!![name] = MyListValue()
        }
        next = {
            val res = child.next()
            if (res != null) {
                counters[uuid]!![name]!!.add(res)
            } else {
                SanityCheck.println({ "$uuid finished $name" })
            }
            /*return*/res
        }
        close = {
            child.close()
            _close()
        }
    }
}

class ColumnIteratorDebugCount(val uuid: Long, name: String, val child: ColumnIterator) : ColumnIterator() {
    companion object {
        val counters = mutableMapOf<Long, MutableMap<String, Int>>()
        fun debug() {
            for ((k, v) in counters) {
                SanityCheck.println({ "$k -> $v" })
            }
            counters.clear()
        }
    }

    init {
        if (counters[uuid] == null) {
            counters[uuid] = mutableMapOf(name to 0)
        } else {
            counters[uuid]!![name] = 0
        }
        next = {
            val res = child.next()
            if (res != null) {
                counters[uuid]!![name] = counters[uuid]!![name]!! + 1
            }
            /*return*/res
        }
        close = {
            child.close()
            _close()
        }
    }
}

object ColumnIteratorDebugFast {
inline    fun debug() {
    }

  inline  operator fun invoke(uuid: Long, name: String, child: ColumnIterator) = child
}
