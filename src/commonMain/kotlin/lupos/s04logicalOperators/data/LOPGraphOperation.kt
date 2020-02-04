package lupos.s04logicalOperators.data
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s04logicalOperators.data.LOPConstant
import lupos.s04logicalOperators.data.LOPExpression
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase



enum class GraphOperationType {
    CREATE, CLEAR, DROP
}

class LOPGraphOperation() : LOPBase() {
    var silent = false
    var graphref: ASTGraphRef? = null
    var action = GraphOperationType.CREATE
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPGraphOperation")
        return res
    }
}
