package lupos.s04logicalOperators.multiinput
import lupos.s04logicalOperators.LOPBase

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase


class LOPMinus(first: OPBase, second: OPBase) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(first, second)
    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames() + children[1].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPMinus")
        res.addContent(childrenToXML())
        return res
    }
}
