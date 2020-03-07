package lupos.s04arithmetikOperators.multiinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.*

abstract class AOPBinaryOperationFixedName (query:Query,operatorID: EOperatorID,
classname: String, 
 children: Array<OPBase>): AOPBase(query,operatorID,classname,children) {
}
