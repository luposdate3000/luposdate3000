package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.EOperatorID


abstract class AOPConstantString : AOPConstant() {
    override val operatorID = EOperatorID.AOPConstantStringID
    override val classname = "AOPConstantString"
    abstract val content: String
    abstract val delimiter: String
}
