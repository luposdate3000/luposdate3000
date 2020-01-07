package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable

class LOPProjection(val variables: MutableList<LOPVariable> = mutableListOf()) : LOPSingleInputBase() {
    constructor(variables: MutableList<LOPVariable> = mutableListOf(), child: OPBase) : this(variables) {
        this.child = child
    }

    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName}\n${indentation}\tvariables:\n"
        for (var1 in variables) {
            res += "${indentation}\t\t$var1"
        }
        return res + "${indentation}\tchild:\n" + child.toString("${indentation}\t\t")
    }
}
