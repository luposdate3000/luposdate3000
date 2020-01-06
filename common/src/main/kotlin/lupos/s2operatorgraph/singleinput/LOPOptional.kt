package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.OPBase

class LOPOptional() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}