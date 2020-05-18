package lupos.s04logicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPJoin(query: Query, first: OPBase, second: OPBase, @JvmField val optional: Boolean) : LOPBase(query, EOperatorID.LOPJoinID, "LOPJoin", arrayOf(first, second), ESortPriority.JOIN) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun equals(other: Any?): Boolean {
        if (other !is LOPJoin) {
            return false
        }
        if (optional != other.optional) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPJoin(query, children[0].cloneOP(), children[1].cloneOP(), optional)

    companion object {
        fun getColumns(columnsA: List<String>, columnsB: List<String>): Array<MutableList<String>/*0:Join,1:AOnly,2:BOnly*/> {
            val res = Array(3) { mutableListOf<String>() }
            res[2].addAll(columnsB)
            for (name in columnsA) {
                if (res[2].contains(name)) {
                    res[0].add(name)
                    res[2].remove(name)
                } else {
                    res[1].add(name)
                }
            }
            return res
        }

        fun mergeHistograms(a: HistogramResult, b: HistogramResult,optional:Boolean): HistogramResult {
            var res = HistogramResult()
            var columns = getColumns(a.values.keys.toList(),b.values.keys.toList())
            if (columns[0].size == 0) {
                res.count = a.count * b.count
                a.values.forEach { k, v ->
                    res.values[k] = v
                }
                b.values.forEach { k, v ->
                    res.values[k] = v
                }
            } else {
                var d0 = 1.0//distinct in a
                var c0 = a.count.toDouble()
                var d1 = 1.0//distinct in b
                var c1 = b.count.toDouble()
                for (v in columns[0]) {
                    d0 = d0 * a.values[v]!!.toDouble()
                    d1 = d1 * b.values[v]!!.toDouble()
                    if (a.values[v]!! < b.values[v]!! || optional) {
                        res.values[v] = a.values[v]!!
                    } else {
                        res.values[v] = b.values[v]!!
                    }
                }
                if (d0 > c0) {
                    d0 = c0
                }
                if (d1 > c1) {
                    d1 = c1
                }
                var estimatedMatches = d0 * d1
                var estimatedRowsPerMatch0 = c0 / d0
                var estimatedRowsPerMatch1 = c1 / d1
                for (v in columns[1]) {
                    res.values[v] = a.values[v]!!
                }
                for (v in columns[2]) {
                    res.values[v] = b.values[v]!!
                }
                res.count = (estimatedMatches * estimatedRowsPerMatch0 * estimatedRowsPerMatch1).toInt() + 1
            }
            return res
        }
    }

    override fun calculateHistogram(): HistogramResult {
        return mergeHistograms(children[0].getHistogram(), children[1].getHistogram(),optional)
    }
}
