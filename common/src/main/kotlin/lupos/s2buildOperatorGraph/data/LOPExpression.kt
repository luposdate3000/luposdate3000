package lupos.s2buildOperatorGraph.data

import lupos.misc.*
import lupos.s1buildSyntaxTree.sparql1_1.ASTNode
import lupos.s1buildSyntaxTree.sparql1_1.ASTVar
import lupos.s2buildOperatorGraph.LOPBase

class LOPExpression(val child: ASTNode) : LOPBase() {

    fun getAllVariablesInChildren(node: ASTNode): List<String> {
        val res = mutableListOf<String>()
        if (node is ASTVar)
            res.add(node.name)
        for (c in node.children)
            res.addAll(getAllVariablesInChildren(c))
        return res
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getAllVariablesInChildren(child)
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${child.toString("${indentation}\t")}"
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPExpression")
    }
}
