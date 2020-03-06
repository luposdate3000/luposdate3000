package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID


abstract class AOPConstantString : AOPConstant() {
    override val classname = "AOPConstantString"
    abstract val content: String
    abstract val delimiter: String
}
