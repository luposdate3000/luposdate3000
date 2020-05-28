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
Coverage.funStart(4367)
        if (other !is LOPJoin) {
Coverage.ifStart(4368)
            return false
        }
Coverage.statementStart(4369)
        if (optional != other.optional) {
Coverage.ifStart(4370)
            return false
        }
Coverage.statementStart(4371)
        for (i in children.indices) {
Coverage.forLoopStart(4372)
            if (children[i] != other.children[i]) {
Coverage.ifStart(4373)
                return false
            }
Coverage.statementStart(4374)
        }
Coverage.statementStart(4375)
        return true
    }
    override fun cloneOP() = LOPJoin(query, children[0].cloneOP(), children[1].cloneOP(), optional)
    companion object {
        fun getColumns(columnsA: List<String>, columnsB: List<String>): Array<MutableList<String>/*0:Join,1:AOnly,2:BOnly*/> {
Coverage.funStart(4376)
            val res = Array(3) { mutableListOf<String>() }
Coverage.statementStart(4377)
            res[2].addAll(columnsB)
Coverage.statementStart(4378)
            for (name in columnsA) {
Coverage.forLoopStart(4379)
                if (res[2].contains(name)) {
Coverage.ifStart(4380)
                    res[0].add(name)
Coverage.statementStart(4381)
                    res[2].remove(name)
Coverage.statementStart(4382)
                } else {
Coverage.ifStart(4383)
                    res[1].add(name)
Coverage.statementStart(4384)
                }
Coverage.statementStart(4385)
            }
Coverage.statementStart(4386)
            return res
        }
        fun mergeHistograms(a: HistogramResult, b: HistogramResult, optional: Boolean): HistogramResult {
Coverage.funStart(4387)
            var res = HistogramResult()
Coverage.statementStart(4388)
            var columns = getColumns(a.values.keys.toList(), b.values.keys.toList())
Coverage.statementStart(4389)
            var c0 = a.count.toDouble()
Coverage.statementStart(4390)
            var c1 = b.count.toDouble()
Coverage.statementStart(4391)
            var estimatedResults = c0 * c1
Coverage.statementStart(4392)
            var tmpMap = mutableMapOf<String, Int>()
Coverage.statementStart(4393)
            for (v in columns[0]) {
Coverage.forLoopStart(4394)
                val av = a.values[v]!!.toDouble()
Coverage.statementStart(4395)
                val bv = b.values[v]!!.toDouble()
Coverage.statementStart(4396)
                if (av == 0.0) {
Coverage.ifStart(4397)
                    estimatedResults = 0.0
Coverage.statementStart(4398)
                    tmpMap[v] = 0
Coverage.statementStart(4399)
                } else if (bv == 0.0) {
Coverage.ifStart(4400)
                    estimatedResults = 0.0
Coverage.statementStart(4401)
                    tmpMap[v] = 0
Coverage.statementStart(4402)
                } else if (av < bv) {
Coverage.ifStart(4403)
                    //not all rows from b get a match
Coverage.statementStart(4404)
                    val diff = bv - av
Coverage.statementStart(4405)
                    estimatedResults = estimatedResults * (1 - diff / bv)
Coverage.statementStart(4406)
                    tmpMap[v] = av.toInt()
Coverage.statementStart(4407)
                } else {
Coverage.ifStart(4408)
                    //not all rows from a get a match
Coverage.statementStart(4409)
                    val diff = av - bv
Coverage.statementStart(4410)
                    estimatedResults = estimatedResults * (1 - diff / av)
Coverage.statementStart(4411)
                    tmpMap[v] = bv.toInt()
Coverage.statementStart(4412)
                }
Coverage.statementStart(4413)
            }
Coverage.statementStart(4414)
            if (estimatedResults < 0.0) {
Coverage.ifStart(4415)
                estimatedResults = 0.0
Coverage.statementStart(4416)
            }
Coverage.statementStart(4417)
            if (optional) {
Coverage.ifStart(4418)
                estimatedResults += c0
Coverage.statementStart(4419)
                if (estimatedResults > c0 * c1) {
Coverage.ifStart(4420)
                    estimatedResults = c0 * c1
Coverage.statementStart(4421)
                }
Coverage.statementStart(4422)
            }
Coverage.statementStart(4423)
            res.count = (estimatedResults + 0.9999).toInt()
Coverage.statementStart(4424)
            for (v in columns[1]) {
Coverage.forLoopStart(4425)
                tmpMap[v] = a.values[v]!!
Coverage.statementStart(4426)
            }
Coverage.statementStart(4427)
            for (v in columns[2]) {
Coverage.forLoopStart(4428)
                tmpMap[v] = b.values[v]!!
Coverage.statementStart(4429)
            }
Coverage.statementStart(4430)
            tmpMap.forEach { k, v ->
Coverage.statementStart(4431)
                if (v > res.count) {
Coverage.ifStart(4432)
                    res.values[k] = res.count
Coverage.statementStart(4433)
                } else {
Coverage.ifStart(4434)
                    res.values[k] = v
Coverage.statementStart(4435)
                }
Coverage.statementStart(4436)
            }
Coverage.statementStart(4437)
            return res
        }
    }
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4438)
        return mergeHistograms(children[0].getHistogram(), children[1].getHistogram(), optional)
    }
}
