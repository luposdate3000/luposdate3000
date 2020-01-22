package lupos.s2buildOperatorGraph.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s2buildOperatorGraph.data.LOPVariable

class LOPGroup(var by: List<LOPVariable>) : LOPSingleInputBase() {
    var bindings: OPBase? = null

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (v in by)
            tmp.add(v.name)
        if (bindings != null)
            return tmp + bindings!!.getProvidedVariableNames()
        return tmp
    }

    override fun getRequiredVariableNames(): List<String> {
        if (bindings != null)
            return child.getRequiredVariableNames() + bindings!!.getRequiredVariableNames()
        else
            return child.getRequiredVariableNames()
    }

    constructor(by: List<LOPVariable>, child: OPBase) : this(by) {
        this.child = child
    }

    constructor(by: List<LOPVariable>, bindings: OPBase?, child: OPBase) : this(by) {
        this.bindings = bindings
        this.child = child
    }

    override fun toString(indentation: String): String {
        var bindings: OPBase? = this.bindings
        if (bindings == null)
            bindings = LOPNOOP()
        return "${indentation}${this::class.simpleName}\n${indentation}\tvariable:\n${by.toString()}${indentation}\tbindings:\n${bindings.toString("${indentation}\t\t")}${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPGroup")
        var byString = ""
        for (b in by) {
            byString = byString + "," + b.name
        }
        res.addAttribute("by", byString)
        if (bindings != null)
            res.addContent(bindings!!.toXMLElement())
        res.addContent(child.toXMLElement())
        return res
    }
}
