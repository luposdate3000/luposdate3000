package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.OPBase

class LOPBind(val name: OPBase, val expression: OPBase) : LOPSingleInputBase() {
    constructor(name: OPBase, expression: OPBase, child: OPBase) : this(name, expression) {
        this.child = child
    }
}