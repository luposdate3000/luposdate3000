package lupos.s03buildOperatorGraph.singleinput

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.LOPBase
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPNOOP
import lupos.s03buildOperatorGraph.singleinput.LOPOptional
import lupos.s03buildOperatorGraph.singleinput.LOPProjection


class LOPServiceVAR(val name: String, val silent: Boolean, var constraint: OPBase) : LOPSingleInputBase() {
    constructor(name: String, silent: Boolean, constraint: OPBase, child: OPBase) : this(name, silent, constraint) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return constraint.getProvidedVariableNames() + child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return constraint.getRequiredVariableNames() + child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPService")
        res.addAttribute("name", name)
        res.addAttribute("silent", "" + silent)
        res.addContent(XMLElement("constraint").addContent(constraint.toXMLElement()))
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
