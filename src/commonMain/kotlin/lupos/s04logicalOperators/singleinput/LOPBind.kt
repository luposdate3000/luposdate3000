package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPBind(val name: LOPVariable, val expression: OPBase) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(name: LOPVariable, expression: OPBase, child: OPBase) : this(name, expression) {
        children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name.name) + children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return expression.getRequiredVariableNames() + children[0].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPBind")
        res.addAttribute("name", name.name)
        res.addContent(XMLElement("LocalValue").addContent(expression.toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
