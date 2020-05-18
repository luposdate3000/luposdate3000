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

        fun mergeHistograms(a: HistogramResult, b: HistogramResult, optional: Boolean): HistogramResult {
            var res = HistogramResult()
            var columns = getColumns(a.values.keys.toList(), b.values.keys.toList())
            var c0 = a.count.toDouble()
            var c1 = b.count.toDouble()
            var estimatedResults = c0 * c1
            var tmpMap = mutableMapOf<String, Int>()
            for (v in columns[0]) {
                val av = a.values[v]!!
                val bv = b.values[v]!!
if(av==0){
estimatedResults=0.0
tmpMap[v]=0
}else if(bv==0){
estimatedResults=0.0
tmpMap[v]=0
}else                if (av < bv) {
                    //not all rows from b get a match
                    val diff = bv - av
                    val eliminatedRows = (diff / bv) * c1
                    estimatedResults -= eliminatedRows
                    tmpMap[v] = av
                } else {
                    //not all rows from a get a match
                    val diff = av - bv
                    val eliminatedRows = (diff / av) * c0
                    estimatedResults -= eliminatedRows
                    tmpMap[v] = bv
                }
            }
            if (estimatedResults < 0.0) {
                estimatedResults = 0.0
            }
            if (optional) {
                estimatedResults += a.count.toDouble()
                if (estimatedResults > c0 * c1) {
                    estimatedResults = c0 * c1
                }
            }
            res.count = estimatedResults.toInt()
            for (v in columns[1]) {
                tmpMap[v] = a.values[v]!!
            }
            for (v in columns[2]) {
                tmpMap[v] = b.values[v]!!
            }
            tmpMap.forEach { k, v ->
                if (v > res.count) {
                    res.values[k] = res.count
                } else {
                    res.values[k] = v
                }
            }
            return res
        }
    }

    override fun calculateHistogram(): HistogramResult {
        return mergeHistograms(children[0].getHistogram(), children[1].getHistogram(), optional)
    }
}
