package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPServiceVAR : LOPBase {
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    val name: String
    val silent: Boolean

    constructor(name: String, silent: Boolean, constraint: OPBase) : super() {
        this.name = name
        this.silent = silent
        children[1] = constraint
    }

    constructor(name: String, silent: Boolean, constraint: OPBase, child: OPBase) : this(name, silent, constraint) {
        this.children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[1].getProvidedVariableNames() + children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPService")
        res.addAttribute("name", name)
        res.addAttribute("silent", "" + silent)
        res.addContent(XMLElement("constraint").addContent(children[1].toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
