package lupos.s16network

import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.network.sockets.Socket
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.core.Output.*
import java.net.InetSocketAddress
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import  kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChannel
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.iterator.RowIteratorBuf
import lupos.s04logicalOperators.iterator.RowIteratorChildIterator
import lupos.s04logicalOperators.iterator.RowIteratorMerge
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.*

class TripleStoreBulkImportDistributed(val query: Query, val graphName: String) {
    val values = Array(3) { ResultSetDictionary.undefValue }
    val accessedHosts = Array(TripleStoreLocalBase.distinctIndices.size) { mutableMapOf<ServerCommunicationKnownHost, ImportHelper>() }

    class ImportHelper(val socket: Socket, val input: ByteReadChannel, val output: ByteWriteChannel, val builder:ByteArrayBuilder=ByteArrayBuilder()) {
    }

    suspend fun insert(si: Value, pi: Value, oi: Value) {
        values[0] = si
        values[1] = pi
        values[2] = oi
        for (i in 0 until TripleStoreLocalBase.distinctIndices.size) {
            val idx = TripleStoreLocalBase.distinctIndices[i]
            val host = ServerCommunicationDistribution.getHostForFullTriple(values, query, idx)
            var helper = accessedHosts[i][host]
            val helper2: ImportHelper
            if (helper == null) {
println("Bulk open new socket")
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                helper2 = ImportHelper(socket, socket.openReadChannel(), socket.openWriteChannel())
                accessedHosts[i][host] = helper2
                var builder = ByteArrayBuilder()
                builder.writeInt(ServerCommunicationHeader.IMPORT.ordinal)
                builder.writeLong(query.transactionID)
                builder.writeInt(idx.ordinal)
                builder.writeString(graphName)
println("Bulk going to write packet")
                helper2.output.writeByteArray(builder)
                helper2.output.flush()
            } else {
                helper2 = helper
            }
println("Bulk appending data")
                        ServerCommunicationTransferTriples.sendTriples(si,pi,oi,query.dictionary,helper2.builder) {
                            helper2.output.writeByteArray(it)
                            helper2.output.flush()
                        }
        }
    }

    suspend fun finishImport() {
println("Bulk finishing")
        for (i in 0 until TripleStoreLocalBase.distinctIndices.size) {
            val idx = TripleStoreLocalBase.distinctIndices[i]
            for ((host, helper) in accessedHosts[i]) {
if(helper.builder.size>0){
helper.output.writeByteArray(helper.builder)
                            helper.output.flush()
}
                var builder = ByteArrayBuilder()
                builder.writeInt(ServerCommunicationHeader.RESPONSE_FINISHED.ordinal)
                builder.writeLong(query.transactionID)
println("Bulk sending finish-signal")
                helper.output.writeByteArray(builder)
                helper.output.flush()
                val response = helper.input.readByteArray()
                val header3 = ServerCommunicationHeader.values()[response.readInt()]
                if (header3 != ServerCommunicationHeader.RESPONSE_FINISHED) {
                    throw Exception("unexpected result $header3")
                }
println("Bulk close a socket")
                helper.socket.close()
            }
        }
    }
}
