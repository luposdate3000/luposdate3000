package lupos.s2operatorgraph.data

import lupos.s2operatorgraph.LOPBase

class LOPVariable(var name: String) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${name}'\n"
}