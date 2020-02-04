package lupos.s04logicalOperators.data
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.data.LOPConstant
import lupos.s04logicalOperators.data.LOPExpression
import lupos.s04logicalOperators.data.LOPGraphOperation
import lupos.s04logicalOperators.data.LOPInsertData
import lupos.s04logicalOperators.data.LOPTriple
import lupos.s04logicalOperators.data.LOPValues
import lupos.s04logicalOperators.LOPBase



class LOPVariable(var name: String) : LOPBase() {
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPVariable")
    }
}
