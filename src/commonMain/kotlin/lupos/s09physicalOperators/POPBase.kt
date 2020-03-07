package lupos.s09physicalOperators
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase
import lupos.s03resultRepresentation.*

abstract class POPBase(query:Query, 
operatorID: EOperatorID,
 classname: String,
  resultSet: ResultSet,
 children: Array<OPBase>) :
 OPBase(query,operatorID,classname,resultSet,children) {
}
