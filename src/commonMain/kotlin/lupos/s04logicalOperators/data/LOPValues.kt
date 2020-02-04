package lupos.s04logicalOperators.data
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.data.LOPConstant
import lupos.s04logicalOperators.data.LOPExpression
import lupos.s04logicalOperators.data.LOPGraphOperation
import lupos.s04logicalOperators.data.LOPInsertData
import lupos.s04logicalOperators.data.LOPTriple
import lupos.s04logicalOperators.data.LOPVariable
import lupos.s04logicalOperators.LOPBase



class LOPValues(val variables: List<LOPVariable>, val values: List<LOPExpression>) : LOPBase() {
    override fun getProvidedVariableNames(): List<String> {
        var res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPValues")
        val xmlvariables = XMLElement("LocalVariables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("LocalBindings")
        res.addContent(bindings)
        for (v in variables)
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        for (v in values) {
            val it = v.child.children.iterator()
            for (v2 in variables)
                bindings.addContent(XMLElement("LocalBinding").addAttribute("name", v2.name).addContent(LOPExpression(it.next()).toXMLElement()))
        }
        return res
    }
}
