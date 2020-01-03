package lupos.s2operatorgraph.data

import lupos.s2operatorgraph.LOPBase

class LOPIRI(val iri: String) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${iri}'\n"
}