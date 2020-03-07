package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID


abstract class AOPConstantString (query:Query,operatorID: EOperatorID,classname: String,val delimiter: String,val content: String): AOPConstant(query,operatorID,classname) {
    override operator fun compareTo(other: AOPConstant): Int {
        require(other is AOPConstantString)
        return valueToString()!!.compareTo(other.valueToString()!!)
    }
}
