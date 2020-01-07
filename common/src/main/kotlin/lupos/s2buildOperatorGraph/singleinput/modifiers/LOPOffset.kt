package lupos.s2buildOperatorGraph.singleinput.modifiers

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPOffset(val offset: Int) : LOPSingleInputBase() {
    constructor(offset: Int, child: OPBase) : this(offset) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${offset}'\n" + child.toString("${indentation}\t")
}