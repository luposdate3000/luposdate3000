package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.LOPBase
import lupos.s2operatorgraph.OPBase

open class LOPSingleInputBase() : LOPBase() {
    var child: OPBase = OPBase()

    constructor(child: OPBase) : this() {
        this.child = child
    }

    fun setChild(child: OPBase): OPBase {
        this.child = child
        return child
    }
}
