package lupos.s03resultRepresentation
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s09physicalOperators.POPBase


object ResultRepresenationNetwork {
    fun toNetworkPackage(query: POPBase): ByteArray {
        throw Exception("not implemented")
    }

    fun fromNetworkPackage(dictionary: ResultSetDictionary, data: ByteArray): POPBase {
        throw Exception("not implemented")
    }
}
