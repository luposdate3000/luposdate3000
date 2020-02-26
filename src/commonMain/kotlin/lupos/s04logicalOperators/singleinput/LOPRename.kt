package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.noinput.OPNothing


class LOPRename(val nameTo: AOPVariable, val nameFrom: AOPVariable) : LOPBase() {
    override val operatorID = EOperatorID.LOPRenameID
    override val classname = "LOPRename"
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(nameTo: AOPVariable, nameFrom: AOPVariable, child: OPBase) : this(nameTo, nameFrom) {
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
        val res = (children[0].getProvidedVariableNames() - nameFrom.name + nameTo.name).distinct()
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = listOf<String>(nameFrom.name)
        return res
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("nameTo", nameTo.name).addAttribute("nameFrom", nameFrom.name)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPRename)
            return false
        if (nameTo != other.nameTo)
            return false
        if (nameFrom != other.nameFrom)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
