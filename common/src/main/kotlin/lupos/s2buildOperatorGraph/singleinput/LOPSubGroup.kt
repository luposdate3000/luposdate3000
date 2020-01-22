package lupos.s2buildOperatorGraph.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.OPNothing

class LOPSubGroup() : LOPSingleInputBase() {
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
        val res = XMLElement("LOPSubgroup")
        res.addContent(child.toXMLElement())
        return res
    }
}
