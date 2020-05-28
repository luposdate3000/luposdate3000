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
import lupos.s03resultRepresentation.ValueDefinition
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
object ServerCommunicationTransferParams {
    fun receiveParams(packet: ByteArrayRead, query: Query): Array<AOPBase> {
Coverage.funStart(16970)
/*always assume SPO*/
Coverage.statementStart(16971)
        var paramsF = Array<Boolean>(3) { true }
Coverage.statementStart(16972)
        var paramsS = Array<String>(3) { "" }
Coverage.statementStart(16973)
        for (i in 0 until 3) {
Coverage.forLoopStart(16974)
            paramsF[i] = packet.readByte() != 0.toByte()
Coverage.statementStart(16975)
            paramsS[i] = packet.readString()
Coverage.statementStart(16976)
        }
Coverage.statementStart(16977)
        val params = Array<AOPBase>(3) {
Coverage.statementStart(16978)
            var res: AOPBase
Coverage.statementStart(16979)
            if (paramsF[it]) {
Coverage.ifStart(16980)
                res = AOPVariable(query, paramsS[it])
Coverage.statementStart(16981)
            } else {
Coverage.ifStart(16982)
                res = AOPConstant(query, ValueDefinition(paramsS[it]))
Coverage.statementStart(16983)
            }
Coverage.statementStart(16984)
            /*return*/res
        }
Coverage.statementStart(16985)
        return params
    }
    fun sendParams(builder: ByteArrayBuilder, params: Array<AOPBase>) {
Coverage.funStart(16986)
/*always assume SPO*/
Coverage.statementStart(16987)
        for (i in 0 until 3) {
Coverage.forLoopStart(16988)
            val p = params[i]
Coverage.statementStart(16989)
            if (p is AOPVariable) {
Coverage.ifStart(16990)
                builder.writeByte(0x1)
Coverage.statementStart(16991)
                builder.writeString(p.name)
Coverage.statementStart(16992)
            } else {
Coverage.ifStart(16993)
                val q = p as AOPConstant
Coverage.statementStart(16994)
                builder.writeByte(0x0)
Coverage.statementStart(16995)
                builder.writeString(q.toSparql())
Coverage.statementStart(16996)
            }
Coverage.statementStart(16997)
        }
Coverage.statementStart(16998)
    }
}
