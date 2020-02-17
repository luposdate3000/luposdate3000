package lupos.s12p2p

import com.soywiz.korio.stream.AsyncStream
import com.soywiz.korio.stream.AsyncStreamBase
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ENetworkMessageType
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value


class TransferHelperNetwork : AsyncStreamBase {
    companion object {
        fun processBinary(d: ByteArray): ByteArray {
            throw Exception("not implemented")
        }
    }

    val dictionary = ResultSetDictionary()
    val data = DynamicByteArray()
    var lastHeader = ENetworkMessageType.NONE
    var lastCounterPos = 0
    var lastCounterValue = 0
    var lastDictionaryKey: Value? = null

    constructor(transactionID: Long) {
        throw Exception("not implemented")
    }

    fun addTriple(graphName: String, s: String, p: String, o: String, idx: EIndexPattern) {
        throw Exception("not implemented")
    }

    override suspend fun read(position: Long, buffer: ByteArray, offset: Int, len: Int): Int {
        throw Exception("not implemented")
    }

    override suspend fun getLength(): Long {
        throw Exception("not implemented")
    }

    fun finish(): AsyncStream {
        throw Exception("not implemented")
    }
}

