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
object ServerCommunicationTransferTriples {
    fun receiveTriples(packet: ByteArrayRead, dict: ResultSetDictionary, expectedColumns: Int, outputAsSingle: Boolean, remoteName: String): Array<MutableList<Value>> {
Coverage.funStart(16999)
/*always assume SPO even _if some of the components are allowed to be missing*/
Coverage.statementStart(17000)
        val columns = packet.readInt()
Coverage.statementStart(17001)
        require(columns == expectedColumns)
Coverage.statementStart(17002)
        require(columns > 0)
Coverage.statementStart(17003)
        var res: Array<MutableList<Value>>
Coverage.statementStart(17004)
        if (outputAsSingle) {
Coverage.ifStart(17005)
            res = Array(1) { mutableListOf<Value>() }
Coverage.statementStart(17006)
        } else {
Coverage.ifStart(17007)
            res = Array(columns) { mutableListOf<Value>() }
Coverage.statementStart(17008)
        }
Coverage.statementStart(17009)
        val idsReceiveMap = mutableMapOf<Value, Value>()
Coverage.statementStart(17010)
        while (packet.remaining() > 0) {
Coverage.whileLoopStart(17011)
            for (i in 0 until columns) {
Coverage.forLoopStart(17012)
                val v = packet.readInt()
Coverage.statementStart(17013)
                val v2 = idsReceiveMap[v]
Coverage.statementStart(17014)
                val v3: Value
Coverage.statementStart(17015)
                if (v2 == null) {
Coverage.ifStart(17016)
                    if (!ResultSetDictionary.isGlobalBNode(v)) {
Coverage.ifStart(17017)
                        var s = packet.readString()
Coverage.statementStart(17018)
                        if (ResultSetDictionary.isLocalBNode(v)) {
Coverage.ifStart(17019)
                            s = "_:" + remoteName + s.substring(2, s.length)
Coverage.statementStart(17020)
                        }
Coverage.statementStart(17021)
                        v3 = dict.createValue(s)
Coverage.statementStart(17022)
                        idsReceiveMap[v] = v3
Coverage.statementStart(17023)
                    } else {
Coverage.ifStart(17024)
                        v3 = v
Coverage.statementStart(17025)
                    }
Coverage.statementStart(17026)
                } else {
Coverage.ifStart(17027)
                    v3 = v2
Coverage.statementStart(17028)
                }
Coverage.statementStart(17029)
                if (outputAsSingle) {
Coverage.ifStart(17030)
                    res[0].add(v3)
Coverage.statementStart(17031)
                } else {
Coverage.ifStart(17032)
                    res[i].add(v3)
Coverage.statementStart(17033)
                }
Coverage.statementStart(17034)
            }
Coverage.statementStart(17035)
        }
Coverage.statementStart(17036)
        return res
    }
    suspend fun sendTriples(bundle: IteratorBundle, dict: ResultSetDictionary, params: Array<AOPBase>, onFullPacket: suspend (ByteArrayBuilder) -> Unit) {
Coverage.funStart(17037)
/*always assume SPO*/
Coverage.statementStart(17038)
        var iterators = mutableListOf<ColumnIterator>()
Coverage.statementStart(17039)
        for (p in params) {
Coverage.forLoopStart(17040)
            if (p is AOPVariable && p.name != "_") {
Coverage.ifStart(17041)
                iterators.add(bundle.columns[p.name]!!)
Coverage.statementStart(17042)
            }
Coverage.statementStart(17043)
        }
Coverage.statementStart(17044)
        sendTriples(iterators.toTypedArray(), dict, onFullPacket)
Coverage.statementStart(17045)
    }
    suspend fun sendTriples(iterators: Array<ColumnIterator>, dict: ResultSetDictionary, onFullPacket: suspend (ByteArrayBuilder) -> Unit) {
Coverage.funStart(17046)
        /*always assume SPO*/
Coverage.statementStart(17047)
        var builder = ByteArrayBuilder()
Coverage.statementStart(17048)
        loop@ while (true) {
Coverage.whileLoopStart(17049)
            builder.writeInt(ServerCommunicationHeader.RESPONSE_TRIPLES.ordinal)
Coverage.statementStart(17050)
            builder.writeInt(iterators.size)
Coverage.statementStart(17051)
            var counter = 0
Coverage.statementStart(17052)
            val idsSentList = mutableSetOf<Value>()
Coverage.statementStart(17053)
            while (builder.size < NETWORK_PACKET_SIZE || counter < NETWORK_PACKET_MIN_TRIPLES) {
Coverage.whileLoopStart(17054)
                counter++
Coverage.statementStart(17055)
                for (i in 0 until iterators.size) {
Coverage.forLoopStart(17056)
                    val it = iterators[i]
Coverage.statementStart(17057)
                    val v = it.next()
Coverage.statementStart(17058)
                    if (v == null) {
Coverage.ifStart(17059)
                        require(i == 0)
Coverage.statementStart(17060)
                        break@loop
                    } else {
Coverage.statementStart(17061)
                        builder.writeInt(v)
Coverage.statementStart(17062)
                        if (!idsSentList.contains(v)) {
Coverage.ifStart(17063)
                            idsSentList.add(v)
Coverage.statementStart(17064)
                            if (!ResultSetDictionary.isGlobalBNode(v)) {
Coverage.ifStart(17065)
                                val vd = dict.getValue(v)
Coverage.statementStart(17066)
                                builder.writeString(vd.toSparql())
Coverage.statementStart(17067)
                            }
Coverage.statementStart(17068)
                        }
Coverage.statementStart(17069)
                    }
Coverage.statementStart(17070)
                }
Coverage.statementStart(17071)
            }
Coverage.statementStart(17072)
            onFullPacket(builder)
Coverage.statementStart(17073)
            builder.reset()
Coverage.statementStart(17074)
        }
Coverage.statementStart(17075)
        onFullPacket(builder)
Coverage.statementStart(17076)
    }
}
