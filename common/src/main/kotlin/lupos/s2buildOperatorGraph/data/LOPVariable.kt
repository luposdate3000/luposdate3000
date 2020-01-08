package lupos.s2buildOperatorGraph.data

import lupos.s2buildOperatorGraph.LOPBase

class LOPVariable(var name: String) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '$name'\n"
}
