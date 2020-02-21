package lupos.s04logicalOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPExpression(val child: AOPBase) : LOPBase() {
    override val children: Array<OPBase> = arrayOf()
    fun getAllVariablesInChildren(node: AOPBase): List<String> {
        val res = mutableListOf<String>()
        if (node is AOPVariable)
            res.add(node.name)
        for (c in node.children)
            res.addAll(getAllVariablesInChildren(c as AOPBase))
        return res
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getAllVariablesInChildren(child)
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPExpression")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPExpression)
            return false
        return child === other.child
    }
}
