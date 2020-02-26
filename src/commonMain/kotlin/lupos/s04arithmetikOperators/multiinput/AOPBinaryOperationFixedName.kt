package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.EOperatorID

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


abstract class AOPBinaryOperationFixedName() : AOPBase() {
    override val operatorID=EOperatorID.AOPBinaryOperationFixedNameID
    override val classname = "AOPBinaryOperationFixedName"
}
