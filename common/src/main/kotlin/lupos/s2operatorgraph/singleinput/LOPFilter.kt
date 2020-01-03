package lupos.s2operatorgraph.singleinput

import lupos.s1parser.sparql1_1.ASTNode
import lupos.s2operatorgraph.OPBase

class LOPFilter(val filter: ASTNode) : LOPSingleInputBase() {
    constructor(filter: ASTNode, child: OPBase) : this(filter) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${filter}'\n"
}