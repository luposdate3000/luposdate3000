package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPGroup(var by: List<AOPVariable>) : LOPBase() {
    override val classname = "LOPGroup"
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    override fun childrenToVerifyCount() = 1

    override fun getProvidedVariableNames(): List<String> {
        val res = (children[1].getProvidedVariableNames() + Array(by.size) { by[it].name }).distinct()
        println("($classname)($uuid)getProvidedVariableNames $res")
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        res.addAll(children[1].getRequiredVariableNamesRecoursive())
        for (b in by)
            res.addAll(b.getRequiredVariableNames())
        println("($classname)($uuid)getRequiredVariableNames $res")
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        require(additionalProvided.isEmpty())
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by)
            localRequire.add(v.name)
        localRequire += children[1].getRequiredVariableNames()
        children[0].syntaxVerifyAllVariableExists(localProvide, autocorrect)
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("undefined Variable")
            }
        }
    }

    constructor(by: List<AOPVariable>, child: OPBase) : this(by) {
        children[0] = child
    }

    constructor(by: List<AOPVariable>, bindings: OPBase?, child: OPBase) : this(by) {
        if (bindings != null)
            children[1] = bindings
        children[0] = child
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPGroup")
        val byxml = XMLElement("LocalBy")
        res.addContent(byxml)
        for (b in by)
            byxml.addContent(XMLElement("LocalVariable").addAttribute("name", b.name))
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPGroup)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
