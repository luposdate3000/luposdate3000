package lupos.s03buildOperatorGraph.singleinput

import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s03buildOperatorGraph.singleinput.LOPOptional
import lupos.s03buildOperatorGraph.singleinput.LOPNOOP
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.data.LOPVariable

import lupos.s00misc.XMLElement


class LOPProjection(val variables: MutableList<LOPVariable> = mutableListOf()) : LOPSingleInputBase() {
    constructor(variables: MutableList<LOPVariable> = mutableListOf(), child: OPBase) : this(variables) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPProjection")
        val vars = XMLElement("LocalVariables")
        res.addContent(vars)
        for (v in variables)
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        res.addContent(child.toXMLElement())
        return res
    }
}
