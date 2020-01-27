package lupos.s2buildOperatorGraph.singleinput

import lupos.s00misc.XMLElement


import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable

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
