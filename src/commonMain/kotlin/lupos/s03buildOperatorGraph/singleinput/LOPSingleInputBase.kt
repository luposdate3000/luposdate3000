package lupos.s03buildOperatorGraph.singleinput

import lupos.s03buildOperatorGraph.singleinput.LOPRename
import lupos.s03buildOperatorGraph.singleinput.LOPProjection
import lupos.s03buildOperatorGraph.singleinput.LOPOptional
import lupos.s03buildOperatorGraph.singleinput.LOPNOOP
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.OPNothing
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.LOPBase


abstract class LOPSingleInputBase() : LOPBase() {
    var child: OPBase = OPNothing()

    constructor(child: OPBase) : this() {
        this.child = child
    }

    fun setChild(child: OPBase): OPBase {
        this.child = child
        return child
    }

    fun getLatestChild(): LOPSingleInputBase {
        val c = child
        if (c is LOPSingleInputBase) {
            return c.getLatestChild()
        }
        return this
    }

}
