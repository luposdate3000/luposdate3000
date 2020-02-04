package lupos.s03buildOperatorGraph.singleinput

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase


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
