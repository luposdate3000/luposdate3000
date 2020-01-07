package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPExpression

class LOPFilter(val filter: LOPExpression) : LOPSingleInputBase() {
    constructor(filter: LOPExpression, child: OPBase) : this(filter) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tfilter:\n'${filter.toString("${indentation}\t\t")}'\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}
