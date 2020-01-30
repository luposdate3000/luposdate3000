package lupos.s03buildOperatorGraph.singleinput

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPNOOP
import lupos.s03buildOperatorGraph.singleinput.LOPOptional
import lupos.s03buildOperatorGraph.singleinput.LOPProjection
import lupos.s03buildOperatorGraph.singleinput.LOPRename
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase


class LOPSort(val asc: Boolean, var by: OPBase) : LOPSingleInputBase() {
    constructor(asc: Boolean, by: OPBase, child: OPBase) : this(asc, by) {
        this.child = child
    }


    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPSort")
        res.addAttribute("by", (by as LOPVariable).name)
        if (asc)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(child.toXMLElement())
        return res
    }
}
