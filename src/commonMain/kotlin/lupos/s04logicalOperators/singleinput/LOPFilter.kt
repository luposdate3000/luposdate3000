package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPFilter(val filter: LOPExpression) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(filter: LOPExpression, child: OPBase) : this(filter) {
        children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames() + filter.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPFilter")
        res.addContent(XMLElement("LocalFilter").addContent(filter.toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
