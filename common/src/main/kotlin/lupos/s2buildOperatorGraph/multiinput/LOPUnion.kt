package lupos.s2buildOperatorGraph.multiinput

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPUnion(first: OPBase, val second: OPBase) : LOPSingleInputBase(first) {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${child.toString("$indentation\t")}${second.toString("$indentation\t")}"

}