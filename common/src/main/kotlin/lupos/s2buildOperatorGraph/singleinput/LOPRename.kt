package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.OPBase

class LOPRename(val nameTo: OPBase, val nameFrom: OPBase) : LOPSingleInputBase() {
    constructor(nameTo: OPBase, nameFrom: OPBase, child: OPBase) : this(nameTo, nameFrom) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n$indentation\tnameTo:\n${nameTo.toString("$indentation\t\t")}$indentation\t´nameFrom:\n${nameFrom.toString("$indentation\t\t")}$indentation\tchild:\n${child.toString("$indentation\t\t")}"
}
