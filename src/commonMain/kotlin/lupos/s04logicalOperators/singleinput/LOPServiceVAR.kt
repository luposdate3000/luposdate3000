package lupos.s04logicalOperators.singleinput
import lupos.s04logicalOperators.noinput.OPNothing

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
import lupos.s04logicalOperators.singleinput.LOPServiceIRI


class LOPServiceVAR(val name: String, val silent: Boolean, constraint: OPBase) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing(), constraint)

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
