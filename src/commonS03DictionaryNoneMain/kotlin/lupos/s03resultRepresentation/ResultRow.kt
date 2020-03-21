package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class ResultRow() : Comparable<ResultRow> {
    @JvmField
    val values = mutableMapOf<Variable, Value>()

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    @JvmField
    val uuid: Long = global_uuid.next()

    override fun toString(): String {
        return values.toString()
    }

    override operator fun compareTo(other: ResultRow): Int {
        var res = 0
        val s = values.size - other.values.size
        if (s != 0) {
            res = s
            return res
        }
        for (k in values.keys) {
            if (other.values[k] == null) {
                res = 1
                return res
            }
            val c = values[k]!!.compareTo(other.values[k]!!)
            if (c != 0) {
                res = c
                return res
            }
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || !(other is ResultRow))
            return false
        return compareTo(other) == 0
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }
}
