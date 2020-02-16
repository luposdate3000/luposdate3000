package lupos.s12p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpClient
import com.soywiz.korio.net.URL
import com.soywiz.korio.stream.*
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import lupos.s00misc.*
import lupos.s00misc.parseFromXml
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.EndpointImpl
import lupos.testMain


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

