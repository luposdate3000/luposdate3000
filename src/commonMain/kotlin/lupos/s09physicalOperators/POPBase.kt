package lupos.s09physicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


abstract class POPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       resultSet: ResultSet,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, resultSet, children) {
}
