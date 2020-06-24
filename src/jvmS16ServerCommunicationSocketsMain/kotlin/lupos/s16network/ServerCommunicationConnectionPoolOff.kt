package lupos.s16network
import kotlin.time.DurationUnit
import kotlin.time.TimeSource.Monotonic
import java.net.ServerSocket
import java.net.*
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.GlobalScope
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

object ServerCommunicationConnectionPoolOff {
    val keepAliveServerConnection = false
    suspend fun openServerSocket(hostname: String, port: Int): ServerSocket {
val server = ServerSocket()
                server.bind(InetSocketAddress(hostname, port))
return server
    }

  suspend fun accept(server: ServerSocket, action: suspend (ServerCommunicationConnectionPoolHelper) -> Unit) {
        val socket = server.accept()
Thread {
        try {
runBlocking{
                action(ServerCommunicationConnectionPoolHelper(socket, BufferedInputStream(socket.getInputStream()), BufferedOutputStream(socket.getOutputStream())))
}
        } finally {
            socket.close()
        }
            }.start()
    }

    suspend fun openSocket(host: ServerCommunicationKnownHost): ServerCommunicationConnectionPoolHelper {
val socket = Socket()
                socket.connect(InetSocketAddress(hostname, port))
                        return ServerCommunicationConnectionPoolHelper(socket, BufferedInputStream(socket.getInputStream()), BufferedOutputStream(socket.getOutputStream()))
    }

    fun closeSocketClean(host: ServerCommunicationKnownHost, conn: ServerCommunicationConnectionPoolHelper) {
        if (!conn.hadException) {
            conn.socket.close()
        }
    }

    fun closeSocketException(host: ServerCommunicationKnownHost, conn: ServerCommunicationConnectionPoolHelper) {
        conn.hadException = true
        conn.socket.close()
    }

}
