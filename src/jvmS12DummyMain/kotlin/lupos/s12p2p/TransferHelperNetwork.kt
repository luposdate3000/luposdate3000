package lupos.s12p2p
import lupos.s00misc.EOperatorID

import com.soywiz.korio.stream.AsyncStream
import com.soywiz.korio.stream.AsyncStreamBase
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ENetworkMessageType
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s14endpoint.Endpoint


class TransferHelperNetwork : AsyncStreamBase {
    companion object {
        fun processBinary(d: ByteArray): ByteArray {
            var res = ByteArray(0)
            val dictionary = ResultSetDictionary()
            val data = DynamicByteArray(d)
            val transactionID = data.getNextLong()
            var header = ENetworkMessageType.values()[data.getNextInt()]
            while (header != ENetworkMessageType.FINISH) {
                val count = data.getNextInt()
                when (header) {
                    ENetworkMessageType.DICTIONARY_ENTRY -> {
                        for (i in 0 until count)
                            dictionary.createValue(data.getNextString())
                    }
                    ENetworkMessageType.TRIPLE_ADD -> {
                        for (i in 0 until count) {
                            val graphName = dictionary.getValue(data.getNextInt())!!
                            val s = dictionary.getValue(data.getNextInt())!!
                            val p = dictionary.getValue(data.getNextInt())!!
                            val o = dictionary.getValue(data.getNextInt())!!
                            val idx = EIndexPattern.values()[data.getNextInt()]
                            try {
                                Endpoint.process_local_triple_add(graphName, transactionID, s, p, o, idx)
                            } catch (e: Throwable) {
                                e.printStackTrace()
                                res += e.toString().toByteArray()
                            }
                        }
                    }
                }
                header = ENetworkMessageType.values()[data.getNextInt()]
            }
            return res
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
        data.appendInt(gv)
        data.appendInt(sv)
        data.appendInt(pv)
        data.appendInt(ov)
        data.appendInt(idx.ordinal)
        lastCounterValue++
    }

    override suspend fun read(position: Long, buffer: ByteArray, offset: Int, len: Int): Int {
        if (position > data.pos)
            return 0
        if (position + len > data.pos) {
            data.data.copyInto(buffer, offset, position.toInt(), data.pos)
            return data.pos - position.toInt()
        }
        data.data.copyInto(buffer, offset, position.toInt(), position.toInt() + len)
        return len
    }

    override suspend fun getLength(): Long {
        return data.pos.toLong()
    }

    fun finish(): AsyncStream {
        enforceHeader(ENetworkMessageType.FINISH)
        data.finish()
        return AsyncStream(this)
    }
}

