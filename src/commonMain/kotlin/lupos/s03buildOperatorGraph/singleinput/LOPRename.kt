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
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase


class LOPRename(val nameTo: LOPVariable, val nameFrom: LOPVariable) : LOPSingleInputBase() {
    constructor(nameTo: LOPVariable, nameFrom: LOPVariable, child: OPBase) : this(nameTo, nameFrom) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        val variables = child.getProvidedVariableNames()
        for (v in variables) {
            if (v == nameFrom.name)
                res.add(nameTo.name)
            else
                res.add(v)
        }
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        val variables = child.getProvidedVariableNames()
        for (v in variables) {
            res.add(v)
        }
        res.add(nameFrom.name)
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPRename")
        res.addAttribute("nameTo", nameTo.name)
        res.addAttribute("nameFrom", nameFrom.name)
        res.addContent(child.toXMLElement())
        return res
    }
}
