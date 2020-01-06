package lupos.s2operatorgraph.data

import lupos.s2operatorgraph.LOPBase
import lupos.s2operatorgraph.OPBase

class LOPTriple(val s: OPBase, val p: OPBase, val o: OPBase) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\t${s}${indentation}\t${p}${indentation}\t${o}"
}