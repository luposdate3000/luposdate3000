package lupos.s2operatorgraph.multiinput

import lupos.s2operatorgraph.OPBase
import lupos.s2operatorgraph.singleinput.LOPSingleInputBase

class LOPUnion(first: OPBase, val second: OPBase) : LOPSingleInputBase(first) {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${child.toString("$indentation\t")}${second.toString("$indentation\t")}"

}