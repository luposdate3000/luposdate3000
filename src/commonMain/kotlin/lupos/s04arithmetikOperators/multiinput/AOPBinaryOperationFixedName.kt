package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


abstract class AOPBinaryOperationFixedName(query: Query, operatorID: EOperatorID,
                                           classname: String,
                                           children: Array<OPBase>) : AOPBase(query, operatorID, classname, children) {
}
