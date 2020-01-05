package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.OPBase

class LOPBind(val name: OPBase, val expression: OPBase) : LOPSingleInputBase() {
    constructor(name: OPBase, expression: OPBase, child: OPBase) : this(name, expression) {
        this.child = child
    }
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n$indentation\tvariable:\n${name.toString("$indentation\t\t")}$indentation\texpression:\n${expression.toString("$indentation\t\t")}$indentation\tchild:\n${child.toString("$indentation\t\t")}"
}
