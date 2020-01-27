package lupos.s2buildOperatorGraph.singleinput

import lupos.s00misc.XMLElement

import lupos.s2buildOperatorGraph.data.LOPVariable

import lupos.s2buildOperatorGraph.OPBase

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
