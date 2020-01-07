package lupos.s2operatorgraph.data

import lupos.s1parser.sparql1_1.*
import lupos.s2operatorgraph.LOPBase
import lupos.s2operatorgraph.OPBase

class LOPExpression(val child: ASTNode) : LOPBase() {
    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${child.toString("${indentation}\t")}"
    }
}
