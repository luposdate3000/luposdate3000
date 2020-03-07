package lupos.s03resultRepresentation
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField


class ResultRow(columns: Int, undefValue: Value) : Comparable<ResultRow> {
    @JvmField
    val values = Array(columns) { undefValue }

    operator fun set(name: Variable, value: Value) {
        values[name.toInt()] = value
    }

    operator fun get(name: Variable) = values[name.toInt()]

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
