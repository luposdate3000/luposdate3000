package lupos.s2buildOperatorGraph.data
import lupos.s03buildOperatorGraph.data.LOPConstant
import lupos.s00misc.XMLElement


import lupos.s2buildOperatorGraph.LOPBase

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
