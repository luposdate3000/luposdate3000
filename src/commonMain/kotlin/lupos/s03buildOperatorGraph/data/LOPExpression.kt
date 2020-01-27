package lupos.s2buildOperatorGraph.data
import lupos.s03buildOperatorGraph.data.LOPConstant
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s00misc.XMLElement

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

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPExpression")
        res.addContent(XMLElement("expression").addContent(child.toString()))
        return res
    }
}
