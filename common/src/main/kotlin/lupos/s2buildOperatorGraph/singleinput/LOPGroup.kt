package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.OPBase
import lupos.s2operatorgraph.data.LOPVariable

class LOPGroup(var by: LOPVariable) : LOPSingleInputBase() {
    constructor(by: LOPVariable, child: OPBase) : this(by) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tvariable:\n${by.toString("${indentation}\t\t")}${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}
