package lupos.s2buildOperatorGraph.data

import lupos.s1buildSyntaxTree.sparql1_1.ASTNode
import lupos.s2buildOperatorGraph.LOPBase

class LOPExpression(val child: ASTNode) : LOPBase() {
    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${child.toString("${indentation}\t")}"
    }
}
