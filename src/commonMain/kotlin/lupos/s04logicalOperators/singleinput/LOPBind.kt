package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPBind : LOPBase {
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    val name: LOPVariable

    constructor(name: LOPVariable, expression: OPBase) : super() {
        this.name = name
        children[1] = expression
    }

    constructor(name: LOPVariable, expression: OPBase, child: OPBase) : this(name, expression) {
        children[0] = child
    }

    override fun childrenToVerifyCount(): Int = 1

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name.name) + children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[1].getRequiredVariableNames() + children[0].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPBind")
        res.addAttribute("name", name.name)
        res.addContent(childrenToXML())
        return res
    }
}
