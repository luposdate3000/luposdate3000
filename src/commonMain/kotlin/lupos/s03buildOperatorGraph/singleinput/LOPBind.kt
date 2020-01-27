package lupos.s03buildOperatorGraph.singleinput

import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.data.LOPVariable

import lupos.s00misc.XMLElement


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
