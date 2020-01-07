package lupos.s2operatorgraph.singleinput.modifiers

import lupos.s2operatorgraph.OPBase
import lupos.s2operatorgraph.singleinput.LOPSingleInputBase

class LOPReduced() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }
}