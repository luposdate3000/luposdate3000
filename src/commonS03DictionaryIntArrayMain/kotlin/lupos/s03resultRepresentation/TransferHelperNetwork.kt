package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ENetworkMessageType
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s14endpoint.Endpoint

@UseExperimental(ExperimentalStdlibApi::class)
class TransferHelperNetwork {
    companion object {
        fun processBinary(d: ByteArray): ByteArray {
            var res = ByteArray(0)
            val data = DynamicByteArray(d)
            val transactionID = data.getNextLong()
            val query = Query(transactionID = transactionID)
            var header = ENetworkMessageType.values()[data.getNextInt()]
            while (header != ENetworkMessageType.FINISH) {
                val count = data.getNextInt()
                when (header) {
                    ENetworkMessageType.DICTIONARY_ENTRY -> {
                        for (i in 0 until count)
                            query.dictionary.createValue(ValueDefinition(data.getNextString()))
                    }
                    ENetworkMessageType.TRIPLE_ADD -> {
                        for (i in 0 until count) {
                            val graphName = query.dictionary.getValue(data.getNextInt())!!.valueToString()!!
                            val params = Array(3) { query.dictionary.getValue(data.getNextInt()) }
                            val idx = EIndexPattern.values()[data.getNextInt()]
                            try {
                                Endpoint.process_local_triple_add(query, graphName, params, idx)
                            } catch (e: Throwable) {
                                e.printStackTrace()
                                res += e.toString().encodeToByteArray()
                            }
                        }
                    }
                    ENetworkMessageType.GRAPH_CLEAR_ALL -> {
                        Endpoint.process_local_graph_clear_all(query)
                    }
                }
                header = ENetworkMessageType.values()[data.getNextInt()]
            }
            return res
        }
    }

    @JvmField
    val data = DynamicByteArray()
    @JvmField
    var lastHeader = ENetworkMessageType.NONE
    @JvmField
    var lastCounterPos = 0
    @JvmField
    var lastCounterValue = 0
    @JvmField
    var lastDictionaryKey: Value? = null
    val query: Query

    constructor(query: Query) {
        this.query = query
        data.appendLong(query.transactionID)
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

    fun createDictionaryValue(s: String?): Value {
        val tmp: Value
        if (s != null)
            tmp = query.dictionary.createValue(ValueDefinition(s))
        else
            return ResultSetDictionary.undefValue
        if (lastDictionaryKey == null || tmp > lastDictionaryKey!!) {
            enforceHeader(ENetworkMessageType.DICTIONARY_ENTRY)
            data.appendString(s)
            lastCounterValue++
        }
        return tmp
    }

    fun graphClearAll() {
        enforceHeader(ENetworkMessageType.GRAPH_CLEAR_ALL)
    }

    fun addTriple(graphName: String, params: Array<ValueDefinition>, idx: EIndexPattern) {
        val gv = createDictionaryValue(graphName)
        val params = Array(3) { createDictionaryValue(params[it].valueToString()) }
        enforceHeader(ENetworkMessageType.TRIPLE_ADD)
        data.appendInt(gv)
        for (p in params)
            data.appendInt(p)
        data.appendInt(idx.ordinal)
        lastCounterValue++
    }

    fun finish(): DynamicByteArray {
        enforceHeader(ENetworkMessageType.FINISH)
        data.finish()
        return data
    }
}
