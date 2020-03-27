package lupos.s09physicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

abstract class POPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, children) {
}
