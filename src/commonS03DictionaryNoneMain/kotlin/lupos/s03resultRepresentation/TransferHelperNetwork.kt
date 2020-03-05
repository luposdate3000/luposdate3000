package lupos.s03resultRepresentation

import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ENetworkMessageType
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s14endpoint.Endpoint


class TransferHelperNetwork {
    companion object {
        fun processBinary(d: ByteArray): ByteArray {
            throw Exception("not implemented")
        }
    }

    constructor(transactionID: Long) {
        throw Exception("not implemented")
    }

    fun graphClearAll() {
        throw Exception("not implemented")
    }

    fun addTriple(graphName: String, s: AOPConstant, p: AOPConstant, o: AOPConstant, idx: EIndexPattern) {
        throw Exception("not implemented")
    }

    fun finish(): DynamicByteArray {
        throw Exception("not implemented")
    }
}

