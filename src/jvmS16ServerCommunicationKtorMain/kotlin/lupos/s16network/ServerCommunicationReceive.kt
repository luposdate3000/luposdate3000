package lupos.s16network
import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import java.net.InetSocketAddress
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
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
/*
 * list of all functions :: 
 * https://ktor.io/kotlinx/io/io/packets.html
 * https://ktor.io/kotlinx/io/io/channels.html
 * https://kotlinlang.org/docs/reference/coroutines/flow.html#flows
 */
object ServerCommunicationReceive {
    fun start(hostname: String, port: Int, bootstrap: String? = null) {
Coverage.funStart(16607)
        runBlocking {
Coverage.statementStart(16608)
            launch {
Coverage.statementStart(16609)
                val server = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().bind(InetSocketAddress(hostname, port))
Coverage.statementStart(16610)
                while (true) {
Coverage.whileLoopStart(16611)
                    val socket = server!!.accept()
Coverage.statementStart(16612)
                    launch {
Coverage.statementStart(16613)
                        val input = socket.openReadChannel()
Coverage.statementStart(16614)
                        val output = socket.openWriteChannel()
Coverage.statementStart(16615)
                        try {
Coverage.statementStart(16616)
                            val packet = input.readByteArray()
Coverage.statementStart(16617)
                            val header = ServerCommunicationHeader.values()[packet.readInt()]
Coverage.statementStart(16618)
                            val transactionID = packet.readLong()
Coverage.statementStart(16619)
                            val query = Query(transactionID = transactionID)
Coverage.statementStart(16620)
                            when (header) {
                                ServerCommunicationHeader.COMMIT -> {
Coverage.whenCaseStart(16622)
                                    DistributedTripleStore.localStore.commit(query)
Coverage.statementStart(16623)
                                }
                                ServerCommunicationHeader.INSERT -> {
Coverage.whenCaseStart(16624)
                                    val idx = EIndexPattern.values()[packet.readInt()]
Coverage.statementStart(16625)
                                    val graphName = packet.readString()
Coverage.statementStart(16626)
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
Coverage.statementStart(16627)
                                    while (true) {
Coverage.whileLoopStart(16628)
                                        val packet2 = input.readByteArray()
Coverage.statementStart(16629)
                                        val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
Coverage.statementStart(16630)
                                        if (header2 != ServerCommunicationHeader.RESPONSE_TRIPLES) {
Coverage.ifStart(16631)
                                            require(header2 == ServerCommunicationHeader.RESPONSE_FINISHED)
Coverage.statementStart(16632)
                                            break
                                        }
Coverage.statementStart(16633)
                                        val data = ServerCommunicationTransferTriples.receiveTriples(packet2, nodeGlobalDictionary, 3, true, socket.localAddress.toString())[0]
Coverage.statementStart(16634)
                                        graph.modify(query, data, idx, EModifyType.INSERT)
Coverage.statementStart(16635)
                                    }
Coverage.statementStart(16636)
                                }
                                ServerCommunicationHeader.DELETE -> {
Coverage.whenCaseStart(16637)
                                    val idx = EIndexPattern.values()[packet.readInt()]
Coverage.statementStart(16638)
                                    val graphName = packet.readString()
Coverage.statementStart(16639)
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
Coverage.statementStart(16640)
                                    while (true) {
Coverage.whileLoopStart(16641)
                                        val packet2 = input.readByteArray()
Coverage.statementStart(16642)
                                        val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
Coverage.statementStart(16643)
                                        if (header2 != ServerCommunicationHeader.RESPONSE_TRIPLES) {
Coverage.ifStart(16644)
                                            require(header2 == ServerCommunicationHeader.RESPONSE_FINISHED)
Coverage.statementStart(16645)
                                            break
                                        }
Coverage.statementStart(16646)
                                        val data = ServerCommunicationTransferTriples.receiveTriples(packet2, nodeGlobalDictionary, 3, true, socket.localAddress.toString())[0]
Coverage.statementStart(16647)
                                        graph.modify(query, data, idx, EModifyType.DELETE)
Coverage.statementStart(16648)
                                    }
Coverage.statementStart(16649)
                                }
                                ServerCommunicationHeader.CLEAR_ALL_GRAPH -> {
Coverage.whenCaseStart(16650)
                                    DistributedTripleStore.localStore.getDefaultGraph(query).clear()
Coverage.statementStart(16651)
                                    for (g in DistributedTripleStore.getGraphNames()) {
Coverage.forLoopStart(16652)
                                        DistributedTripleStore.dropGraph(query, g)
Coverage.statementStart(16653)
                                    }
Coverage.statementStart(16654)
                                }
                                ServerCommunicationHeader.CLEAR_GRAPH -> {
Coverage.whenCaseStart(16655)
                                    val graphName = packet.readString()
Coverage.statementStart(16656)
                                    DistributedTripleStore.localStore.clearGraph(query, graphName)
Coverage.statementStart(16657)
                                }
                                ServerCommunicationHeader.CREATE_GRAPH -> {
Coverage.whenCaseStart(16658)
                                    val graphName = packet.readString()
Coverage.statementStart(16659)
                                    DistributedTripleStore.localStore.createGraph(query, graphName)
Coverage.statementStart(16660)
                                }
                                ServerCommunicationHeader.DROP_GRAPH -> {
Coverage.whenCaseStart(16661)
                                    val graphName = packet.readString()
Coverage.statementStart(16662)
                                    DistributedTripleStore.localStore.dropGraph(query, graphName)
Coverage.statementStart(16663)
                                }
                                ServerCommunicationHeader.GET_TRIPLES -> {
Coverage.whenCaseStart(16664)
                                    val idx = EIndexPattern.values()[packet.readInt()]
Coverage.statementStart(16665)
                                    val graphName = packet.readString()
Coverage.statementStart(16666)
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
Coverage.statementStart(16667)
                                    val params = ServerCommunicationTransferParams.receiveParams(packet, query)
Coverage.statementStart(16668)
                                    var bundle = graph.getIterator(query, params, idx)
Coverage.statementStart(16669)
                                    if (bundle.hasCountMode()) {
Coverage.ifStart(16670)
                                        var builder = ByteArrayBuilder()
Coverage.statementStart(16671)
                                        builder.writeInt(ServerCommunicationHeader.RESPONSE_TRIPLES_COUNT.ordinal)
Coverage.statementStart(16672)
                                        builder.writeInt(bundle.count)
Coverage.statementStart(16673)
                                        output.writeByteArray(builder)
Coverage.statementStart(16674)
                                    } else {
Coverage.ifStart(16675)
                                                ServerCommunicationTransferTriples.sendTriples(bundle, query.dictionary, params) {
Coverage.statementStart(16676)
                                            output.writeByteArray(it)
Coverage.statementStart(16677)
                                            output.flush()
Coverage.statementStart(16678)
}
Coverage.statementStart(16679)
                                    }
Coverage.statementStart(16680)
                                }
                                ServerCommunicationHeader.GET_HISTOGRAM -> {
Coverage.whenCaseStart(16681)
                                    val idx = EIndexPattern.values()[packet.readInt()]
Coverage.statementStart(16682)
                                    val graphName = packet.readString()
Coverage.statementStart(16683)
                                    val graph = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
Coverage.statementStart(16684)
                                    val params = ServerCommunicationTransferParams.receiveParams(packet, query)
Coverage.statementStart(16685)
                                    val histogram = graph.getHistogram(query, params, idx)
Coverage.statementStart(16686)
                                    var builder = ByteArrayBuilder()
Coverage.statementStart(16687)
                                    builder.writeInt(ServerCommunicationHeader.RESPONSE_HISTOGRAM.ordinal)
Coverage.statementStart(16688)
                                    builder.writeInt(histogram.first)
Coverage.statementStart(16689)
                                    builder.writeInt(histogram.second)
Coverage.statementStart(16690)
                                    output.writeByteArray(builder)
Coverage.statementStart(16691)
                                }
                            }
Coverage.statementStart(16692)
                            var builder = ByteArrayBuilder()
Coverage.statementStart(16693)
                            builder.writeInt(ServerCommunicationHeader.RESPONSE_FINISHED.ordinal)
Coverage.statementStart(16694)
                            output.writeByteArray(builder)
Coverage.statementStart(16695)
                            output.flush()
Coverage.statementStart(16696)
                        } catch (e: Throwable) {
Coverage.statementStart(16697)
                            e.printStackTrace()
Coverage.statementStart(16698)
                        } finally {
Coverage.statementStart(16699)
                            socket.close()
Coverage.statementStart(16700)
                        }
Coverage.statementStart(16701)
                    }
Coverage.statementStart(16702)
                }
Coverage.statementStart(16703)
            }
Coverage.statementStart(16704)
        }
Coverage.statementStart(16705)
    }
}
