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
object ServerCommunicationSend {
    fun commit(query: Query) {
Coverage.funStart(16706)
        for (host in ServerCommunicationDistribution.knownHosts) {
Coverage.forLoopStart(16707)
            runBlocking {
Coverage.statementStart(16708)
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
Coverage.statementStart(16709)
                val input = socket.openReadChannel()
Coverage.statementStart(16710)
                val output = socket.openWriteChannel()
Coverage.statementStart(16711)
                try {
Coverage.statementStart(16712)
                    var builder = ByteArrayBuilder()
Coverage.statementStart(16713)
                    builder.writeInt(ServerCommunicationHeader.COMMIT.ordinal)
Coverage.statementStart(16714)
                    builder.writeLong(query.transactionID)
Coverage.statementStart(16715)
                    output.writeByteArray(builder)
Coverage.statementStart(16716)
                    output.flush()
Coverage.statementStart(16717)
                    val response = input.readByteArray()
Coverage.statementStart(16718)
                    val header = ServerCommunicationHeader.values()[response.readInt()]
Coverage.statementStart(16719)
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
Coverage.ifStart(16720)
                        throw Exception("unexpected result $header")
                    }
Coverage.statementStart(16721)
                } catch (e: Throwable) {
Coverage.statementStart(16722)
                    e.printStackTrace()
Coverage.statementStart(16723)
                } finally {
Coverage.statementStart(16724)
                    socket.close()
Coverage.statementStart(16725)
                }
Coverage.statementStart(16726)
            }
Coverage.statementStart(16727)
        }
Coverage.statementStart(16728)
    }
    fun graphClearAll(query: Query) {
Coverage.funStart(16729)
        for (host in ServerCommunicationDistribution.knownHosts) {
Coverage.forLoopStart(16730)
            runBlocking {
Coverage.statementStart(16731)
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
Coverage.statementStart(16732)
                val input = socket.openReadChannel()
Coverage.statementStart(16733)
                val output = socket.openWriteChannel()
Coverage.statementStart(16734)
                try {
Coverage.statementStart(16735)
                    var builder = ByteArrayBuilder()
Coverage.statementStart(16736)
                    builder.writeInt(ServerCommunicationHeader.CLEAR_ALL_GRAPH.ordinal)
Coverage.statementStart(16737)
                    builder.writeLong(query.transactionID)
Coverage.statementStart(16738)
                    output.writeByteArray(builder)
Coverage.statementStart(16739)
                    output.flush()
Coverage.statementStart(16740)
                    val response = input.readByteArray()
Coverage.statementStart(16741)
                    val header = ServerCommunicationHeader.values()[response.readInt()]
Coverage.statementStart(16742)
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
Coverage.ifStart(16743)
                        throw Exception("unexpected result $header")
                    }
Coverage.statementStart(16744)
                } catch (e: Throwable) {
Coverage.statementStart(16745)
                    e.printStackTrace()
Coverage.statementStart(16746)
                } finally {
Coverage.statementStart(16747)
                    socket.close()
Coverage.statementStart(16748)
                }
Coverage.statementStart(16749)
            }
Coverage.statementStart(16750)
        }
Coverage.statementStart(16751)
    }
    fun graphOperation(query: Query, graphName: String, type: EGraphOperationType) {
Coverage.funStart(16752)
        for (host in ServerCommunicationDistribution.knownHosts) {
Coverage.forLoopStart(16753)
            runBlocking {
Coverage.statementStart(16754)
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
Coverage.statementStart(16755)
                val input = socket.openReadChannel()
Coverage.statementStart(16756)
                val output = socket.openWriteChannel()
Coverage.statementStart(16757)
                try {
Coverage.statementStart(16758)
                    var builder = ByteArrayBuilder()
Coverage.statementStart(16759)
                    when (type) {
                        EGraphOperationType.CLEAR -> {
Coverage.whenCaseStart(16761)
                            builder.writeInt(ServerCommunicationHeader.CLEAR_GRAPH.ordinal)
Coverage.statementStart(16762)
                        }
                        EGraphOperationType.CREATE -> {
Coverage.whenCaseStart(16763)
                            builder.writeInt(ServerCommunicationHeader.CREATE_GRAPH.ordinal)
Coverage.statementStart(16764)
                        }
                        EGraphOperationType.DROP -> {
Coverage.whenCaseStart(16765)
                            builder.writeInt(ServerCommunicationHeader.DROP_GRAPH.ordinal)
Coverage.statementStart(16766)
                        }
                    }
Coverage.statementStart(16767)
                    builder.writeLong(query.transactionID)
Coverage.statementStart(16768)
                    builder.writeString(graphName)
Coverage.statementStart(16769)
                    output.writeByteArray(builder)
Coverage.statementStart(16770)
                    output.flush()
Coverage.statementStart(16771)
                    val response = input.readByteArray()
Coverage.statementStart(16772)
                    val header = ServerCommunicationHeader.values()[response.readInt()]
Coverage.statementStart(16773)
                    if (header != ServerCommunicationHeader.RESPONSE_FINISHED) {
Coverage.ifStart(16774)
                        throw Exception("unexpected result $header")
                    }
Coverage.statementStart(16775)
                } catch (e: Throwable) {
Coverage.statementStart(16776)
                    e.printStackTrace()
Coverage.statementStart(16777)
                } finally {
Coverage.statementStart(16778)
                    socket.close()
Coverage.statementStart(16779)
                }
Coverage.statementStart(16780)
            }
Coverage.statementStart(16781)
        }
Coverage.statementStart(16782)
    }
    class ModifyHelper(val socket: Socket, val input: ByteReadChannel, val output: ByteWriteChannel, val iterators: Array<ColumnIterator>) {
        var job: Job? = null
    }
    suspend fun tripleModify(query: Query, graphName: String, data: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
Coverage.funStart(16783)
        val values = Array(3) { ResultSetDictionary.undefValue }
Coverage.statementStart(16784)
        val accessedHosts = mutableMapOf<ServerCommunicationKnownHost, ModifyHelper>()
Coverage.statementStart(16785)
        loop@ while (true) {
Coverage.whileLoopStart(16786)
            for (i in 0 until 3) {
Coverage.forLoopStart(16787)
                val v = data[i].next()
Coverage.statementStart(16788)
                if (v == null) {
Coverage.ifStart(16789)
                    require(i == 0)
Coverage.statementStart(16790)
                    break@loop
                } else {
Coverage.statementStart(16791)
                    values[i] = v
Coverage.statementStart(16792)
                }
Coverage.statementStart(16793)
            }
Coverage.statementStart(16794)
            val host = ServerCommunicationDistribution.getHostForFullTriple(values, query, idx)
Coverage.statementStart(16795)
            var helper = accessedHosts[host]
Coverage.statementStart(16796)
            val helper2: ModifyHelper
Coverage.statementStart(16797)
            if (helper == null) {
Coverage.ifStart(16798)
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
Coverage.statementStart(16799)
                helper2 = ModifyHelper(socket, socket.openReadChannel(), socket.openWriteChannel(), Array<ColumnIterator>(3) { ColumnIteratorChannel() })
Coverage.statementStart(16800)
                accessedHosts[host] = helper2
Coverage.statementStart(16801)
                var builder = ByteArrayBuilder()
Coverage.statementStart(16802)
                if (type == EModifyType.INSERT) {
Coverage.ifStart(16803)
                    builder.writeInt(ServerCommunicationHeader.INSERT.ordinal)
Coverage.statementStart(16804)
                } else {
Coverage.ifStart(16805)
                    builder.writeInt(ServerCommunicationHeader.DELETE.ordinal)
Coverage.statementStart(16806)
                }
Coverage.statementStart(16807)
                builder.writeLong(query.transactionID)
Coverage.statementStart(16808)
                builder.writeString(graphName)
Coverage.statementStart(16809)
                helper2.output.writeByteArray(builder)
Coverage.statementStart(16810)
                runBlocking {
Coverage.statementStart(16811)
                    helper2.job = launch {
Coverage.statementStart(16812)
                        ServerCommunicationTransferTriples.sendTriples(helper2.iterators, query.dictionary) {
Coverage.statementStart(16813)
                            helper2.output.writeByteArray(it)
Coverage.statementStart(16814)
                            helper2.output.flush()
Coverage.statementStart(16815)
                        }
Coverage.statementStart(16816)
                    }
Coverage.statementStart(16817)
                }
Coverage.statementStart(16818)
            } else {
Coverage.ifStart(16819)
                helper2 = helper
Coverage.statementStart(16820)
            }
Coverage.statementStart(16821)
            for (i in 0 until 3) {
Coverage.forLoopStart(16822)
                (helper2.iterators[i] as ColumnIteratorChannel).append(values[i])
Coverage.statementStart(16823)
            }
Coverage.statementStart(16824)
        }
Coverage.statementStart(16825)
        for ((host, helper) in accessedHosts) {
Coverage.forLoopStart(16826)
            for (i in 0 until 3) {
Coverage.forLoopStart(16827)
                (helper.iterators[i] as ColumnIteratorChannel).writeFinish()
Coverage.statementStart(16828)
            }
Coverage.statementStart(16829)
            SanityCheck.check { helper.job != null }
Coverage.statementStart(16830)
            helper.job!!.join()
Coverage.statementStart(16831)
            var builder = ByteArrayBuilder()
Coverage.statementStart(16832)
            builder.writeInt(ServerCommunicationHeader.RESPONSE_FINISHED.ordinal)
Coverage.statementStart(16833)
            builder.writeLong(query.transactionID)
Coverage.statementStart(16834)
            helper.output.writeByteArray(builder)
Coverage.statementStart(16835)
            val response = helper.input.readByteArray()
Coverage.statementStart(16836)
            val header3 = ServerCommunicationHeader.values()[response.readInt()]
Coverage.statementStart(16837)
            if (header3 != ServerCommunicationHeader.RESPONSE_FINISHED) {
Coverage.ifStart(16838)
                throw Exception("unexpected result $header3")
            }
Coverage.statementStart(16839)
            helper.socket.close()
Coverage.statementStart(16840)
        }
Coverage.statementStart(16841)
    }
    fun tripleGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
Coverage.funStart(16842)
        val hosts = ServerCommunicationDistribution.getHostForPartialTriple(params, idx)
Coverage.statementStart(16843)
        var columnsTmp = mutableListOf<String>()
Coverage.statementStart(16844)
        for (p in params) {
Coverage.forLoopStart(16845)
            if (p is AOPVariable && p.name != "_") {
Coverage.ifStart(16846)
                columnsTmp.add(p.name)
Coverage.statementStart(16847)
            }
Coverage.statementStart(16848)
        }
Coverage.statementStart(16849)
        var builder = ByteArrayBuilder()
Coverage.statementStart(16850)
        builder.writeInt(ServerCommunicationHeader.GET_TRIPLES.ordinal)
Coverage.statementStart(16851)
        builder.writeLong(query.transactionID)
Coverage.statementStart(16852)
        builder.writeInt(idx.ordinal)
Coverage.statementStart(16853)
        builder.writeString(graphName)
Coverage.statementStart(16854)
        ServerCommunicationTransferParams.sendParams(builder, params)
Coverage.statementStart(16855)
        val columns = columnsTmp.toTypedArray()
Coverage.statementStart(16856)
        if (columns.size == 0) {
Coverage.ifStart(16857)
            var count = 0
Coverage.statementStart(16858)
            for (host in hosts) {
Coverage.forLoopStart(16859)
                runBlocking {
Coverage.statementStart(16860)
                    val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
Coverage.statementStart(16861)
                    val input = socket.openReadChannel()
Coverage.statementStart(16862)
                    val output = socket.openWriteChannel()
Coverage.statementStart(16863)
                    try {
Coverage.statementStart(16864)
                        output.writeByteArray(builder)
Coverage.statementStart(16865)
                        val packet2 = input.readByteArray()
Coverage.statementStart(16866)
                        val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
Coverage.statementStart(16867)
                        require(header2 == ServerCommunicationHeader.RESPONSE_TRIPLES_COUNT)
Coverage.statementStart(16868)
                        count += packet2.readInt()
Coverage.statementStart(16869)
                        val packet3 = input.readByteArray()
Coverage.statementStart(16870)
                        val header3 = ServerCommunicationHeader.values()[packet3.readInt()]
Coverage.statementStart(16871)
                        require(header3 == ServerCommunicationHeader.RESPONSE_FINISHED)
Coverage.statementStart(16872)
                    } catch (e: Throwable) {
Coverage.statementStart(16873)
                        e.printStackTrace()
Coverage.statementStart(16874)
                    } finally {
Coverage.statementStart(16875)
                        socket.close()
Coverage.statementStart(16876)
                    }
Coverage.statementStart(16877)
                }
Coverage.statementStart(16878)
            }
Coverage.statementStart(16879)
            return IteratorBundle(count)
        } else {
Coverage.statementStart(16880)
            var iterators = mutableListOf<RowIterator>()
Coverage.statementStart(16881)
            for (host in hosts) {
Coverage.forLoopStart(16882)
                var iterator = RowIteratorChildIterator(columns)
Coverage.statementStart(16883)
                iterators.add(iterator)
Coverage.statementStart(16884)
                runBlocking {
Coverage.statementStart(16885)
                    val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
Coverage.statementStart(16886)
                    val input = socket.openReadChannel()
Coverage.statementStart(16887)
                    val output = socket.openWriteChannel()
Coverage.statementStart(16888)
                    try {
Coverage.statementStart(16889)
                        output.writeByteArray(builder)
Coverage.statementStart(16890)
                        iterator.onNoMoreElements = {
Coverage.statementStart(16891)
                            val packet2 = input.readByteArray()
Coverage.statementStart(16892)
                            val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
Coverage.statementStart(16893)
                            if (header2 == ServerCommunicationHeader.RESPONSE_TRIPLES) {
Coverage.ifStart(16894)
                                val data = ServerCommunicationTransferTriples.receiveTriples(packet2, nodeGlobalDictionary, columns.size, true, socket.localAddress.toString())[0]
Coverage.statementStart(16895)
                                iterator.childs.add(RowIteratorBuf(data, columns))
Coverage.statementStart(16896)
                            } else {
Coverage.ifStart(16897)
                                require(header2 == ServerCommunicationHeader.RESPONSE_FINISHED)
Coverage.statementStart(16898)
                            }
Coverage.statementStart(16899)
                        }
Coverage.statementStart(16900)
                        var tmp = iterator.close
Coverage.statementStart(16901)
                        iterator.close = {
Coverage.statementStart(16902)
                            tmp()
Coverage.statementStart(16903)
                            socket.close()
Coverage.statementStart(16904)
                        }
Coverage.statementStart(16905)
                    } catch (e: Throwable) {
Coverage.statementStart(16906)
                        e.printStackTrace()
Coverage.statementStart(16907)
                    }
Coverage.statementStart(16908)
                }
Coverage.statementStart(16909)
            }
Coverage.statementStart(16910)
            while (iterators.size > 1) {
Coverage.whileLoopStart(16911)
                var tmp = mutableListOf<RowIterator>()
Coverage.statementStart(16912)
                while (iterators.size > 1) {
Coverage.whileLoopStart(16913)
                    var a = iterators.removeAt(0)
Coverage.statementStart(16914)
                    var b = iterators.removeAt(0)
Coverage.statementStart(16915)
                    tmp.add(RowIteratorMerge(a, b, ValueComparatorFast(), 0))
Coverage.statementStart(16916)
                }
Coverage.statementStart(16917)
                if (iterators.size == 1) {
Coverage.ifStart(16918)
                    tmp.add(iterators[0])
Coverage.statementStart(16919)
                }
Coverage.statementStart(16920)
                iterators = tmp
Coverage.statementStart(16921)
            }
Coverage.statementStart(16922)
            return IteratorBundle(iterators[0])
        }
Coverage.statementStart(16923)
    }
    fun histogramGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
Coverage.funStart(16924)
        val hosts = ServerCommunicationDistribution.getHostForPartialTriple(params, idx)
Coverage.statementStart(16925)
        var builder = ByteArrayBuilder()
Coverage.statementStart(16926)
        builder.writeInt(ServerCommunicationHeader.GET_HISTOGRAM.ordinal)
Coverage.statementStart(16927)
        builder.writeLong(query.transactionID)
Coverage.statementStart(16928)
        builder.writeInt(idx.ordinal)
Coverage.statementStart(16929)
        builder.writeString(graphName)
Coverage.statementStart(16930)
        ServerCommunicationTransferParams.sendParams(builder, params)
Coverage.statementStart(16931)
        var resFirst = 0
Coverage.statementStart(16932)
        var resSecond = 0
Coverage.statementStart(16933)
        for (host in hosts) {
Coverage.forLoopStart(16934)
            runBlocking {
Coverage.statementStart(16935)
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
Coverage.statementStart(16936)
                val input = socket.openReadChannel()
Coverage.statementStart(16937)
                val output = socket.openWriteChannel()
Coverage.statementStart(16938)
                try {
Coverage.statementStart(16939)
                    output.writeByteArray(builder)
Coverage.statementStart(16940)
                    val packet2 = input.readByteArray()
Coverage.statementStart(16941)
                    val header2 = ServerCommunicationHeader.values()[packet2.readInt()]
Coverage.statementStart(16942)
                    require(header2 == ServerCommunicationHeader.RESPONSE_HISTOGRAM)
Coverage.statementStart(16943)
                    resFirst += packet2.readInt()
Coverage.statementStart(16944)
                    resSecond += packet2.readInt()
Coverage.statementStart(16945)
                    val packet3 = input.readByteArray()
Coverage.statementStart(16946)
                    val header3 = ServerCommunicationHeader.values()[packet3.readInt()]
Coverage.statementStart(16947)
                    require(header3 == ServerCommunicationHeader.RESPONSE_FINISHED)
Coverage.statementStart(16948)
                } catch (e: Throwable) {
Coverage.statementStart(16949)
                    e.printStackTrace()
Coverage.statementStart(16950)
                } finally {
Coverage.statementStart(16951)
                    socket.close()
Coverage.statementStart(16952)
                }
Coverage.statementStart(16953)
            }
Coverage.statementStart(16954)
        }
Coverage.statementStart(16955)
        return Pair(resFirst, resSecond)
    }
    fun start(hostname: String = "localhost", port: Int = NETWORK_DEFAULT_PORT, bootstrap: String? = null) {
Coverage.funStart(16956)
        if (bootstrap != null) {
Coverage.ifStart(16957)
            val hosts = bootstrap.split("|")
Coverage.statementStart(16958)
            for (h in hosts) {
Coverage.forLoopStart(16959)
                val h2 = h.split(":")
Coverage.statementStart(16960)
                if (h2.size == 1) {
Coverage.ifStart(16961)
                    ServerCommunicationDistribution.registerKnownHost(h2[0], NETWORK_DEFAULT_PORT)
Coverage.statementStart(16962)
                } else {
Coverage.ifStart(16963)
                    require(h2.size == 2)
Coverage.statementStart(16964)
                    ServerCommunicationDistribution.registerKnownHost(h2[0], h2[1].toInt())
Coverage.statementStart(16965)
                }
Coverage.statementStart(16966)
            }
Coverage.statementStart(16967)
        }
Coverage.statementStart(16968)
        ServerCommunicationReceive.start(hostname, port, bootstrap)
Coverage.statementStart(16969)
    }
}
