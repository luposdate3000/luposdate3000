package lupos.s09physicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class POPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, children) {
}
