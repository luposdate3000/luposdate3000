package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.OPNothing

class LOPSubGroup() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
	this.child=child
    }
}
