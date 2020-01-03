package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.OPBase

class LOPResult() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }
}