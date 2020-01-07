package lupos.s2buildOperatorGraph.singleinput.modifiers

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPDistinct() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }
}