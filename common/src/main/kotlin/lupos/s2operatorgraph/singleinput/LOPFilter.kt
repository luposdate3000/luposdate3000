package lupos.s2operatorgraph.singleinput

import lupos.s1parser.sparql1_1.ASTNode
import lupos.s2operatorgraph.OPBase
import lupos.s2operatorgraph.data.*

class LOPFilter(val filter: LOPExpression) : LOPSingleInputBase() {
    constructor(filter: LOPExpression, child: OPBase) : this(filter) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tfilter:\n'${filter.toString("${indentation}\t\t")}'\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}
