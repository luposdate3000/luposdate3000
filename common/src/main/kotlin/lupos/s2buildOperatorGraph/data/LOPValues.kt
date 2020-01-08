package lupos.s2buildOperatorGraph.data

import lupos.s2buildOperatorGraph.LOPBase

class LOPValues(val variables: List<LOPVariable>, val values: List<LOPExpression>) : LOPBase() {
    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName}\n${indentation}\tvariables:\n"
        for (v in variables) {
            res += v.toString("${indentation}\t\t")
        }
        res += "$indentation\tvalues:\n"
        for (v in values) {
            res += v.toString("${indentation}\t\t")
        }
        return res
    }
}
