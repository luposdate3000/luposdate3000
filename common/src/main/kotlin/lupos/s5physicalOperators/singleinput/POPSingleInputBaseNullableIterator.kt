package lupos.s5physicalOperators.singleinput

import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPBaseNullableIterator

open abstract class POPSingleInputBaseNullableIterator(val child: POPBase) : POPBaseNullableIterator() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
}
