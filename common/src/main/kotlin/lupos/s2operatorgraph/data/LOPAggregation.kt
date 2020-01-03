package lupos.s2operatorgraph.data

import lupos.s1parser.sparql1_1.Aggregation
import lupos.s2operatorgraph.LOPBase
import lupos.s2operatorgraph.OPBase

class LOPAggregation(val type: Aggregation, val distinct: Boolean, val children: List<OPBase>) : LOPBase() {
    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName} '${type}' '${distinct}'\n"
        for (c in children) {
            res += c.toString("${indentation}\t")
        }
        return res
    }
}