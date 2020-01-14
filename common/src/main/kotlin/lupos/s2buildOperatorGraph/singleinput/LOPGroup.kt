package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s2buildOperatorGraph.data.LOPVariable

class LOPGroup(var by: List<LOPVariable>) : LOPSingleInputBase() {
    var bindings: OPBase? = null

    constructor(by: List<LOPVariable>, child: OPBase) : this(by) {
        this.child = child
    }

    constructor(by: List<LOPVariable>, bindings: OPBase?, child: OPBase) : this(by) {
        this.bindings = bindings
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tvariable:\n${by.toString()}${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}
