package lupos.s2operatorgraph.singleinput.modifiers

import lupos.s2operatorgraph.OPBase
import lupos.s2operatorgraph.singleinput.LOPSingleInputBase

class LOPOffset(val offset: Int) : LOPSingleInputBase() {
    constructor(offset: Int, child: OPBase) : this(offset) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${offset}'\n" + child.toString("${indentation}\t")
}