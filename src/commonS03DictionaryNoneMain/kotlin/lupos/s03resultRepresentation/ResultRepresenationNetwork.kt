package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

object ResultRepresenationNetwork {
    fun toNetworkPackage(query: POPBase): ByteArray {
        throw Exception("not implemented")
    }

    fun fromNetworkPackage(query: Query, resultSet: ResultSet, data: ByteArray): POPBase {
        throw Exception("not implemented")
    }
}
