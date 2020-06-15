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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s00misc.CommuncationUnexpectedHeaderException
import lupos.s00misc.CoroutinesHelper
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
import lupos.s04logicalOperators.iterator.RowIteratorDebug
import lupos.s04logicalOperators.iterator.RowIteratorMerge
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.*

object ServerCommunicationSend {
    var myHostname = "localhost"
    var myPort = NETWORK_DEFAULT_PORT
    suspend fun bulkImport(query: Query, graphName: String, action: suspend (TripleStoreBulkImportDistributed) -> Unit) {
        val bulk = TripleStoreBulkImportDistributed(query, graphName)
        action(bulk)
        CoroutinesHelper.runBlock {
            bulk.finishImport()
        }
    }

    fun commit(query: Query) {
        for (host in ServerCommunicationDistribution.knownHosts) {
            runBlocking {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                val input = socket.openReadChannel()
                val output = socket.openWriteChannel()
                try {
                    var builder = ByteArrayBuilder()
                    builder.writeInt(ServerCommunicationHeader.COMMIT.ordinal)
                    builder.writeLong(query.transactionID)
                    output.writeByteArray(builder)
                    output.flush()
                    val response = input.readByteArray()
                    val header = ServerCommunicationHeader.values()[response.readInt()]
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
                        throw CommuncationUnexpectedHeaderException("$header")
                    }
                } catch (e: Throwable) {
                    println("TODO exception 3")
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
                    var builder = ByteArrayBuilder()
                    builder.writeInt(ServerCommunicationHeader.CLEAR_ALL_GRAPH.ordinal)
                    builder.writeLong(query.transactionID)
                    output.writeByteArray(builder)
                    output.flush()
                    val response = input.readByteArray()
                    val header = ServerCommunicationHeader.values()[response.readInt()]
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
                        throw CommuncationUnexpectedHeaderException("$header")
                    }
                } catch (e: Throwable) {
                    println("TODO exception 4")
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
                    var builder = ByteArrayBuilder()
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
                    builder.writeString(graphName)
                    output.writeByteArray(builder)
                    output.flush()
                    val response = input.readByteArray()
                    val header = ServerCommunicationHeader.values()[response.readInt()]
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
                        throw CommuncationUnexpectedHeaderException("$header")
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                } finally {
                    socket.close()
                }
            }
        }
    }

    class ModifyHelper(val socket: Socket, val input: ByteReadChannel, val output: ByteWriteChannel, val iterators: Array<ColumnIterator>) {
        var job: Job? = null
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
            val host = ServerCommunicationDistribution.getHostForFullTriple(values, query, idx)
            var helper = accessedHosts[host]
            val helper2: ModifyHelper
            if (helper == null) {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                helper2 = ModifyHelper(socket, socket.openReadChannel(), socket.openWriteChannel(), Array<ColumnIterator>(3) { ColumnIteratorChannel() })
                accessedHosts[host] = helper2
                var builder = ByteArrayBuilder()
                if (type == EModifyType.INSERT) {
                    builder.writeInt(ServerCommunicationHeader.INSERT.ordinal)
                } else {
                    builder.writeInt(ServerCommunicationHeader.DELETE.ordinal)
                }
                builder.writeLong(query.transactionID)
                builder.writeInt(idx.ordinal)
                builder.writeString(graphName)
                helper2.output.writeByteArray(builder)
                helper2.output.flush()
                runBlocking {
                    helper2.job = launch {
                        ServerCommunicationTransferTriples.sendTriples(helper2.iterators, query.dictionary) {
                            helper2.output.writeByteArray(it)
                            helper2.output.flush()
                        }
                    }
                }
            } else {
                helper2 = helper
            }
            for (i in 0 until 3) {
                (helper2.iterators[i] as ColumnIteratorChannel).append(values[i])
            }
        }
        for ((host, helper) in accessedHosts) {
            for (i in 0 until 3) {
                (helper.iterators[i] as ColumnIteratorChannel).writeFinish()
            }
            SanityCheck.check { helper.job != null }
            helper.job!!.join()
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
            helper.socket.close()
        }
    }

    fun tripleGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
        val hosts = ServerCommunicationDistribution.getHostForPartialTriple(params, idx)
        var columnsTmp = mutableListOf<String>()
        for (p in params) {
            if (p is AOPVariable && p.name != "_") {
                columnsTmp.add(p.name)
            }
        }
        var builder = ByteArrayBuilder()
        builder.writeInt(ServerCommunicationHeader.GET_TRIPLES.ordinal)
        builder.writeLong(query.transactionID)
        builder.writeInt(idx.ordinal)
        builder.writeString(graphName)
        ServerCommunicationTransferParams.sendParams(builder, params)
        val columns = columnsTmp.toTypedArray()
        if (columns.size == 0) {
            var count = 0
            for (host in hosts) {
                runBlocking {
                    val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                    val input = socket.openReadChannel()
                    val output = socket.openWriteChannel()
                    try {
                        output.writeByteArray(builder)
                        output.flush()
                        val packet2 = input.readByteArray()
                        val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
                        require(header2 == ServerCommunicationHeader.RESPONSE_TRIPLES_COUNT)
                        count += packet2.readInt()
                        val packet3 = input.readByteArray()
                        val header3 = ServerCommunicationHeader.values()[packet3.readInt()]
                        require(header3 == ServerCommunicationHeader.RESPONSE_FINISHED)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    } finally {
                        socket.close()
                    }
                }
            }
            return IteratorBundle(count)
        } else {
            var iterators = mutableListOf<RowIterator>()
            for (host in hosts) {
                var iterator = RowIteratorChildIterator(columns)
                iterators.add(iterator)
                runBlocking {
                    val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                    val input = socket.openReadChannel()
                    val output = socket.openWriteChannel()
                    try {
                        output.writeByteArray(builder)
                        output.flush()
                        iterator.onNoMoreElements = {
                            val packet2 = input.readByteArray()
                            val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
                            if (header2 == ServerCommunicationHeader.RESPONSE_TRIPLES) {
                                val data = ServerCommunicationTransferTriples.receiveTriples(packet2, nodeGlobalDictionary, columns.size, true, socket.localAddress.toString())[0]
                                iterator.childs.add(RowIteratorDebug("a", RowIteratorBuf(data, columns)))
                            } else {
                                require(header2 == ServerCommunicationHeader.RESPONSE_FINISHED)
                            }
                        }
                        var tmp = iterator.close
                        iterator.close = {
                            tmp()
                            socket.close()
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }
            while (iterators.size > 1) {
                var tmp = mutableListOf<RowIterator>()
                while (iterators.size > 1) {
                    var a = iterators.removeAt(0)
                    var b = iterators.removeAt(0)
                    tmp.add(RowIteratorDebug("b", RowIteratorMerge(a, b, ValueComparatorFast(), 0)))
                }
                if (iterators.size == 1) {
                    tmp.add(iterators[0])
                }
                iterators = tmp
            }
            return IteratorBundle(RowIteratorDebug("c", iterators[0]))
        }
/*Coverage Unreachable*/
    }

    fun histogramGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        val hosts = ServerCommunicationDistribution.getHostForPartialTriple(params, idx)
        var builder = ByteArrayBuilder()
        builder.writeInt(ServerCommunicationHeader.GET_HISTOGRAM.ordinal)
        builder.writeLong(query.transactionID)
        builder.writeInt(idx.ordinal)
        builder.writeString(graphName)
        ServerCommunicationTransferParams.sendParams(builder, params)
        var resFirst = 0
        var resSecond = 0
        for (host in hosts) {
            runBlocking {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
                val input = socket.openReadChannel()
                val output = socket.openWriteChannel()
                try {
                    output.writeByteArray(builder)
                    output.flush()
                    val packet2 = input.readByteArray()
                    val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
                    require(header2 == ServerCommunicationHeader.RESPONSE_HISTOGRAM)
                    resFirst += packet2.readInt()
                    resSecond += packet2.readInt()
                    val packet3 = input.readByteArray()
                    val header3 = ServerCommunicationHeader.values()[packet3.readInt()]
                    require(header3 == ServerCommunicationHeader.RESPONSE_FINISHED)
                } catch (e: Throwable) {
                    e.printStackTrace()
                } finally {
                    socket.close()
                }
            }
        }
        return Pair(resFirst, resSecond)
    }

    fun start(hostname: String = myHostname, port: Int = myPort, bootstrap: String? = null) {
        myHostname = hostname
        myPort = port
        ServerCommunicationDistribution.registerKnownHost(hostname, port)
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
