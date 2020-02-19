package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPGroup(var by: List<LOPVariable>) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing(),OPNothing())

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (v in by)
            tmp.add(v.name)
            return tmp + children[1].getProvidedVariableNames()
        return tmp
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        require(additionalProvided.isEmpty())
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by)
            localRequire.add(v.name)
            localRequire += children[1].getRequiredVariableNames()
        for (c in children)
            c.syntaxVerifyAllVariableExists(localProvide, autocorrect)
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("undefined Variable")
            }
        }
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    constructor(by: List<LOPVariable>, child: OPBase) : this(by) {
        children[0] = child
    }

    constructor(by: List<LOPVariable>, bindings: OPBase?, child: OPBase) : this(by) {
	if(bindings!=null)
        children[1] = bindings
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
        if (children[1] !is OPNothing)
            xmlbindings.addContent(children[1].toXMLElement())
        res.addContent(childrenToXML())
        return res
    }
}
