package lupos.s2buildOperatorGraph.singleinput.modifiers

import lupos.misc.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPLimit(val limit: Int) : LOPSingleInputBase() {
    constructor(limit: Int, child: OPBase) : this(limit) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '$limit'\n" + child.toString("${indentation}\t")
    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPLimit")
        res.addAttribute("limit", "" + limit)
        res.addContent(child.toXMLElement())
        return res
    }
}
