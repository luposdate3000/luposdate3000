package lupos.s04logicalOperators.singleinput
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.data.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPSingleInputBase



class LOPBind(val name: LOPVariable, val expression: OPBase) : LOPSingleInputBase() {
    constructor(name: LOPVariable, expression: OPBase, child: OPBase) : this(name, expression) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name.name) + child.getRequiredVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return expression.getRequiredVariableNames() + child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPBind")
        res.addAttribute("name", name.name)
        res.addContent(XMLElement("LocalValue").addContent(expression.toXMLElement()))
        res.addContent(child.toXMLElement())
        return res
    }
}
