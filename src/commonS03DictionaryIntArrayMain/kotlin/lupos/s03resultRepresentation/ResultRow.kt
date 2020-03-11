package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class ResultRow(columns: Int, undefValue: Value) : Comparable<ResultRow> {
    @JvmField
    val values = Array(columns) { undefValue }

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    @JvmField
    val uuid: Long = global_uuid.next()

    override fun toString() = values.toString()

    override operator fun compareTo(other: ResultRow): Int {
        var res = 0
        val s = values.size - other.values.size
        if (s != 0) {
            res = s
            return res
        }
        for (k in values.indices) {
            val c = values[k].compareTo(other.values[k])
            if (c != 0) {
                res = c
                return res
            }
        }
        return res
    }

    override fun equals(other: Any?) = (!(other == null || !(other is ResultRow))) && compareTo(other) == 0

    override fun hashCode(): Int {
        var res = values.size.hashCode()
        for (v in values)
            res += v.hashCode()
        return res
    }

}
