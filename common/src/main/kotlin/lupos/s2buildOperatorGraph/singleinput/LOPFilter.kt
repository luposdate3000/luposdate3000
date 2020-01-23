package lupos.s2buildOperatorGraph.singleinput

import lupos.misc.XMLElement

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPExpression

class LOPFilter(val filter: LOPExpression) : LOPSingleInputBase() {
    constructor(filter: LOPExpression, child: OPBase) : this(filter) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + filter.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPFilter")
        res.addContent(XMLElement("LocalFilter").addContent(filter.toXMLElement()))
        res.addContent(child.toXMLElement())
        return res
    }
}
