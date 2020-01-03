package lupos.s2operatorgraph.data

import lupos.s2operatorgraph.LOPBase

class LOPLiteral(val content: String, val delimiter: String) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${content}' '${delimiter}'\n"
}