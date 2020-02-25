package lupos.s04logicalOperators.noinput

import lupos.s00misc.EGraphOperationType
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPGraphOperation() : LOPBase() {
    override val classname = "LOPGraphOperation"
    override val children: Array<OPBase> = arrayOf()
    var silent = false
    var graphref1: ASTGraphRef? = null
    var graphref2: ASTGraphRef? = null
    var action = EGraphOperationType.CREATE
    override fun getProvidedVariableNames() = mutableListOf<String>()

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPGraphOperation")
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPGraphOperation)
            return false
        if (silent != other.silent)
            return false
        if (graphref1 != other.graphref1)
            return false
        if (graphref2 != other.graphref2)
            return false
        if (action != other.action)
            return false
        return true
    }
}
