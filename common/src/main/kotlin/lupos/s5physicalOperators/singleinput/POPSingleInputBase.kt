package lupos.s5physicalOperators.singleinput

import lupos.s5physicalOperators.POPBase

open abstract class POPSingleInputBase(val child: POPBase) : POPBase() {

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"

}
