package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
abstract class AOPBinaryOperationFixedName(query: Query, operatorID: EOperatorID,
                                           classname: String,
                                           children: Array<OPBase>) : AOPBase(query, operatorID, classname, children) {
}
