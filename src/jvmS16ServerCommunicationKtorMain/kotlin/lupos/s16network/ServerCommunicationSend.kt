package lupos.s16network

import kotlin.jvm.JvmField
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

object ServerCommunicationSend {
    fun commit(query: Query) {
        for (host in ServerCommunicationDistribution.knownHosts) {
            runBlocking {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                val input = socket.openReadChannel()
                val output = socket.openWriteChannel()
                try {
                    var builder = BytePacketBuilder()
                    builder.writeInt(ServerCommunicationHeader.COMMIT.ordinal)
                    builder.writeLong(query.transactionID)
                    output.writePacket(builder.build())
                    output.flush()
                    builder.close()
                    val response = input.readPacket()
                    val header = ServerCommunicationHeader.values()[response.readInt()]
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
                        throw Exception("unexpected result $header")
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                } finally {
                    socket.close()
                }
            }
        }
    }

    fun graphClearAll(query: Query) {
        for (host in ServerCommunicationDistribution.knownHosts) {
            runBlocking {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                val input = socket.openReadChannel()
                val output = socket.openWriteChannel()
                try {
                    var builder = BytePacketBuilder()
                    builder.writeInt(ServerCommunicationHeader.CLEAR_ALL_GRAPH.ordinal)
                    builder.writeLong(query.transactionID)
                    output.writePacket(builder.build())
                    output.flush()
                    builder.close()
                    val response = input.readPacket()
                    val header = ServerCommunicationHeader.values()[response.readInt()]
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
                        throw Exception("unexpected result $header")
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                } finally {
                    socket.close()
                }
            }
        }
    }

    fun graphOperation(query: Query, graphName: String, type: EGraphOperationType) {
        for (host in ServerCommunicationDistribution.knownHosts) {
            runBlocking {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                val input = socket.openReadChannel()
                val output = socket.openWriteChannel()
                try {
                    var builder = BytePacketBuilder()
                    when (type) {
                        EGraphOperationType.CLEAR -> {
                            builder.writeInt(ServerCommunicationHeader.CLEAR_GRAPH.ordinal)
                        }
                        EGraphOperationType.CREATE -> {
                            builder.writeInt(ServerCommunicationHeader.CREATE_GRAPH.ordinal)
                        }
                        EGraphOperationType.DROP -> {
                            builder.writeInt(ServerCommunicationHeader.DROP_GRAPH.ordinal)
                        }
                    }
                    builder.writeLong(query.transactionID)
                    builder.writeUtf8(graphName)
                    output.writePacket(builder.build())
                    builder.close()
                    output.flush()
                    val response = input.readPacket()
                    val header = ServerCommunicationHeader.values()[response.readInt()]
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
                        throw Exception("unexpected result $header")
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                } finally {
                    socket.close()
                }
            }
        }
    }

    class ModifyHelper(val socket: Int, val input: Int, val output: Int, val iterators: Array<ColumnIterator>) {
    }

    suspend fun tripleModify(query: Query, graphName: String, data: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
        val values = Array(3) { ResultSetDictionary.undefValue }
        val accessedHosts = mutableMapOf<ServerCommunicationKnownHost, ModifyHelper>()
        loop@ while (true) {
            for (i in 0 until 3) {
                val v = data[i].next()
                if (v == null) {
                    require(i == 0)
                    break@loop
                } else {
                    values[i] = v
                }
            }
            val host = ServerCommunicationKnownHost.getHostForFullTriple(values, query, idx)
            var helper = accessedHosts[host]
            val helper2: ModifyHelper
            if (helper == null) {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                helper2 = ModifyHelper(socket, socket.openReadChannel(), socket.openWriteChannel(), Array(3) { ColumnIterator() })
                accessedHosts.add(host, helper2)
                var builder = BytePacketBuilder()
                if (type == EModifyType.INSERT) {
                    builder.writeInt(ServerCommunicationHeader.INSERT.ordinal)
                } else {
                    builder.writeInt(ServerCommunicationHeader.DELETE.ordinal)
                }
                builder.writeLong(query.transactionID)
                builder.writeUtf8(graphName)
                helper2.output.writePacket(builder.build())
                builder.close()
            launch {
                ServerCommunicationTransferTriples.sendTriples(helper2.iterators, query.dictionary) {
                    helper2.output.writePacket(it)
                    helper2.output.flush()
                }
            }
            } else {
                helper2 = helper
            }
TODO ("append values")
        }
TODO ("flush all and send termination signal")
TODO ("wait for ack")
    }

    fun tripleGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
        TODO("xxx")
    }

    fun histogramGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        TODO("xxx")
    }

    fun start(hostname: String="localhost", port: Int=NETWORK_DEFAULT_PORT, bootstrap: String? = null) {
        if (bootstrap != null) {
            val hosts = bootstrap.split("|")
            for (h in hosts) {
                val h2 = h.split(":")
                if (h2.size == 1) {
                    ServerCommunicationDistribution.registerKnownHost(h2[0], NETWORK_DEFAULT_PORT)
                } else {
                    require(h2.size == 2)
                    ServerCommunicationDistribution.registerKnownHost(h2[0], h2[1].toInt())
                }
            }
        }
        ServerCommunicationReceive.start(hostname, port, bootstrap)
    }
}
