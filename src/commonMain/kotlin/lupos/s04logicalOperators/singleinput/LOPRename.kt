package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection


class LOPRename(val nameTo: LOPVariable, val nameFrom: LOPVariable) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(nameTo: LOPVariable, nameFrom: LOPVariable, child: OPBase) : this(nameTo, nameFrom) {
        this.children[0] = child
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
        val res = mutableListOf<String>()
        val variables = children[0].getProvidedVariableNames()
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
        res.addContent(childrenToXML())
        return res
    }
}
