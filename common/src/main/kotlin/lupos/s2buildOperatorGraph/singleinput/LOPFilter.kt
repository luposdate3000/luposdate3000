package lupos.s2buildOperatorGraph.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPExpression

class LOPFilter(val filter: LOPExpression) : LOPSingleInputBase() {
    constructor(filter: LOPExpression, child: OPBase) : this(filter) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + filter.getRequiredVariableNames()
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tfilter:\n'${filter.toString("${indentation}\t\t")}'\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPFilter")
        res.addAttribute("expression", filter.child.toString())
        res.addContent(child.toXMLElement())
        return res
    }
}
