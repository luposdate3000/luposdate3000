package lupos.s2buildOperatorGraph.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.*
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.data.*

class LOPBind(val name: LOPVariable, val expression: OPBase) : LOPSingleInputBase() {
    constructor(name: LOPVariable, expression: OPBase, child: OPBase) : this(name, expression) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name.name) + child.getRequiredVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return expression.getRequiredVariableNames() + child.getRequiredVariableNames()
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n$indentation\tvariable:\n${name.toString("$indentation\t\t")}$indentation\texpression:\n${expression.toString("$indentation\t\t")}$indentation\tchild:\n${child.toString("$indentation\t\t")}"
    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPBind")
        res.addAttribute("name", name.name)
        res.addContent(XMLElement("LocalValue").addContent(expression.toXMLElement()))
        res.addContent(child.toXMLElement())
        return res
    }
}
