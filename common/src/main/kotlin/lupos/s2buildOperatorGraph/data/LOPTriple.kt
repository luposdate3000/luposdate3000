package lupos.s2buildOperatorGraph.data

import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase

class LOPTriple(val s: OPBase, val p: OPBase, val o: OPBase) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\t${s}${indentation}\t${p}${indentation}\t${o}"
}