package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.OPBase

class LOPMakeBooleanResult() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }
}