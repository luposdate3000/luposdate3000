package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID


abstract class AOPConstantString : AOPConstant() {
    override val classname = "AOPConstantString"
    abstract val content: String
    abstract val delimiter: String
override operator fun compareTo(other: AOPConstant): Int{
require(other is AOPConstantString)
return valueToString()!!.compareTo(other.valueToString()!!)
}
}
