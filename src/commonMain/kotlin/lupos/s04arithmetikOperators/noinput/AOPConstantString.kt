package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.singleinput.AOPAggregation
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCall
import lupos.s04arithmetikOperators.singleinput.AOPNot



abstract class AOPConstantString : AOPConstant() {
    override val operatorID = EOperatorID.AOPConstantStringID
    override val classname = "AOPConstantString"
    abstract val content: String
    abstract val delimiter: String
}
