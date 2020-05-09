package lupos.s04logicalOperators.iterator
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef



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
                    println("$k -> $key")
                }
                for (i in 0 until count) {
                    try {
                        println(v.keys.map { "$k -> ${v[it]!![i]}" })
                    } catch (e: Throwable) {
                        println(e.message)
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
                println("$uuid finished $name")
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
                println("$k -> $v")
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
    fun debug() {
    }

    operator fun invoke(uuid: Long, name: String, child: ColumnIterator) = child
}
