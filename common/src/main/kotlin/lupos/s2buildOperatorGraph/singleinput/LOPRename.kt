package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable

class LOPRename(val nameTo: LOPVariable, val nameFrom: LOPVariable) : LOPSingleInputBase() {
    constructor(nameTo: LOPVariable, nameFrom: LOPVariable, child: OPBase) : this(nameTo, nameFrom) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        val variables = child.getProvidedVariableNames()
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
        val variables = child.getProvidedVariableNames()
        for (v in variables) {
            res.add(v)
        }
        res.add(nameFrom.name)
        return res
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n$indentation\tnameTo:\n${nameTo.toString("$indentation\t\t")}$indentation\t´nameFrom:\n${nameFrom.toString("$indentation\t\t")}$indentation\tchild:\n${child.toString("$indentation\t\t")}"
}
