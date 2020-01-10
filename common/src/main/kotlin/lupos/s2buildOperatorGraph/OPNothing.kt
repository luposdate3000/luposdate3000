package lupos.s2buildOperatorGraph

import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase

class OPNothing() : OPBase() {

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}"
}
