package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.LOPBase

class LOPConstruct(val construct: List<List<LOPBase>> = mutableListOf()) : LOPSingleInputBase() {
    constructor(construct: List<List<LOPBase>> = mutableListOf(), child: OPBase) : this(construct) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName}\n${indentation}\tconstructs:\n"
        for (v in construct) {
            res += "${indentation}\t\tconstruct"
            for (u in v) {
                res += "${indentation}\t\t\t$u"
            }
        }
        return res + "${indentation}\tchild:\n" + child.toString("${indentation}\t\t")
    }
}
