package lupos.s2operatorgraph.singleinput.modifiers

import lupos.s2operatorgraph.OPBase
import lupos.s2operatorgraph.singleinput.LOPSingleInputBase

class LOPLimit(val limit: Int) : LOPSingleInputBase() {
    constructor(limit: Int, child: OPBase) : this(limit) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${limit}'\n" + child.toString("${indentation}\t")
}