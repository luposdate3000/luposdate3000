package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase

abstract class LOPSingleInputBase() : LOPBase() {
    var child: OPBase = LOPNOOP()

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

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}
