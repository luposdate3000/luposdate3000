package lupos.s04logicalOperators.singleinput
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.LOPBase

import lupos.s00misc.XMLElement
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
import lupos.s04logicalOperators.singleinput.LOPServiceVAR


class LOPSort(val asc: Boolean, var by: OPBase) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(asc: Boolean, by: OPBase, child: OPBase) : this(asc, by) {
        this.children[0] = child
    }


    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPSort")
        res.addAttribute("by", (by as LOPVariable).name)
        if (asc)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(childrenToXML())
        return res
    }
}
