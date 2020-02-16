package lupos.s03resultRepresentation

import kotlinx.coroutines.*
import lupos.s00misc.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s09physicalOperators.POPBase


object ResultRepresenationNetwork {
    fun toNetworkPackage(query: POPBase): ByteArray {
	throw Exception("not implemented")
    }

    fun fromNetworkPackage(dictionary: ResultSetDictionary, data: ByteArray): POPBase {
	throw Exception("not implemented")
    }
}
