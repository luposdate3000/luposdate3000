package lupos.s03buildOperatorGraph.singleinput

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase


class LOPGroup(var by: List<LOPVariable>) : LOPSingleInputBase() {
    var bindings: OPBase? = null

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (v in by)
            tmp.add(v.name)
        if (bindings != null)
            return tmp + bindings!!.getProvidedVariableNames()
        return tmp
    }

    override fun getRequiredVariableNames(): List<String> {
        if (bindings != null)
            return child.getRequiredVariableNames() + bindings!!.getRequiredVariableNames()
        else
            return child.getRequiredVariableNames()
    }

    constructor(by: List<LOPVariable>, child: OPBase) : this(by) {
        this.child = child
    }

    constructor(by: List<LOPVariable>, bindings: OPBase?, child: OPBase) : this(by) {
        this.bindings = bindings
        this.child = child
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPGroup")
        val byxml = XMLElement("LocalBy")
        res.addContent(byxml)
        for (b in by)
            byxml.addContent(XMLElement("LocalVariable").addAttribute("name", b.name))
        val xmlbindings = XMLElement("LocalBindings")
        res.addContent(xmlbindings)
        if (bindings != null)
            xmlbindings.addContent(bindings!!.toXMLElement())
        res.addContent(child.toXMLElement())
        return res
    }
}
