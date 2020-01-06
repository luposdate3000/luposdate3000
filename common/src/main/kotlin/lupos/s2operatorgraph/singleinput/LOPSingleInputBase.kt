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

    fun getLatestChild(): LOPSingleInputBase {
        val c = child
        if (c is LOPSingleInputBase) {
            return c.getLatestChild()
        }
        return this
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"

}
