package lupos.s16network

import kotlin.jvm.JvmField
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.CommuncationUnexpectedHeaderException
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase

class TripleStoreBulkImportDistributed(val query: Query, val graphName: String) {
    @JvmField
    val values = Array(3) { ResultSetDictionary.undefValue }

    @JvmField
    val accessedHosts = Array(TripleStoreLocalBase.distinctIndices.size) { mutableMapOf<ServerCommunicationKnownHost, ServerCommunicationImportHelper>() }
    fun insert(si: Value, pi: Value, oi: Value) {
        values[0] = si
        values[1] = pi
        values[2] = oi
        for (i in 0 until TripleStoreLocalBase.distinctIndices.size) {
            val idx = TripleStoreLocalBase.distinctIndices[i]
            val host = ServerCommunicationDistribution.getHostForFullTriple(values, query, idx)
            var helper = accessedHosts[i][host]
            val helper2: ServerCommunicationImportHelper
            if (helper == null) {
                val conn = ServerCommunicationConnectionPool.openSocket(host)
                helper2 = ServerCommunicationImportHelper(conn, conn.input, conn.output)
                accessedHosts[i][host] = helper2
                var builder = ByteArrayBuilder()
                builder.writeInt(ServerCommunicationHeader.IMPORT.ordinal)
                builder.writeLong(query.transactionID)
                builder.writeInt(idx.ordinal)
                builder.writeString(graphName)
                helper2.output.writeByteArray(builder)
                helper2.output.flush()
            } else {
                helper2 = helper
            }
            ServerCommunicationTransferTriples.sendTriples(si, pi, oi, query.dictionary, helper2.builder) {
                helper2.output.writeByteArray(it)
                helper2.output.flush()
            }
        }
    }

    fun finishImport() {
        for (i in 0 until TripleStoreLocalBase.distinctIndices.size) {
            val idx = TripleStoreLocalBase.distinctIndices[i]
            for ((host, helper) in accessedHosts[i]) {
                if (helper.builder.size > 0) {
                    helper.output.writeByteArray(helper.builder)
                    helper.output.flush()
                }
                var builder = ByteArrayBuilder()
                builder.writeInt(ServerCommunicationHeader.RESPONSE_FINISHED.ordinal)
                builder.writeLong(query.transactionID)
                helper.output.writeByteArray(builder)
                helper.output.flush()
                val response = helper.input.readByteArray()
                val header3 = ServerCommunicationHeader.values()[response.readInt()]
                if (header3 != ServerCommunicationHeader.RESPONSE_FINISHED) {
                    throw CommuncationUnexpectedHeaderException("$header3")
                }
                ServerCommunicationConnectionPool.closeSocketClean(host, helper.conn)
            }
        }
    }
}
