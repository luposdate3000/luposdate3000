package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class AOPBinaryOperationFixedName(query: IQuery, operatorID: EOperatorID,
                                           classname: String,
                                           children: Array<IOPBase>) : AOPBase(query, operatorID, classname, children)
