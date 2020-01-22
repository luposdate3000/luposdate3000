package lupos.s2buildOperatorGraph.multiinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPMinus(first: OPBase, var second: OPBase) : LOPSingleInputBase(first) {
    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames() + second.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + second.getRequiredVariableNames()
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${child.toString("$indentation\t")}${second.toString("$indentation\t")}"
    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPMinus")
        res.addContent(child.toXMLElement())
        res.addContent(second.toXMLElement())
        return res
    }
}
