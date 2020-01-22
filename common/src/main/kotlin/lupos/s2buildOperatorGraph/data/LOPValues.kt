package lupos.s2buildOperatorGraph.data

import lupos.misc.*
import lupos.s2buildOperatorGraph.LOPBase

class LOPValues(val variables: List<LOPVariable>, val values: List<LOPExpression>) : LOPBase() {
    override fun getProvidedVariableNames(): List<String> {
        var res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName}\n${indentation}\tvariables:\n"
        for (v in variables) {
            res += v.toString("${indentation}\t\t")
        }
        res += "$indentation\tvalues:\n"
        for (v in values) {
            res += v.toString("${indentation}\t\t")
        }
        return res
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPValues")
    }
}
