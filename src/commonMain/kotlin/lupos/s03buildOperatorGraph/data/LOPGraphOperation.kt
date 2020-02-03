package lupos.s03buildOperatorGraph.data

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03buildOperatorGraph.data.LOPConstant
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.LOPBase
import lupos.s03buildOperatorGraph.OPBase

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
