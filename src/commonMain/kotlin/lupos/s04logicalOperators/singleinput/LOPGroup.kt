package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter


class LOPGroup(var by: List<LOPVariable>) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())
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
            return children[0].getRequiredVariableNames() + bindings!!.getRequiredVariableNames()
        else
            return children[0].getRequiredVariableNames()
    }

    constructor(by: List<LOPVariable>, child: OPBase) : this(by) {
        children[0] = child
    }

    constructor(by: List<LOPVariable>, bindings: OPBase?, child: OPBase) : this(by) {
        this.bindings = bindings
        children[0] = child
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
        res.addContent(childrenToXML())
        return res
    }
}
