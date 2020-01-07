package lupos.s2buildOperatorGraph.multiinput

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPJoin(first: OPBase, val second: OPBase, val optional: Boolean) : LOPSingleInputBase(first) {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} optional: ${optional}\n${child.toString("$indentation\t")}${second.toString("$indentation\t")}"
}