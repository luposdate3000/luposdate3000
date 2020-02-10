package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPRename(val nameTo: LOPVariable, val nameFrom: LOPVariable) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(nameTo: LOPVariable, nameFrom: LOPVariable, child: OPBase) : this(nameTo, nameFrom) {
        this.children[0] = child
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        val localProvide = children[0].getProvidedVariableNames()
        val localRequire = listOf<String>(nameFrom.name)
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

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        val variables = children[0].getProvidedVariableNames()
        for (v in variables) {
            if (v == nameFrom.name)
                res.add(nameTo.name)
            else
                res.add(v)
        }
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        return listOf<String>(nameFrom.name)
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPRename")
        res.addAttribute("nameTo", nameTo.name)
        res.addAttribute("nameFrom", nameFrom.name)
        res.addContent(childrenToXML())
        return res
    }
}
