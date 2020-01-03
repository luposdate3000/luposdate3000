package lupos.s2operatorgraph.multiinput

import lupos.s2operatorgraph.LOPBase
import lupos.s2operatorgraph.OPBase

class LOPJoin(val first: OPBase, val second: OPBase) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${first.toString("$indentation\t")}${second.toString("$indentation\t")}"
}