package lupos.s04logicalOperators.singleinput
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPSingleInputBase



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
