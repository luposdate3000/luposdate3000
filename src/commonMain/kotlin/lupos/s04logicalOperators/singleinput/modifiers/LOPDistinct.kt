package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPSingleInputBase



class LOPDistinct() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPDistinct")
        res.addContent(child.toXMLElement())
        return res
    }
}
