package lupos.s2operatorgraph.data

import lupos.s1parser.sparql1_1.BuiltInFunctions
import lupos.s2operatorgraph.LOPBase
import lupos.s2operatorgraph.OPBase

class LOPBuildInCall(val function: BuiltInFunctions, val children: List<OPBase>) : LOPBase() {
    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName} '${function}'\n"
        for (c in children) {
            res += c.toString("${indentation}\t")
        }
        return res
    }
}