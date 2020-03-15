package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


abstract class LOPBase(query: Query, operatorID: EOperatorID, classname: String, children: Array<OPBase>) :
        OPBase(query, operatorID, classname, ResultSet(ResultSetDictionary()), children) {
}
