package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPUndef : AOPConstant() {
    override val operatorID = EOperatorID.AOPUndefID
    override val classname = "AOPUndef"
    override val children: Array<OPBase> = arrayOf()


    override fun valueToString(): String? = null
    override fun equals(other: Any?): Boolean {
        return other is AOPUndef
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPUndef to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPUndef to Int")
    }

    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPUndef to Boolean")
    }
}
