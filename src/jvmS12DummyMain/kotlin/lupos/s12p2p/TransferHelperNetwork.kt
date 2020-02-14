package lupos.s12p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpClient
import com.soywiz.korio.net.URL
import com.soywiz.korio.stream.*
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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
            val dictionary = ResultSetDictionary()
            val data = DynamicByteArray(d)
            val transactionID = data.getNextLong()
            var header = ENetworkMessageType.values()[data.getNextInt()]
            while (header != ENetworkMessageType.FINISH) {
                val count = data.getNextInt()
                when (header) {
                    ENetworkMessageType.NONE -> {
                    }
                    ENetworkMessageType.FINISH -> {
                    }
                    ENetworkMessageType.DICTIONARY_ENTRY -> {
                        for (i in 0 until count) {
                            dictionary.createValue(data.getNextString())
                        }
                    }
                    ENetworkMessageType.TRIPLE_ADD -> {
                        for (i in 0 until count) {
                            val graphName = dictionary.getValue(data.getNextLong())!!
                            val s = dictionary.getValue(data.getNextLong())!!
                            val p = dictionary.getValue(data.getNextLong())!!
                            val o = dictionary.getValue(data.getNextLong())!!
                            val idx = EIndexPattern.values()[data.getNextInt()]
                            Endpoint.process_local_triple_add(graphName, transactionID, s, p, o, idx)
                        }
                    }
                }
                header = ENetworkMessageType.values()[data.getNextInt()]
            }
            return ByteArray(0)
        }
    }

    val dictionary = ResultSetDictionary()
    val data = DynamicByteArray()
    var lastHeader = ENetworkMessageType.NONE
    var lastCounterPos = 0
    var lastCounterValue = 0
    var lastDictionaryKey: Value? = null

    constructor(transactionID: Long) {
        data.appendLong(transactionID)
    }

    fun enforceHeader(h: ENetworkMessageType) {
        if (lastHeader != h) {
            if (lastCounterValue > 0)
                data.setInt(lastCounterValue, lastCounterPos)
            lastCounterValue = 0
            lastHeader = h
            data.appendInt(h.ordinal)
            if (h != ENetworkMessageType.FINISH)
                lastCounterPos = data.appendSpace(4)
        }
    }

    fun createDictionaryValue(s: String): Value {
        val tmp = dictionary.createValue(s)
        if (lastDictionaryKey == null || tmp > lastDictionaryKey!!) {
            enforceHeader(ENetworkMessageType.DICTIONARY_ENTRY)
            data.appendString(s)
            lastCounterValue++
        }
        return tmp
    }

    fun addTriple(graphName: String, s: String, p: String, o: String, idx: EIndexPattern) {
        val gv = createDictionaryValue(graphName)
        val sv = createDictionaryValue(s)
        val pv = createDictionaryValue(p)
        val ov = createDictionaryValue(o)
        enforceHeader(ENetworkMessageType.TRIPLE_ADD)
        data.appendLong(gv)
        data.appendLong(sv)
        data.appendLong(pv)
        data.appendLong(ov)
        data.appendInt(idx.ordinal)
        lastCounterValue++
    }

    override suspend fun read(position: Long, buffer: ByteArray, offset: Int, len: Int): Int {
        data.data.copyInto(buffer, position.toInt(), offset, len)
        return len
    }

    fun finish(): AsyncStream {
        enforceHeader(ENetworkMessageType.FINISH)
        val binary = data.finish()
        return AsyncStream(this)
    }
}

