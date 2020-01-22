package lupos.s2buildOperatorGraph.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.OPBase

class LOPOptional() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPOptional")
        res.addContent(child.toXMLElement())
        return res
    }
}
