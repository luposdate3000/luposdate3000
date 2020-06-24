package lupos.s16network

import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.*

object ServerCommunicationReceive {
    fun start(hostname: String, port: Int, bootstrap: String? = null) {
        GlobalScope.launch {
            val server = ServerCommunicationConnectionPool.openServerSocket(hostname, port)
            while (true) {
                val socket = ServerCommunicationConnectionPool.accept(server)
                launch {
                    val input = socket.input
                    val output = socket.output
                    var readHeader = false
                    try {
                        while (true) {
                            readHeader = false
                            val packet = input.readByteArray()
                            val header = ServerCommunicationHeader.values()[packet.readInt()]
                            val transactionID = packet.readLong()
                            val query = Query(transactionID = transactionID)
                            readHeader = true
                            when (header) {
                                ServerCommunicationHeader.COMMIT -> {
                                    DistributedTripleStore.localStore.commit(query)
                                }
                                ServerCommunicationHeader.INSERT -> {
                                    val idx = EIndexPattern.values()[packet.readInt()]
                                    val graphName = packet.readString()
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
                                    while (true) {
                                        val packet2 = input.readByteArray()
                                        val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
                                        if (header2 != ServerCommunicationHeader.RESPONSE_TRIPLES) {
                                            require(header2 == ServerCommunicationHeader.RESPONSE_FINISHED)
                                            break
                                        }
                                        val data = ServerCommunicationTransferTriples.receiveTriples(packet2, nodeGlobalDictionary, 3, true, socket.localAddress.toString())[0]
                                        graph.modify(query, data, idx, EModifyType.INSERT)
                                    }
                                }
                                ServerCommunicationHeader.IMPORT -> {
                                    val idx = EIndexPattern.values()[packet.readInt()]
                                    val graphName = packet.readString()
                                    val bulk = TripleStoreBulkImport(query, graphName, idx)
                                    while (true) {
                                        println("Receive waiting_for RESPONSE_TRIPLES or RESPONSE_FINISHED")
                                        val packet2 = input.readByteArray()
                                        val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
                                        if (header2 != ServerCommunicationHeader.RESPONSE_TRIPLES) {
                                            require(header2 == ServerCommunicationHeader.RESPONSE_FINISHED)
                                            break
                                        }
                                        println("Receive processing RESPONSE_TRIPLES")
                                        ServerCommunicationTransferTriples.receiveTriples(packet2, nodeGlobalDictionary, socket.localAddress.toString(), bulk)
                                    }
                                    println("Receive finishing import")
                                    bulk.finishImport()
                                }
                                ServerCommunicationHeader.DELETE -> {
                                    val idx = EIndexPattern.values()[packet.readInt()]
                                    val graphName = packet.readString()
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
                                    while (true) {
                                        val packet2 = input.readByteArray()
                                        val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
                                        if (header2 != ServerCommunicationHeader.RESPONSE_TRIPLES) {
                                            require(header2 == ServerCommunicationHeader.RESPONSE_FINISHED)
                                            break
                                        }
                                        val data = ServerCommunicationTransferTriples.receiveTriples(packet2, nodeGlobalDictionary, 3, true, socket.localAddress.toString())[0]
                                        graph.modify(query, data, idx, EModifyType.DELETE)
                                    }
                                }
                                ServerCommunicationHeader.CLEAR_ALL_GRAPH -> {
                                    DistributedTripleStore.localStore.getDefaultGraph(query).clear()
                                    for (g in DistributedTripleStore.getGraphNames()) {
                                        DistributedTripleStore.dropGraph(query, g)
                                    }
                                }
                                ServerCommunicationHeader.CLEAR_GRAPH -> {
                                    val graphName = packet.readString()
                                    DistributedTripleStore.localStore.clearGraph(query, graphName)
                                }
                                ServerCommunicationHeader.CREATE_GRAPH -> {
                                    val graphName = packet.readString()
                                    DistributedTripleStore.localStore.createGraph(query, graphName)
                                }
                                ServerCommunicationHeader.DROP_GRAPH -> {
                                    val graphName = packet.readString()
                                    DistributedTripleStore.localStore.dropGraph(query, graphName)
                                }
                                ServerCommunicationHeader.GET_TRIPLES -> {
                                    val idx = EIndexPattern.values()[packet.readInt()]
                                    val graphName = packet.readString()
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
                                    val params = ServerCommunicationTransferParams.receiveParams(packet, query)
                                    var bundle = graph.getIterator(query, params, idx)
                                    if (bundle.hasCountMode()) {
                                        var builder = ByteArrayBuilder()
                                        builder.writeInt(ServerCommunicationHeader.RESPONSE_TRIPLES_COUNT.ordinal)
                                        builder.writeInt(bundle.count)
                                        output.writeByteArray(builder)
                                    } else {
                                        ServerCommunicationTransferTriples.sendTriples(bundle, query.dictionary, params) {
                                            output.writeByteArray(it)
                                            output.flush()
                                        }
                                    }
                                }
                                ServerCommunicationHeader.GET_HISTOGRAM -> {
                                    val idx = EIndexPattern.values()[packet.readInt()]
                                    val graphName = packet.readString()
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
                                    val params = ServerCommunicationTransferParams.receiveParams(packet, query)
                                    val histogram = graph.getHistogram(query, params, idx)
                                    var builder = ByteArrayBuilder()
                                    builder.writeInt(ServerCommunicationHeader.RESPONSE_HISTOGRAM.ordinal)
                                    builder.writeInt(histogram.first)
                                    builder.writeInt(histogram.second)
                                    output.writeByteArray(builder)
                                }
                            }
                            var builder = ByteArrayBuilder()
                            builder.writeInt(ServerCommunicationHeader.RESPONSE_FINISHED.ordinal)
                            output.writeByteArray(builder)
                            output.flush()
                            if (!ServerCommunicationConnectionPool.keepAliveServerConnection) {
                                break
                            }
                        }
                    } catch (e: Throwable) {
                        if (readHeader) {
//TODO propagate errors to the requesting node
                            println("TODO exception 2")
                            e.printStackTrace()
                        } else {
//connection closed before anything read ... assume there was an error on the other side, which tried to connect
                        }
                    } finally {
                        socket.close()
                    }
                }
            }
/*Coverage Unreachable*/
        }
    }
}
